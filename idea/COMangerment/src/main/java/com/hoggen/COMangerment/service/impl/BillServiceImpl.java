package com.hoggen.COMangerment.service.impl;

import com.hoggen.COMangerment.dao.BillDao;
import com.hoggen.COMangerment.entity.Bill;
import com.hoggen.COMangerment.entity.Cashback;
import com.hoggen.COMangerment.entity.User;
import com.hoggen.COMangerment.enums.UserStateEnum;
import com.hoggen.COMangerment.service.BillService;
import com.hoggen.COMangerment.service.CashbackService;
import com.hoggen.COMangerment.service.UserService;
import com.hoggen.COMangerment.util.RetrunDataStructModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;
    @Autowired
    private UserService userService;

    @Autowired
    private CashbackService cashbackService;

    @Override
    public int insertBill(Bill bill) {
        int effect = -1;
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
            cashback.setUserId(bill.getBillsId());
            cashback.setType(0);
            cashback.setCreateTime(new Date());
            int backResult = cashbackService.insertCashback(cashback);
            if (backResult < 0){
                    return  -1;
            }
        }
        return effect;
    }
}