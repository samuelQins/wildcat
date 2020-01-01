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
package com.wildcatcloud.wildcat.common.log.aspect;

import com.wildcatcloud.wildcat.common.log.annotation.SysLog;
import com.wildcatcloud.wildcat.common.log.event.SysLogEvent;
import com.wildcatcloud.wildcat.common.log.utils.SysLogUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author samuelQin
 * @date 2019/7/9
 * @description
 * 操作日志AOP
 * 使用spring event异步入库
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class SysLogAspect {

    private final ApplicationEventPublisher publisher;

    @SneakyThrows
    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);
        com.wildcatcloud.wildcat.admin.api.entity.SysLog sysLogVo
                = SysLogUtils.getSysLog();
        sysLogVo.setTitle(sysLog.value());
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        Long endTime = System.currentTimeMillis();
        sysLogVo.setTime(endTime - startTime);
        publisher.publishEvent(new SysLogEvent(logVo));
        return obj;
    }

}
