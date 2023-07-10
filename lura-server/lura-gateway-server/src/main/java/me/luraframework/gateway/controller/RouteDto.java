package me.luraframework.gateway.controller;

import lombok.Value;

/**
 * @ description: RouteDto
 * @ author: Liu Ran
 * @ data: 7/10/23 20:25
 */

@Value(staticConstructor = "of")
public class RouteDto {
    private String id;
    private String url;
}
