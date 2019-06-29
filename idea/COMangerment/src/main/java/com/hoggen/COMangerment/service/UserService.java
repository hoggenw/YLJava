package com.hoggen.COMangerment.service;

import com.hoggen.COMangerment.dto.UserExecution;
import com.hoggen.COMangerment.entity.User;

import java.util.ArrayList;

public interface UserService {



	/**
	 * @注释 分页
	 */
	UserExecution getUserList(User userCondition, int pageIndex, int pageSize);

	/**
	 * 通过用户名称 查询用户
	 *
	 * @param String name
	 * @return User
	 */
	User queryUserByName(String name);

	/**
	 * 通过用户id 查询用户
	 *
	 * @param String name
	 * @return User
	 */
	User queryUserById(Long userId);

	/**
	 * 添加用户
	 *
	 */
	UserExecution addUser(User user);

	/**
	 * 修改用户信息 type 1 password 2.冻结 解冻
	 */
	UserExecution modifyUser(User user, int type);

	/**
	 * 删除用户
	 */
	UserExecution deleteUser(Long userId);

}
