package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Table(name = "BANK_MASTER")
@Cache(type = CacheType.NONE)
public class BankMasterBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2812633613456782194L;

	@Id
	private String  BANK_CODE         ;
	
	@Column(name = "BANK_NAME")
	private String BANK_NAME          ;
	
	@Column(name = "BANK_FULL_NAME_TH")
	private String  BANK_FULL_NAME_TH  ;
	
	@Column(name = "BANK_FULL_NAME_EN")
	private String BANK_FULL_NAME_EN  ;
	
	@Column(name = "ACT_FLAG")
	private String ACT_FLAG           ;
	
	@Column(name = "CRTD_BY")
	private String CRTD_BY            ;
	
	@Column(name = "CRTD_DTTM")
	@Temporal(TemporalType.TIMESTAMP)
	private  Date CRTD_DTTM          ;
	
	@Column(name = "LAST_CHNG_BY")
	private  String LAST_CHNG_BY       ;
	
	@Column(name = "LAST_CHNG_DTTM")
	@Temporal(TemporalType.TIMESTAMP)
	private  Date LAST_CHNG_DTTM     ;

	public String getBANK_CODE() {
		return BANK_CODE;
	}

	public void setBANK_CODE(String bANK_CODE) {
		BANK_CODE = bANK_CODE;
	}

	public String getBANK_NAME() {
		return BANK_NAME;
	}

	public void setBANK_NAME(String bANK_NAME) {
		BANK_NAME = bANK_NAME;
	}

	public String getBANK_FULL_NAME_TH() {
		return BANK_FULL_NAME_TH;
	}

	public void setBANK_FULL_NAME_TH(String bANK_FULL_NAME_TH) {
		BANK_FULL_NAME_TH = bANK_FULL_NAME_TH;
	}

	public String getBANK_FULL_NAME_EN() {
		return BANK_FULL_NAME_EN;
	}

	public void setBANK_FULL_NAME_EN(String bANK_FULL_NAME_EN) {
		BANK_FULL_NAME_EN = bANK_FULL_NAME_EN;
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
	
	public String toString(){
		return "BANK_CODE:" + BANK_CODE 
				+ "|BANK_NAME:" + BANK_NAME 
				+ "|BANK_FULL_NAME_TH:" + BANK_FULL_NAME_TH 
				+ "|BANK_FULL_NAME_EN:" + BANK_FULL_NAME_EN 
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|CRTD_BY:" + CRTD_BY 
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM;
	}

}
