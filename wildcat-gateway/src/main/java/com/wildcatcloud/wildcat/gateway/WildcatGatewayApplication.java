/*
 *
 *      Copyright (c) 2019-2026, wlidcat All rights reserved.
 *
 *  Do not alter or remove copyright notices or this file header
 *
 *  This code is free software; you can redistribute it and/or modify it
 *
 *  Author: wlidcat (bishengqin@gmail.com)
 *
 */
package com.wildcatcloud.wildcat.gateway;

import com.wildcatcloud.wildcat.common.gateway.annotation.EnableWildcatDynamicRoute;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Author WlidcatQin
 * @Date 2019/12/10 20:40
 */
@EnableWildcatDynamicRoute
@SpringCloudApplication
public class WildCatGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(WildCatGatewayApplication.class, args);
    }
}
