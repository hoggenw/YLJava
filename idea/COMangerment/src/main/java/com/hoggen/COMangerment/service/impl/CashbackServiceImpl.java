package com.hoggen.COMangerment.service.impl;


import com.hoggen.COMangerment.dao.CashbackDao;
import com.hoggen.COMangerment.entity.Cashback;
import com.hoggen.COMangerment.service.CashbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
