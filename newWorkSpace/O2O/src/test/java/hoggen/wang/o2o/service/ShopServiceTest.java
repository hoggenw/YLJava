package hoggen.wang.o2o.service;

import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hoggen.wang.dto.ShopExecution;
import hoggen.wang.entity.Area;
import hoggen.wang.entity.Shop;
import hoggen.wang.entity.ShopCategory;
import hoggen.wang.service.ShopService;

public class ShopServiceTest {
	@Autowired
	private ShopService shopService;

	@Test
	@Ignore
	public void testShopList() {
		Shop shopCondition = new Shop();
		shopCondition.setOwnerId(1l);
		ShopExecution sExecution = shopService.getShopList(shopCondition, 0, 5);
		System.out.println("ssd： " + sExecution.getShopList().size());
		System.out.println("总数： " + sExecution.getCount());
		// assertEquals(5, sExecution.getShopList().size());
		// int count = sExecution.getCount();
		// assertEquals(8, count);
	}

	@Test
	@Ignore
	public void testModifyShop() throws RuntimeException, FileNotFoundException {

		Shop shop = new Shop();// shopService.getByShopId(30l);

	}

	@Test
	@Ignore
	public void testAddShop() throws Exception {
		Shop shop = new Shop();
		shop.setOwnerId(1L);
		Area area = new Area();
		area.setAreaId(1L);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(1L);
		shop.setShopName("mytest1");
		shop.setShopDesc("mytest1");
		shop.setShopAddr("testaddr1");
		shop.setPhone("13810524526");
		shop.setShopImg("test1");
		shop.setLongitude(1D);
		shop.setLatitude(1D);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("审核中");
		shop.setArea(area);
		shop.setShopCategory(sc);

		// File shopImg = new File("/Users/wangliugen/Desktop/image/美女.jpg");
		// ShopExecution se = shopService.addShop(shop, shopImg);
		// assertEquals("mytest1", se.getShop().getShopName());
	}

}
