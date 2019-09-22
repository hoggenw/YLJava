package com.hoggen.COMangerment.controller;

import com.hoggen.COMangerment.dto.BillExecution;
import com.hoggen.COMangerment.entity.Bill;
import com.hoggen.COMangerment.entity.Cashback;
import com.hoggen.COMangerment.entity.User;
import com.hoggen.COMangerment.enums.UserStateEnum;
import com.hoggen.COMangerment.service.BillService;
import com.hoggen.COMangerment.service.CashbackService;
import com.hoggen.COMangerment.util.HttpServletRequestUtil;
import com.hoggen.COMangerment.util.RetrunDataStructModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CashbackController {


    @Autowired
    private CashbackService cashbackService;



    @RequestMapping(value = "/api/user/listBack", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> listBack(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();


        int pageIndex = HttpServletRequestUtil.getInt(request, "page_index");
        int pageSize = HttpServletRequestUtil.getInt(request, "page_size");
        if ((pageIndex > -1)) {

            Long userId = HttpServletRequestUtil.getLong(request, "userId");
            String pId = HttpServletRequestUtil.getString(request, "pId");



            Cashback cashback = new Cashback();
            if (userId > 0) {
                cashback.setUserId(userId);
            }

            if (pId != null && Long.valueOf(pId) > 0){
                cashback.setpId(Long.valueOf(pId));
            }else {
                modelMap.put("errno", UserStateEnum.INFOILLEGAl.getState());
                modelMap.put("errmsg", UserStateEnum.INFOILLEGAl.getStateInfo());
                modelMap.put("data", modelMapData);
                return modelMap;
            }

            modelMapData = cashbackService.queryBackList(cashback, pageIndex, pageSize);

            modelMap.put("errno", UserStateEnum.SUCCESS.getState());
            modelMap.put("errmsg", UserStateEnum.SUCCESS.getStateInfo());
            modelMap.put("data", modelMapData);
        } else {
            modelMap.put("errno", UserStateEnum.INFOILLEGAl.getState());
            modelMap.put("errmsg", UserStateEnum.INFOILLEGAl.getStateInfo());
            modelMap.put("data", modelMapData);
        }
        return modelMap;
    }

    @RequestMapping(value = "/api/user/backFinish", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> freezeUser(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();

        String token = request.getHeader("token");

        int suceess =  HttpServletRequestUtil.getInt(request,"suceess");
        Long back_id = HttpServletRequestUtil.getLong(request, "back_id");

        if (suceess < 0 || suceess > 1 || back_id < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INPUT_ERROR.getState(),UserStateEnum.INPUT_ERROR.getStateInfo(),modelMapData);

        }

        Cashback judge = cashbackService.queryByCashbackId(back_id);
        if (judge == null){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.EMPTY.getState(),UserStateEnum.EMPTY.getStateInfo(),modelMapData);

        }



        // System.out.println("password:" + password);
        Cashback temp = new Cashback();
        temp.setSuccess(suceess);
        temp.setBackId(back_id);
        int effect = cashbackService.updateCashback(temp);

        if (effect < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INNER_ERROR.getState(),UserStateEnum.INNER_ERROR.getStateInfo(),modelMapData);
        }
        return RetrunDataStructModelUtil.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMapData);

    }

}
