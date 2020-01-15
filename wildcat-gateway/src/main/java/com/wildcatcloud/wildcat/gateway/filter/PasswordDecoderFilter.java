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
package com.wildcatcloud.wildcat.gateway.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import com.wildcatcloud.wildcat.common.core.constant.SecurityConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Author WlidcatQin
 * @Date 2019/12/9 21:10
 */
@Slf4j
@Component
public class PasswordDecoderFilter extends AbstractGatewayFilterFactory {
    private static final String PASSWORD = "password";
    private static final String KEY_ALGORITHM = "AES";
    @Value("${security.encode.key:1234567812345678}")
    private String encodeKey;

    @SneakyThrows
    private static String decryptAES(String data, String pass) {
        AES aes = new AES(Mode.CBC, Padding.NoPadding,
                new SecretKeySpec(pass.getBytes(), KEY_ALGORITHM),
                new IvParameterSpec(pass.getBytes()));
        byte[] result = aes.decrypt(Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
        return new String(result, StandardCharsets.UTF_8);
    }


    /**
     * 将16进制转换转换为二进制
     *
     * @param hexStr
     * @return
     */
    @SneakyThrows
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 将二进制转换成16进制
     *      * @param buf
     *      * @return
     *      
     */
    @SneakyThrows
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是登录请求，直接向下执行
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.OAUTH_TOKEN_URL)) {
                return chain.filter(exchange);
            }

            URI uri = exchange.getRequest().getURI();
            String queryParam = uri.getRawQuery();
            Map<String, String> paramMap = HttpUtil.decodeParamMap(queryParam, CharsetUtil.UTF_8);

            String password = paramMap.get(PASSWORD);
            if (StrUtil.isNotBlank(password)) {
                try {
                    password = decryptAES(password, encodeKey);
                } catch (Exception e) {
                    log.error("密码解密失败:{}", password);
                    return Mono.error(e);
                }
                paramMap.put(PASSWORD, password.trim());
            }
            URI newUri = UriComponentsBuilder.fromUri(uri)
                    .replaceQuery(HttpUtil.toParams(paramMap))
                    .build(true)
                    .toUri();
            ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
            return chain.filter(exchange.mutate().request(newRequest).build());
        };
    }
}
