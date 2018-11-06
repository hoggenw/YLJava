package com.wang.mall.service;

import java.util.List;

import com.wang.mall.entity.Area;

public interface AreaService {

	public final static String AREALIST = "areaList";

	List<Area> getAreaList();

}
