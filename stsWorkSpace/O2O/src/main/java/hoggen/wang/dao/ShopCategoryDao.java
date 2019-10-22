package hoggen.wang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hoggen.wang.entity.ShopCategory;

public interface ShopCategoryDao {

	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);

}
