package com.hoggen.COMangerment.dao;

import com.hoggen.COMangerment.entity.Bill;
import com.hoggen.COMangerment.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BillDao {

    int insertBill(Bill bill);

    /**
     * 查询用户列表并分页，可输入的条件有：
     *
     * @param billCondition
     * @param
     * @param pageSize
     * @return
     */
    List<Bill> queryBillList(@Param("billCondition") Bill billCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize,@Param("endTime") Date endTime);



    int updateBill(Bill billCondition);


    Bill queryBill(Long billsId);


    /**
     * 查询对应的用户总数
     *
     * @param
     * @return
     */
    int queryBillCount(@Param("billCondition") Bill billCondition,@Param("endTime") Date endTime);


}
