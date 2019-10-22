package hoggen.wang.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import hoggen.wang.dto.ShopExecution;
import hoggen.wang.entity.Shop;

public interface ShopService {

	/**
	 * 根据shopCondition分页返回相应数据
	 * 
	 * @param Shop
	 *            shop
	 * @return ShopExecution shopExecution
	 * @throws Exception
	 */
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

	/**
	 * 创建商铺
	 * 
	 * @param Shop
	 *            shop
	 * @return ShopExecution shopExecution
	 * @throws Exception
	 */
	ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;

	/**
	 * 查询指定店铺信息
	 * 
	 * @param long
	 *            shopId
	 * @return Shop shop
	 */
	Shop getByShopId(long shopId);

	/**
	 * 更新店铺信息（从店家角度）
	 * 
	 * @param areaId
	 * @param shopAddr
	 * @param phone
	 * @param shopImg
	 * @param shopDesc
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;

}
