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
@Table(name="BILLER_PYMT_CODE")
@Cache(type=CacheType.NONE)
public class BillerPymtCode implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="BLLR_MSGE_CODE", nullable=false)
	private String BLLR_MSGE_CODE;
	
	@Column(name="BLLR_MSGE_CODE_MSGE_TH", nullable=true)
	private String BLLR_MSGE_CODE_MSGE_TH;
	
	@Column(name="BLLR_MSGE_CODE_MSGE_EN", nullable=true)
	private String BLLR_MSGE_CODE_MSGE_EN;
	
	@Column(name="BLLR_MSGE_CODE_TYPE", nullable=true)
	private String BLLR_MSGE_CODE_TYPE;
	
	@Column(name="BLLR_MSGE_CODE_RETY_FLAG", nullable=true)
	private String BLLR_MSGE_CODE_RETY_FLAG;
		
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
		return "BLLR_MSGE_CODE:" + BLLR_MSGE_CODE 
				+ "|BLLR_MSGE_CODE_MSGE_TH:" + BLLR_MSGE_CODE_MSGE_TH 
				+ "|BLLR_MSGE_CODE_MSGE_EN:" + BLLR_MSGE_CODE_MSGE_EN 
				+ "|BLLR_MSGE_CODE_TYPE:" + BLLR_MSGE_CODE_TYPE 
				+ "|BLLR_MSGE_CODE_RETY_FLAG:" + BLLR_MSGE_CODE_RETY_FLAG 
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|CRTD_BY:" + CRTD_BY 
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM;
	}

	public String getBLLR_MSGE_CODE() {
		return BLLR_MSGE_CODE;
	}

	public void setBLLR_MSGE_CODE(String bLLR_MSGE_CODE) {
		BLLR_MSGE_CODE = bLLR_MSGE_CODE;
	}

	public String getBLLR_MSGE_CODE_MSGE_TH() {
		return BLLR_MSGE_CODE_MSGE_TH;
	}

	public void setBLLR_MSGE_CODE_MSGE_TH(String bLLR_MSGE_CODE_MSGE_TH) {
		BLLR_MSGE_CODE_MSGE_TH = bLLR_MSGE_CODE_MSGE_TH;
	}

	public String getBLLR_MSGE_CODE_MSGE_EN() {
		return BLLR_MSGE_CODE_MSGE_EN;
	}

	public void setBLLR_MSGE_CODE_MSGE_EN(String bLLR_MSGE_CODE_MSGE_EN) {
		BLLR_MSGE_CODE_MSGE_EN = bLLR_MSGE_CODE_MSGE_EN;
	}

	public String getBLLR_MSGE_CODE_TYPE() {
		return BLLR_MSGE_CODE_TYPE;
	}

	public void setBLLR_MSGE_CODE_TYPE(String bLLR_MSGE_CODE_TYPE) {
		BLLR_MSGE_CODE_TYPE = bLLR_MSGE_CODE_TYPE;
	}

	public String getBLLR_MSGE_CODE_RETY_FLAG() {
		return BLLR_MSGE_CODE_RETY_FLAG;
	}

	public void setBLLR_MSGE_CODE_RETY_FLAG(String bLLR_MSGE_CODE_RETY_FLAG) {
		BLLR_MSGE_CODE_RETY_FLAG = bLLR_MSGE_CODE_RETY_FLAG;
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
