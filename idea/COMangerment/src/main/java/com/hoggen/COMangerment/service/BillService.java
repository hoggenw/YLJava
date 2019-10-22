package com.hoggen.COMangerment.service;

import com.hoggen.COMangerment.dto.BillExecution;
import com.hoggen.COMangerment.entity.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BillService {

    int insertBill(Bill bill);

    BillExecution queryBillList(Bill billCondition, int pageIndex, int pageSize, Date endTime);

    /**
     * 查询对应的用户总数
     *
     * @param
     * @return
     */
    int queryBillCount(Bill billCondition);


    int updateBill(Long billsId);
}
