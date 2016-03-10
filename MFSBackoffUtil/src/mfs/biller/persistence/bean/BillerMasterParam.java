package mfs.biller.persistence.bean;

import java.io.Serializable;

public class BillerMasterParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer BLLR_MSTR_ID;
	private String ACT_FLAG;
	
	public String toString() {
		return "BLLR_MSTR_ID:" + BLLR_MSTR_ID 
				+ "|ACT_FLAG:" + ACT_FLAG;
	}
	
	public Integer getBLLR_MSTR_ID() {
		return BLLR_MSTR_ID;
	}
	public void setBLLR_MSTR_ID(Integer bLLR_MSTR_ID) {
		BLLR_MSTR_ID = bLLR_MSTR_ID;
	}
	public String getACT_FLAG() {
		return ACT_FLAG;
	}
	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}
	
	
}
