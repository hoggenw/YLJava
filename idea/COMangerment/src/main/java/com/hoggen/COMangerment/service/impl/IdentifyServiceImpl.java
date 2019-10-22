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
			modelMap.put("errno", LoginStateEnum.FREEZE.getState());
			modelMap.put("errmsg", LoginStateEnum.FREEZE.getStateInfo());
			modelMap.put("data", modelMapData);
			return modelMap;
		}

		if (user != null && user.getPassword() != null) {
			if ((MD5Util.MD5Encode(password + user.getRandomString())).equals(user.getPassword())) {

				modelMap.put("errno", LoginStateEnum.SUCCESS.getState());
				modelMap.put("errmsg", LoginStateEnum.SUCCESS.getStateInfo());

				String token = JwtUtil.sign(name, String.valueOf(user.getUserId()),String.valueOf(user.getRealName()));
				modelMapData.put("token",token);
				modelMapData.put("userId",user.getUserId());
				modelMapData.put("name",user.getUserName());
				modelMap.put("data",modelMapData);
				user.setToken(token);
				user.setLastLoginTime(new Date());
				adminService.modifyAdmin(user);

			} else {

				modelMap.put("errno", LoginStateEnum.USERNONE.getState());
				modelMap.put("errmsg", LoginStateEnum.USERNONE.getStateInfo());
				modelMap.put("data", modelMapData);
			}

		} else {
			modelMap.put("errno", LoginStateEnum.EMPTY.getState());
			modelMap.put("errmsg", LoginStateEnum.EMPTY.getStateInfo());
			modelMap.put("date", modelMapData);

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
		String token = request.getHeader("token");
		Long userid = Long.valueOf(JwtUtil.getLoginUserId(token));
		if (userid == null || userid.equals("")){

		}else {
			Admin user = adminService.queryAdminById(userid);
			user.setToken("");
			user.setLastLoginTime(new Date());
			adminService.modifyAdmin(user);
		}

		modelMap.put("errno", LoginStateEnum.SUCCESS.getState());
		modelMap.put("errmsg", LoginStateEnum.SUCCESS.getStateInfo());
		modelMap.put("data", modelMapData);

		return modelMap;
	}


}
