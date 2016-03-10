package mfs.biller.persistence.bean;

import java.io.Serializable;

public class BillerBarcodeParam implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer BLLR_SRVC_ID;
	private Integer BARC_ID;
	private String ACT_FLAG;
	
	public String toString() {
		
		return "BLLR_SRVC_ID:" + BLLR_SRVC_ID +"BARC_ID:" + BARC_ID 
				+ "|ACT_FLAG:" + ACT_FLAG;
	}

	public Integer getBARC_ID() {
		return BARC_ID;
	}

	public void setBARC_ID(Integer bARC_ID) {
		BARC_ID = bARC_ID;
	}

	public String getACT_FLAG() {
		return ACT_FLAG;
	}

	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}

	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}

	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}
	
}
