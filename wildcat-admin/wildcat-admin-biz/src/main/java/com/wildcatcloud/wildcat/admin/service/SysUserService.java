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
package com.wildcatcloud.wildcat.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wildcatcloud.wildcat.admin.api.dto.UserInfo;
import com.wildcatcloud.wildcat.admin.api.entity.SysUser;

/**
 * @Author WlidcatQin
 * @Date 2020/1/7 20:27
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 查询用户信息
     *
     * @param sysUser 用户
     * @return userInfo
     */
    UserInfo findUserInfo(SysUser sysUser);
}
