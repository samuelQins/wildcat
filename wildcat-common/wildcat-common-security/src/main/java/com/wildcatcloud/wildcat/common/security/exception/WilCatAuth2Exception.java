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
package com.wildcatcloud.wildcat.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wildcatcloud.wildcat.common.security.component.WildCatAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Author WlidcatQin
 * @Date 2019/12/26 20:30
 */
@JsonSerialize(using = WildCatAuth2ExceptionSerializer.class)
public class WilCatAuth2Exception extends OAuth2Exception {

    @Getter
    private String errorCode;

    public WilCatAuth2Exception(String msg) {
        super(msg);
    }

    public WilCatAuth2Exception(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
