package com.hoggen.COMangerment.service.impl;


import com.hoggen.COMangerment.dao.CashbackDao;
import com.hoggen.COMangerment.dto.BillExecution;
import com.hoggen.COMangerment.entity.Bill;
import com.hoggen.COMangerment.entity.Cashback;
import com.hoggen.COMangerment.enums.UserStateEnum;
import com.hoggen.COMangerment.service.CashbackService;
import com.hoggen.COMangerment.util.PageCalculatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CashbackServiceImpl implements CashbackService {

    @Autowired
    private CashbackDao cashbackDao;

    @Override
    public int insertCashback(Cashback cashback) {
        int effect = 0;
        effect = cashbackDao.insertCashback(cashback);
        return effect;
    }

    @Override
    public Map<String, Object> queryBackList(Cashback backCondition, int pageIndex, int pageSize) {

        if (pageSize <= 0) {
            pageSize = 20;
        }
        int rowIndex = PageCalculatorUtil.calculatorRowIndex(pageIndex, pageSize);

        List<?> lists = cashbackDao.queryBackList(backCondition,rowIndex,pageSize);
        int count = cashbackDao.queryBackCount(backCondition);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("backlist", lists);
        result.put("count", count);

        return result;
}

    @Override
    public int queryBackCount(Cashback backCondition) {
        return 0;
    }

    @Override
    public Cashback queryByCashbackId(Long backId) {
        return cashbackDao.queryByCashbackId(backId);
    }

    @Override
    public int updateCashback(Cashback cashback) {
        return cashbackDao.updateCashback(cashback);
    }
}
