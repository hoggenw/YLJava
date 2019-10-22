package hoggen.wang.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import hoggen.wang.dto.ProductExecution;
import hoggen.wang.entity.Product;

public interface ProductService {
	/**
	 * @注释 分页
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

	Product getProductById(long productId);

	/**
	 * @注释 1 thumbnail缩略图 2.productImgs 商品图片
	 */
	ProductExecution addProduct(Product product, CommonsMultipartFile thumbnail, List<CommonsMultipartFile> productImgs)
			throws RuntimeException;

	/**
	 * @注释
	 * 
	 */
	ProductExecution modifyProduct(Product product, CommonsMultipartFile thumbnail,
			List<CommonsMultipartFile> productImgs) throws RuntimeException;
}
