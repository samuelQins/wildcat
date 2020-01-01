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
package com.wildcatcloud.wildcat.codegen;

import com.wildcatcloud.wildcat.common.swagger.annotation.EnableWildcatSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@EnableWildcatSwagger2
public class WildCatCodeGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(WildCatCodeGenApplication.class);
    }
}
