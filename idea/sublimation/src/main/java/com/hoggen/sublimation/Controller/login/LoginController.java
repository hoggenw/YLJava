package com.hoggen.sublimation.Controller.login;


import com.hoggen.sublimation.dto.UserExecution;
import com.hoggen.sublimation.entity.User;
import com.hoggen.sublimation.service.httpsevice.LoginService;
import com.hoggen.sublimation.service.httpsevice.UserService;
import com.hoggen.sublimation.util.ResponedUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/api/login")
@Api(tags = "用户管理模块")
@Slf4j
public class LoginController {


    @Autowired
    private LoginService identifyService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户登录")
    private Map<String, Object> userLogin(@RequestParam(value = "phone", required=true) @ApiParam(value = "手机号码", required = true) String phone,
                                          @RequestParam(value = "passworld", required=true) @ApiParam(value = "密码", required = true )String passworld) {


        return identifyService.userLoginJudge(phone,passworld);
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
