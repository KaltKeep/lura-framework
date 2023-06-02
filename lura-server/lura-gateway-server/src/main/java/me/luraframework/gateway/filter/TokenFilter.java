package me.luraframework.gateway.filter;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.luraframework.commons.exception.AppException;
import me.luraframework.gateway.config.ApplicationProperties;
import me.luraframework.gateway.constant.HttpKey;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.google.common.collect.ImmutableMap.of;
import static me.luraframework.gateway.exception.GatewayErrorCode.INVALID_TOKEN;
import static me.luraframework.gateway.exception.GatewayErrorCode.UNAUTHORIZED;

@Slf4j
@RequiredArgsConstructor
public class TokenFilter implements GlobalFilter, Ordered {

    private final ApplicationProperties applicationProperties;
    private final WebClient.Builder builder;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final boolean debug = true;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (debug || isWhitelist(exchange)){
            return chain.filter(exchange);
        }

        return doAuthentication(exchange)
                .then(chain.filter(exchange));
    }

    private boolean isWhitelist(ServerWebExchange exchange) {
        String path = exchange.getRequest().getPath().value();
        String method = exchange.getRequest().getMethodValue();
        for (ApplicationProperties.Whitelist whitelist : applicationProperties.getWhitelist()) {
            if (method.equalsIgnoreCase(whitelist.getMethod()) && antPathMatcher.match(whitelist.getUrl(), path)) {
                return true;
            }
        }
        return false;
    }

    private Mono<String> doAuthentication(ServerWebExchange exchange) {

        String token = getToken(exchange);
        if (Strings.isBlank(token)) {
            return Mono.error(new AppException(UNAUTHORIZED, of()));
        }

        return builder.build().post()
                      .uri(applicationProperties.getAuth().getCheckUrl(), ImmutableMap.of("accessToken", token))
                      .contentType(MediaType.APPLICATION_JSON)
                      .retrieve()
                      .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new AppException(INVALID_TOKEN, of())))
                      .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new AppException(INVALID_TOKEN, of())))
                      .bodyToMono(String.class)
                      .doOnNext(log::info)
                      .doOnNext(userInfo -> exchange.getRequest().mutate().header("userInfo", userInfo));
    }

    private String getToken(ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
