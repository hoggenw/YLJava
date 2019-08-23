package com.hoggen.COMangerment.controller;


import com.hoggen.COMangerment.dto.UserExecution;
import com.hoggen.COMangerment.entity.User;
import com.hoggen.COMangerment.enums.UserStateEnum;
import com.hoggen.COMangerment.service.IdentifyService;
import com.hoggen.COMangerment.service.UserService;
import com.hoggen.COMangerment.util.HttpServletRequestUtil;
import com.hoggen.COMangerment.util.JwtUtil;
import com.hoggen.COMangerment.util.RetrunDataStructModelUtil;
import com.hoggen.COMangerment.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.LinkOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserManagerController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user/listUsers", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> listUsers(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();


        int pageIndex = HttpServletRequestUtil.getInt(request, "page_index");
        int pageSize = HttpServletRequestUtil.getInt(request, "page_size");
        if ((pageIndex > -1)) {

            Integer status = HttpServletRequestUtil.getInt(request, "status");

            String phone = HttpServletRequestUtil.getString(request, "phone");
            String realName = HttpServletRequestUtil.getString(request, "realName");


            User userCondition = new User();
            if (status == 0 || status == 1) {
                userCondition.setStatus(status);
            }
            if (realName != null) {
                userCondition.setRealName(realName);
            }
            if (phone != null) {
                userCondition.setMobile(phone);
            }


            UserExecution pe = userService.getUserList(userCondition, pageIndex, pageSize);

            modelMap.put("errno", pe.getState());
            modelMap.put("errmsg", pe.getStateInfo());
            modelMapData.put("count", pe.getCount());
            modelMapData.put("accounts", pe.getUserList());
            modelMap.put("data", modelMapData);
        } else {
            modelMap.put("errno", UserStateEnum.INFOILLEGAl.getState());
            modelMap.put("errmsg", UserStateEnum.INFOILLEGAl.getStateInfo());
            modelMap.put("data", modelMapData);
        }
        return modelMap;
    }



    @RequestMapping(value = "/api/user/add", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addUser(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();

        String token = request.getHeader("token");

        String pMobile = HttpServletRequestUtil.getString(request, "pMobile");
        // System.out.println("user_name:" + name);
        String mobile = HttpServletRequestUtil.getString(request, "mobile");

        String realName = HttpServletRequestUtil.getString(request, "realName");

        String remark = HttpServletRequestUtil.getString(request, "remark");

        int sex =  HttpServletRequestUtil.getInt(request,"sex");

        String birthday = HttpServletRequestUtil.getString(request, "birthday");

        String address = HttpServletRequestUtil.getString(request, "address");

        Integer  integral = HttpServletRequestUtil.getInt(request,"integral");
        if (integral < 0){
            integral =  0;
        }

        Long createBy = Long.valueOf(JwtUtil.getLoginUserId(token)) ;

        int nameLen = StringUtil.getStringLen(realName);
        int mobileLen = StringUtil.getStringLen(mobile);
//        int birthdayLen = StringUtil.getStringLen(birthday);
//        int addressLen = StringUtil.getStringLen(address);

        if (nameLen < 2 || nameLen > 60) {
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.NAMEOFFLINE.getState(),UserStateEnum.NAMEOFFLINE.getStateInfo(),modelMapData);
        }

        if (mobileLen < 3 ) {
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.MOBILEOFFLINE.getState(),UserStateEnum.MOBILEOFFLINE.getStateInfo(),modelMapData);

        }
        if (sex < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.SEXEMPTY.getState(),UserStateEnum.SEXEMPTY.getStateInfo(),modelMapData);

        }

        Long pid = Long.valueOf(0);
        if (pMobile != null && StringUtil.getStringLen(pMobile) > 0){
            User judge = userService.queryByUserMobile(pMobile);

            if (judge != null) {
                pid = judge.getUserId();
            }
        }


        // System.out.println("password:" + password);
        User user = new User();
        user.setpId(pid);
        user.setMobile(mobile);
        user.setRealName(realName);
        user.setSex(sex);
        user.setIntegral(integral);
        user.setRemark(remark);
        user.setCreateTime(new Date());
        user.setAddress(address);
        user.setBirthday(birthday);
        user.setCreateBy(createBy);
        user.setStatus(0);

        int effect = userService.insertUser(user);

        if (effect < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INNER_ERROR.getState(),UserStateEnum.INNER_ERROR.getStateInfo(),modelMapData);
        }
        return RetrunDataStructModelUtil.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMapData);

    }


    @RequestMapping(value = "/api/user/update", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> updateUser(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();

        String token = request.getHeader("token");

        Long userId = HttpServletRequestUtil.getLong(request, "userId");

        if (userId < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INPUT_ERROR.getState(),UserStateEnum.INPUT_ERROR.getStateInfo(),modelMapData);

        }

        User judge = userService.queryByUserId(userId);
        if (judge == null){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.EMPTY.getState(),UserStateEnum.EMPTY.getStateInfo(),modelMapData);

        }
        User user = new User();
        user.setUserId(userId);

        String pMobile = HttpServletRequestUtil.getString(request, "pMobile");

        if (pMobile != null && StringUtil.getStringLen(pMobile) > 0){
            User Puser = userService.queryByUserMobile(pMobile);

            if (Puser != null) {
                user.setpId(Puser.getUserId());
            }
        }
        // System.out.println("user_name:" + name);
        String mobile = HttpServletRequestUtil.getString(request, "mobile");
        int mobileLen = StringUtil.getStringLen(mobile);

        if (mobileLen > 0 ) {
           user.setMobile(mobile);
        }

        String realName = HttpServletRequestUtil.getString(request, "realName");
        int nameLen = StringUtil.getStringLen(realName);
        if (nameLen > 0) {
            user.setRealName(realName);
        }


        String remark = HttpServletRequestUtil.getString(request, "remark");

        if (StringUtil.getStringLen(remark)>0){
            user.setRemark(remark);
        }
        int sex =  HttpServletRequestUtil.getInt(request,"sex");
        if (sex == 0 || sex == 1){
            user.setSex(sex);
        }

        String birthday = HttpServletRequestUtil.getString(request, "birthday");

        if (StringUtil.getStringLen(birthday)>0){
            user.setBirthday(birthday);
        }





        String address = HttpServletRequestUtil.getString(request, "address");
        if (StringUtil.getStringLen(address)>0){
            user.setAddress(address);
        }

        Integer  integral = HttpServletRequestUtil.getInt(request,"integral");

        if (integral >= 0){
           user.setIntegral(integral);
        }
        int effect = userService.updateUser(user);
        if (effect < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INNER_ERROR.getState(),UserStateEnum.INNER_ERROR.getStateInfo(),modelMapData);
        }
        return RetrunDataStructModelUtil.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMapData);

    }

    @RequestMapping(value = "/api/user/freeze", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> freezeUser(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();

        String token = request.getHeader("token");

        int status =  HttpServletRequestUtil.getInt(request,"status");
        Long userId = HttpServletRequestUtil.getLong(request, "userId");

        if (status < 0 || userId < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INPUT_ERROR.getState(),UserStateEnum.INPUT_ERROR.getStateInfo(),modelMapData);

        }

        User judge = userService.queryByUserId(userId);
        if (judge == null){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.EMPTY.getState(),UserStateEnum.EMPTY.getStateInfo(),modelMapData);

        }



        // System.out.println("password:" + password);
        User user = new User();
        user.setStatus(status);
        user.setUserId(userId);
        int effect = userService.updateUser(user);

        if (effect < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INNER_ERROR.getState(),UserStateEnum.INNER_ERROR.getStateInfo(),modelMapData);
        }
        return RetrunDataStructModelUtil.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMapData);

    }

    @RequestMapping(value = "/api/user/delete", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> deleteUser(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();
        String token = request.getHeader("token");
        Long userId = HttpServletRequestUtil.getLong(request, "userId");

        if (userId < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INPUT_ERROR.getState(),UserStateEnum.INPUT_ERROR.getStateInfo(),modelMapData);

        }

        User judge = userService.queryByUserId(userId);
        if (judge == null){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.EMPTY.getState(),UserStateEnum.EMPTY.getStateInfo(),modelMapData);

        }

        int effect = userService.deleteUserId(userId);

        if (effect < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INNER_ERROR.getState(),UserStateEnum.INNER_ERROR.getStateInfo(),modelMapData);
        }
        return RetrunDataStructModelUtil.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMapData);

    }


}
