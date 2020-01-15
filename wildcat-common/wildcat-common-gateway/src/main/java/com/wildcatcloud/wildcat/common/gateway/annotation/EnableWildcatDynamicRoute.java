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
package com.wildcatcloud.wildcat.common.gateway.annotation;

import com.wildcatcloud.wildcat.common.gateway.configuration.DynamicRouteAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author WlidcatQin
 * @Date 2019/12/10 19:53
 * 开启wildcat 动态路由
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(DynamicRouteAutoConfiguration.class)
public @interface EnableWildcatDynamicRoute {
}
