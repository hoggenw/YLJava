package com.hoggen.COMangerment.controller;

import com.hoggen.COMangerment.dto.UserExecution;
import com.hoggen.COMangerment.entity.User;
import com.hoggen.COMangerment.enums.UserStateEnum;
import com.hoggen.COMangerment.service.UserService;
import com.hoggen.COMangerment.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class RecommendController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/api/user/listRecommend", method = RequestMethod.POST)
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



            UserExecution pe = userService.queryRecommendUserList(userCondition, pageIndex, pageSize);

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
}
