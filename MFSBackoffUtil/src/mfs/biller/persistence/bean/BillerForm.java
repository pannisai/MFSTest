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

@Entity
@Table(name="BILLER_FORM")
@Cache(type=CacheType.NONE)
public class BillerForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BLLR_FORM_ID", nullable=false)
	private Integer BLLR_FORM_ID;
	
	@Column(name="BLLR_FORM_NAME", nullable=true)
	private String BLLR_FORM_NAME;
	
	@Column(name="BLLR_FORM_CAPT_TH", nullable=true)
	private String BLLR_FORM_CAPT_TH;
	
	@Column(name="BLLR_FORM_CAPT_EN", nullable=true)
	private String BLLR_FORM_CAPT_EN;
	
	@Column(name="BLLR_SRVC_ID", nullable=false)
	private Integer BLLR_SRVC_ID;
	
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
	
	public String toString() {
		return "BLLR_FORM_ID:" + BLLR_FORM_ID 
				+ "|BLLR_FORM_NAME:" + BLLR_FORM_NAME 
				+ "|BLLR_FORM_CAPT_TH:" + BLLR_FORM_CAPT_TH 
				+ "|BLLR_FORM_CAPT_EN:" + BLLR_FORM_CAPT_EN 
				+ "|BLLR_SRVC_ID:" + BLLR_SRVC_ID 
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|CRTD_BY:" + CRTD_BY 
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM;
	}

	public Integer getBLLR_FORM_ID() {
		return BLLR_FORM_ID;
	}

	public void setBLLR_FORM_ID(Integer bLLR_FORM_ID) {
		BLLR_FORM_ID = bLLR_FORM_ID;
	}

	public String getBLLR_FORM_NAME() {
		return BLLR_FORM_NAME;
	}

	public void setBLLR_FORM_NAME(String bLLR_FORM_NAME) {
		BLLR_FORM_NAME = bLLR_FORM_NAME;
	}

	public String getBLLR_FORM_CAPT_TH() {
		return BLLR_FORM_CAPT_TH;
	}

	public void setBLLR_FORM_CAPT_TH(String bLLR_FORM_CAPT_TH) {
		BLLR_FORM_CAPT_TH = bLLR_FORM_CAPT_TH;
	}

	public String getBLLR_FORM_CAPT_EN() {
		return BLLR_FORM_CAPT_EN;
	}

	public void setBLLR_FORM_CAPT_EN(String bLLR_FORM_CAPT_EN) {
		BLLR_FORM_CAPT_EN = bLLR_FORM_CAPT_EN;
	}

	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}

	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
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
	
}
