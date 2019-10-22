package hoggen.wang.dao;

import java.util.List;

import hoggen.wang.entity.Area;

public interface AreaDao {
	/**
	 * @注释 列出区域列表
	 * @return areaList
	 */
	List<Area> queryArea();

}
