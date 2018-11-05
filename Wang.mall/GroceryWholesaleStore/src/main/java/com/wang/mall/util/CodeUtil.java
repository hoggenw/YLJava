package com.wang.mall.util;

import javax.servlet.http.HttpServletRequest;


public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String verifyCodeExpected = ((String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY)).toLowerCase();
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual").toLowerCase();
		;
		if (verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)) {
			return false;
		}
		return true;

	}

}
