package com.wang.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.mall.dao.AreaDao;
import com.wang.mall.entity.Area;
import com.wang.mall.service.AreaService;



@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao rAreaDao;

	@Override
	public List<Area> getAreaList() {
		// TODO Auto-generated method stub
		return rAreaDao.queryArea();

	}

}
