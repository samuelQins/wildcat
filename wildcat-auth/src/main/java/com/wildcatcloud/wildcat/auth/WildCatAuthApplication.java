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
package com.wildcatcloud.wildcat.auth;

import com.wildcatcloud.wildcat.common.security.annotation.EnableWildCatFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author WlidcatQin
 * @Date 2019/12/26 21:12
 */
@SpringCloudApplication
@EnableWildCatFeignClients
public class WildCatAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(WildCatAuthApplication.class, args);
    }

}
