package hoggen.wang.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import hoggen.wang.dto.ShopExecution;
import hoggen.wang.entity.Shop;

public interface ShopService {

	/**
	 * 创建商铺
	 * 
	 * @param Shop
	 *            shop
	 * @return ShopExecution shopExecution
	 * @throws Exception
	 */
	ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;

}
