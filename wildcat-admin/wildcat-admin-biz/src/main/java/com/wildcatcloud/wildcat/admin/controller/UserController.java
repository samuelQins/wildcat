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
package com.wildcatcloud.wildcat.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.wildcatcloud.wildcat.admin.api.entity.SysUser;
import com.wildcatcloud.wildcat.admin.service.SysUserService;
import com.wildcatcloud.wildcat.common.core.util.Result;
import com.wildcatcloud.wildcat.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author WlidcatQin
 * @Date 2020/1/7 20:30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(value = "user", tags = "用户管理模块")
public class UserController {
    private final SysUserService userService;

    @GetMapping("/info/{username}")
    @Inner
    public Result info(@PathVariable String username) {
        SysUser user = userService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return new Result<>(Boolean.FALSE, String.format("用户信息为空 %s", username));
        }
        return new Result<>(userService.findUserInfo(user));
    }
}
