package me.luraframework.logging.filter;

import com.cait.samps.core.auth.HeaderKey;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Function;

/**
 * @ description: AccessField
 * @ author: Liu Ran
 * @ data: 5/16/23 15:20
 */
public interface AccessField {


    @RequiredArgsConstructor
    public enum Request {
        REQUEST_PATH(HttpServletRequest::getRequestURI),
        REQUEST_METHOD(HttpServletRequest::getMethod),
        REQUEST_PARAMS(HttpServletRequest::getQueryString),
        USER_CONTEXT(req -> req.getHeader(HeaderKey.USER_HEADER_KEY)),
        REQUEST_DATA(req -> formatJsonOneRow(new String(req.getContentAsByteArray())));
        private final Function<ContentCachingRequestWrapper, String> func;

        public Object getValue(ContentCachingRequestWrapper req) {
            return func.apply(req);
        }


    }

    @RequiredArgsConstructor
    enum Response {
        RESPONSE_DATA(resp -> formatJsonOneRow(new String(resp.getContentAsByteArray()))),
        RESPONSE_STATUS(HttpServletResponse::getStatus);

        private final Function<ContentCachingResponseWrapper, Object> func;

        public Object getValue(ContentCachingResponseWrapper resp) {
            return func.apply(resp);
        }

    }

    static String formatJsonOneRow(String str) {
        return str.replaceAll("\r", "").replaceAll("\n", "");
    }
}
