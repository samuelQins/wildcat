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
package com.wildcatcloud.wildcat.erueka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author WlidcatQin
 * @Date 2019/9/28
 */
@EnableEurekaServer
@SpringBootApplication
public class WildCatEruekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(WildCatEruekaApplication.class, args);
    }
}
