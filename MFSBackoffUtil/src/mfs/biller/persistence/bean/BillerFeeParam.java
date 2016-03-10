package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.Date;

public class BillerFeeParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date FROM_DTTM;
	private Date TO_DTTM;
	private String BLLR_SRVC_ID;
	private String BLLR_FEE_ID;
	private Integer PAGE_NO; 
	private Integer PAGE_SIZE;

	public String toString(){
		return "BLLR_SRVC_ID:" + BLLR_SRVC_ID 
				+ "|BLLR_FEE_ID:" + BLLR_FEE_ID
				+ "|FROM_DTTM:" + FROM_DTTM
				+ "|TO_DTTM:" + TO_DTTM;
	}

	public Date getFROM_DTTM() {
		return FROM_DTTM;
	}

	public void setFROM_DTTM(Date fROM_DTTM) {
		FROM_DTTM = fROM_DTTM;
	}

	public Date getTO_DTTM() {
		return TO_DTTM;
	}

	public void setTO_DTTM(Date tO_DTTM) {
		TO_DTTM = tO_DTTM;
	}

	public String getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}

	public void setBLLR_SRVC_ID(String bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}

	public Integer getPAGE_NO() {
		return PAGE_NO;
	}

	public void setPAGE_NO(Integer pAGE_NO) {
		PAGE_NO = pAGE_NO;
	}

	public Integer getPAGE_SIZE() {
		return PAGE_SIZE;
	}

	public void setPAGE_SIZE(Integer pAGE_SIZE) {
		PAGE_SIZE = pAGE_SIZE;
	}

	public String getBLLR_FEE_ID() {
		return BLLR_FEE_ID;
	}

	public void setBLLR_FEE_ID(String bLLR_FEE_ID) {
		BLLR_FEE_ID = bLLR_FEE_ID;
	}
}
