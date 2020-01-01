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
package com.wildcatcloud.wildcat.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author WlidcatQin
 * @Date 2019/9/24
 */
@EnableConfigServer
@SpringCloudApplication
public class WildCatConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(WildCatConfigApplication.class, args);
    }
}
