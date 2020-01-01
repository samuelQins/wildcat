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
package com.wildcatcloud.wildcat.common.core.config;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildcatcloud.wildcat.common.core.Jackson.WildcatJavaTimeModule;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期反序列化时区问题
 * @Author WlidcatQin
 * @Date 2019/9/28
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfig {
        @Bean
        public Jackson2ObjectMapperBuilderCustomizer customizer() {
            return builder -> {
                builder.locale(Locale.CHINA);
                builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
                builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
                builder.modules(new WildcatJavaTimeModule());
            };
        }
}
