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
@Table(name = "BANK_SERVICE")
@Cache(type = CacheType.NONE)
public class BankServicebean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7206988328626623715L;

	@Id
	private Integer BANK_SRVC_ID;

	@Column(name = "BANK_SRVC_CODE")
	private String BANK_SRVC_CODE;

	@Column(name = "BANK_CODE")
	private String BANK_CODE;

	@Column(name = "FDM_MTI_CODE")
	private String FDM_MTI_CODE;

	@Column(name = "BANK_SRVC_NAME")
	private String BANK_SRVC_NAME;

	@Column(name = "BANK_CUTF_TIME")
	private Integer BANK_CUTF_TIME;

	@Column(name = "ACT_FLAG")
	private String ACT_FLAG;

	@Column(name = "CRTD_BY")
	private String CRTD_BY;

	@Column(name = "CRTD_DTTM")
	@Temporal(TemporalType.TIMESTAMP)
	private Date CRTD_DTTM;

	@Column(name = "LAST_CHNG_BY")
	private String LAST_CHNG_BY;

	@Column(name = "LAST_CHNG_DTTM")
	@Temporal(TemporalType.TIMESTAMP)
	private Date LAST_CHNG_DTTM;

	public Integer getBANK_SRVC_ID() {
		return BANK_SRVC_ID;
	}

	public void setBANK_SRVC_ID(Integer bANK_SRVC_ID) {
		BANK_SRVC_ID = bANK_SRVC_ID;
	}

	public String getBANK_SRVC_CODE() {
		return BANK_SRVC_CODE;
	}

	public void setBANK_SRVC_CODE(String bANK_SRVC_CODE) {
		BANK_SRVC_CODE = bANK_SRVC_CODE;
	}

	public String getBANK_CODE() {
		return BANK_CODE;
	}

	public void setBANK_CODE(String bANK_CODE) {
		BANK_CODE = bANK_CODE;
	}

	public String getFDM_MTI_CODE() {
		return FDM_MTI_CODE;
	}

	public void setFDM_MTI_CODE(String fDM_MTI_CODE) {
		FDM_MTI_CODE = fDM_MTI_CODE;
	}

	public String getBANK_SRVC_NAME() {
		return BANK_SRVC_NAME;
	}

	public void setBANK_SRVC_NAME(String bANK_SRVC_NAME) {
		BANK_SRVC_NAME = bANK_SRVC_NAME;
	}

	public Integer getBANK_CUTF_TIME() {
		return BANK_CUTF_TIME;
	}

	public void setBANK_CUTF_TIME(Integer bANK_CUTF_TIME) {
		BANK_CUTF_TIME = bANK_CUTF_TIME;
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

	public String toString() {
		return "BANK_SRVC_ID:" + BANK_SRVC_ID + "|BANK_SRVC_CODE:" + BANK_SRVC_CODE + "|BANK_CODE:" + BANK_CODE + "|FDM_MTI_CODE:" + FDM_MTI_CODE + "|BANK_SRVC_NAME:" + BANK_SRVC_NAME + "|BANK_CUTF_TIME:" + BANK_CUTF_TIME + "|ACT_FLAG:" + ACT_FLAG + "|CRTD_BY:" + CRTD_BY + "|CRTD_DTTM:" + CRTD_DTTM + "|LAST_CHNG_BY:" + LAST_CHNG_BY + "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM;
	}

}
