package com.hoggen.COMangerment.service;

import com.hoggen.COMangerment.dto.UserExecution;
import com.hoggen.COMangerment.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface UserService {



	/**
	 * @注释 分页
	 */
	UserExecution getUserList(User userCondition, int pageIndex, int pageSize);

	/**
	 * 查询对应的用户总数
	 *
	 * @param
	 * @return
	 */
	int queryUserCount(@Param("userCondition") User userCondition);

	/**
	 * 通过用户电话 查询用户
	 *
	 * @param
	 * @return User
	 */
	User queryByUserMobile(String mobile);

	/**
	 * 通过用户id 查询用户
	 *
	 * @param
	 * @return User
	 */
	User queryByUserId(Long userId);

	/**
	 * 插入用户
	 *
	 */
	int insertUser(User user);

	/**
	 * 更新用户
	 *
	 */
	int updateUser(User user);

	/**
	 * 更新用户
	 *
	 */
	int deleteUserId(Long userId);

}
