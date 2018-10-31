package hoggen.wang.dao;

import java.util.List;

import hoggen.wang.entity.ProductImg;

public interface ProductImgDao {

	List<ProductImg> queryProductImgList(long productId);

	/**
	 * @注释 批量添加商品图片
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);

	int deleteProductImgByProductId(long productId);

}
