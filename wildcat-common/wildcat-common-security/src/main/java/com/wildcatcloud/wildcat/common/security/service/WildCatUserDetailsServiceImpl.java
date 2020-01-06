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
package com.wildcatcloud.wildcat.common.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author WlidcatQin
 * @Date 2019/12/24 21:10
 * 用户信息获取接口
 */
@Slf4j
@Service
@AllArgsConstructor
public class WildCatUserDetailsServiceImpl implements WildCatUserDetailsService {
    private final CacheManager cacheManager;
    @Override
    public UserDetails loadUserBySocial(String code) throws UsernameNotFoundException {

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cache cache = cacheManager.getCache("user_details");
        if (cache != null && cache.get(username) != null) {
            return (WildCatUser) cache.get(username).get();
        }
        if (cache!=null && cache.get(username) == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new WildCatUser(1,1,1,"admin","123456",true,true,true,true,null);
    }
}
