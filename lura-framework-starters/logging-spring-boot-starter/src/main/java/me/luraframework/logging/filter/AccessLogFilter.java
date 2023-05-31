package me.luraframework.logging.filter;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import me.luraframework.logging.config.LogProperties;
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
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description: 请求日志拦截类，打印所有请求的信息
 * @author: Liu Ran
 * @data: 10/25/22 17:30
 */
@Slf4j
public class AccessLogFilter extends OncePerRequestFilter {

    private final String[] skippedUrls = new String[]{"/v3/api-docs/**", "/swagger-ui/*", "/**/v3/api-docs/**", "/**/swagger-ui/*"};
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private final LogProperties logProperties;

    private String logFormat;

    public AccessLogFilter(LogProperties logProperties) {
        this.logProperties = logProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper(response);
        Stopwatch stopwatch = Stopwatch.createStarted();
        filterChain.doFilter(req, res);
        stopwatch.stop();
        if (shouldLog(request)) {
            log.info(getLogFormat(), getLogArguments(req, res, stopwatch.elapsed(TimeUnit.MICROSECONDS)));
            res.copyBodyToResponse();
        }
    }


    private boolean shouldLog(HttpServletRequest request) {
        return Arrays.stream(skippedUrls).noneMatch(skippedUrl -> pathMatcher.match(skippedUrl, request.getRequestURI()));
    }

    private String getLogFormat() {
        if (logFormat != null) {
            return logFormat;
        }
        StringBuilder logStr = new StringBuilder();
        logProperties.getAccess().getRequest().forEach(field -> logStr.append(field.name().toLowerCase()).append(": {}, "));
        logProperties.getAccess().getResponse().forEach(field -> logStr.append(field.name().toLowerCase()).append(": {}, "));
        logStr.append("cost_time: {}");
        logFormat = logStr.toString();
        return logFormat;
    }

    private Object[] getLogArguments(ContentCachingRequestWrapper req, ContentCachingResponseWrapper resp, long costTime) {
        List<Object> arguments = new LinkedList<>();
        logProperties.getAccess().getRequest().forEach(field -> arguments.add(field.getValue(req)));
        logProperties.getAccess().getResponse().forEach(field -> arguments.add(field.getValue(resp)));
        arguments.add(costTime);
        return arguments.toArray();
    }


}
