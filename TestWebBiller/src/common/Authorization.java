package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mfs.biller.util.AppUtil;
import exception.BillerManageWebException;

public class Authorization implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 350065367159671594L;
	private String username;
	private String refId;
	private String menuId;
	private String clientIP;
	private boolean search;
	private boolean insert;
	private boolean update;
	private boolean delete;
	private boolean export;
	
	private String serviceCode;
	private String accessTextFileFlag;
	private String accessReportFileFlag;
	private String branch;
		
	private List<Integer> serviceIds;

	public void setPermission(String permStr) {
		try {
			if (AppUtil.isEmpty(permStr)) {
				return;
			}
			String[] permissions = permStr.split(",");
			for (String perm : permissions) {
				if (AppUtil.trim(perm).equals("1000")) {
					setSearch(true);
				} else if (AppUtil.trim(perm).equals("2000")) {
					setInsert(true);
				} else if (AppUtil.trim(perm).equals("3000")) {
					setUpdate(true);
				} else if (AppUtil.trim(perm).equals("4000")) {
					setDelete(true);
				} else if (AppUtil.trim(perm).equals("6100")) {
					setExport(true);
				}
			}
		} catch (Exception ex) {
			throw new BillerManageWebException("Set permission fail" + ex);
		}

	}

	public void setServiceIds(String serviceIdStr) {
		try {
			if (AppUtil.isEmpty(serviceIdStr)) {
				return;
			}
			String[] serviceIdStrs = serviceIdStr.split(",");
			serviceIds = new ArrayList<Integer>();
			if (serviceIdStrs != null) {
				for (String serviceId : serviceIdStrs) {
					try {
						serviceIds.add(Integer.parseInt(serviceId));
					} catch (NumberFormatException nfe) {

					}
				}
			}
						
		} catch (Exception ex) {
			throw new BillerManageWebException("Set ServiceId fail" + ex);
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public boolean isInsert() {
		return insert;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public List<Integer> getServiceIds() {
		return serviceIds;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getAccessTextFileFlag() {
		return accessTextFileFlag;
	}

	public void setAccessTextFileFlag(String accessTextFileFlag) {
		this.accessTextFileFlag = accessTextFileFlag;
	}

	public String getAccessReportFileFlag() {
		return accessReportFileFlag;
	}

	public void setAccessReportFileFlag(String accessReportFileFlag) {
		this.accessReportFileFlag = accessReportFileFlag;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public boolean isExport() {
		return export;
	}

	public void setExport(boolean export) {
		this.export = export;
	}

}
