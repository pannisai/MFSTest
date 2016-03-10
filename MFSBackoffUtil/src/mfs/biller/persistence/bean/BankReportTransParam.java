package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.Date;

public class BankReportTransParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Date FROM_TRNS_DTTM;
	private Date TO_TRNS_DTTM;
	private String BANK_SRVC_ID;
	private String TRNS_BANK_CODE;
	private String TRNS_ID;
	private String TRNS_STTS_FLAG;
	private Integer PAGE_NO; 
	private Integer PAGE_SIZE;
	
	public String toString() {
		return "FROM_TRNS_DTTM:" + FROM_TRNS_DTTM 
				+ "|TO_TRNS_DTTM:" + TO_TRNS_DTTM
				+ "|BANK_SRVC_ID:" + BANK_SRVC_ID
				+ "|TRNS_BANK_CODE:" + TRNS_BANK_CODE
				+ "|TRNS_ID:" + TRNS_ID
				+ "|TRNS_STTS_FLAG:" + TRNS_STTS_FLAG
				+ "|PAGE_NO:" + PAGE_NO
				+ "|PAGE_SIZE:" + PAGE_SIZE;
	}

	public Date getFROM_TRNS_DTTM() {
		return FROM_TRNS_DTTM;
	}

	public void setFROM_TRNS_DTTM(Date fROM_TRNS_DTTM) {
		FROM_TRNS_DTTM = fROM_TRNS_DTTM;
	}

	public Date getTO_TRNS_DTTM() {
		return TO_TRNS_DTTM;
	}

	public void setTO_TRNS_DTTM(Date tO_TRNS_DTTM) {
		TO_TRNS_DTTM = tO_TRNS_DTTM;
	}



	public String getTRNS_ID() {
		return TRNS_ID;
	}

	public void setTRNS_ID(String tRNS_ID) {
		TRNS_ID = tRNS_ID;
	}

	

	public String getBANK_SRVC_ID() {
		return BANK_SRVC_ID;
	}

	public void setBANK_SRVC_ID(String bANK_SRVC_ID) {
		BANK_SRVC_ID = bANK_SRVC_ID;
	}

	public String getTRNS_BANK_CODE() {
		return TRNS_BANK_CODE;
	}

	public void setTRNS_BANK_CODE(String tRNS_BANK_CODE) {
		TRNS_BANK_CODE = tRNS_BANK_CODE;
	}

	public String getTRNS_STTS_FLAG() {
		return TRNS_STTS_FLAG;
	}

	public void setTRNS_STTS_FLAG(String tRNS_STTS_FLAG) {
		TRNS_STTS_FLAG = tRNS_STTS_FLAG;
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

}
