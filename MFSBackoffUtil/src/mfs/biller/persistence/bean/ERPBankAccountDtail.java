package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

public class ERPBankAccountDtail implements Serializable {

	private static final long serialVersionUID = 1L;


	private Integer BLLR_SRVC_FIN_ID;
	

	private Integer BLLR_SRVC_ID;
	

	private String BLLR_ACCT_NO;
	

	private String BLLR_ACCT_NAME;
	

	private String BLLR_ACCT_BRAC_NAME;
	

	private Integer BLLR_ACCT_SEQ;
	

	private String BLLR_ACCT_PROD_TYPE;
	

    private Date BLLR_ACCT_STRT_DTTM;
	
	
    private Date BLLR_ACCT_STOP_DTTM;
	

    private String ACT_FLAG;
	

    private String CRTD_BY;
	

    private Date CRTD_DTTM;
	

    private String LAST_CHNG_BY;
	

    private Date LAST_CHNG_DTTM;
	

    private String BLLR_ACCT_BANK_CODE;
	

    private String BLLR_ERP_SUP_CODE;
	

    private String BLLR_ERP_SUP_SITE_CODE;
	

    private String BLLR_ERP_LINE_SUP_NUMB;
	
	
    private String BLLR_ERP_LINE_SUP_SITE_CODE;
    
    private String BLLR_SRVC_NAME_EN;
    
    private String BANK_NAME ;
    private String BLLR_SRVC_CODE;
	
	public String toString() {
		return "BLLR_SRVC_FIN_ID:" + BLLR_SRVC_FIN_ID 
				+ "|BLLR_SRVC_ID:" + BLLR_SRVC_ID 
		+ "|BLLR_ACCT_NO:" + BLLR_ACCT_NO 
		+ "|BLLR_ACCT_NAME:" + BLLR_ACCT_NAME 
		+ "|BLLR_ACCT_BRAC_NAME:" + BLLR_ACCT_BRAC_NAME 
		+ "|BLLR_ACCT_SEQ:" + BLLR_ACCT_SEQ 
		+ "|BLLR_ACCT_PROD_TYPE:" + BLLR_ACCT_PROD_TYPE 
		+ "|BLLR_ACCT_STRT_DTTM:" + BLLR_ACCT_STRT_DTTM 
		+ "|BLLR_ACCT_STOP_DTTM:" + BLLR_ACCT_STOP_DTTM 
		+ "|ACT_FLAG:" + ACT_FLAG 
		+ "|CRTD_BY:" + CRTD_BY 
		+ "|CRTD_DTTM:" + CRTD_DTTM 
		+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
		+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM 
		+ "|BLLR_ACCT_BANK_CODE:" + BLLR_ACCT_BANK_CODE 
		+ "|BLLR_ERP_SUP_CODE:" + BLLR_ERP_SUP_CODE 
		+ "|BLLR_ERP_SUP_SITE_CODE:" + BLLR_ERP_SUP_SITE_CODE 
		+ "|BLLR_ERP_LINE_SUP_NUMB:" + BLLR_ERP_LINE_SUP_NUMB 
		+ "|BLLR_ERP_LINE_SUP_SITE_CODE:" + BLLR_ERP_LINE_SUP_SITE_CODE 
		+ "|BLLR_SRVC_NAME_EN:" + BLLR_SRVC_NAME_EN 
		+ "|BANK_NAME:" + BANK_NAME 
		+ "|BLLR_SRVC_CODE:" + BLLR_SRVC_CODE ;
	}

	public Integer getBLLR_SRVC_FIN_ID() {
		return BLLR_SRVC_FIN_ID;
	}

