package com.hoggen.COMangerment.controller;


import com.hoggen.COMangerment.entity.SysSetting;
import com.hoggen.COMangerment.enums.UserStateEnum;
import com.hoggen.COMangerment.service.SysSettingService;
import com.hoggen.COMangerment.util.HttpServletRequestUtil;
import com.hoggen.COMangerment.util.RetrunDataStructModelUtil;
import com.hoggen.COMangerment.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SysSettingController {

    @Autowired
    private SysSettingService sysSettingService;


    @RequestMapping(value = "/api/admin/getConfig", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> getConfig(HttpServletRequest request) {

        Map<String, Object> modelMapData = new HashMap<String, Object>();



        List<?>   judge = sysSettingService.find();
        if (judge.get(0) == null ){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.DATA_DOES_NOT_EXIST.getState(),UserStateEnum.DATA_DOES_NOT_EXIST.getStateInfo(),modelMapData);
        }
        return RetrunDataStructModelUtil.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),judge.get(0));


    }

    @RequestMapping(value = "/api/admin/updateConfig", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> updateCupon(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();


        Integer first_time_limit = HttpServletRequestUtil.getInt(request,"first_time_limit");
        Integer second_time_limit = HttpServletRequestUtil.getInt(request,"second_time_limit");

        Integer third_time_limit = HttpServletRequestUtil.getInt(request,"third_time_limit");
        Integer first_percent = HttpServletRequestUtil.getInt(request,"first_percent");

        Integer second_percent = HttpServletRequestUtil.getInt(request,"second_percent");
        Integer third_percent = HttpServletRequestUtil.getInt(request,"third_percent");



        if (first_time_limit < 0 || second_time_limit < 0 || third_time_limit < 0  || first_percent < 0
                || second_percent < 0 || third_percent < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INFOILLEGAl.getState(),UserStateEnum.INFOILLEGAl.getStateInfo(),modelMapData);
        }

        SysSetting temp = new SysSetting();
        temp.setId(Long.valueOf(100001012L));
        temp.setFirst_time_limit(first_time_limit);
        temp.setFirst_percent(first_percent);
        temp.setSecond_time_limit(second_time_limit);
        temp.setSecond_percent(second_percent);
        temp.setThird_time_limit(third_time_limit);
        temp.setThird_percent(third_percent);

        int effect = sysSettingService.updateById(temp);

        if (effect < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INNER_ERROR.getState(),UserStateEnum.INNER_ERROR.getStateInfo(),modelMapData);

        }

        return RetrunDataStructModelUtil.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMapData);


    }
}
