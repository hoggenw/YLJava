package com.hoggen.sublimation.service.httpsevice.Impl;


import com.fulang.knight.dao.RubbishCarDao;
import com.fulang.knight.dao.RubbishCompanyDao;
import com.fulang.knight.dao.UserDao;
import com.fulang.knight.entity.*;
import com.fulang.knight.enums.LoginStateEnum;
import com.fulang.knight.service.*;
import com.fulang.knight.util.HttpServletRequestUtil;
import com.fulang.knight.util.JwtUtil;
import com.fulang.knight.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RubbishCarDao rubbishCarDao;

    @Autowired
    private RubbishCompanyDao rubbishCompanyDao;

    @Autowired
    private SysAreaService sysAreaService;

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
                user.setToken(token);
                user.setLastLoginTime(new Date());
                user.setTokenTime(new Date());
                userDao.updateUserToken(user);

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
    public Map<String, Object> userInfo(HttpServletRequest request) {
        logger.info("userInfo");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();
        String token = request.getHeader("token");
        // 根据账号  查找运输人id
        User user = userService.queryByUserId(Long.valueOf(JwtUtil.getLoginUserID(token)));
        if(user != null ){
            if( user.getDriverId() != null){
                // 返回运输人信息
                //车
                Map<String, Object> carParameterMap = new HashMap<String, Object>();
                carParameterMap.put("fields", "*");
                carParameterMap.put("driverId", user.getDriverId());
                carParameterMap.put("sortord", "id asc");
                carParameterMap.put("firstIndex", 0);
                carParameterMap.put("limit", 0);
                List<RubbishCar> rubbishCars = rubbishCarDao.getList(carParameterMap);
                modelMapData.put("rubbishCars",rubbishCars);

                //公司
                List<RubbishCompany> rubbishCompanies = rubbishCompanyDao.getCompaniesByDriverId(user.getDriverId());
                for (RubbishCompany rubbishCompany2 : rubbishCompanies) {
                    List<SysArea> areas = sysAreaService.getAreasByCode(rubbishCompany2.getAreaId(), 0, new ArrayList<SysArea>());

                    String area = areas.get(0).getName() +" "
                            + areas.get(1).getName() +" "
                            + areas.get(2).getName();
                    String address = rubbishCompany2.getAddress();
                    rubbishCompany2.setAddress(area  + " " + address);
                }
                modelMapData.put("rubbishCompanies",rubbishCompanies);
                modelMapData.put("name", user.getUserName());
                modelMapData.put("phone",user.getMobile());
            }

            modelMapData.put("roleType",user.getRoleType());
            modelMapData.put("userId",user.getUserId());
            modelMapData.put("token",token);

        }else {
            return BaseService.returnCode(LoginStateEnum.EMPTY.getState(), LoginStateEnum.EMPTY.getStateInfo(), new HashMap<>());
        }


        modelMap.put("errno", "0");
        modelMap.put("errmsg", "操作成功");
        modelMap.put("data", modelMapData);
        return modelMap;
    }



}
