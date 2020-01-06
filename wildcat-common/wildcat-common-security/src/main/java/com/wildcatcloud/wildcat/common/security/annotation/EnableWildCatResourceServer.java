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
package com.wildcatcloud.wildcat.common.security.annotation;

import com.wildcatcloud.wildcat.common.security.component.WildCatResourceServerAutoConfiguration;
import com.wildcatcloud.wildcat.common.security.component.WildCatSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @Author samuelQin
 * @Date 2019/12/30 21:19
 * @DES 服务资源注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({WildCatResourceServerAutoConfiguration.class, WildCatSecurityBeanDefinitionRegistrar.class})
public @interface EnableWildCatResourceServer {
}
