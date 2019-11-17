package com.hoggen.sublimation.Controller.login;


import com.hoggen.sublimation.dto.LoginDTO;
import com.hoggen.sublimation.dto.RegisterDTO;
import com.hoggen.sublimation.dto.ReturnUserDTO;
import com.hoggen.sublimation.dto.UserExecution;
import com.hoggen.sublimation.entity.User;
import com.hoggen.sublimation.enums.LoginStateEnum;
import com.hoggen.sublimation.service.httpsevice.LoginService;
import com.hoggen.sublimation.service.httpsevice.UserService;
import com.hoggen.sublimation.util.CodeJudgeUtil;
import com.hoggen.sublimation.util.ResponedUtils;
import com.hoggen.sublimation.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import sun.misc.BASE64Encoder;


@Controller
@RequestMapping(value="/api/login")
@Api(tags = "用户管理模块")
@Slf4j
public class LoginController {


    @Autowired
    private LoginService identifyService;

    @Autowired
    private UserService userService;

    @Autowired
    private Producer captchaProducer;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户登录")
    private Map<String, Object> userLogin(@Validated @RequestBody LoginDTO loginDTO ) {


        return identifyService.userLoginJudge(loginDTO.getPhone(),loginDTO.getPassword());
    }


    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody

    private Map<String, Object> userInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = identifyService.userInfo(request);
        return modelMap;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户注册")
    private Map<String, Object> register(HttpServletRequest request,@Validated @RequestBody RegisterDTO registerDTO)  {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!CodeJudgeUtil.codeJudge(request,registerDTO.getCode())){
           return ResponedUtils.returnCode(LoginStateEnum.CODEERROR.getState(),LoginStateEnum.CODEERROR.getStateInfo(),modelMap);
        }
        User user = new User();
        user.setPassword(registerDTO.getPassword());
        user.setMobile(registerDTO.getPhone());
        user.setUserName(registerDTO.getName());
        user.setRandomString(StringUtil.getRandomString(8));
        user.setCreateTime( new Date());
        user.setUpdateTime(new Date());
        UserExecution effect = userService.insertUser(user);

        return ResponedUtils.returnCode(effect.getState(),effect.getStateInfo(),new ReturnUserDTO(effect.getUser()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> update(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        User user = new User();
        UserExecution effect = userService.modifyUser(user);

        return ResponedUtils.returnCode(effect.getState(),effect.getStateInfo(),effect.getUser());
    }


    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "获取注册验证码")
    @RequestMapping(value = "/getKaptchaImage", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object>  getKaptchaImage(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {



        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        // 生产验证码字符串并保存到session中
        String createText = captchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, createText);
        // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
        BufferedImage challenge = captchaProducer.createImage(createText);
        ImageIO.write(challenge, "png", jpegOutputStream);
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("img", encoder.encode(jpegOutputStream.toByteArray()));

        return ResponedUtils.returnCode(LoginStateEnum.SUCCESS.getState(),LoginStateEnum.SUCCESS.getStateInfo(),modelMap);

//
//        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
//        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        response.setHeader("Cache-Control", "no-store");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", 0);
//        response.setContentType("image/png");
//        ServletOutputStream responseOutputStream = response.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();


    }


}
