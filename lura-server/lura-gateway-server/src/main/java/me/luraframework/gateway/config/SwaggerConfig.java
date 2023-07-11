package me.luraframework.gateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import org.springdoc.core.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ description: SwaggerConfig
 * @ author: Liu Ran
 * @ data: 6/2/23 16:41
 */
@Configuration
@AllArgsConstructor
public class SwaggerConfig {

    private final SwaggerUiConfigProperties configProperties;
    private final RouteDefinitionLocator locator;

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();

        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
                .forEach(routeDefinition -> {
                    String name = routeDefinition.getId().replaceAll("-service", "");
                    swaggerUiConfigParameters.addGroup(name);
                    swaggerUiConfigParameters.addUrl(routeDefinition.getUri().toString() + "/v2/api-docs");
                    groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
                });
        return groups;
    }

    private void refreshSwaggerConfig() {
        List<GroupedOpenApi> groups = new ArrayList<>();
        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();

        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
                .forEach(routeDefinition -> {
                    AbstractSwaggerUiConfigProperties.SwaggerUrl url = new AbstractSwaggerUiConfigProperties.SwaggerUrl();
                    String name = routeDefinition.getId().replaceAll("-service", "");
                    url.setName(name);
                    url.setUrl(routeDefinition.getUri().toString() + "/v2/api-docs");
                    urls.add(url);
                });
        configProperties.setUrls(urls);
    }


    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title("springdoc gateway API")
                .description("springdoc gateway API")
                .version("v1.0.0")
                .contact(new Contact()
                        .name("姓名")
                        .email("邮箱")));
    }


}
