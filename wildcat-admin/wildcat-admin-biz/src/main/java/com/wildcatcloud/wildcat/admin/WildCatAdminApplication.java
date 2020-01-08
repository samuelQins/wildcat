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
package com.wildcatcloud.wildcat.admin;

import com.wildcatcloud.wildcat.common.security.annotation.EnableWildCatFeignClients;
import com.wildcatcloud.wildcat.common.security.annotation.EnableWildCatResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Author WlidcatQin
 * @Date 2020/1/7 21:05
 */
@SpringCloudApplication
@EnableWildCatFeignClients
@EnableWildCatResourceServer
public class WildCatAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(WildCatAdminApplication.class, args);
    }
}
