package com.hoggen.COMangerment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoggen.COMangerment.service.IdentifyService;

@Controller
public class UserLoginController {

	@Autowired
	private IdentifyService identifyService;

//	@RequestMapping(value = "/login/user", method = RequestMethod.POST)
//	@ResponseBody
//	private Map<String, Object> userLogin(HttpServletRequest request) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		modelMap = identifyService.userLoginJudge(request);
//
//		return modelMap;
//	}


	@RequestMapping(value = "/api/login/admin", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> adminLogin(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap = identifyService.adminLoginJudge(request);

		return modelMap;
	}

	@RequestMapping(value = "/api/quit/admin", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> quit(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap = identifyService.adminQuit(request);
		return modelMap;
	}

}
