package com.wang.mall.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wang.mall.BaseTest;
import com.wang.mall.entity.Area;

public class AreaDaoTest extends BaseTest {
	@Autowired
	private AreaDao rAreaDao;

	@Test
	public void testQueryArea() {

		List<Area> areaList = rAreaDao.queryArea();
		assertEquals(2, areaList.size());
	}
}
