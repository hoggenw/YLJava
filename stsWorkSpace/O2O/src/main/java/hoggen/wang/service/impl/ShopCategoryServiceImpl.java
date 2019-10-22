package hoggen.wang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoggen.wang.dao.ShopCategoryDao;
import hoggen.wang.entity.ShopCategory;
import hoggen.wang.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

	@Autowired
	private ShopCategoryDao rShopCategoryDao;

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
		// TODO Auto-generated method stub
		return rShopCategoryDao.queryShopCategory(shopCategoryCondition);
	}

}
