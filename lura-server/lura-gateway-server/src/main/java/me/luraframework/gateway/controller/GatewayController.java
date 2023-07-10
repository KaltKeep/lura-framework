package me.luraframework.gateway.controller;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @ description: GatewayController
 * @ author: Liu Ran
 * @ data: 7/10/23 20:12
 */

@RestController
@RequestMapping("gateway")
@AllArgsConstructor
public class GatewayController {

    private final RouteLocator routeLocator;

    @GetMapping("routes")
    public Flux<RouteDto> routes() {
        return routeLocator.getRoutes()
                .map(route -> RouteDto.of(route.getId(), route.getUri().toString()));
    }

}
