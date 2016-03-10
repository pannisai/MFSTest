package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.Date;

public class ReportTransParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Date FROM_TRNS_DTTM;
	private Date TO_TRNS_DTTM;
	private String TRNS_SRVC_CODE;
	private String TRNS_SRCE_CHNL_CODE;
	private String TRNS_ID;
	private String TRNS_FUNC_CODE;
	private String TRNS_STTS_CODE;
	private String TRNS_PYMT_STTS_CODE;
	private Integer PAGE_NO; 
	private Integer PAGE_SIZE;
	
	public String toString() {
		return "FROM_TRNS_DTTM:" + FROM_TRNS_DTTM 
				+ "|TO_TRNS_DTTM:" + TO_TRNS_DTTM
				+ "|TRNS_SRVC_CODE:" + TRNS_SRVC_CODE
				+ "|TRNS_SRCE_CHNL_CODE:" + TRNS_SRCE_CHNL_CODE
				+ "|TRNS_ID:" + TRNS_ID
				+ "|TRNS_FUNC_CODE:" + TRNS_FUNC_CODE
				+ "|TRNS_STTS_CODE:" + TRNS_STTS_CODE
				+ "|TRNS_PYMT_STTS_CODE:" + TRNS_PYMT_STTS_CODE
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

	public String getTRNS_SRVC_CODE() {
		return TRNS_SRVC_CODE;
	}

	public void setTRNS_SRVC_CODE(String tRNS_SRVC_CODE) {
		TRNS_SRVC_CODE = tRNS_SRVC_CODE;
	}

	public String getTRNS_SRCE_CHNL_CODE() {
		return TRNS_SRCE_CHNL_CODE;
	}

	public void setTRNS_SRCE_CHNL_CODE(String tRNS_SRCE_CHNL_CODE) {
		TRNS_SRCE_CHNL_CODE = tRNS_SRCE_CHNL_CODE;
	}

	public String getTRNS_ID() {
		return TRNS_ID;
	}

	public void setTRNS_ID(String tRNS_ID) {
		TRNS_ID = tRNS_ID;
	}

	public String getTRNS_FUNC_CODE() {
		return TRNS_FUNC_CODE;
	}

	public void setTRNS_FUNC_CODE(String tRNS_FUNC_CODE) {
		TRNS_FUNC_CODE = tRNS_FUNC_CODE;
	}

	public String getTRNS_STTS_CODE() {
		return TRNS_STTS_CODE;
	}

	public void setTRNS_STTS_CODE(String tRNS_STTS_CODE) {
		TRNS_STTS_CODE = tRNS_STTS_CODE;
	}

	public String getTRNS_PYMT_STTS_CODE() {
		return TRNS_PYMT_STTS_CODE;
	}

	public void setTRNS_PYMT_STTS_CODE(String tRNS_PYMT_STTS_CODE) {
		TRNS_PYMT_STTS_CODE = tRNS_PYMT_STTS_CODE;
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
