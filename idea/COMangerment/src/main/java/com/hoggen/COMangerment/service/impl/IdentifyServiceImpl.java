package com.hoggen.COMangerment.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoggen.COMangerment.dao.AdminDao;
import com.hoggen.COMangerment.entity.Admin;
import com.hoggen.COMangerment.service.AdminService;
import com.hoggen.COMangerment.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoggen.COMangerment.entity.User;
import com.hoggen.COMangerment.enums.LoginStateEnum;
import com.hoggen.COMangerment.service.IdentifyService;
import com.hoggen.COMangerment.service.UserService;
import com.hoggen.COMangerment.util.HttpServletRequestUtil;
import com.hoggen.COMangerment.util.MD5Util;

@Service
@Transactional
public class IdentifyServiceImpl implements IdentifyService {

	@Autowired
	private AdminService adminService;



	private static final Logger logger = LoggerFactory.getLogger(IdentifyServiceImpl.class);

//	@Override
//	public Map<String, Object> userLoginJudge(HttpServletRequest request) {
//		logger.info("userLoginJudge");
//		String name = HttpServletRequestUtil.getString(request, "user_name");
//		// System.out.println("user_name:" + name);
//		String password = HttpServletRequestUtil.getString(request, "password");
//		// System.out.println("password:" + password);
//		User user = userService.queryUserByName(name);
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		Map<String, Object> modelMapData = new HashMap<String, Object>();
//		if (user != null && user.getStatus() == 1) {
//			modelMap.put("ErrorCode", LoginStateEnum.FREEZE.getState());
//			modelMap.put("Message", LoginStateEnum.FREEZE.getStateInfo());
//			modelMap.put("Data", modelMapData);
//			return modelMap;
//		}
//
//		if (user != null && user.getPassword() != null) {
//			if ((MD5Util.MD5Encode(password + user.getRandomString())).equals(user.getPassword())) {
//				String token = JwtUtil.sign(user.getUserName(), user.getUserId().toString(),"1");
//				HttpSession session = request.getSession();
//
//				session.setAttribute("token",token);//设置token,参数token是要设置的具体值
//				System.out.println("session.getId:  " + session.getId());
////			session.getAttribute("token");//在需要使获取token
//				modelMapData.put("id", user.getUserId());
//				modelMapData.put("token", token);
//				modelMap.put("Data", modelMapData);
//			} else {
//
//				modelMap.put("ErrorCode", LoginStateEnum.USERNONE.getState());
//				modelMap.put("Message", LoginStateEnum.USERNONE.getStateInfo());
//				modelMap.put("Data", modelMapData);
//			}
//
//		} else {
//			modelMap.put("ErrorCode", LoginStateEnum.EMPTY.getState());
//			modelMap.put("Message", LoginStateEnum.EMPTY.getStateInfo());
//			modelMap.put("Data", modelMapData);
//
//		}
//
//		return modelMap;
//	}



	@Override
	public Map<String, Object> adminLoginJudge(HttpServletRequest request) {
		logger.info("adminLoginJudge");
		String name = HttpServletRequestUtil.getString(request, "user_name");
		System.out.println("user_name:" + name);
		String password = HttpServletRequestUtil.getString(request, "password");
		 System.out.println("password:" + password);
		Admin user = adminService.queryAdminByName(name);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String, Object> modelMapData = new HashMap<String, Object>();
		if (user != null && user.getStatus() == 1) {
			modelMap.put("ErrorCode", LoginStateEnum.FREEZE.getState());
			modelMap.put("Message", LoginStateEnum.FREEZE.getStateInfo());
			modelMap.put("Data", modelMapData);
			return modelMap;
		}

		if (user != null && user.getPassword() != null) {
			if ((MD5Util.MD5Encode(password + user.getRandomString())).equals(user.getPassword())) {
				String token = JwtUtil.sign(user.getUserName(), user.getUserId().toString(),"2");
				HttpSession session = request.getSession();

				session.setAttribute("token",token);//设置token,参数token是要设置的具体值
				System.out.println("session.getId:  " + session.getId());
//			session.getAttribute("token");//在需要使获取token
				modelMap.put("ErrorCode", LoginStateEnum.SUCCESS.getState());
				modelMap.put("Message", LoginStateEnum.SUCCESS.getStateInfo());
				modelMapData.put("id", user.getUserId());
				modelMapData.put("token", token);
				modelMap.put("Data", modelMapData);
			} else {

				modelMap.put("ErrorCode", LoginStateEnum.USERNONE.getState());
				modelMap.put("Message", LoginStateEnum.USERNONE.getStateInfo());
				modelMap.put("Data", modelMapData);
			}

		} else {
			modelMap.put("ErrorCode", LoginStateEnum.EMPTY.getState());
			modelMap.put("Message", LoginStateEnum.EMPTY.getStateInfo());
			modelMap.put("Data", modelMapData);

		}

		return modelMap;
	}

	@Override
	public Map<String, Object> userQuit(HttpServletRequest request) {
		return null;
	}

	@Override
	public Map<String, Object> adminQuit(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String, Object> modelMapData = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		session.setAttribute("token","");//设置token,参数token是要设置的具体值
		modelMap.put("ErrorCode", LoginStateEnum.SUCCESS.getState());
		modelMap.put("Message", LoginStateEnum.SUCCESS.getStateInfo());
		modelMap.put("Data", modelMapData);
		return modelMap;
	}


}
