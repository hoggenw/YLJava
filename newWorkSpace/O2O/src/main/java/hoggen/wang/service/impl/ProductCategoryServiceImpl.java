package hoggen.wang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoggen.wang.dao.ProductCategoryDao;
import hoggen.wang.entity.ProductCategory;
import hoggen.wang.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	// @Autowired
	// private ProductDao productDao;

	@Override
	public List<ProductCategory> getByShopId(long shopId) {
		return productCategoryDao.queryByShopId(shopId);
	}

}
