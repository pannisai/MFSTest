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
@Table(name="BILLER_PAYMENT_VALIDATE")
@Cache(type=CacheType.NONE)
public class BillerPaymentValidate implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BLLR_PMNT_VALD_ID", nullable=false)
	private Integer BLLR_PMNT_VALD_ID;
	
	@Column(name="BLLR_SRVC_ID", nullable=true)
	private Integer BLLR_SRVC_ID;
	
	@Column(name="BLLR_PMNT_OVER_FLAG", nullable=true)
	private String BLLR_PMNT_OVER_FLAG;
	
	@Column(name="BLLR_PMNT_FULL_FLAG", nullable=true)
	private String BLLR_PMNT_FULL_FLAG;
	
	@Column(name="BLLR_PMNT_PART_FLAG", nullable=true)
	private String BLLR_PMNT_PART_FLAG;
	
	@Column(name="BLLR_PRES_FLAG", nullable=true)
	private String BLLR_PRES_FLAG;
	
	@Column(name="BLLR_PMNT_AMNT_MIN", nullable=true)
	private BigDecimal BLLR_PMNT_AMNT_MIN;
	
	@Column(name="BLLR_PMNT_AMNT_MAX", nullable=true)
	private BigDecimal BLLR_PMNT_AMNT_MAX;
	
	@Column(name="BLLR_BILL_AMNT_FLAG", nullable=true)
	private String BLLR_BILL_AMNT_FLAG;
	
	@Column(name="BLLR_OVER_DUE_FLAG", nullable=true)
	private String BLLR_OVER_DUE_FLAG;
	
	@Column(name="BLLR_NON_FDM_FEE_FLAG", nullable=true)
	private String BLLR_NON_FDM_FEE_FLAG;
	
	@Column(name="BLLR_NON_FDM_COMS_FLAG", nullable=true)
	private String BLLR_NON_FDM_COMS_FLAG;
	
	@Column(name="ACT_FLAG", nullable=true)
	private String ACT_FLAG;
	
	@Column(name="CRTD_BY", nullable=true)
    private String CRTD_BY;
	
	@Column(name="CRTD_DTTM", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
    private Date CRTD_DTTM;
	
	@Column(name="LAST_CHNG_BY", nullable=true)
    private String LAST_CHNG_BY;
	
	@Column(name="LAST_CHNG_DTTM", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
    private Date LAST_CHNG_DTTM;
	
	@Column(name="BLLR_MAX_NO_MNTH")
	private Integer BLLR_MAX_NO_MNTH;
	
	
	
	@Column(name="BLLR_DENM_FLAG", nullable=true)
    private String BLLR_DENM_FLAG;
	
	public String toString() {
		return "BLLR_PMNT_VALD_ID:" + BLLR_PMNT_VALD_ID 
				+ "|BLLR_SRVC_ID:" + BLLR_SRVC_ID 
				+ "|BLLR_PMNT_OVER_FLAG:" + BLLR_PMNT_OVER_FLAG 
				+ "|BLLR_PMNT_FULL_FLAG:" + BLLR_PMNT_FULL_FLAG 
				+ "|BLLR_PMNT_PART_FLAG:" + BLLR_PMNT_PART_FLAG 
				+ "|BLLR_PRES_FLAG:" + BLLR_PRES_FLAG 
				+ "|BLLR_PMNT_AMNT_MIN:" + BLLR_PMNT_AMNT_MIN 
				+ "|BLLR_PMNT_AMNT_MAX:" + BLLR_PMNT_AMNT_MAX 
				+ "|BLLR_BILL_AMNT_FLAG:" + BLLR_BILL_AMNT_FLAG 
				+ "|BLLR_OVER_DUE_FLAG:" + BLLR_OVER_DUE_FLAG 
				+ "|BLLR_NON_FDM_FEE_FLAG:" + BLLR_NON_FDM_FEE_FLAG 
				+ "|BLLR_NON_FDM_COMS_FLAG:" + BLLR_NON_FDM_COMS_FLAG 
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|CRTD_BY:" + CRTD_BY 
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM
				+ "|BLLR_MAX_NO_MNTH:" + BLLR_MAX_NO_MNTH
				+ "|BLLR_DENM_FLAG:" + BLLR_DENM_FLAG;
	}

	public Integer getBLLR_PMNT_VALD_ID() {
		return BLLR_PMNT_VALD_ID;
	}

	public void setBLLR_PMNT_VALD_ID(Integer bLLR_PMNT_VALD_ID) {
		BLLR_PMNT_VALD_ID = bLLR_PMNT_VALD_ID;
	}

	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}

	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}

	public String getBLLR_PMNT_OVER_FLAG() {
		return BLLR_PMNT_OVER_FLAG;
	}

	public void setBLLR_PMNT_OVER_FLAG(String bLLR_PMNT_OVER_FLAG) {
		BLLR_PMNT_OVER_FLAG = bLLR_PMNT_OVER_FLAG;
	}

	public String getBLLR_PMNT_FULL_FLAG() {
		return BLLR_PMNT_FULL_FLAG;
	}

	public void setBLLR_PMNT_FULL_FLAG(String bLLR_PMNT_FULL_FLAG) {
		BLLR_PMNT_FULL_FLAG = bLLR_PMNT_FULL_FLAG;
	}

	public String getBLLR_PMNT_PART_FLAG() {
		return BLLR_PMNT_PART_FLAG;
	}

	public void setBLLR_PMNT_PART_FLAG(String bLLR_PMNT_PART_FLAG) {
		BLLR_PMNT_PART_FLAG = bLLR_PMNT_PART_FLAG;
	}

	public String getBLLR_PRES_FLAG() {
		return BLLR_PRES_FLAG;
	}

	public void setBLLR_PRES_FLAG(String bLLR_PRES_FLAG) {
		BLLR_PRES_FLAG = bLLR_PRES_FLAG;
	}

	public BigDecimal getBLLR_PMNT_AMNT_MIN() {
		return BLLR_PMNT_AMNT_MIN;
	}

	public void setBLLR_PMNT_AMNT_MIN(BigDecimal bLLR_PMNT_AMNT_MIN) {
		BLLR_PMNT_AMNT_MIN = bLLR_PMNT_AMNT_MIN;
	}

	public BigDecimal getBLLR_PMNT_AMNT_MAX() {
		return BLLR_PMNT_AMNT_MAX;
	}

	public void setBLLR_PMNT_AMNT_MAX(BigDecimal bLLR_PMNT_AMNT_MAX) {
		BLLR_PMNT_AMNT_MAX = bLLR_PMNT_AMNT_MAX;
	}

	public String getBLLR_BILL_AMNT_FLAG() {
		return BLLR_BILL_AMNT_FLAG;
	}

	public void setBLLR_BILL_AMNT_FLAG(String bLLR_BILL_AMNT_FLAG) {
		BLLR_BILL_AMNT_FLAG = bLLR_BILL_AMNT_FLAG;
	}

	public String getBLLR_OVER_DUE_FLAG() {
		return BLLR_OVER_DUE_FLAG;
	}

	public void setBLLR_OVER_DUE_FLAG(String bLLR_OVER_DUE_FLAG) {
		BLLR_OVER_DUE_FLAG = bLLR_OVER_DUE_FLAG;
	}

	public String getBLLR_NON_FDM_FEE_FLAG() {
		return BLLR_NON_FDM_FEE_FLAG;
	}

	public void setBLLR_NON_FDM_FEE_FLAG(String bLLR_NON_FDM_FEE_FLAG) {
		BLLR_NON_FDM_FEE_FLAG = bLLR_NON_FDM_FEE_FLAG;
	}

	public String getBLLR_NON_FDM_COMS_FLAG() {
		return BLLR_NON_FDM_COMS_FLAG;
	}

	public void setBLLR_NON_FDM_COMS_FLAG(String bLLR_NON_FDM_COMS_FLAG) {
		BLLR_NON_FDM_COMS_FLAG = bLLR_NON_FDM_COMS_FLAG;
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

	public Integer getBLLR_MAX_NO_MNTH() {
		return BLLR_MAX_NO_MNTH;
	}

	public void setBLLR_MAX_NO_MNTH(Integer bLLR_MAX_NO_MNTH) {
		BLLR_MAX_NO_MNTH = bLLR_MAX_NO_MNTH;
	}

	public String getBLLR_DENM_FLAG() {
		return BLLR_DENM_FLAG;
	}

	public void setBLLR_DENM_FLAG(String bLLR_DENM_FLAG) {
		BLLR_DENM_FLAG = bLLR_DENM_FLAG;
	}
	

}
