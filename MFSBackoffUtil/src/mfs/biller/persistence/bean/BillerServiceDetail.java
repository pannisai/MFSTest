package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.Date;

public class BillerServiceDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer BLLR_SRVC_ID;
	private String BLLR_SRVC_NAME_EN;
	private Integer BLLR_CATG_ID;
	private String BLLR_CATG_NAME;
	private String BLLR_CATG_MENU_SEQ;
	private Integer BLLR_MSTR_ID;
	private String BLLR_CODE;
	private String BLLR_SRVC_MENU_SEQ;
	private String BLLR_SRVC_BARC_FLAG;
	private String BLLR_SRVC_GUST_MOBN_FLAG;
	private Date BLLR_SRVC_STAT_DATE;
	private Date BLLR_SRVC_EXPR_DATE;
	private String LAST_CHNG_BY;
	private Date LAST_CHNG_DTTM;
	private String BLLR_SRVC_CODE;
	private Integer BLLR_FETR_ID;
	private String BLLR_CNCL_ONLN_FLAG;
	
	public String toString() {
		return "BLLR_SRVC_ID:" + BLLR_SRVC_ID 
				+ "|BLLR_SRVC_NAME_EN:" + BLLR_SRVC_NAME_EN 
				+ "|BLLR_CATG_ID:" + BLLR_CATG_ID 
				+ "|BLLR_CATG_NAME:" + BLLR_CATG_NAME 
				+ "|BLLR_CATG_MENU_SEQ:" + BLLR_CATG_MENU_SEQ 
				+ "|BLLR_MSTR_ID:" + BLLR_MSTR_ID 
				+ "|BLLR_CODE:" + BLLR_CODE 
				+ "|BLLR_SRVC_MENU_SEQ:" + BLLR_SRVC_MENU_SEQ 
				+ "|BLLR_SRVC_BARC_FLAG:" + BLLR_SRVC_BARC_FLAG 
				+ "|BLLR_SRVC_GUST_MOBN_FLAG:" + BLLR_SRVC_GUST_MOBN_FLAG 
				+ "|BLLR_SRVC_STAT_DATE:" + BLLR_SRVC_STAT_DATE 
				+ "|BLLR_SRVC_EXPR_DATE:" + BLLR_SRVC_EXPR_DATE 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|BLLR_SRVC_CODE:" + BLLR_SRVC_CODE 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM 
				+ "|BLLR_FETR_ID:" + BLLR_FETR_ID 
				+ "|BLLR_CNCL_ONLN_FLAG:" + BLLR_CNCL_ONLN_FLAG;
	}
	
	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}
	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}
	public String getBLLR_SRVC_NAME_EN() {
		return BLLR_SRVC_NAME_EN;
	}
	public void setBLLR_SRVC_NAME_EN(String bLLR_SRVC_NAME_EN) {
		BLLR_SRVC_NAME_EN = bLLR_SRVC_NAME_EN;
	}
	public Integer getBLLR_CATG_ID() {
		return BLLR_CATG_ID;
	}
	public void setBLLR_CATG_ID(Integer bLLR_CATG_ID) {
		BLLR_CATG_ID = bLLR_CATG_ID;
	}
	public String getBLLR_CATG_NAME() {
		return BLLR_CATG_NAME;
	}
	public void setBLLR_CATG_NAME(String bLLR_CATG_NAME) {
		BLLR_CATG_NAME = bLLR_CATG_NAME;
	}
	public String getBLLR_CATG_MENU_SEQ() {
		return BLLR_CATG_MENU_SEQ;
	}
	public void setBLLR_CATG_MENU_SEQ(String bLLR_CATG_MENU_SEQ) {
		BLLR_CATG_MENU_SEQ = bLLR_CATG_MENU_SEQ;
	}
	public Integer getBLLR_MSTR_ID() {
		return BLLR_MSTR_ID;
	}
	public void setBLLR_MSTR_ID(Integer bLLR_MSTR_ID) {
		BLLR_MSTR_ID = bLLR_MSTR_ID;
	}
	public String getBLLR_CODE() {
		return BLLR_CODE;
	}
	public void setBLLR_CODE(String bLLR_CODE) {
		BLLR_CODE = bLLR_CODE;
	}
	public String getBLLR_SRVC_MENU_SEQ() {
		return BLLR_SRVC_MENU_SEQ;
	}
	public void setBLLR_SRVC_MENU_SEQ(String bLLR_SRVC_MENU_SEQ) {
		BLLR_SRVC_MENU_SEQ = bLLR_SRVC_MENU_SEQ;
	}
	public String getBLLR_SRVC_BARC_FLAG() {
		return BLLR_SRVC_BARC_FLAG;
	}
	public void setBLLR_SRVC_BARC_FLAG(String bLLR_SRVC_BARC_FLAG) {
		BLLR_SRVC_BARC_FLAG = bLLR_SRVC_BARC_FLAG;
	}
	public String getBLLR_SRVC_GUST_MOBN_FLAG() {
		return BLLR_SRVC_GUST_MOBN_FLAG;
	}
	public void setBLLR_SRVC_GUST_MOBN_FLAG(String bLLR_SRVC_GUST_MOBN_FLAG) {
		BLLR_SRVC_GUST_MOBN_FLAG = bLLR_SRVC_GUST_MOBN_FLAG;
	}
	public Date getBLLR_SRVC_STAT_DATE() {
		return BLLR_SRVC_STAT_DATE;
	}
	public void setBLLR_SRVC_STAT_DATE(Date bLLR_SRVC_STAT_DATE) {
		BLLR_SRVC_STAT_DATE = bLLR_SRVC_STAT_DATE;
	}
	public Date getBLLR_SRVC_EXPR_DATE() {
		return BLLR_SRVC_EXPR_DATE;
	}
	public void setBLLR_SRVC_EXPR_DATE(Date bLLR_SRVC_EXPR_DATE) {
		BLLR_SRVC_EXPR_DATE = bLLR_SRVC_EXPR_DATE;
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

	public String getBLLR_SRVC_CODE() {
		return BLLR_SRVC_CODE;
	}

	public void setBLLR_SRVC_CODE(String bLLR_SRVC_CODE) {
		BLLR_SRVC_CODE = bLLR_SRVC_CODE;
	}

	public Integer getBLLR_FETR_ID() {
		return BLLR_FETR_ID;
	}

	public void setBLLR_FETR_ID(Integer bLLR_FETR_ID) {
		BLLR_FETR_ID = bLLR_FETR_ID;
	}

	public String getBLLR_CNCL_ONLN_FLAG() {
		return BLLR_CNCL_ONLN_FLAG;
	}

	public void setBLLR_CNCL_ONLN_FLAG(String bLLR_CNCL_ONLN_FLAG) {
		BLLR_CNCL_ONLN_FLAG = bLLR_CNCL_ONLN_FLAG;
	}
	

}
