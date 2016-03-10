package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ImportExcelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3998261646939451162L;

	private String TABLE_NAME;
	private List<Map<String, Object>> listRowData;
	private List<Map<String, Object>> listRowStatus;
	private boolean isSuccess;

	public String toString() {
		return "TABLE_NAME:" + TABLE_NAME;
	}

	public String getTABLE_NAME() {
		return TABLE_NAME;
	}

	public void setTABLE_NAME(String tABLE_NAME) {
		TABLE_NAME = tABLE_NAME;
	}

	public List<Map<String, Object>> getListRowData() {
		return listRowData;
	}

	public void setListRowData(List<Map<String, Object>> listRowData) {
		this.listRowData = listRowData;
	}

	public List<Map<String, Object>> getListRowStatus() {
		return listRowStatus;
	}

	public void setListRowStatus(List<Map<String, Object>> listRowStatus) {
		this.listRowStatus = listRowStatus;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
}
