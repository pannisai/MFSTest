package mfs.biller.persistence.bean;

import java.io.Serializable;

public class BillerChannelParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer BLLR_CHNL_ID;
	private String ACT_FLAG;
	
	public String toString() {
		return "BLLR_CHNL_ID:" + BLLR_CHNL_ID 
				+ "|ACT_FLAG:" + ACT_FLAG;
	}
	
	public Integer getBLLR_CHNL_ID() {
		return BLLR_CHNL_ID;
	}
	public void setBLLR_CHNL_ID(Integer bLLR_CHNL_ID) {
		BLLR_CHNL_ID = bLLR_CHNL_ID;
	}
	public String getACT_FLAG() {
		return ACT_FLAG;
	}
	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}
	
}
