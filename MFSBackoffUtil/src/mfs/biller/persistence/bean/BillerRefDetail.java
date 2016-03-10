package mfs.biller.persistence.bean;

import java.io.Serializable;

public class BillerRefDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer REF_ID;
	private String REF_LABL_TH;
	private String REF_LABL_EN;
	private Integer BLLR_FORM_ID;
	private String BLLR_FORM_NAME;
	
	public String toString() {
		return "REF_ID:" + REF_ID 
				+ "|REF_LABL_TH:" + REF_LABL_TH 
				+ "|REF_LABL_EN:" + REF_LABL_EN 
				+ "|BLLR_FORM_ID:" + BLLR_FORM_ID 
				+ "|BLLR_FORM_NAME:" + BLLR_FORM_NAME;
	}

	public Integer getREF_ID() {
		return REF_ID;
	}

	public void setREF_ID(Integer rEF_ID) {
		REF_ID = rEF_ID;
	}

	public String getREF_LABL_TH() {
		return REF_LABL_TH;
	}

	public void setREF_LABL_TH(String rEF_LABL_TH) {
		REF_LABL_TH = rEF_LABL_TH;
	}

	public String getREF_LABL_EN() {
		return REF_LABL_EN;
	}

	public void setREF_LABL_EN(String rEF_LABL_EN) {
		REF_LABL_EN = rEF_LABL_EN;
	}

	public Integer getBLLR_FORM_ID() {
		return BLLR_FORM_ID;
	}

	public void setBLLR_FORM_ID(Integer bLLR_FORM_ID) {
		BLLR_FORM_ID = bLLR_FORM_ID;
	}

	public String getBLLR_FORM_NAME() {
		return BLLR_FORM_NAME;
	}

	public void setBLLR_FORM_NAME(String bLLR_FORM_NAME) {
		BLLR_FORM_NAME = bLLR_FORM_NAME;
	}
	
}
