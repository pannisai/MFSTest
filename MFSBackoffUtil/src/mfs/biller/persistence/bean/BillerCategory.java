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
@Table(name = "BILLER_CATEGORY")
@Cache(type = CacheType.NONE)
public class BillerCategory implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -6700556386422714291L;

	@Id
	@Column(name = "BLLR_CATG_ID", nullable = false)
	private Integer BLLR_CATG_ID;

	@Column(name = "BLLR_CATG_NAME", nullable = true)
	private String BLLR_CATG_NAME;

	@Column(name = "BLLR_CATG_DESC", nullable = true)
	private String BLLR_CATG_DESC;

	@Column(name = "ACT_FLAG", nullable = true)
	private String ACT_FLAG;

	@Column(name = "BLLR_CATG_MENU_SEQ", nullable = true)
	private Integer BLLR_CATG_MENU_SEQ;

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

	public String toString() {
		return "BLLR_CATG_ID:" + BLLR_CATG_ID + "|BLLR_CATG_NAME:"
				+ BLLR_CATG_NAME + "|BLLR_CATG_DESC:" + BLLR_CATG_DESC
				+ "|ACT_FLAG:" + ACT_FLAG + "|CRTD_BY:" + CRTD_BY
				+ "|CRTD_DTTM:" + CRTD_DTTM + "|LAST_CHNG_BY:" + LAST_CHNG_BY
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM + "|BLLR_CATG_MENU_SEQ:"
				+ BLLR_CATG_MENU_SEQ;
	}

	public Integer getBLLR_CATG_ID() {
		return BLLR_CATG_ID;
	}

	public void setBLLR_CATG_ID(Integer bLLR_CATG_ID) {
		BLLR_CATG_ID = bLLR_CATG_ID;
	}

	public String getBLLR_CATG_NAME() {
		return BLLR_CATG_NAME;
	}

	public void setBLLR_CATG_NAME(String bLLR_CATG_NAME) {
		BLLR_CATG_NAME = bLLR_CATG_NAME;
	}

	public String getBLLR_CATG_DESC() {
		return BLLR_CATG_DESC;
	}

	public void setBLLR_CATG_DESC(String bLLR_CATG_DESC) {
		BLLR_CATG_DESC = bLLR_CATG_DESC;
	}

	public String getACT_FLAG() {
		return ACT_FLAG;
	}

	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}

	public Integer getBLLR_CATG_MENU_SEQ() {
		return BLLR_CATG_MENU_SEQ;
	}

	public void setBLLR_CATG_MENU_SEQ(Integer bLLR_CATG_MENU_SEQ) {
		BLLR_CATG_MENU_SEQ = bLLR_CATG_MENU_SEQ;
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

//	public boolean equals(Object obj) {
//		if (!(obj instanceof BillerCategory))
//			return false;
//		if (obj == this)
//			return true;
//		return this.BLLR_CATG_ID == ((BillerCategory) obj).BLLR_CATG_ID;
//	}
//
//	public int hashCode() {
//		int result = 0;
//		result = BLLR_CATG_ID;
//		return result;
//	}

}
