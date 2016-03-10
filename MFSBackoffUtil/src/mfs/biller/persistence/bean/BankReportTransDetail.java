package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BankReportTransDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String TRNS_ID;
	private Date TRNS_DTTM;
	private String TRNS_BANK_CODE;
	private String BANK_NAME;
	private String BANK_SRVC_NAME;
	private BigDecimal TRNS_TNSF_AMNT;
	private String TRNS_ACCT_TO;
	private String TRNS_ACCT_FROM;
	private String BANK_MSGE_CODE_MSGE_EN;
	private Date TRNS_EFCT_DTTM;
	private String TRNS_RVSL_STTS_CODE;
	private String TRNS_BANK_REF;
	private String TRNS_BANK_STTS_CODE;
	private String TRNS_BANK_STTS_MSG;
	private Date TRNS_RVSL_DTTM;
	private Date LAST_CHNG_DTTM ;
	private Integer BANK_SRVC_ID ;
	private Date TRNS_TNSF_DTTM;
	private String TRNS_ACCT_HOLD_NAME;
	private String TRNS_BANK_BRNC_CODE;
	
	public String toString() {
		return "TRNS_ID:" + TRNS_ID 
				+ "|TRNS_DTTM:" + TRNS_DTTM
				+ "|TRNS_BANK_CODE:" + TRNS_BANK_CODE
				+ "|BANK_NAME:" + BANK_NAME
				+ "|BANK_SRVC_NAME:" + BANK_SRVC_NAME
				+ "|TRNS_TNSF_AMNT:" + TRNS_TNSF_AMNT
				+ "|TRNS_ACCT_TO:" + TRNS_ACCT_TO
				+ "|TRNS_ACCT_FROM:" + TRNS_ACCT_FROM
				+ "|BANK_MSGE_CODE_MSGE_EN:" + BANK_MSGE_CODE_MSGE_EN
				+ "|TRNS_EFCT_DTTM:" + TRNS_EFCT_DTTM
				+ "|TRNS_RVSL_STTS_CODE:" + TRNS_RVSL_STTS_CODE
				+ "|TRNS_BANK_REF:" + TRNS_BANK_REF
				+ "|TRNS_BANK_STTS_CODE:" + TRNS_BANK_STTS_CODE
				+ "|TRNS_BANK_STTS_MSG:" + TRNS_BANK_STTS_MSG
				+ "|TRNS_RVSL_DTTM:" + TRNS_RVSL_DTTM
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM
				+ "|BANK_SRVC_ID:" + BANK_SRVC_ID
				+ "|TRNS_TNSF_DTTM:" + TRNS_TNSF_DTTM
				+ "|TRNS_ACCT_HOLD_NAME:" + TRNS_ACCT_HOLD_NAME
		+ "|TRNS_BANK_BRNC_CODE:" + TRNS_BANK_BRNC_CODE;
	}

	public String getTRNS_ID() {
		return TRNS_ID;
	}

	public void setTRNS_ID(String tRNS_ID) {
		TRNS_ID = tRNS_ID;
	}

	public Date getTRNS_DTTM() {
		return TRNS_DTTM;
	}

	public void setTRNS_DTTM(Date tRNS_DTTM) {
		TRNS_DTTM = tRNS_DTTM;
	}

	public String getTRNS_BANK_CODE() {
		return TRNS_BANK_CODE;
	}

	public void setTRNS_BANK_CODE(String tRNS_BANK_CODE) {
		TRNS_BANK_CODE = tRNS_BANK_CODE;
	}

	public String getBANK_NAME() {
		return BANK_NAME;
	}

	public void setBANK_NAME(String bANK_NAME) {
		BANK_NAME = bANK_NAME;
	}

	public String getBANK_SRVC_NAME() {
		return BANK_SRVC_NAME;
	}

	public void setBANK_SRVC_NAME(String bANK_SRVC_NAME) {
		BANK_SRVC_NAME = bANK_SRVC_NAME;
	}


	public String getTRNS_ACCT_TO() {
		return TRNS_ACCT_TO;
	}

	public void setTRNS_ACCT_TO(String tRNS_ACCT_TO) {
		TRNS_ACCT_TO = tRNS_ACCT_TO;
	}

	public String getTRNS_ACCT_FROM() {
		return TRNS_ACCT_FROM;
	}

	public void setTRNS_ACCT_FROM(String tRNS_ACCT_FROM) {
		TRNS_ACCT_FROM = tRNS_ACCT_FROM;
	}

	public String getBANK_MSGE_CODE_MSGE_EN() {
		return BANK_MSGE_CODE_MSGE_EN;
	}

	public void setBANK_MSGE_CODE_MSGE_EN(String bANK_MSGE_CODE_MSGE_EN) {
		BANK_MSGE_CODE_MSGE_EN = bANK_MSGE_CODE_MSGE_EN;
	}


	public String getTRNS_RVSL_STTS_CODE() {
		return TRNS_RVSL_STTS_CODE;
	}

	public void setTRNS_RVSL_STTS_CODE(String tRNS_RVSL_STTS_CODE) {
		TRNS_RVSL_STTS_CODE = tRNS_RVSL_STTS_CODE;
	}

	public String getTRNS_BANK_REF() {
		return TRNS_BANK_REF;
	}

	public void setTRNS_BANK_REF(String tRNS_BANK_REF) {
		TRNS_BANK_REF = tRNS_BANK_REF;
	}

	public String getTRNS_BANK_STTS_CODE() {
		return TRNS_BANK_STTS_CODE;
	}

	public void setTRNS_BANK_STTS_CODE(String tRNS_BANK_STTS_CODE) {
		TRNS_BANK_STTS_CODE = tRNS_BANK_STTS_CODE;
	}

	public String getTRNS_BANK_STTS_MSG() {
		return TRNS_BANK_STTS_MSG;
	}

	public void setTRNS_BANK_STTS_MSG(String tRNS_BANK_STTS_MSG) {
		TRNS_BANK_STTS_MSG = tRNS_BANK_STTS_MSG;
	}

	public Date getTRNS_RVSL_DTTM() {
		return TRNS_RVSL_DTTM;
	}

	public void setTRNS_RVSL_DTTM(Date tRNS_RVSL_DTTM) {
		TRNS_RVSL_DTTM = tRNS_RVSL_DTTM;
	}

	public Date getLAST_CHNG_DTTM() {
		return LAST_CHNG_DTTM;
	}

	public void setLAST_CHNG_DTTM(Date lAST_CHNG_DTTM) {
		LAST_CHNG_DTTM = lAST_CHNG_DTTM;
	}

	public Integer getBANK_SRVC_ID() {
		return BANK_SRVC_ID;
	}

	public void setBANK_SRVC_ID(Integer bANK_SRVC_ID) {
		BANK_SRVC_ID = bANK_SRVC_ID;
	}

	public Date getTRNS_TNSF_DTTM() {
		return TRNS_TNSF_DTTM;
	}

	public void setTRNS_TNSF_DTTM(Date tRNS_TNSF_DTTM) {
		TRNS_TNSF_DTTM = tRNS_TNSF_DTTM;
	}

	public String getTRNS_ACCT_HOLD_NAME() {
		return TRNS_ACCT_HOLD_NAME;
	}

	public void setTRNS_ACCT_HOLD_NAME(String tRNS_ACCT_HOLD_NAME) {
		TRNS_ACCT_HOLD_NAME = tRNS_ACCT_HOLD_NAME;
	}

	public String getTRNS_BANK_BRNC_CODE() {
		return TRNS_BANK_BRNC_CODE;
	}

	public void setTRNS_BANK_BRNC_CODE(String tRNS_BANK_BRNC_CODE) {
		TRNS_BANK_BRNC_CODE = tRNS_BANK_BRNC_CODE;
	}

	public BigDecimal getTRNS_TNSF_AMNT() {
		return TRNS_TNSF_AMNT;
	}

	public void setTRNS_TNSF_AMNT(BigDecimal tRNS_TNSF_AMNT) {
		TRNS_TNSF_AMNT = tRNS_TNSF_AMNT;
	}

	public Date getTRNS_EFCT_DTTM() {
		return TRNS_EFCT_DTTM;
	}

	public void setTRNS_EFCT_DTTM(Date tRNS_EFCT_DTTM) {
		TRNS_EFCT_DTTM = tRNS_EFCT_DTTM;
	}

	
}
