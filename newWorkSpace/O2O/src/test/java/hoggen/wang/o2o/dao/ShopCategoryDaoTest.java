package hoggen.wang.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hoggen.wang.dao.ShopCategoryDao;
import hoggen.wang.entity.ShopCategory;
import hoggen.wang.o2o.baseTest;

public class ShopCategoryDaoTest extends baseTest {
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Test
	public void testBQueryShopCategory() throws Exception {

		ShopCategory sc = new ShopCategory();
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
		assertEquals(1, shopCategoryList.size());
		shopCategoryList = shopCategoryDao.queryShopCategory(sc);
		assertEquals(2, shopCategoryList.size());
		sc.setParentId(1L);
		shopCategoryList = shopCategoryDao.queryShopCategory(sc);
		assertEquals(2, shopCategoryList.size());
	}

}
