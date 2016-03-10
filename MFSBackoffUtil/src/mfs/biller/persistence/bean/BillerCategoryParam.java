package mfs.biller.persistence.bean;

import java.io.Serializable;

public class BillerCategoryParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String BLLR_CATG_ID;
	private String ACT_FLAG;

	public String toString(){
		return "BLLR_CATG_ID:" + BLLR_CATG_ID 
				+ "|ACT_FLAG:" + ACT_FLAG;
	}

	public String getBLLR_CATG_ID() {
		return BLLR_CATG_ID;
	}

	public void setBLLR_CATG_ID(String bLLR_CATG_ID) {
		BLLR_CATG_ID = bLLR_CATG_ID;
	}

	public String getACT_FLAG() {
		return ACT_FLAG;
	}

	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}
	
}
