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
@Table(name="BILLER_BARCODE")
@Cache(type=CacheType.NONE)
public class BillerBarcode implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="BARC_ID", nullable=false)
	private Integer BARC_ID;
	
	@Column(name="BARC_NAME", nullable=true)
	private String BARC_NAME;
	
	@Column(name="BARC_CRRG_RETN_FLAG", nullable=true)
	private String BARC_CRRG_RETN_FLAG;
	
	@Column(name="BARC_PERF_FLAG", nullable=true)
	private String BARC_PERF_FLAG;
	
	@Column(name="BARC_PERF_VALUE", nullable=true)
	private String BARC_PERF_VALUE;
	
	@Column(name="BARC_LINE_MAX", nullable=true)
	private Integer BARC_LINE_MAX;
	
	@Column(name="BARC_EFFT_DATE", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date BARC_EFFT_DATE;
	
	@Column(name="BARC_LINE_NO", nullable=true)
	private Integer BARC_LINE_NO;
	
	@Column(name="BLLR_SRVC_ID", nullable=true)
	private Integer BLLR_SRVC_ID;	
	
	@Column(name="BARC_NEW_LINE_POST", nullable=true)
	private String BARC_NEW_LINE_POST;
	
	@Column(name="BARC_SRVC_CODE", nullable=true)
	private String BARC_SRVC_CODE;
	
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
		
	@Column(name="BARC_TYPE", nullable=true)
    private String BARC_TYPE;
	
	@Column(name="BARC_EXPR_DATE", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date BARC_EXPR_DATE;
	
	private String BLLR_SRVC_NAME_EN;
	
	public String toString() {
		return "BARC_ID:" + BARC_ID 
				+ "|BARC_NAME:" + BARC_NAME 
				+ "|BARC_CRRG_RETN_FLAG:" + BARC_CRRG_RETN_FLAG 
				+ "|BARC_PERF_FLAG:" + BARC_PERF_FLAG 
				+ "|BARC_PERF_VALUE:" + BARC_PERF_VALUE 
				+ "|BARC_LINE_MAX:" + BARC_LINE_MAX 
				+ "|BARC_EFFT_DATE:" + BARC_EFFT_DATE 
				+ "|BARC_LINE_NO:" + BARC_LINE_NO 
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|CRTD_BY:" + CRTD_BY 
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM
		+ "|BARC_TYPE:" + BARC_TYPE
		+ "|BLLR_SRVC_ID:" + BLLR_SRVC_ID
		+ "|BARC_SRVC_CODE:" + BARC_SRVC_CODE
		+ "|BARC_NEW_LINE_POST:" + BARC_NEW_LINE_POST
		+"|BARC_EXPR_DATE"+BARC_EXPR_DATE;
		
	}

	public Integer getBARC_ID() {
		return BARC_ID;
	}

	public void setBARC_ID(Integer bARC_ID) {
		BARC_ID = bARC_ID;
	}

	public String getBARC_NAME() {
		return BARC_NAME;
	}

	public void setBARC_NAME(String bARC_NAME) {
		BARC_NAME = bARC_NAME;
	}

	public String getBARC_CRRG_RETN_FLAG() {
		return BARC_CRRG_RETN_FLAG;
	}

	public void setBARC_CRRG_RETN_FLAG(String bARC_CRRG_RETN_FLAG) {
		BARC_CRRG_RETN_FLAG = bARC_CRRG_RETN_FLAG;
	}

	public String getBARC_PERF_FLAG() {
		return BARC_PERF_FLAG;
	}

	public void setBARC_PERF_FLAG(String bARC_PERF_FLAG) {
		BARC_PERF_FLAG = bARC_PERF_FLAG;
	}

	public String getBARC_PERF_VALUE() {
		return BARC_PERF_VALUE;
	}

	public void setBARC_PERF_VALUE(String bARC_PERF_VALUE) {
		BARC_PERF_VALUE = bARC_PERF_VALUE;
	}

	public Integer getBARC_LINE_MAX() {
		return BARC_LINE_MAX;
	}

	public void setBARC_LINE_MAX(Integer bARC_LINE_MAX) {
		BARC_LINE_MAX = bARC_LINE_MAX;
	}

	public Date getBARC_EFFT_DATE() {
		return BARC_EFFT_DATE;
	}

	public void setBARC_EFFT_DATE(Date bARC_EFFT_DATE) {
		BARC_EFFT_DATE = bARC_EFFT_DATE;
	}

	public Integer getBARC_LINE_NO() {
		return BARC_LINE_NO;
	}

	public void setBARC_LINE_NO(Integer bARC_LINE_NO) {
		BARC_LINE_NO = bARC_LINE_NO;
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

	public String getBARC_TYPE() {
		return BARC_TYPE;
	}

	public void setBARC_TYPE(String bARC_TYPE) {
		BARC_TYPE = bARC_TYPE;
	}

	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}

	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}

	public String getBARC_NEW_LINE_POST() {
		return BARC_NEW_LINE_POST;
	}

	public void setBARC_NEW_LINE_POST(String bARC_NEW_LINE_POST) {
		BARC_NEW_LINE_POST = bARC_NEW_LINE_POST;
	}

	public String getBARC_SRVC_CODE() {
		return BARC_SRVC_CODE;
	}

	public void setBARC_SRVC_CODE(String bARC_SRVC_CODE) {
		BARC_SRVC_CODE = bARC_SRVC_CODE;
	}

	public String getBLLR_SRVC_NAME_EN() {
		return BLLR_SRVC_NAME_EN;
	}

	public void setBLLR_SRVC_NAME_EN(String bLLR_SRVC_NAME_EN) {
		BLLR_SRVC_NAME_EN = bLLR_SRVC_NAME_EN;
	}

	public Date getBARC_EXPR_DATE() {
		return BARC_EXPR_DATE;
	}

	public void setBARC_EXPR_DATE(Date bARC_EXPR_DATE) {
		BARC_EXPR_DATE = bARC_EXPR_DATE;
	}

	
	
}
