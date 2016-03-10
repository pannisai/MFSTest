package mfs.biller.persistence.bean;

import java.io.Serializable;

public class BillerServiceParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer BLLR_CATG_ID;
	private Integer BLLR_MSTR_ID;
	private String BLLR_SRVC_CODE;
	
	public String toString() {
		return "BLLR_CATG_ID:" + BLLR_CATG_ID 
				+ "|BLLR_MSTR_ID:" + BLLR_MSTR_ID
				+ "|BLLR_SRVC_CODE:" + BLLR_SRVC_CODE;
	}
	
	public Integer getBLLR_CATG_ID() {
		return BLLR_CATG_ID;
	}
	public void setBLLR_CATG_ID(Integer bLLR_CATG_ID) {
		BLLR_CATG_ID = bLLR_CATG_ID;
	}
	public Integer getBLLR_MSTR_ID() {
		return BLLR_MSTR_ID;
	}
	public void setBLLR_MSTR_ID(Integer bLLR_MSTR_ID) {
		BLLR_MSTR_ID = bLLR_MSTR_ID;
	}
	public String getBLLR_SRVC_CODE() {
		return BLLR_SRVC_CODE;
	}
	public void setBLLR_SRVC_CODE(String bLLR_SRVC_CODE) {
		BLLR_SRVC_CODE = bLLR_SRVC_CODE;
	}
	
	
}
