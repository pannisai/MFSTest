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

@Entity
@Table(name = "BILLER_FEE")
@Cache(type = CacheType.NONE)
public class BillerFee implements Serializable {
	private static final long serialVersionUID = -6700556386422714291L;
	
	@Id
	@Column(name = "BLLR_FEE_ID", nullable = false)
	private String BLLR_FEE_ID;

	@Column(name = "BLLR_SRVC_ID", nullable = true)
	private String BLLR_SRVC_ID;

	@Column(name = "BLLR_FEE_MAST_ID", nullable = true)
	private String BLLR_FEE_MAST_ID;

	@Column(name = "FEE_AMOUNT", nullable = true)
	private BigDecimal FEE_AMOUNT;

	@Column(name = "FUNDAMO_FEE_AMOUNT", nullable = true)
	private BigDecimal FUNDAMO_FEE_AMOUNT;

	@Column(name = "EFFT_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date EFFT_DATE;
	
	@Column(name = "EXPR_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date EXPR_DATE;
	
	@Column(name="ACT_FLAG", nullable=true)
	private String ACT_FLAG;
	
	@Column(name = "CRTD_BY", nullable = true)
	private String CRTD_BY;

	@Column(name = "CRTD_DTTM", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date CRTD_DTTM;

	@Column(name = "LAST_CHNG_BY", nullable = true)
	private String LAST_CHNG_BY;

	@Column(name = "LAST_CHNG_DTTM", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date LAST_CHNG_DTTM;	
	
	private String BLLR_SRVC_NAME_EN;	
	private String FEE_TYPE;
	private BigDecimal SRVC_FEE;
	
	public String toString() {
		return "BLLR_FEE_ID:" + BLLR_FEE_ID 
				+ "|BLLR_SRVC_ID:" + BLLR_SRVC_ID 
				+ "|BLLR_FEE_MAST_ID:" + BLLR_FEE_MAST_ID
				+ "|FEE_AMOUNT:" + FEE_AMOUNT 				
				+ "|FUNDAMO_FEE_AMOUNT:" + FUNDAMO_FEE_AMOUNT 
				+ "|EFFT_DATE:" + EFFT_DATE
				+ "|EXPR_DATE:" + EXPR_DATE 
				+ "|ACT_FLAG:" + ACT_FLAG 				
				+ "|CRTD_BY:" + CRTD_BY
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM;
	}

	public String getBLLR_FEE_ID() {
		return BLLR_FEE_ID;
	}

	public void setBLLR_FEE_ID(String bLLR_FEE_ID) {
		BLLR_FEE_ID = bLLR_FEE_ID;
	}

	public String getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}

	public void setBLLR_SRVC_ID(String bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}

	public String getBLLR_FEE_MAST_ID() {
		return BLLR_FEE_MAST_ID;
	}

	public void setBLLR_FEE_MAST_ID(String bLLR_FEE_MAST_ID) {
		BLLR_FEE_MAST_ID = bLLR_FEE_MAST_ID;
	}

	public BigDecimal getFEE_AMOUNT() {
		return FEE_AMOUNT;
	}

	public void setFEE_AMOUNT(BigDecimal fEE_AMOUNT) {
		FEE_AMOUNT = fEE_AMOUNT;
	}

	public BigDecimal getFUNDAMO_FEE_AMOUNT() {
		return FUNDAMO_FEE_AMOUNT;
	}

	public void setFUNDAMO_FEE_AMOUNT(BigDecimal fUNDAMO_FEE_AMOUNT) {
		FUNDAMO_FEE_AMOUNT = fUNDAMO_FEE_AMOUNT;
	}

	public Date getEFFT_DATE() {
		return EFFT_DATE;
	}

	public void setEFFT_DATE(Date eFFT_DATE) {
		EFFT_DATE = eFFT_DATE;
	}

	public Date getEXPR_DATE() {
		return EXPR_DATE;
	}

	public void setEXPR_DATE(Date eXPR_DATE) {
		EXPR_DATE = eXPR_DATE;
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

	public String getBLLR_SRVC_NAME_EN() {
		return BLLR_SRVC_NAME_EN;
	}

	public void setBLLR_SRVC_NAME_EN(String bLLR_SRVC_NAME_EN) {
		BLLR_SRVC_NAME_EN = bLLR_SRVC_NAME_EN;
	}

	public String getFEE_TYPE() {
		return FEE_TYPE;
	}

	public void setFEE_TYPE(String fEE_TYPE) {
		FEE_TYPE = fEE_TYPE;
	}

	public BigDecimal getSRVC_FEE() {
		return SRVC_FEE;
	}

	public void setSRVC_FEE(BigDecimal sRVC_FEE) {
		SRVC_FEE = sRVC_FEE;
	}
}
