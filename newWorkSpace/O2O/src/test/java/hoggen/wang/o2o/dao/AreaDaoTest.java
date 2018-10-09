package hoggen.wang.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hoggen.wang.dao.AreaDao;
import hoggen.wang.entity.Area;
import hoggen.wang.o2o.baseTest;

public class AreaDaoTest extends baseTest {
	@Autowired
	private AreaDao rAreaDao;

	@Test
	public void testQueryArea() {
		List<Area> areaList = rAreaDao.queryArea();
		assertEquals(2, areaList.size());
	}
}
