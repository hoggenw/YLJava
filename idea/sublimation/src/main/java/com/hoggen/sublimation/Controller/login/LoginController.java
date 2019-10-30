package com.hoggen.sublimation.Controller.login;


import com.hoggen.sublimation.dto.UserExecution;
import com.hoggen.sublimation.entity.User;
import com.hoggen.sublimation.service.httpsevice.LoginService;
import com.hoggen.sublimation.service.httpsevice.UserService;
import com.hoggen.sublimation.util.ResponedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/api/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService identifyService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> userLogin(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = identifyService.userLoginJudge(request);
        logger.info("用户登录");

        return modelMap;
    }


    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> userInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = identifyService.userInfo(request);
        return modelMap;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> register(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        User user = new User();
        UserExecution effect = userService.insertUser(user);

        return ResponedUtils.returnCode(effect.getState(),effect.getStateInfo(),effect.getUser());
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> update(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        User user = new User();
        UserExecution effect = userService.modifyUser(user);

        return ResponedUtils.returnCode(effect.getState(),effect.getStateInfo(),effect.getUser());
    }

}
