package com.wang.mall.dao;

import java.util.List;

import com.wang.mall.entity.Area;


public interface AreaDao {
	/**
	 * @注释 列出区域列表
	 * @return areaList
	 */
	List<Area> queryArea();

}
