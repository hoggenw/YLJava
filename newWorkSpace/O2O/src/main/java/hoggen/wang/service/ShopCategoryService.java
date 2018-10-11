package hoggen.wang.service;

import java.util.List;

import hoggen.wang.entity.ShopCategory;

public interface ShopCategoryService {
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);

}
