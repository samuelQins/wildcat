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

import com.wildcatcloud.wildcat.common.core.constant.SecurityConstants;
import com.wildcatcloud.wildcat.common.security.annotation.EnableWildCatResourceServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @Author WlidcatQin
 * @Date 2019/12/30 21:25
 */
@Slf4j
public class WildCatSecurityBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        if (registry.isBeanNameInUse(SecurityConstants.RESOURCE_SERVER_CONFIGURER)) {
            log.warn("本地存在资源服务器配置，覆盖默认配置:" + SecurityConstants.RESOURCE_SERVER_CONFIGURER);
            return;
        }

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(WildCatResourceServerConfigurerAdapter.class);
        MutablePropertyValues mpv = new MutablePropertyValues();
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(
                EnableWildCatResourceServer.class.getName());
        Object details = annotationAttributes.get("details");
        mpv.add("details", details);
        beanDefinition.setPropertyValues(mpv);
        registry.registerBeanDefinition(SecurityConstants.RESOURCE_SERVER_CONFIGURER, beanDefinition);
    }
}
