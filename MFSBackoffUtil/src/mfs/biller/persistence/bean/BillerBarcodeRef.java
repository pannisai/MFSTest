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
@Table(name="BILLER_BARCODE_REF")
@Cache(type=CacheType.NONE)
public class BillerBarcodeRef implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="BARC_REF_ID", nullable=false)
	private Integer BARC_REF_ID;
	
	@Column(name="REF_ID", nullable=false)
	private Integer REF_ID;
	
	@Column(name="BARC_ID", nullable=false)
	private Integer BARC_ID;
	
	@Column(name="BARC_LINE_ID", nullable=true)
	private Integer BARC_LINE_ID;
	
	@Column(name="BARC_PART_FLAG", nullable=true)
	private String BARC_PART_FLAG;
	
	@Column(name="BARC_PART_STAT", nullable=true)
	private Integer BARC_PART_STAT;
	
	@Column(name="BARC_PART_LENT", nullable=true)
	private Integer BARC_PART_LENT;
	
	@Column(name="BARC_REMV_CHAR", nullable=true)
	private String BARC_REMV_CHAR;
	
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
	
	public String toString() {
		return "BARC_REF_ID:" + BARC_REF_ID 
				+ "|REF_ID:" + REF_ID 
				+ "|BARC_ID:" + BARC_ID 
				+ "|BARC_LINE_ID:" + BARC_LINE_ID 
				+ "|BARC_PART_FLAG:" + BARC_PART_FLAG 
				+ "|BARC_PART_STAT:" + BARC_PART_STAT 
				+ "|BARC_PART_LENT:" + BARC_PART_LENT 
				+ "|BARC_REMV_CHAR:" + BARC_REMV_CHAR 
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|CRTD_BY:" + CRTD_BY 
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM 
				+ "|BARC_TYPE:" + BARC_TYPE;
	}

	public Integer getBARC_REF_ID() {
		return BARC_REF_ID;
	}

	public void setBARC_REF_ID(Integer bARC_REF_ID) {
		BARC_REF_ID = bARC_REF_ID;
	}

	public Integer getREF_ID() {
		return REF_ID;
	}

	public void setREF_ID(Integer rEF_ID) {
		REF_ID = rEF_ID;
	}

	public Integer getBARC_ID() {
		return BARC_ID;
	}

	public void setBARC_ID(Integer bARC_ID) {
		BARC_ID = bARC_ID;
	}

	public Integer getBARC_LINE_ID() {
		return BARC_LINE_ID;
	}

	public void setBARC_LINE_ID(Integer bARC_LINE_ID) {
		BARC_LINE_ID = bARC_LINE_ID;
	}

	public String getBARC_PART_FLAG() {
		return BARC_PART_FLAG;
	}

	public void setBARC_PART_FLAG(String bARC_PART_FLAG) {
		BARC_PART_FLAG = bARC_PART_FLAG;
	}

	public Integer getBARC_PART_STAT() {
		return BARC_PART_STAT;
	}

	public void setBARC_PART_STAT(Integer bARC_PART_STAT) {
		BARC_PART_STAT = bARC_PART_STAT;
	}

	public Integer getBARC_PART_LENT() {
		return BARC_PART_LENT;
	}

	public void setBARC_PART_LENT(Integer bARC_PART_LENT) {
		BARC_PART_LENT = bARC_PART_LENT;
	}

	public String getBARC_REMV_CHAR() {
		return BARC_REMV_CHAR;
	}

	public void setBARC_REMV_CHAR(String bARC_REMV_CHAR) {
		BARC_REMV_CHAR = bARC_REMV_CHAR;
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
	
	
}
