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

import feign.hystrix.FallbackFactory;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author WlidcatQin
 * @Date 2020/1/1 17:17
 * 默认 Fallback，避免写过多fallback类
 *
 * @param <T> 泛型标记
 * @author L.cm
 */
@Slf4j
@NoArgsConstructor
public class WildCatFeignFallbackFactory<T> implements FallbackFactory<T> {
    public static final WildCatFeignFallbackFactory INSTANCE = new WildCatFeignFallbackFactory();
    private static final ConcurrentMap<Class<?>, Object> FALLBACK_MAP = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public T create(final Class<?> type, final Throwable cause) {
        return (T) FALLBACK_MAP.computeIfAbsent(type, key -> {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(key);
            enhancer.setCallback(new WildCatFeignFallbackMethod(type, cause));
            return enhancer.create();
        });
    }

    @Override
    public T create(Throwable cause) {
        return null;
    }
}
