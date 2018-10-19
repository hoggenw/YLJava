package hoggen.wang.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import hoggen.wang.dao.ShopDao;
import hoggen.wang.dto.ShopExecution;
import hoggen.wang.entity.Shop;
import hoggen.wang.enums.ShopStateEnum;
import hoggen.wang.service.ShopService;
import hoggen.wang.util.ImageUtil;
import hoggen.wang.util.PageCalculatorUtil;
import hoggen.wang.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	/**
	 * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	@Override
	@Transactional // 事物标签
	public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException {
		// TODO Auto-generated method stub
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			// 初始值复制
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				// RuntimeException才会回滚
				throw new RuntimeException("创建店铺失败");
			} else {
				// 添加成功,图片流不为空
				if (shopImg != null) {
					// 存储图片,java是值传递
					try {
						addShopImg(shop, shopImg);
						// shop.getShopImg();
					} catch (Exception e) {
						// TODO: handle exception
						throw new RuntimeException("addShopImg error:" + e.getMessage());
					}
					// 更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new RuntimeException("更新店铺图片地址失败");
					}
					return new ShopExecution(ShopStateEnum.CHECK, shop);

				}
			}

		} catch (Exception e) {
			throw new RuntimeException("addShop error:" + e.getMessage());
			// TODO: handle exception
		}

		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
		// 获取shop图片目录的相对值路径
		String deString = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, deString);
		shop.setShopImg(shopImgAddr);

	}

	@Override
	public Shop getByShopId(long shopId) {
		// TODO Auto-generated method stub
		return shopDao.queryByShopId(shopId);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException {

		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		} else {
			try {
				// 判断是否需要处理图片
				if (shopImg != null) {
					Shop tempShop = shopDao.queryByShopId(shop.getShopId());
					if (tempShop.getShopImg() != null) {
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					}
					// 存储图片,java是值传递
					try {
						addShopImg(shop, shopImg);
						// shop.getShopImg();
					} catch (Exception e) {
						// TODO: handle exception
						throw new RuntimeException("addShopImg error:" + e.getMessage());
					}

				}
				// 更新店铺信息
				shop.setLastEditTime(new Date());
				int effectedNum = shopDao.updateShop(shop);
				if (effectedNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else {
					shop = shopDao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCESS, shop);
				}
			} catch (Exception e) {
				throw new RuntimeException();
			}

		}

	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		int rowIndex = PageCalculatorUtil.calculatorRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution sExecution = new ShopExecution();
		if (shopList != null) {
			sExecution.setShopList(shopList);
			sExecution.setCount(count);
		} else {
			sExecution.setState(ShopStateEnum.INNER_ERROR.getState());
		}

		return sExecution;
	}

}
