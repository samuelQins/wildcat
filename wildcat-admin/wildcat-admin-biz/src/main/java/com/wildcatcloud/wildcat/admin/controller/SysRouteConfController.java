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

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.extension.api.R;
import com.wildcatcloud.wildcat.admin.service.SysRouteConfService;
import com.wildcatcloud.wildcat.common.core.util.Result;
import com.wildcatcloud.wildcat.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author WlidcatQin
 * @Date 2020/1/13 19:56
 * 动态路由
 */
@RestController
@AllArgsConstructor
@RequestMapping("/route")
@Api(value = "route",tags = "动态路由管理模块")
public class SysRouteConfController {
    private final SysRouteConfService sysRouteConfService;


    /**
     * 获取当前定义的路由信息
     *
     * @return
     */
    @GetMapping
    public Result listRoutes() {
        return new Result<>(sysRouteConfService.list());
    }

    /**
     * 修改路由
     *
     * @param routes 路由定义
     * @return
     */
    @SysLog("修改路由")
    @PutMapping
    public Result updateRoutes(@RequestBody JSONArray routes) {
        return new Result(sysRouteConfService.updateRoutes(routes));
    }

}
