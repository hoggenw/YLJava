package com.hoggen.COMangerment.controller;


import com.hoggen.COMangerment.entity.Bill;
import com.hoggen.COMangerment.entity.Cashback;
import com.hoggen.COMangerment.entity.User;
import com.hoggen.COMangerment.enums.UserStateEnum;
import com.hoggen.COMangerment.service.BillService;
import com.hoggen.COMangerment.service.CashbackService;
import com.hoggen.COMangerment.service.IdentifyService;
import com.hoggen.COMangerment.service.UserService;
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
import java.util.Map;

@Controller
public class BillManagerController {

    @Autowired
    private BillService billService;
    @Autowired
    private UserService userService;

    @Autowired
    private CashbackService cashbackService;

    @RequestMapping(value = "/api/bill/add", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addBill(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();
        String token = request.getHeader("token");
        int type = HttpServletRequestUtil.getInt(request,"type");
        if (type < 0){
            type = 0;
        }


        Bill bill = new Bill();
        bill.setType(type);

        Long userId = HttpServletRequestUtil.getLong(request, "userId");

        if (userId < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INPUT_ERROR.getState(),UserStateEnum.INPUT_ERROR.getStateInfo(),modelMapData);

        }

        User tempUser = userService.queryByUserId(userId);

        if (tempUser == null){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.EMPTY.getState(),UserStateEnum.EMPTY.getStateInfo(),modelMapData);
        }

        bill.setUserId(userId);



        if (tempUser.getpId() > 0) {
            bill.setpId(tempUser.getpId());
        }

        int integral = HttpServletRequestUtil.getInt(request,"integral");
        if (integral < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INTEGRAL_NULL.getState(),UserStateEnum.INTEGRAL_NULL.getStateInfo(),modelMapData);

        }

        bill.setIntegral(integral);


        String remark = HttpServletRequestUtil.getString(request,"remark");
        if (StringUtil.getStringLen(remark) > 0){
            bill.setRemark(remark);
        }

        bill.setCreateTime(new Date());

        int effect = billService.insertBill(bill);

        if (effect < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INNER_ERROR.getState(),UserStateEnum.INNER_ERROR.getStateInfo(),modelMapData);
        }



        return RetrunDataStructModelUtil.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMapData);

    }
}
