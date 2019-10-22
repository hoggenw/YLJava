package com.hoggen.COMangerment.controller;


import com.hoggen.COMangerment.dto.BillExecution;
import com.hoggen.COMangerment.dto.UserExecution;
import com.hoggen.COMangerment.entity.Bill;
import com.hoggen.COMangerment.entity.Cashback;
import com.hoggen.COMangerment.entity.User;
import com.hoggen.COMangerment.enums.OperatingTypeEnum;
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
        bill.setMobile(tempUser.getMobile());
        bill.setRealName(tempUser.getRealName());

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

    @RequestMapping(value = "/api/bill/discard", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> discardBill(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();
        long billId = HttpServletRequestUtil.getLong(request,"billId");
        if (billId < 0){
            return RetrunDataStructModelUtil.returnCode(UserStateEnum.INPUT_ERROR.getState(),UserStateEnum.INPUT_ERROR.getStateInfo(),modelMapData);

        }

        int result = billService.updateBill(billId);
        if (result < 0){
            if (result == -10001){
                return RetrunDataStructModelUtil.returnCode(OperatingTypeEnum.SUCE_OR.getState(),OperatingTypeEnum.SUCE_OR.getStateInfo(),modelMapData);

            }
            else{
                return RetrunDataStructModelUtil.returnCode(OperatingTypeEnum.OPERATION_ERROR.getState(),OperatingTypeEnum.OPERATION_ERROR.getStateInfo(),modelMapData);

            }

        }
        return RetrunDataStructModelUtil.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMapData);

    }




    @RequestMapping(value = "/api/user/listBills", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> listBills(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();


        int pageIndex = HttpServletRequestUtil.getInt(request, "page_index");
        int pageSize = HttpServletRequestUtil.getInt(request, "page_size");
        if ((pageIndex > -1)) {

            Long userId = HttpServletRequestUtil.getLong(request, "userId");

            String phone = HttpServletRequestUtil.getString(request, "phone");
            String realName = HttpServletRequestUtil.getString(request, "realName");
            String pId = HttpServletRequestUtil.getString(request, "pId");
            String beginTimeString = HttpServletRequestUtil.getString(request, "begin_time");
            String endTimeString = HttpServletRequestUtil.getString(request, "over_time");
            Date endTime = null;
            if (endTimeString != null) {
                endTime = StringUtil.strToDateLong(endTimeString);
            }


            Bill billCondition = new Bill();
            if (userId > 0) {
                billCondition.setUserId(userId);
            }
            if (realName != null) {
                billCondition.setRealName(realName);
            }
            if (phone != null) {
                billCondition.setMobile(phone);
            }
            if (pId != null && Long.valueOf(pId) > 0){
                billCondition.setpId(Long.valueOf(pId));
            }

            if (beginTimeString != null) {
                billCondition.setCreateTime(StringUtil.strToDateLong(beginTimeString));
            }

            BillExecution pe = billService.queryBillList(billCondition, pageIndex, pageSize,endTime);

            modelMap.put("errno", pe.getState());
            modelMap.put("errmsg", pe.getStateInfo());
            modelMapData.put("count", pe.getCount());
            modelMapData.put("bills", pe.getBillList());
            modelMap.put("data", modelMapData);
        } else {
            modelMap.put("errno", UserStateEnum.INFOILLEGAl.getState());
            modelMap.put("errmsg", UserStateEnum.INFOILLEGAl.getStateInfo());
            modelMap.put("data", modelMapData);
        }
        return modelMap;
    }
}
