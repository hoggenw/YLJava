package com.hoggen.sublimation.util;


import com.hoggen.sublimation.dao.TableDealDao;
import com.hoggen.sublimation.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Configuration
@Component // 此注解必加
@EnableScheduling // 此注解必加
@Slf4j
public class YLSchedutask {


    @Autowired
    private TableDealDao tableDealDao;

    public void beginCreateTable() {

        log.info("time " + StringUtil.dateToStrYMD(getFirstWeakDay(getTomorrow(new Date()))));
        String timeFail = StringUtil.dateToStrYMD(getFirstWeakDay(getTomorrow(new Date())));
        tableDealDao.createNewTable("friendship_apply_" +timeFail);


    }



    Date getFirstWeakDay(Date date) {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        ca.setTime(date);

        ca.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date lastDay = ca.getTime(); // 结果
        return lastDay;
    }

    /**
     * @Param 周六凌晨触发，新建周日表
     * @Author:hoggen
     * @Date:17:28 2019-11-25
     */
    Date getTomorrow(Date date) {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        ca.setTime(date);
        ;// 月份是从0开始的，
        ca.add(Calendar.DAY_OF_YEAR, + 1); // 日期加1
        Date lastDay = ca.getTime(); // 结果
        log.info("明天日期：" + StringUtil.dateToStrYMD(lastDay));
        return lastDay;
    }
}
