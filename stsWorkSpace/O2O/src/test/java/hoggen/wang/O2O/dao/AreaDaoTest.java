package hoggen.wang.O2O.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hoggen.wang.dao.AreaDao;
import hoggen.wang.entity.Area;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
	@Autowired
	private AreaDao rAreaDao;

	@Test
	public void testQueryArea() {
		List<Area> areaList = rAreaDao.queryArea();
		assertEquals(2, areaList.size());
	}
}
