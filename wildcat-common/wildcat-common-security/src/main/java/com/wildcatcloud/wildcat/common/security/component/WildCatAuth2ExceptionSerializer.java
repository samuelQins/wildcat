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
package com.wildcatcloud.wildcat.common.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wildcatcloud.wildcat.common.core.constant.CommonConstants;
import com.wildcatcloud.wildcat.common.security.exception.WilCatAuth2Exception;
import lombok.SneakyThrows;

/**
 * @Author WlidcatQin
 * @Date 2019/12/26 20:31
 * 定义异常BootOAuth2Exception 的序列化类
 */
public class WildCatAuth2ExceptionSerializer extends StdSerializer<WilCatAuth2Exception> {

    public WildCatAuth2ExceptionSerializer() {
        super(WilCatAuth2Exception.class);
    }

    @Override
    @SneakyThrows
    public void serialize(WilCatAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
        gen.writeStartObject();
        gen.writeObjectField("code", CommonConstants.FAIL);
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("data", value.getErrorCode());
        gen.writeEndObject();
    }
}
