/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.pig4cloud.pigx.gateway.support;

import cn.hutool.json.JSONObject;
import com.pig4cloud.pigx.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author lengleng
 * @date 2018/10/31
 * <p>
 * 动态路由业务端点
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/route")
public class DynamicRouteEndpoint {
	private final DynamicRouteHandler dynamicRouteHandler;

	/**
	 * 获取当前定义的路由信息
	 *
	 * @return {"id"："RouteDefinition"}
	 */
	@GetMapping
	public Mono<Map<String, RouteDefinition>> routes() {
		return dynamicRouteHandler.routes();
	}

	/**
	 * 修改路由
	 *
	 * @param routes 路由定义
	 * @return
	 */
	@PutMapping
	public R edit(@RequestBody JSONObject routes) {
		return new R(dynamicRouteHandler.editRoutes(routes));
	}

	/**
	 * 回滚初始化配置
	 *
	 * @return
	 */
	@GetMapping("/fallback")
	public Mono<R> fallback() {
		return Mono.just(new R(dynamicRouteHandler.fallback()));
	}
}
