package hoggen.wang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hoggen.wang.dao.ProductCategoryDao;
import hoggen.wang.dao.ProductDao;
import hoggen.wang.dto.ProductCategoryExecution;
import hoggen.wang.entity.ProductCategory;
import hoggen.wang.enums.ProductCategoryStateEnum;
import hoggen.wang.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;

	@Override
	public List<ProductCategory> getByShopId(long shopId) {
		return productCategoryDao.queryByShopId(shopId);
	}

	@Override
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws RuntimeException {
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if (effectedNum < 0) {
					throw new RuntimeException("店铺类别创建失败");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS, productCategoryList);
				}

			} catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException("batchAddProductCategory error");
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws RuntimeException {

		try {
			int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
			if (effectedNum <= 0) {
				throw new RuntimeException("商品类别删除失败");
			}

		} catch (Exception e) {

			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}

		try {
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if (effectedNum <= 0) {
				throw new RuntimeException("店铺类别删除失败");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}

		} catch (Exception e) {
			throw new RuntimeException("deleteProductCategory error: " + e.getMessage());
		}

	}

}
