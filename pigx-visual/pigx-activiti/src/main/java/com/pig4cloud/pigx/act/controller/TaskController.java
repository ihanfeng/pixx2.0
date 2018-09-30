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

package com.pig4cloud.pigx.act.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.pig4cloud.pigx.act.dto.LeaveBillDto;
import com.pig4cloud.pigx.act.service.ActTaskService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author lengleng
 * @date 2018/9/28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {
	private final ActTaskService actTaskService;

	@GetMapping("/todo")
	public Page todo(@RequestParam Map<String, Object> params) {
		return actTaskService.findTaskByName(params, SecurityUtils.getUsername());
	}

	@GetMapping("/{id}")
	public R task(@PathVariable String id) {
		return new R(actTaskService.findTaskByTaskId(id));
	}

	@PostMapping
	public R task(@RequestBody LeaveBillDto leaveBillDto) {
		return new R(actTaskService.submitTask(leaveBillDto));
	}

	@GetMapping("/view/{id}")
	public void viewCurrentImage(@PathVariable String id, HttpServletResponse resp) throws IOException {
		InputStream imageStream = actTaskService.viewByTaskId(id);
		byte[] b = new byte[1024];
		int len;
		while ((len = imageStream.read(b, 0, 1024)) != -1) {
			resp.getOutputStream().write(b, 0, len);
		}
	}

	@GetMapping("/comment/{id}")
	public R commitList(@PathVariable String id) {
		return new R(actTaskService.findCommentByTaskId(id));
	}


}
