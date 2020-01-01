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
package com.wildcatcloud.wildcat.codegen.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author WlidcatQin
 * @Date 2019/9/11
 */
public interface SysGeneratorMapper {
    /**
     * 分页查询表格
     */
    IPage<List<Map<String, Object>>> queryList(Page page, @Param("tableName") String tableName);

    /**
     * 查询表信息
     */
    Map<String, String> queryTable(String tableName);

    /**
     * 查询表列信息
     */
    List<Map<String, String>> queryColumns(String tableName);
}
