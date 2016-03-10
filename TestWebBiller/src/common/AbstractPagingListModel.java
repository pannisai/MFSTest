package common;

import mfs.biller.persistence.bean.UserInfoBean;

public abstract class AbstractPagingListModel<T> {
	private int currentPage;
	private int pageSize;
	private ListModelList<T> items;

	public AbstractPagingListModel(int currentPage, int pageSize,UserInfoBean userInfo) throws Exception{
		super();
//	    this.refreshPaging(currentPage, pageSize,userInfo);
	}
	
	public void refreshPaging(int currentPage, int pageSize,UserInfoBean userInfo) throws Exception{
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.items = getPageData(currentPage, pageSize,userInfo);
	}

	protected abstract int getTotalSize(UserInfoBean userInfo) throws Exception;

	protected abstract ListModelList<T> getPageData(int currentPage, int pageSize,UserInfoBean userInfo) throws Exception;

	

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public ListModelList<T> getItems() {
		return items;
	}

	public void setItems(ListModelList<T> items) {
		this.items = items;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
