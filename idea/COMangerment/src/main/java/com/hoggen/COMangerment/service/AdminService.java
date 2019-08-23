package com.hoggen.COMangerment.service;

import com.hoggen.COMangerment.dto.AdminExecution;
import com.hoggen.COMangerment.entity.Admin;

public interface AdminService {


    /**
     * 通过用户名称 查询用户
     *
     * @param String name
     * @return User
     */
    Admin queryAdminByName(String name);

    /**
     * 通过用户id 查询用户
     *
     * @param String name
     * @return User
     */
    Admin queryAdminById(Long userId);

    /**
     * 修改用户信息 type 1 password 2.冻结 解冻
     */
    int modifyAdmin(Admin user);
}
