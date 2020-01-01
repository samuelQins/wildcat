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
package com.wildcatcloud.wildcat.common.security.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.wildcatcloud.wildcat.common.core.constant.CommonConstants;
import com.wildcatcloud.wildcat.common.core.util.Result;
import lombok.Getter;

/**
 * @Author WlidcatQin
 * @Date 2020/1/1 16:58
 * Feign异常类
 */
public class WildCatFeignException extends RuntimeException {
    @Getter
    private final Result result;

    public WildCatFeignException(Result result) {
        super(result.getMsg());
        this.result = result;
    }

    public WildCatFeignException(String message) {
        super(message);
        this.result = Result.builder()
                .code(CommonConstants.FAIL)
                .msg(message).build();
    }

    /**
     * 提高性能
     *
     * @return {Throwable}
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
