package hoggenwang;

import java.util.List;

public class pageBean {

	private List<Student> data;// 当前页的数据
	private Integer firstPage;// 首页
	private Integer prePage;// 上一页
	private Integer nextPage;// 下一页
	private Integer totalPage;// 末页、总页数
	private Integer currentPage;// 当前页
	private Integer totalCount;// 总记录数
	private Integer pageSize;// 每页显示的记录数

	public pageBean() {
		// TODO Auto-generated constructor stub
	}

	public List<Student> getData() {
		return data;
	}

	public void setData(List<Student> data) {
		this.data = data;
	}

	public int getFirstPage() {
		return 1;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getPrePage() {
		return this.getCurrentPage() == this.getFirstPage() ? 1 : this.getCurrentPage() - 1;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return this.getCurrentPage() == this.getTotalPage() ? this.getTotalPage() : this.getCurrentPage() + 1;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalPage() {
		return this.getTotalCount() % this.getPageSize() == 0 ? this.getTotalCount() / this.getPageSize()
				: this.getTotalCount() / this.getPageSize() + 1;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
