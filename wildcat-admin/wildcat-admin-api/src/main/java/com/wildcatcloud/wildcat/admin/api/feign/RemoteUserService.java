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
package com.wildcatcloud.wildcat.admin.api.feign;

import com.wildcatcloud.wildcat.admin.api.dto.UserInfo;
import com.wildcatcloud.wildcat.common.core.constant.ServiceNameConstants;
import com.wildcatcloud.wildcat.common.core.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @Author WlidcatQin
 * @Date 2020/1/7 20:32
 */
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
//     * @param from     调用标志
     * @return R
     */
    @GetMapping("/user/info/{username}")
    Result<UserInfo> info(@PathVariable("username") String username);
}
