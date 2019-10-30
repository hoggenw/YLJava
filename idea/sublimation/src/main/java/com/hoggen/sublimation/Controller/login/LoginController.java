package com.fulang.knight.controller;

import com.fulang.knight.KnightApplication;
import com.fulang.knight.entity.*;
import com.fulang.knight.enums.DriverStatusEnum;
import com.fulang.knight.enums.LoginStateEnum;
import com.fulang.knight.service.BaseService;
import com.fulang.knight.service.LoginService;
import com.fulang.knight.service.UserService;
import com.fulang.knight.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService identifyService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/login/user", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> userLogin(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = identifyService.userLoginJudge(request);
        logger.info("用户登录");

        return modelMap;
    }


    @RequestMapping(value = "/api/user/info", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> userInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = identifyService.userInfo(request);
        return modelMap;
    }

}
