package hoggen.wang.service;

import java.util.List;

import hoggen.wang.entity.Area;

public interface AreaService {

	public final static String AreaLIST = "areaList";

	List<Area> getAreaList();

}
