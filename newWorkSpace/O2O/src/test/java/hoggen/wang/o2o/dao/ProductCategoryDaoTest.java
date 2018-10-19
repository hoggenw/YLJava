package hoggen.wang.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import hoggen.wang.dao.ProductCategoryDao;
import hoggen.wang.entity.ProductCategory;
import hoggen.wang.o2o.baseTest;

//MethodSorters.JVM代码顺讯
//MethodSorters.NAME_ASCENDING 按照方法名字 testA testB testC
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends baseTest {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Test
	@Ignore
	public void testBQueryByShopId() throws Exception {
		long shopId = 46;
		List<ProductCategory> productCategoryList = productCategoryDao.queryByShopId(shopId);
		assertEquals(1, productCategoryList.size());
		System.out.println(productCategoryList.get(0).toString());

	}

	@Test
	public void testAInsertProductCategory() throws Exception {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("商品类别5");
		productCategory.setProductCategoryDesc("测试商品类别5");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setLastEditTime(new Date());
		productCategory.setShopId(46L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("商品类别6");
		productCategory2.setProductCategoryDesc("测试商品类别6");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setLastEditTime(new Date());
		productCategory2.setShopId(46L);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		assertEquals(2, effectedNum);
	}

	@Test
	public void testCDeleteProductCategory() throws Exception {
		long shopId = 46;
		List<ProductCategory> productCategoryList = productCategoryDao.queryByShopId(shopId);

		for (ProductCategory productCategory : productCategoryList) {
			if ("商品类别5".equals(productCategory.getProductCategoryName())
					|| "商品类别6".equals(productCategory.getProductCategoryName())) {
				int effectedNum = productCategoryDao.deleteProductCategory(productCategory.getProductCategoryId(),
						shopId);
				assertEquals(1, effectedNum);
			}
		}
	}

}
