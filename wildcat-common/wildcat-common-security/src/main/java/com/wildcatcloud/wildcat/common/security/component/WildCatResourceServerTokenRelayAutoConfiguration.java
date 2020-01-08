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

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.OAuth2ClientConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;

import java.lang.annotation.*;

/**
 * @Author WlidcatQin
 * @Date 2020/1/7 22:11
 */
@Configuration
@AutoConfigureAfter(OAuth2AutoConfiguration.class)
@ConditionalOnWebApplication
@ConditionalOnProperty("security.oauth2.client.client-id")
public class WildCatResourceServerTokenRelayAutoConfiguration {
    @Bean
    public AccessTokenContextRelay accessTokenContextRelay(OAuth2ClientContext context) {
        return new AccessTokenContextRelay(context);
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Conditional(OAuth2OnClientInResourceServerCondition.class)
    @interface ConditionalOnOAuth2ClientInResourceServer {

    }

    private static class OAuth2OnClientInResourceServerCondition
            extends AllNestedConditions {

        public OAuth2OnClientInResourceServerCondition() {
            super(ConfigurationPhase.REGISTER_BEAN);
        }

        @ConditionalOnBean(ResourceServerConfiguration.class)
        static class Server {
        }

        @ConditionalOnBean(OAuth2ClientConfiguration.class)
        static class Client {
        }

    }
}
