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
@Table(name="BILLER_DENOMINATE")
@Cache(type=CacheType.NONE)
public class BillerDenominate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BLLR_DENM_ID", nullable=false)
	private Integer BLLR_DENM_ID;
	
	@Column(name="BLLR_SRVC_ID", nullable=true)
	private Integer BLLR_SRVC_ID;
	
	@Column(name="BLLR_DENM_RATE", nullable=true)
	private BigDecimal BLLR_DENM_RATE;
	
	@Column(name="BLLR_MAX_NO_MNTH", nullable=true)
	private Integer BLLR_MAX_NO_MNTH;
	
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
	
	@Column(name="ACT_FLAG", nullable=true)
    private String ACT_FLAG;
	
	public String toString() {
		return "BLLR_DENM_ID:" + BLLR_DENM_ID 
				+ "|BLLR_SRVC_ID:" + BLLR_SRVC_ID 
		+ "|BLLR_DENM_RATE:" + BLLR_DENM_RATE 
		+ "|BLLR_MAX_NO_MNTH:" + BLLR_MAX_NO_MNTH 
		+ "|ACT_FLAG:" + ACT_FLAG 
		+ "|CRTD_BY:" + CRTD_BY 
		+ "|CRTD_DTTM:" + CRTD_DTTM 
		+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
		+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM ;
		
	}

	public Integer getBLLR_DENM_ID() {
		return BLLR_DENM_ID;
	}

	public void setBLLR_DENM_ID(Integer bLLR_DENM_ID) {
		BLLR_DENM_ID = bLLR_DENM_ID;
	}

	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}

	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}

	public BigDecimal getBLLR_DENM_RATE() {
		return BLLR_DENM_RATE;
	}

	public void setBLLR_DENM_RATE(BigDecimal bLLR_DENM_RATE) {
		BLLR_DENM_RATE = bLLR_DENM_RATE;
	}

	public Integer getBLLR_MAX_NO_MNTH() {
		return BLLR_MAX_NO_MNTH;
	}

	public void setBLLR_MAX_NO_MNTH(Integer bLLR_MAX_NO_MNTH) {
		BLLR_MAX_NO_MNTH = bLLR_MAX_NO_MNTH;
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

	public String getACT_FLAG() {
		return ACT_FLAG;
	}

	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}

	

	
	
}
