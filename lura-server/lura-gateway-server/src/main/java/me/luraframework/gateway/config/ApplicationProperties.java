package me.luraframework.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private Auth auth = new Auth();
    private List<Whitelist> whitelist;


    @Data
    public static class Auth {
        private String checkUrl = "http://lura-framework-auth-server/auth/v1/userInfoByToken?accessToken={accessToken}";
    }

    @Data
    public static class Whitelist {
        private String url;
        private String method;
    }


}
