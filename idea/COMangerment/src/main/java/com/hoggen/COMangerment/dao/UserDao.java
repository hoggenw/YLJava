package com.hoggen.COMangerment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hoggen.COMangerment.entity.User;

public interface UserDao {

	/**
	 * 查询用户列表并分页，可输入的条件有：
	 *
	 * @param userCondition
	 * @param
	 * @param pageSize
	 * @return
	 */
	List<User> queryUserList(@Param("userCondition") User userCondition, @Param("rowIndex") int rowIndex,
									  @Param("pageSize") int pageSize);



	/**
	 * 查询对应的用户总数
	 *
	 * @param
	 * @return
	 */
	int queryUserCount(@Param("userCondition") User userCondition);




	/**
	 * 查询用户列表并分页，可输入的条件有：
	 *
	 * @param userCondition
	 * @param
	 * @param pageSize
	 * @return
	 */
	List<User> queryRecommendUserList(@Param("userCondition") User userCondition, @Param("rowIndex") int rowIndex,
							 @Param("pageSize") int pageSize);


	/**
	 * 查询对应的用户总数
	 *
	 * @param
	 * @return
	 */
	int queryRecommendUserCount(@Param("userCondition") User userCondition);

	/**
	 * 通过用户名称 查询用户
	 *
	 * @param String name
	 * @return User
	 */
	User queryByUserMobile(String mobile);

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
