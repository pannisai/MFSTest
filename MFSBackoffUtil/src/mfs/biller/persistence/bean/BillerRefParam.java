package mfs.biller.persistence.bean;

import java.io.Serializable;

public class BillerRefParam implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer BLLR_SRVC_ID;
	private Integer BLLR_FORM_ID;
	
	public String toString() {
		return "BLLR_SRVC_ID:" + BLLR_SRVC_ID 
				+ "|BLLR_FORM_ID:" + BLLR_FORM_ID;
	}

	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}

	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}

	public Integer getBLLR_FORM_ID() {
		return BLLR_FORM_ID;
	}

	public void setBLLR_FORM_ID(Integer bLLR_FORM_ID) {
		BLLR_FORM_ID = bLLR_FORM_ID;
	}
	
}
