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
package com.wildcatcloud.wildcat.auth.config;

import com.wildcatcloud.wildcat.common.core.constant.SecurityConstants;
import com.wildcatcloud.wildcat.common.security.component.WildCatWebResponseExceptionTranslator;
import com.wildcatcloud.wildcat.common.security.service.WildCatClientDetailsService;
import com.wildcatcloud.wildcat.common.security.service.WildCatUser;
import com.wildcatcloud.wildcat.common.security.service.WildCatUserDetailsService;
import com.wildcatcloud.wildcat.data.tenant.TenantContextHolder;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author WlidcatQin
 * @Date 2019/12/24 20:53
 * AuthorizationServerConfigurerAdapter 类中3个不同的configure方法分别
 * configure(ClientDetailsServiceConfigurer clients)
 * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息；
 * configure(AuthorizationServerEndpointsConfigurer endpoints)
 * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)，还有token的存储方式(tokenStore)；
 * configure(AuthorizationServerSecurityConfigurer security)
 * 用来配置令牌端点(Token Endpoint)的安全约束；
 *
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final DataSource dataSource;
    private final WildCatUserDetailsService wildCatUserDetailsService;
    private final AuthenticationManager authenticationManagerBean;
    private final RedisConnectionFactory redisConnectionFactory;

    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {
        WildCatClientDetailsService clientDetailsService = new WildCatClientDetailsService(dataSource);
        clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
        clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
        clients.withClientDetails(clientDetailsService);
    }

    /*
     * @Author samuelQin
     * @Date 2019/12/24 21:38
     * @DES 配置令牌端点(Token Endpoint)的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .allowFormAuthenticationForClients() //主要是让/oauth/token支持client_id以及client_secret作登录认证
                // 开启/oauth/token_key验证端口无权限访问
//                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore())
//                .tokenServices(defaultTokenServices())
                .tokenEnhancer(tokenEnhancer())
                .userDetailsService(wildCatUserDetailsService)
                .authenticationManager(authenticationManagerBean)
                .reuseRefreshTokens(false)
                .exceptionTranslator(new WildCatWebResponseExceptionTranslator());
    }


    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(SecurityConstants.WILDCAT_PREFIX + SecurityConstants.OAUTH_PREFIX);
        tokenStore.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator() {
            @Override
            public String extractKey(OAuth2Authentication authentication) {
                return super.extractKey(authentication) + ":" + TenantContextHolder.getTenantId();
            }
        });
        return tokenStore;
    }

    /**
     * token增强，客户端模式不增强。
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (SecurityConstants.CLIENT_CREDENTIALS
                    .equals(authentication.getOAuth2Request().getGrantType())) {
                return accessToken;
            }
            final Map<String, Object> additionalInfo = new HashMap<>(8);
            WildCatUser wildCatUser = (WildCatUser) authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put("user_id", wildCatUser.getId());
            additionalInfo.put("username", wildCatUser.getUsername());
            additionalInfo.put("dept_id", wildCatUser.getDeptId());
            additionalInfo.put("tenant_id", wildCatUser.getTenantId());
            additionalInfo.put("license", SecurityConstants.WILDCAT_LICENSE);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

    

    //  生成token的处理
/*    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 是否支持 refreshToken
        tokenServices.setSupportRefreshToken(true);
        // 是否复用 refreshToken
        tokenServices.setReuseRefreshToken(true);
        // tokenServices.setTokenEnhancer(tokenEnhancer());
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        //默认30天，这里修改
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }*/
}
