package com.hoggen.COMangerment.service.impl;


import com.hoggen.COMangerment.dao.AdminDao;
import com.hoggen.COMangerment.dao.UserDao;
import com.hoggen.COMangerment.dto.UserExecution;
import com.hoggen.COMangerment.entity.User;
import com.hoggen.COMangerment.enums.UserStateEnum;
import com.hoggen.COMangerment.service.UserService;
import com.hoggen.COMangerment.util.PageCalculatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDao rUserDao;


    @Override
    public UserExecution queryRecommendUserList(User userCondition, int pageIndex, int pageSize) {
        if (pageSize <= 0) {
            pageSize = 20;
        }
        int rowIndex = PageCalculatorUtil.calculatorRowIndex(pageIndex, pageSize);
        List<User> userList = rUserDao.queryRecommendUserList(userCondition, rowIndex, pageSize);
        int count = rUserDao.queryRecommendUserCount(userCondition);
        UserExecution pe = new UserExecution(UserStateEnum.SUCCESS);
        pe.setCount(count);
        pe.setUserList(userList);
        return pe;
    }

    @Override
    public int queryRecommendUserCount(User userCondition) {
        return 0;
    }

    @Override
    public UserExecution getUserList(User userCondition, int pageIndex, int pageSize) {
        if (pageSize <= 0) {
            pageSize = 20;
        }
        int rowIndex = PageCalculatorUtil.calculatorRowIndex(pageIndex, pageSize);
        List<User> userList = rUserDao.queryUserList(userCondition, rowIndex, pageSize);
        int count = rUserDao.queryUserCount(userCondition);
        UserExecution pe = new UserExecution(UserStateEnum.SUCCESS);
        pe.setCount(count);
        pe.setUserList(userList);
        return pe;
    }

    @Override
    public int queryUserCount(User userCondition) {

        return 0;
    }

    @Override
    public User queryByUserMobile(String mobile) {
        User user = null;
        user = rUserDao.queryByUserMobile(mobile);
        return user;
    }

    @Override
    public User queryByUserId(Long userId) {
        User user = null;
        user = rUserDao.queryByUserId(userId);
        return user;
    }

    @Override
    public int insertUser(User user) {
        int effect = 0;
        effect = rUserDao.insertUser(user);
        return effect;
    }

    @Override
    public int updateUser(User user) {
        int effect = 0;
        effect = rUserDao.updateUser(user);
        return effect;
    }

    @Override
    public int deleteUserId(Long userId) {
        int effect = 0;
        effect = rUserDao.deleteUserId(userId);
        return effect;
    }
}
