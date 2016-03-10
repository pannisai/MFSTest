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
@Table(name="BILLER_MASTER")
@Cache(type=CacheType.NONE)
public class BillerMaster implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="BLLR_MSTR_ID", nullable=false)
	private Integer  BLLR_MSTR_ID;
	
	@Column(name="BLLR_MSTR_NAME", nullable=true)
	private String BLLR_MSTR_NAME;

	@Column(name="NAME_THA", nullable=true)
	private String NAME_THA;

	@Column(name="COMP_CODE", nullable=true)
	private String COMP_CODE;

	@Column(name="BLLR_CODE", nullable=true)
	private String BLLR_CODE;

	@Column(name="ACT_FLAG", nullable=true)
	private String ACT_FLAG;

	@Column(name="BLLR_TAX_NUMB", nullable=true)
	private String BLLR_TAX_NUMB;

	@Column(name="BLLR_TAX_NUMB2", nullable=true)
	private String BLLR_TAX_NUMB2;
	
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
	
	public String toString(){
		return "BLLR_MSTR_ID:" + BLLR_MSTR_ID 
				+ "|BLLR_MSTR_NAME:" + BLLR_MSTR_NAME 
				+ "|NAME_THA:" + NAME_THA 
				+ "|COMP_CODE:" + COMP_CODE 
				+ "|BLLR_CODE:" + BLLR_CODE 
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|BLLR_TAX_NUMB:" + BLLR_TAX_NUMB 
				+ "|BLLR_TAX_NUMB2:" + BLLR_TAX_NUMB2
				+ "|CRTD_BY:" + CRTD_BY 
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM;
	}

	public Integer getBLLR_MSTR_ID() {
		return BLLR_MSTR_ID;
	}

	public void setBLLR_MSTR_ID(Integer bLLR_MSTR_ID) {
		BLLR_MSTR_ID = bLLR_MSTR_ID;
	}

	public String getBLLR_MSTR_NAME() {
		return BLLR_MSTR_NAME;
	}

	public void setBLLR_MSTR_NAME(String bLLR_MSTR_NAME) {
		BLLR_MSTR_NAME = bLLR_MSTR_NAME;
	}

	public String getNAME_THA() {
		return NAME_THA;
	}

	public void setNAME_THA(String nAME_THA) {
		NAME_THA = nAME_THA;
	}

	public String getCOMP_CODE() {
		return COMP_CODE;
	}

	public void setCOMP_CODE(String cOMP_CODE) {
		COMP_CODE = cOMP_CODE;
	}

	public String getBLLR_CODE() {
		return BLLR_CODE;
	}

	public void setBLLR_CODE(String bLLR_CODE) {
		BLLR_CODE = bLLR_CODE;
	}

	public String getACT_FLAG() {
		return ACT_FLAG;
	}

	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}

	public String getBLLR_TAX_NUMB() {
		return BLLR_TAX_NUMB;
	}

	public void setBLLR_TAX_NUMB(String bLLR_TAX_NUMB) {
		BLLR_TAX_NUMB = bLLR_TAX_NUMB;
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

	public String getBLLR_TAX_NUMB2() {
		return BLLR_TAX_NUMB2;
	}

	public void setBLLR_TAX_NUMB2(String bLLR_TAX_NUMB2) {
		BLLR_TAX_NUMB2 = bLLR_TAX_NUMB2;
	}
	
}
