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

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.wildcatcloud.wildcat.admin.api.dto.UserInfo;
import com.wildcatcloud.wildcat.admin.api.entity.SysUser;
import com.wildcatcloud.wildcat.admin.api.feign.RemoteUserService;
import com.wildcatcloud.wildcat.common.core.constant.CommonConstants;
import com.wildcatcloud.wildcat.common.core.constant.SecurityConstants;
import com.wildcatcloud.wildcat.common.core.util.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author WlidcatQin
 * @Date 2019/12/24 21:10
 * 用户信息获取接口
 */
@Slf4j
@Service
@AllArgsConstructor
public class WildCatUserDetailsServiceImpl implements WildCatUserDetailsService {
    private final RemoteUserService remoteUserService;
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
        Result<UserInfo> result = remoteUserService.info(username,SecurityConstants.FROM_IN);
        UserDetails userDetails = getUserDetails(result);
        cache.put(username, userDetails);
        return userDetails;
    }

    /**
     * 构建userdetails用户：admin 登录失败，异常
     *
     * @param result 用户信息
     * @return
     */
    private UserDetails getUserDetails(Result<UserInfo> result) {
        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        UserInfo info = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

        }
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();
        boolean enabled = StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL);
        // 构造security用户

        return new WildCatUser(user.getUserId(), user.getDeptId(), user.getTenantId(), user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(), enabled,
                true, true, !CommonConstants.STATUS_LOCK.equals(user.getLockFlag()), authorities);
    }
}
