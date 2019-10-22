package hoggen.wang.O2O.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hoggen.wang.entity.Area;
import hoggen.wang.service.AreaService;
import hoggen.wang.service.CacheService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceTest {
	@Autowired
	private AreaService areaService;

	@Autowired
	private CacheService cacheService;

	@Test
	@Ignore
	public void testGetAraeList() {
		List<Area> areas = areaService.getAreaList();
		assertEquals("西安", areas.get(0).getAreaName());
		cacheService.removeFromCache(areaService.AreaLIST);

	}
}
