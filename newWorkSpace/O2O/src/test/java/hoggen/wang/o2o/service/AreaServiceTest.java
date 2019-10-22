package hoggen.wang.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hoggen.wang.entity.Area;
import hoggen.wang.o2o.baseTest;
import hoggen.wang.service.AreaService;

public class AreaServiceTest extends baseTest {
	@Autowired
	private AreaService areaService;

	@Test
	public void testGetAraeList() {
		List<Area> areas = areaService.getAreaList();
		assertEquals("西安", areas.get(0).getAreaName());

	}
}
