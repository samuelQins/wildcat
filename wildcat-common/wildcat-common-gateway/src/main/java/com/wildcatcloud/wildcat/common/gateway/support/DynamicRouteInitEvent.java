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
package com.wildcatcloud.wildcat.common.gateway.support;

import org.springframework.context.ApplicationEvent;

/**
 * @Author WlidcatQin
 * @Date 2019/12/10 20:05
 * 路由初始化事件
 */
public class DynamicRouteInitEvent  extends ApplicationEvent {
    public DynamicRouteInitEvent(Object source) {
        super(source);
    }
}
