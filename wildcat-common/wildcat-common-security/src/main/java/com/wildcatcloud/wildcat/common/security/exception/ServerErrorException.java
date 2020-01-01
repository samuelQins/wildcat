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
import com.wildcatcloud.wildcat.common.security.component.WilCatAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @Author WlidcatQin
 * @Date 2019/12/26 20:37
 */
@JsonSerialize(using = WilCatAuth2ExceptionSerializer.class)
public class ServerErrorException extends WilCatAuth2Exception {

    public ServerErrorException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "server_error";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

}
