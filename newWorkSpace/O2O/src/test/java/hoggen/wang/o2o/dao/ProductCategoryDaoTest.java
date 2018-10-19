package hoggen.wang.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hoggen.wang.dao.ProductCategoryDao;
import hoggen.wang.entity.ProductCategory;
import hoggen.wang.o2o.baseTest;

public class ProductCategoryDaoTest extends baseTest {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Test
	public void testBQueryByShopId() throws Exception {
		long shopId = 46;
		List<ProductCategory> productCategoryList = productCategoryDao.queryByShopId(shopId);
		assertEquals(1, productCategoryList.size());
		System.out.println(productCategoryList.get(0).toString());

	}

}
