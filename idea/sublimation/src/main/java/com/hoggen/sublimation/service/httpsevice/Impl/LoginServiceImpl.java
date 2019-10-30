package com.hoggen.sublimation.service.httpsevice.Impl;


import com.hoggen.sublimation.dao.UserDao;
import com.hoggen.sublimation.entity.User;
import com.hoggen.sublimation.enums.LoginStateEnum;
import com.hoggen.sublimation.service.httpsevice.LoginService;
import com.hoggen.sublimation.service.httpsevice.UserService;
import com.hoggen.sublimation.util.HttpServletRequestUtil;
import com.hoggen.sublimation.util.JwtUtil;
import com.hoggen.sublimation.util.MD5Util;
import com.hoggen.sublimation.util.ResponedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);


    @Override
    public Map<String, Object> userLoginJudge(HttpServletRequest request) {
        logger.info("userLoginJudge");
        // TODO Auto-generated method stub
        String name = HttpServletRequestUtil.getString(request, "user_name");
        // System.out.println("user_name:" + name);
        String password = HttpServletRequestUtil.getString(request, "password");
        // System.out.println("password:" + password);
        User user = userService.queryByUserName(name);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();
        if (user != null && user.getStatus() == 1) {
            modelMap.put("errno", LoginStateEnum.EMPTY.getState());
            modelMap.put("errmsg", LoginStateEnum.EMPTY.getStateInfo());
            modelMap.put("date", modelMapData);
            return modelMap;
        }
        if (user != null && user.getPassword() != null) {
            if ((MD5Util.MD5Encode(password + user.getRandomString())).equals(user.getPassword())) {

                modelMap.put("errno", LoginStateEnum.SUCCESS.getState());
                modelMap.put("errmsg", LoginStateEnum.SUCCESS.getStateInfo());

                String token = JwtUtil.sign(name, String.valueOf(user.getUserId()),String.valueOf(user.getRoleType()));
                modelMapData.put("token",token);
                modelMapData.put("roleType",user.getRoleType());
                modelMapData.put("userId",user.getUserId());

                modelMap.put("data",modelMapData);
//                user.setToken(token);
//                user.setLastLoginTime(new Date());
//                user.setTokenTime(new Date());
//                userDao.updateUserToken(user);

            } else {

                modelMap.put("errno", LoginStateEnum.USERNONE.getState());
                modelMap.put("errmsg", LoginStateEnum.USERNONE.getStateInfo());
                modelMap.put("data", modelMapData);
            }

        } else {
            modelMap.put("errno", LoginStateEnum.EMPTY.getState());
            modelMap.put("errmsg", LoginStateEnum.EMPTY.getStateInfo());
            modelMap.put("data", modelMapData);

        }

        return modelMap;
    }



    @Override
    public Map<String, Object> userInfo(HttpServletRequest request) {
        logger.info("userInfo");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();
        String token = request.getHeader("token");
        // 根据账号  查找运输人id
        User user = userService.queryByUserId(Long.valueOf(JwtUtil.getLoginUserID(token)));
        if(user != null ){

            modelMapData.put("roleType",user.getRoleType());
            modelMapData.put("userId",user.getUserId());
            modelMapData.put("token",token);

        }else {
            return ResponedUtils.returnCode(LoginStateEnum.EMPTY.getState(), LoginStateEnum.EMPTY.getStateInfo(), new HashMap<>());
        }


        modelMap.put("errno", "0");
        modelMap.put("errmsg", "操作成功");
        modelMap.put("data", modelMapData);
        return modelMap;
    }



}
