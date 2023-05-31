package me.luraframework.logging.config;

import com.google.common.collect.Lists;
import lombok.Data;
import me.luraframework.logging.filter.AccessField;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import static me.luraframework.logging.filter.AccessField.Request.REQUEST_DATA;
import static me.luraframework.logging.filter.AccessField.Request.REQUEST_METHOD;
import static me.luraframework.logging.filter.AccessField.Request.REQUEST_PARAMS;
import static me.luraframework.logging.filter.AccessField.Request.REQUEST_PATH;
import static me.luraframework.logging.filter.AccessField.Request.USER_CONTEXT;
import static me.luraframework.logging.filter.AccessField.Response.RESPONSE_STATUS;


/**
 * @ description: LogProperties
 * @ author: Liu Ran
 * @ data: 5/16/23 10:03
 */

@Data
@ConfigurationProperties(prefix = "samps.logging")
public class LogProperties {

    private Access access = new Access();

    @Data
    public static class Access {
        private List<AccessField.Request> request = Lists.newArrayList(REQUEST_PATH, REQUEST_METHOD, REQUEST_PARAMS, REQUEST_DATA, USER_CONTEXT);
        private List<AccessField.Response> response = Lists.newArrayList(RESPONSE_STATUS);
    }

}
