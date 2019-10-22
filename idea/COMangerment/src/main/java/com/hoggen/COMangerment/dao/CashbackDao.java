package com.hoggen.COMangerment.dao;

import com.hoggen.COMangerment.entity.Bill;
import com.hoggen.COMangerment.entity.Cashback;
import com.hoggen.COMangerment.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CashbackDao {

    int insertCashback(Cashback cashback);

    List<?>  queryBackList(@Param("backCondition") Cashback backCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize,@Param("endTime") Date endTime);



    /**
     * 查询对应的用户总数
     *
     * @param
     * @return
     */
    int queryBackCount(@Param("backCondition") Cashback backCondition,@Param("endTime") Date endTime);


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
