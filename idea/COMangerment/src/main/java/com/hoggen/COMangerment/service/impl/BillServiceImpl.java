package com.hoggen.COMangerment.service.impl;

import com.hoggen.COMangerment.dao.BillDao;
import com.hoggen.COMangerment.dao.CashbackDao;
import com.hoggen.COMangerment.dao.SysSettingDao;
import com.hoggen.COMangerment.dto.BillExecution;
import com.hoggen.COMangerment.dto.UserExecution;
import com.hoggen.COMangerment.entity.Bill;
import com.hoggen.COMangerment.entity.Cashback;
import com.hoggen.COMangerment.entity.User;
import com.hoggen.COMangerment.enums.UserStateEnum;
import com.hoggen.COMangerment.service.BillService;
import com.hoggen.COMangerment.service.CashbackService;
import com.hoggen.COMangerment.service.UserService;
import com.hoggen.COMangerment.util.PageCalculatorUtil;
import com.hoggen.COMangerment.util.RetrunDataStructModelUtil;
import com.hoggen.COMangerment.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;
    @Autowired
    private UserService userService;

    @Autowired
    private CashbackDao  cashbackDao;

    @Autowired
    private CashbackService cashbackService;

    @Autowired
    private SysSettingDao sysSettingDao;

    @Override
    public int insertBill(Bill bill) {
        int effect = -1;
        bill.setOperation(0);
        effect = billDao.insertBill(bill);
        System.out.println("订单id 为： " + bill.getBillsId());

        //修改用户积分
        User updateUser = new User();
        updateUser.setUserId(bill.getUserId());
        User tempUser = userService.queryByUserId(bill.getUserId());
        int userIntegral = tempUser.getIntegral();
        int addIntegral = userIntegral + bill.getIntegral();
        updateUser.setIntegral(addIntegral);
        int result = userService.updateUser(updateUser);

        if (result < 0){
            return  -1;
        }

        //是否需要订单返现记录
        if (tempUser.getpId() > 0) {
            Cashback cashback = new Cashback();
            cashback.setpId(tempUser.getpId());
            cashback.setBillId(bill.getBillsId());
            cashback.setUserId(bill.getUserId());
            cashback.setType(0);

            Integer percent = 0;

            List<?> model =  sysSettingDao.find(100001012L);

            Map<String, Object> judgeData = new HashMap<String, Object>();
            if (model.get(0) != null ){
                judgeData = (Map<String, Object>) model.get(0);
            }

            Integer first_time_limit = 0;
            if (judgeData.get("first_time_limit") != null){
                first_time_limit =  Integer.valueOf(String.valueOf(judgeData.get("first_time_limit"))) ;

            }

            Integer first_percent = 0;
            if (judgeData.get("first_percent") != null){
                first_percent =  Integer.valueOf(String.valueOf(judgeData.get("first_percent"))) ;

            }

            Date creatTime = tempUser.getCreateTime();

            if ((new Date()).before(StringUtil.getDay(creatTime, first_time_limit))){
                percent = first_percent;
            }else {
                Integer second_time_limit = 0;
                if (judgeData.get("second_time_limit") != null){
                    second_time_limit =  Integer.valueOf(String.valueOf(judgeData.get("second_time_limit"))) ;

                }
                Integer second_percent = 0;
                if (judgeData.get("second_percent") != null){
                    second_percent =  Integer.valueOf(String.valueOf(judgeData.get("second_percent"))) ;

                }


                if ((new Date()).before(StringUtil.getDay(creatTime, second_time_limit))){
                    percent = second_percent;

                }else  {
                    Integer third_time_limit = 0;
                    if (judgeData.get("third_time_limit") != null){
                        third_time_limit =  Integer.valueOf(String.valueOf(judgeData.get("third_time_limit"))) ;

                    }
                    Integer third_percent = 0;
                    if (judgeData.get("first_percent") != null){
                        third_percent =  Integer.valueOf(String.valueOf(judgeData.get("third_percent"))) ;

                    }

                    if ((new Date()).before(StringUtil.getDay(creatTime, third_time_limit))){
                        percent = third_percent;

                    }else {
                        percent = 0;
                    }


                }


            }
            if (percent == 0){
               // '订单状态：0，为返现；1，已返现，2，该订单已过返现时间，3，该订单返现作废，4其他',
                cashback.setType(2);
            }

            cashback.setPercent(percent);
            cashback.setCreateTime(new Date());
            int backResult = cashbackService.insertCashback(cashback);




            if (backResult < 0){
                    return  -1;
            }


            long backId = cashback.getBackId();
            Bill updateBill = new Bill();
            updateBill.setBillsId(bill.getBillsId());
            updateBill.setBackId(backId);
            int updateResult =billDao.updateBill(updateBill);
            if (updateResult < 0){
                return  -1;
            }

        }
        return effect;
    }

    @Override
    public BillExecution queryBillList(Bill billCondition, int pageIndex, int pageSize, Date endTime) {
        if (pageSize <= 0) {
            pageSize = 20;
        }
        int rowIndex = PageCalculatorUtil.calculatorRowIndex(pageIndex, pageSize);
        List<Bill> billList = billDao.queryBillList(billCondition, rowIndex, pageSize,endTime);
        int count = billDao.queryBillCount(billCondition,endTime);
        BillExecution pe = new BillExecution(UserStateEnum.SUCCESS);
        pe.setCount(count);
        pe.setBillList(billList);
        return pe;
    }

    @Override
    public int queryBillCount(Bill billCondition) {
        return 0;
    }

    @Override
    public int updateBill(Long billsId) {
        Bill  billTemp = billDao.queryBill(billsId);
        if (billTemp != null)
        {
            //修改用户积分
            User updateUser = new User();
            updateUser.setUserId(billTemp.getUserId());
            User tempUser = userService.queryByUserId(billTemp.getUserId());
            int userIntegral = tempUser.getIntegral();
            int addIntegral = userIntegral - billTemp.getIntegral();
            updateUser.setIntegral(addIntegral);


            //修改返现状态 （已返现的的订单不能废弃）
            if (billTemp.getBackId() > 0) {
                Cashback backTemp = cashbackDao.queryByCashbackId(billTemp.getBackId());
                if (backTemp.getSuccess() == 1){
                    return  -10001;
                }

                Cashback back = new Cashback();
                back.setBackId(billTemp.getBackId());
                back.setType(3);
                int result = cashbackDao.updateCashback(back);
                if (result < 0){
                    return  -1;
                }

            }
            int result = userService.updateUser(updateUser);

            if (result < 0){
                return  -1;
            }

            Bill updateBill = new Bill();
            updateBill.setBillsId(billTemp.getBillsId());
            updateBill.setOperation(1);
            int result3 = billDao.updateBill(updateBill);
            if (result3 < 0){
                return  -1;
            }
        }
        return 0;
    }



}
