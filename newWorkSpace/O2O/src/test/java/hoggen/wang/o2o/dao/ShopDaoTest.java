package hoggen.wang.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hoggen.wang.dao.ShopDao;
import hoggen.wang.entity.Area;
import hoggen.wang.entity.Shop;
import hoggen.wang.entity.ShopCategory;
import hoggen.wang.o2o.baseTest;

public class ShopDaoTest extends baseTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	public void testQueryShoplist() {
		Shop shopCondition = new Shop();
		shopCondition.setOwnerId(1l);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
		assertEquals(5, shopList.size());
		int count = shopDao.queryShopCount(shopCondition);
		assertEquals(8, count);
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(33l);
		shopCondition.setShopCategory(shopCategory);
		List<Shop> shopCategoryIdList = shopDao.queryShopList(shopCondition, 0, 5);
		assertEquals(4, shopCategoryIdList.size());

	}

	@Test
	@Ignore
	public void testQueryByShopId() {
		// TODO Auto-generated method stub
		long shopId = 46;
		Shop shop = shopDao.queryByShopId(shopId);
		long areaID = shop.getArea().getAreaId();
		System.out.println("areaID: " + areaID);
		System.out.println("areaName: " + shop.getArea().getAreaName());

		assertEquals(1, areaID);
	}

	@Test
	@Ignore
	public void testInsertShop() {
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
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		shop.setArea(area);
		shop.setShopCategory(sc);
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}

	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(30l);
		shop.setShopName("my test shop");
		shop.setAdvice("审核通过");
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}

	@Test
	@Ignore
	public void testEDeleteShopByName() throws Exception {
		String shopName = "mytest1";
		int effectedNum = shopDao.deleteShopByName(shopName);
		assertEquals(1, effectedNum);

	}

}
