package com.hoggen.sublimation.service.httpsevice;

import com.fulang.knight.dto.UserExecution;
import com.fulang.knight.entity.User;

public interface UserService {
    /**
     * 通过用户名称 查询用户
     *
     * @param String name
     * @return User
     */
    User queryByUserName(String name);

    /**
     * 通过用户id 查询用户
     *
     * @param String name
     * @return User
     */
    User queryByUserId(Long userId);


    /**
     * 插入用户
     *
     */
    UserExecution insertUser(User user);


    /**
     * 修改用户信息 type 1 password 2.冻结 解冻
     */
    UserExecution modifyUser(User user, int type);


    /**
     * 删除用户
     */
    UserExecution deleteUser(Long userId);


    /**
     * @注释 分页
     */
    UserExecution getUserList(User userCondition, int pageIndex, int pageSize);
}
