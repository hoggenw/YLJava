package com.hoggen.COMangerment.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface IdentifyService {


	/**
	 * 判定登录是否成功
	 */
	//Map<String, Object> userLoginJudge(HttpServletRequest request);

	Map<String, Object> adminLoginJudge(HttpServletRequest request);

	/**
	 * 用户退出登录
	 */
	Map<String, Object> userQuit(HttpServletRequest request);

	/**
	 * 管理员退出登录
	 */
	Map<String, Object> adminQuit(HttpServletRequest request);
}