	public void setBLLR_SRVC_FIN_ID(Integer bLLR_SRVC_FIN_ID) {
		BLLR_SRVC_FIN_ID = bLLR_SRVC_FIN_ID;
	}

	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}

	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}

	public String getBLLR_ACCT_NO() {
		return BLLR_ACCT_NO;
	}

	public void setBLLR_ACCT_NO(String bLLR_ACCT_NO) {
		BLLR_ACCT_NO = bLLR_ACCT_NO;
	}

	public String getBLLR_ACCT_NAME() {
		return BLLR_ACCT_NAME;
	}

	public void setBLLR_ACCT_NAME(String bLLR_ACCT_NAME) {
		BLLR_ACCT_NAME = bLLR_ACCT_NAME;
	}

	public String getBLLR_ACCT_BRAC_NAME() {
		return BLLR_ACCT_BRAC_NAME;
	}

	public void setBLLR_ACCT_BRAC_NAME(String bLLR_ACCT_BRAC_NAME) {
		BLLR_ACCT_BRAC_NAME = bLLR_ACCT_BRAC_NAME;
	}

	public Integer getBLLR_ACCT_SEQ() {
		return BLLR_ACCT_SEQ;
	}

	public void setBLLR_ACCT_SEQ(Integer bLLR_ACCT_SEQ) {
		BLLR_ACCT_SEQ = bLLR_ACCT_SEQ;
	}

	public String getBLLR_ACCT_PROD_TYPE() {
		return BLLR_ACCT_PROD_TYPE;
	}

	public void setBLLR_ACCT_PROD_TYPE(String bLLR_ACCT_PROD_TYPE) {
		BLLR_ACCT_PROD_TYPE = bLLR_ACCT_PROD_TYPE;
	}

	public Date getBLLR_ACCT_STRT_DTTM() {
		return BLLR_ACCT_STRT_DTTM;
	}

	public void setBLLR_ACCT_STRT_DTTM(Date bLLR_ACCT_STRT_DTTM) {
		BLLR_ACCT_STRT_DTTM = bLLR_ACCT_STRT_DTTM;
	}

	public Date getBLLR_ACCT_STOP_DTTM() {
		return BLLR_ACCT_STOP_DTTM;
	}

	public void setBLLR_ACCT_STOP_DTTM(Date bLLR_ACCT_STOP_DTTM) {
		BLLR_ACCT_STOP_DTTM = bLLR_ACCT_STOP_DTTM;
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

	public String getBLLR_ACCT_BANK_CODE() {
		return BLLR_ACCT_BANK_CODE;
	}

	public void setBLLR_ACCT_BANK_CODE(String bLLR_ACCT_BANK_CODE) {
		BLLR_ACCT_BANK_CODE = bLLR_ACCT_BANK_CODE;
	}

	public String getBLLR_ERP_SUP_CODE() {
		return BLLR_ERP_SUP_CODE;
	}

	public void setBLLR_ERP_SUP_CODE(String bLLR_ERP_SUP_CODE) {
		BLLR_ERP_SUP_CODE = bLLR_ERP_SUP_CODE;
	}

	public String getBLLR_ERP_SUP_SITE_CODE() {
		return BLLR_ERP_SUP_SITE_CODE;
	}

	public void setBLLR_ERP_SUP_SITE_CODE(String bLLR_ERP_SUP_SITE_CODE) {
		BLLR_ERP_SUP_SITE_CODE = bLLR_ERP_SUP_SITE_CODE;
	}

	public String getBLLR_ERP_LINE_SUP_NUMB() {
		return BLLR_ERP_LINE_SUP_NUMB;
	}

	public void setBLLR_ERP_LINE_SUP_NUMB(String bLLR_ERP_LINE_SUP_NUMB) {
		BLLR_ERP_LINE_SUP_NUMB = bLLR_ERP_LINE_SUP_NUMB;
	}

	public String getBLLR_ERP_LINE_SUP_SITE_CODE() {
		return BLLR_ERP_LINE_SUP_SITE_CODE;
	}

	public void setBLLR_ERP_LINE_SUP_SITE_CODE(String bLLR_ERP_LINE_SUP_SITE_CODE) {
		BLLR_ERP_LINE_SUP_SITE_CODE = bLLR_ERP_LINE_SUP_SITE_CODE;
	}

	public String getBLLR_SRVC_NAME_EN() {
		return BLLR_SRVC_NAME_EN;
	}

	public void setBLLR_SRVC_NAME_EN(String bLLR_SRVC_NAME_EN) {
		BLLR_SRVC_NAME_EN = bLLR_SRVC_NAME_EN;
	}

	public String getBANK_NAME() {
		return BANK_NAME;
	}

	public void setBANK_NAME(String bANK_NAME) {
		BANK_NAME = bANK_NAME;
	}

	public String getBLLR_SRVC_CODE() {
		return BLLR_SRVC_CODE;
	}

	public void setBLLR_SRVC_CODE(String bLLR_SRVC_CODE) {
		BLLR_SRVC_CODE = bLLR_SRVC_CODE;
	}

	
	
	
}
