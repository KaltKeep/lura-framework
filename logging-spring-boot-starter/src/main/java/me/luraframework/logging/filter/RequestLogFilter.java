package me.luraframework.logging.filter;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RequestLogFilter extends OncePerRequestFilter {

    private final String[] skippedUrls = {"/**/api-docs/**", "/swagger-ui/*", "/actuator/**", "/webjars/**", "/swagger-ui.html", "/swagger-resources/**"};
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper(response);
        Stopwatch stopwatch = Stopwatch.createStarted();
        filterChain.doFilter(req, res);
        stopwatch.stop();
        if (shouldLog(request)) {
            log.info("request path: {}, method: {}, params: {}, data: {}; response: {}; cost: {}ms", req.getRequestURI(), req.getMethod(),
                    req.getQueryString(),
                    formatJsonOneRow(new String(req.getContentAsByteArray(), req.getCharacterEncoding())),
                    formatJsonOneRow(new String(res.getContentAsByteArray(), res.getCharacterEncoding())),
                    stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
        res.copyBodyToResponse();
    }

    private String formatJsonOneRow(String str) {
        return str.replaceAll("\r\n\t", str);
    }

    private boolean shouldLog(HttpServletRequest request) {
        return Arrays.stream(skippedUrls).noneMatch(skippedUrl -> pathMatcher.match(skippedUrl, request.getRequestURI()));
    }
}
