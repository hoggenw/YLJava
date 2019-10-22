package com.hoggen.COMangerment.service;

import com.hoggen.COMangerment.entity.Cashback;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CashbackService {

    int insertCashback(Cashback cashback);


    Map<String, Object> queryBackList(Cashback backCondition, int pageIndex, int pageSize, Date endTime);



    /**
     * 查询对应的用户总数
     *
     * @param
     * @return
     */
    int queryBackCount( Cashback backCondition);


    /**
     * 通过用户id 查询用户
     *
     * @param String name
     * @return User
     */
    Cashback queryByCashbackId(Long backId);


    /**
     * 更新用户
     *
     */
    int updateCashback(Cashback cashback);
}
