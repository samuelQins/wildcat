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
package com.wildcatcloud.wildcat.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author WlidcatQin
 * @Date 2020/1/12 22:40
 * 登录类型
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
    /**
     * 账号密码登录
     */
    PWD("PWD", "账号密码登录"),

    /**
     * 验证码登录
     */
    SMS("SMS", "验证码登录"),

    /**
     * QQ登录
     */
    QQ("QQ", "QQ登录"),

    /**
     * 微信登录
     */
    WECHAT("WX", "微信登录");

    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
}
