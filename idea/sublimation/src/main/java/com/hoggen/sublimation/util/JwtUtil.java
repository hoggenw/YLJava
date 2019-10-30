package com.hoggen.sublimation.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

	/**
	 * 过期时间
	 */
	private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

	public static long getExpireTime() {
		return EXPIRE_TIME;
	}

	/**
	 * 
	 * token 私钥
	 */
	private static final String TOKEN_SECRET = "df43r345yy6hfdklajs9089364783sas";

	/**
	 * 
	 * 生成 token
	 */
	public static String sign(String username, String userId, String loginType) {
		try {
			// 过期时间
			Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			// 秘钥及加密算法
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			// 设置头部信息
			Map<String, Object> header = new HashMap<>();
			header.put("typ", "JWT");
			header.put("alg", "HS256");
			String token = JWT.create().withHeader(header).withClaim("loginName", username).withClaim("userId", userId)
					.withClaim("type", loginType).withExpiresAt(date).sign(algorithm);
			System.out.println(token);

			return token;

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取loginName
	 */
	public static String getLoginUserID(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("userId").asString();
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}

	/**
	 * 获取loginType
	 */
	public static String getLoginType(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("type").asString();
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}

//	public static boolean verify(String token) {
//		// 秘钥及加密算法
//		Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//	}

}
