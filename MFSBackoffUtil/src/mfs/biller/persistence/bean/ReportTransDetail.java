package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReportTransDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String TRNS_ID;
	private Date TRNS_DTTM;
	private String TRNS_SRVC_CODE;
	private String TRNS_DEST_CODE;
	private String TRNS_SRCE_CHNL_CODE;
	private String TRNS_REF1;
	private String TRNS_REF2;
	private String TRNS_REF3;
	private String TRNS_REF4;
	private String TRNS_REF5;
	private String TRNS_REF6;
	private BigDecimal TRNS_PAID_AMNT;
	private BigDecimal TRNS_FEE_AMNT;
	private BigDecimal TRNS_VAT_RATE;
	private String TRNS_CUST_ADDR_2;
	private String TRNS_FUNC_CODE;
	private Date TRNS_PYMT_DTTM;
	private String TRNS_STTS_CODE_DESC;
	private String TRNS_PYMT_STTS_CODE_DESC;
	private String BLLR_SRVC_DESC;
	
	public String toString() {
		return "TRNS_ID:" + TRNS_ID 
				+ "|TRNS_DTTM:" + TRNS_DTTM
				+ "|TRNS_SRVC_CODE:" + TRNS_SRVC_CODE
				+ "|TRNS_DEST_CODE:" + TRNS_DEST_CODE
				+ "|TRNS_SRCE_CHNL_CODE:" + TRNS_SRCE_CHNL_CODE
				+ "|TRNS_REF1:" + TRNS_REF1
				+ "|TRNS_REF2:" + TRNS_REF2
				+ "|TRNS_REF3:" + TRNS_REF3
				+ "|TRNS_REF4:" + TRNS_REF4
				+ "|TRNS_REF5:" + TRNS_REF5
				+ "|TRNS_REF6:" + TRNS_REF6
				+ "|TRNS_PAID_AMNT:" + TRNS_PAID_AMNT
				+ "|TRNS_FEE_AMNT:" + TRNS_FEE_AMNT
				+ "|TRNS_VAT_RATE:" + TRNS_VAT_RATE
				+ "|TRNS_CUST_ADDR_2:" + TRNS_CUST_ADDR_2
				+ "|TRNS_FUNC_CODE:" + TRNS_FUNC_CODE
				+ "|TRNS_PYMT_DTTM:" + TRNS_PYMT_DTTM
				+ "|TRNS_STTS_CODE_DESC:" + TRNS_STTS_CODE_DESC
				+ "|TRNS_PYMT_STTS_CODE_DESC:" + TRNS_PYMT_STTS_CODE_DESC
				+ "|BLLR_SRVC_DESC:" + BLLR_SRVC_DESC;
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

	public String getTRNS_SRVC_CODE() {
		return TRNS_SRVC_CODE;
	}

	public void setTRNS_SRVC_CODE(String tRNS_SRVC_CODE) {
		TRNS_SRVC_CODE = tRNS_SRVC_CODE;
	}

	public String getTRNS_DEST_CODE() {
		return TRNS_DEST_CODE;
	}

	public void setTRNS_DEST_CODE(String tRNS_DEST_CODE) {
		TRNS_DEST_CODE = tRNS_DEST_CODE;
	}

	public String getTRNS_SRCE_CHNL_CODE() {
		return TRNS_SRCE_CHNL_CODE;
	}

	public void setTRNS_SRCE_CHNL_CODE(String tRNS_SRCE_CHNL_CODE) {
		TRNS_SRCE_CHNL_CODE = tRNS_SRCE_CHNL_CODE;
	}

	public String getTRNS_REF1() {
		return TRNS_REF1;
	}

	public void setTRNS_REF1(String tRNS_REF1) {
		TRNS_REF1 = tRNS_REF1;
	}

	public BigDecimal getTRNS_PAID_AMNT() {
		return TRNS_PAID_AMNT;
	}

	public void setTRNS_PAID_AMNT(BigDecimal tRNS_PAID_AMNT) {
		TRNS_PAID_AMNT = tRNS_PAID_AMNT;
	}

	public BigDecimal getTRNS_FEE_AMNT() {
		return TRNS_FEE_AMNT;
	}

	public void setTRNS_FEE_AMNT(BigDecimal tRNS_FEE_AMNT) {
		TRNS_FEE_AMNT = tRNS_FEE_AMNT;
	}

	public BigDecimal getTRNS_VAT_RATE() {
		return TRNS_VAT_RATE;
	}

	public void setTRNS_VAT_RATE(BigDecimal tRNS_VAT_RATE) {
		TRNS_VAT_RATE = tRNS_VAT_RATE;
	}

	public String getTRNS_CUST_ADDR_2() {
		return TRNS_CUST_ADDR_2;
	}

	public void setTRNS_CUST_ADDR_2(String tRNS_CUST_ADDR_2) {
		TRNS_CUST_ADDR_2 = tRNS_CUST_ADDR_2;
	}

	public String getTRNS_FUNC_CODE() {
		return TRNS_FUNC_CODE;
	}

	public void setTRNS_FUNC_CODE(String tRNS_FUNC_CODE) {
		TRNS_FUNC_CODE = tRNS_FUNC_CODE;
	}

	public Date getTRNS_PYMT_DTTM() {
		return TRNS_PYMT_DTTM;
	}

	public void setTRNS_PYMT_DTTM(Date tRNS_PYMT_DTTM) {
		TRNS_PYMT_DTTM = tRNS_PYMT_DTTM;
	}

	public String getTRNS_STTS_CODE_DESC() {
		return TRNS_STTS_CODE_DESC;
	}

	public void setTRNS_STTS_CODE_DESC(String tRNS_STTS_CODE_DESC) {
		TRNS_STTS_CODE_DESC = tRNS_STTS_CODE_DESC;
	}

	public String getTRNS_PYMT_STTS_CODE_DESC() {
		return TRNS_PYMT_STTS_CODE_DESC;
	}

	public void setTRNS_PYMT_STTS_CODE_DESC(String tRNS_PYMT_STTS_CODE_DESC) {
		TRNS_PYMT_STTS_CODE_DESC = tRNS_PYMT_STTS_CODE_DESC;
	}

	public String getTRNS_REF2() {
		return TRNS_REF2;
	}

	public void setTRNS_REF2(String tRNS_REF2) {
		TRNS_REF2 = tRNS_REF2;
	}

	public String getTRNS_REF3() {
		return TRNS_REF3;
	}

	public void setTRNS_REF3(String tRNS_REF3) {
		TRNS_REF3 = tRNS_REF3;
	}

	public String getTRNS_REF4() {
		return TRNS_REF4;
	}

	public void setTRNS_REF4(String tRNS_REF4) {
		TRNS_REF4 = tRNS_REF4;
	}

	public String getTRNS_REF5() {
		return TRNS_REF5;
	}

	public void setTRNS_REF5(String tRNS_REF5) {
		TRNS_REF5 = tRNS_REF5;
	}

	public String getTRNS_REF6() {
		return TRNS_REF6;
	}

	public void setTRNS_REF6(String tRNS_REF6) {
		TRNS_REF6 = tRNS_REF6;
	}

	public String getBLLR_SRVC_DESC() {
		return BLLR_SRVC_DESC;
	}

	public void setBLLR_SRVC_DESC(String bLLR_SRVC_DESC) {
		BLLR_SRVC_DESC = bLLR_SRVC_DESC;
	}
	
}
