package com.hoggen.COMangerment.dao;

import com.hoggen.COMangerment.entity.Admin;

public interface AdminDao {
    /**
     * 通过用户名称 查询用户
     *
     * @param
     * @return User
     */
    Admin queryByUserName(String name);

    /**
     * 通过用户id 查询用户
     *
     * @param
     * @return User
     */
    Admin queryByUserId(Long userId);

    /**
     * 更新用户
     *
     */
    int updateAdmin(Admin user);
}
