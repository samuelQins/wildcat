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
package com.wildcatcloud.wildcat.common.security.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.wildcatcloud.wildcat.common.core.constant.CommonConstants;
import com.wildcatcloud.wildcat.common.core.util.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * @Author WlidcatQin
 * @Date 2020/1/1 17:18
 */
@Slf4j
@AllArgsConstructor
public class WildCatFeignFallbackMethod implements MethodInterceptor {
    private Class<?> type;
    private Throwable cause;

    @Nullable
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) {
        log.error("Fallback class:[{}] method:[{}] message:[{}]",
                type.getName(), method.getName(), cause.getMessage());

        if (Result.class == method.getReturnType()) {
            final Result result = cause instanceof WildCatFeignException ?
                    ((WildCatFeignException) cause).getResult() : Result.builder()
                    .code(CommonConstants.FAIL)
                    .msg(cause.getMessage()).build();
            return result;
        }
        return null;
    }
}
