package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;


public class BankSystemMATNDetail implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 9005750106865941230L;

	private Integer BANK_SYSM_MATN_ID;


	private Integer BANK_SRVC_ID;


	private Integer BANK_STAR_TIME;


	private Integer BANK_END_TIME;
	
	
	private String BANK_SRVC_NAME;


	private String BANK_SYSM_MATN_NAME;
	
	
	private String ACT_FLAG;



	private String CRTD_BY;


	private Date CRTD_DTTM;

	
	private String LAST_CHNG_BY;


	private Date LAST_CHNG_DTTM;

	public String toString() {
		return "BANK_SYSM_MATN_ID:" + BANK_SYSM_MATN_ID 
				+ "|BANK_SRVC_ID:"+ BANK_SRVC_ID 
				+ "|BANK_STAR_TIME:" + BANK_STAR_TIME
				+ "|BANK_END_TIME:" + BANK_END_TIME
				+ "|BANK_SRVC_NAME:" + BANK_SRVC_NAME
				+ "|BANK_SYSM_MATN_NAME:" + BANK_SYSM_MATN_NAME
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|CRTD_BY:" + CRTD_BY
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM ;
	}

	public Integer getBANK_SYSM_MATN_ID() {
		return BANK_SYSM_MATN_ID;
	}

	public void setBANK_SYSM_MATN_ID(Integer bANK_SYSM_MATN_ID) {
		BANK_SYSM_MATN_ID = bANK_SYSM_MATN_ID;
	}

	public Integer getBANK_SRVC_ID() {
		return BANK_SRVC_ID;
	}

	public void setBANK_SRVC_ID(Integer bANK_SRVC_ID) {
		BANK_SRVC_ID = bANK_SRVC_ID;
	}

	public Integer getBANK_STAR_TIME() {
		return BANK_STAR_TIME;
	}

	public void setBANK_STAR_TIME(Integer bANK_STAR_TIME) {
		BANK_STAR_TIME = bANK_STAR_TIME;
	}

	public Integer getBANK_END_TIME() {
		return BANK_END_TIME;
	}

	public void setBANK_END_TIME(Integer bANK_END_TIME) {
		BANK_END_TIME = bANK_END_TIME;
	}

	public String getBANK_SRVC_NAME() {
		return BANK_SRVC_NAME;
	}

	public void setBANK_SRVC_NAME(String bANK_SRVC_NAME) {
		BANK_SRVC_NAME = bANK_SRVC_NAME;
	}

	public String getBANK_SYSM_MATN_NAME() {
		return BANK_SYSM_MATN_NAME;
	}

	public void setBANK_SYSM_MATN_NAME(String bANK_SYSM_MATN_NAME) {
		BANK_SYSM_MATN_NAME = bANK_SYSM_MATN_NAME;
	}

	public String getACT_FLAG() {
		return ACT_FLAG;
	}

	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}

	public String getCRTD_BY() {
		return CRTD_BY;
	}

	public void setCRTD_BY(String cRTD_BY) {
		CRTD_BY = cRTD_BY;
	}

	public Date getCRTD_DTTM() {
		return CRTD_DTTM;
	}

	public void setCRTD_DTTM(Date cRTD_DTTM) {
		CRTD_DTTM = cRTD_DTTM;
	}

	public String getLAST_CHNG_BY() {
		return LAST_CHNG_BY;
	}

	public void setLAST_CHNG_BY(String lAST_CHNG_BY) {
		LAST_CHNG_BY = lAST_CHNG_BY;
	}

	public Date getLAST_CHNG_DTTM() {
		return LAST_CHNG_DTTM;
	}

	public void setLAST_CHNG_DTTM(Date lAST_CHNG_DTTM) {
		LAST_CHNG_DTTM = lAST_CHNG_DTTM;
	}

	

//	public boolean equals(Object obj) {
//		if (!(obj instanceof BillerCategory))
//			return false;
//		if (obj == this)
//			return true;
//		return this.BLLR_CATG_ID == ((BillerCategory) obj).BLLR_CATG_ID;
//	}
//
//	public int hashCode() {
//		int result = 0;
//		result = BLLR_CATG_ID;
//		return result;
//	}

}
