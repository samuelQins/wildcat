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
package com.wildcatcloud.wildcat.codegen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wildcatcloud.wildcat.codegen.entity.GenConfig;

import java.util.List;
import java.util.Map;

/**
 * @Author WlidcatQin
 * @Date 2019/10/18 22:06
 */
public interface SysGeneratorService {
    /**
     * 生成代码
     *
     * @param tableNames 表名称
     * @return
     */
    byte[] generatorCode(GenConfig tableNames);

    /**
     * 分页查询表
     * @param tableName 表名
     * @return
     */
    IPage<List<Map<String, Object>>> getPage(Page page, String tableName);
}
