package hoggen.wang.dto;

import java.util.List;

import hoggen.wang.entity.ProductCategory;
import hoggen.wang.enums.ProductCategoryStateEnum;

public class ProductCategoryExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;
	// 获取的shop列表(查询店铺列表的时候用)
	private List<ProductCategory> productCategoryList;

	// 操作失败构造
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 操作成功狗朝气
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

}
