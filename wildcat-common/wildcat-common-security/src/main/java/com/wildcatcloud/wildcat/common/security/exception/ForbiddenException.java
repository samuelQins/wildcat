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
import org.springframework.http.HttpStatus;

/**
 * @Author WlidcatQin
 * @Date 2019/12/26 20:37
 */
@JsonSerialize(using = WildCatAuth2ExceptionSerializer.class)
public class ForbiddenException extends WilCatAuth2Exception {

    public ForbiddenException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "access_denied";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.FORBIDDEN.value();
    }
}
