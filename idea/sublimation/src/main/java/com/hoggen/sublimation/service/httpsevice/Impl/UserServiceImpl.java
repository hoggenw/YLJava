package com.hoggen.sublimation.service.httpsevice.Impl;

import com.fulang.knight.dao.UserDao;
import com.fulang.knight.dto.UserExecution;
import com.fulang.knight.entity.ListUserModel;
import com.fulang.knight.entity.User;
import com.fulang.knight.enums.UserStateEnum;
import com.fulang.knight.service.UserService;
import com.fulang.knight.util.MD5Util;
import com.fulang.knight.util.PageCalculatorUtil;
import com.fulang.knight.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao rUserDao;

    @Override
    public User queryByUserName(String name) {
        User user = null;
        user = rUserDao.queryByUserName(name);
        return user;
    }

    @Override
    public User queryByUserId(Long userId) {
        User user = null;
        user = rUserDao.queryByUserId(userId);
        return user;
    }

    @Override
    public UserExecution insertUser(User user) {

        if (user != null && user.getUserName() != null && user.getPassword() != null) {
            user.setCreateTime(new Date());
            user.setStatus(0);
            String randomString = StringUtil.getRandomString(8);
            String storePassString = MD5Util.MD5Encode(user.getPassword() + randomString);
            user.setPassword(storePassString);
            user.setRandomString(randomString);;
            user.setUpdateTime(new Date());
            try {
                int effectedNum = rUserDao.insertUser(user);
                if (effectedNum <= 0) {
                    logger.error("addUser fail");
                    throw new RuntimeException("添加用户失败");
                }
            } catch (Exception e) {
                logger.error("addUser fail :" + e.getMessage());
                throw new RuntimeException("添加用户失败" + e.toString());
            }
            return new UserExecution(UserStateEnum.SUCCESS, user);
        } else {
            return new UserExecution(UserStateEnum.EMPTY);
        }
    }

    @Override
    public UserExecution modifyUser(User user ,int type) {
        User userSql = queryByUserId(user.getUserId());
        if (userSql == null) {
            return new UserExecution(UserStateEnum.EMPTY);
        }

        if (type == 1) {
            if (user != null && user.getUserId() > 0  ) {
                try {
                    if(user.getPassword() != null){
                        String randomString = StringUtil.getRandomString(8);
                        String storePassString = MD5Util.MD5Encode(user.getPassword() + randomString);
                        user.setPassword(storePassString);
                        user.setRandomString(randomString);
                    }
                    int effectedNum = rUserDao.updateUser(user);
                    if (effectedNum <= 0) {
                        logger.error(" modify User Password fail");
                        throw new RuntimeException("修改用户失败");
                    }
                } catch (Exception e) {
                    logger.error("modify User Password fail :" + e.getMessage());
                    throw new RuntimeException("修改用户失败" + e.toString());
                }

                return new UserExecution(UserStateEnum.SUCCESS, user);
            } else {
                return new UserExecution(UserStateEnum.EMPTY);
            }
        } else if (type == 2) {
            if (user != null && user.getUserId() > 0 && user.getStatus() >= 0) {
                try {

                    int effectedNum = rUserDao.updateUser(user);
                    if (effectedNum <= 0) {
                        logger.error("modify User Status fail");
                        throw new RuntimeException("修改用户失败");
                    }
                } catch (Exception e) {
                    logger.error("modify User Status fail : " + e.getMessage());
                    throw new RuntimeException("修改用户失败" + e.toString());
                }

                return new UserExecution(UserStateEnum.SUCCESS, user);
            } else {
                return new UserExecution(UserStateEnum.EMPTY);
            }

        }
        return null;

    }

    @Override
    public UserExecution deleteUser(Long userId) {
        User userSql = queryByUserId(userId);
        if (userSql == null) {
            return new UserExecution(UserStateEnum.EMPTY);
        }
        try {
            int effectedNum = rUserDao.deleteUserId(userId);
            if (effectedNum <= 0) {
                throw new RuntimeException("删除用户失败");
            }
            return new UserExecution(UserStateEnum.SUCCESS);
        } catch (Exception e) {
            throw new RuntimeException("删除用户失败" + e.toString());
        }
    }

    @Override
    public UserExecution getUserList(User userCondition, int pageIndex, int pageSize) {
        if (pageSize <= 0) {
            pageSize = 20;
        }
        int rowIndex = PageCalculatorUtil.calculatorRowIndex(pageIndex, pageSize);
        List<ListUserModel> userList = rUserDao.queryUserList(userCondition, rowIndex, pageSize);
        int count = rUserDao.queryUserCount(userCondition);
        UserExecution pe = new UserExecution(UserStateEnum.SUCCESS);
        pe.setCount(count);
        pe.setUserList(userList);
        return pe;
    }
}
