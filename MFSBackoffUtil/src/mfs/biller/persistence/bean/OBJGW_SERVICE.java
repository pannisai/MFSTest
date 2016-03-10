package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class OBJGW_SERVICE implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1370037236031907312L;
@Id
	private Integer GW_SRVC_ID;

 	@Column(name="GW_OUTB_ID")
	private Integer GW_OUTB_ID;
	
	@Column(name="GW_SRVC_NAME")
	private String GW_SRVC_NAME;
	
	@Column(name="GW_SRVC_MAP_ID")
	private Integer GW_SRVC_MAP_ID;
	
	@Column(name="ACT_FLAG")
	private String ACT_FLAG;
	
	@Column(name="CRTD_BY")
	private String CRTD_BY;

	@Temporal(TemporalType.DATE)
	@Column(name="CRTD_DTTM")
	private Date CRTD_DTTM;

	@Column(name="LAST_CHNG_BY")
	private String LAST_CHNG_BY;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_CHNG_DTTM")
	private Date LAST_CHNG_DTTM;
	
	@Column(name="GW_CHCK_ACL_FLAG")
	private String GW_CHCK_ACL_FLAG;
	
	@Column(name="GW_CHCK_AMNT_FLAG")
	private String GW_CHCK_AMNT_FLAG;
	
	@Column(name="GW_CHCK_DUE_DATE_FLAG")
	private String GW_CHCK_DUE_DATE_FLAG;

	public Integer getGW_SRVC_ID() {
		return GW_SRVC_ID;
	}

	public void setGW_SRVC_ID(Integer gW_SRVC_ID) {
		GW_SRVC_ID = gW_SRVC_ID;
	}

	public Integer getGW_OUTB_ID() {
		return GW_OUTB_ID;
	}

	public void setGW_OUTB_ID(Integer gW_OUTB_ID) {
		GW_OUTB_ID = gW_OUTB_ID;
	}

	public String getGW_SRVC_NAME() {
		return GW_SRVC_NAME;
	}

	public void setGW_SRVC_NAME(String gW_SRVC_NAME) {
		GW_SRVC_NAME = gW_SRVC_NAME;
	}

	public Integer getGW_SRVC_MAP_ID() {
		return GW_SRVC_MAP_ID;
	}

	public void setGW_SRVC_MAP_ID(Integer gW_SRVC_MAP_ID) {
		GW_SRVC_MAP_ID = gW_SRVC_MAP_ID;
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

	public String getGW_CHCK_ACL_FLAG() {
		return GW_CHCK_ACL_FLAG;
	}

	public void setGW_CHCK_ACL_FLAG(String gW_CHCK_ACL_FLAG) {
		GW_CHCK_ACL_FLAG = gW_CHCK_ACL_FLAG;
	}

	public String getGW_CHCK_AMNT_FLAG() {
		return GW_CHCK_AMNT_FLAG;
	}

	public void setGW_CHCK_AMNT_FLAG(String gW_CHCK_AMNT_FLAG) {
		GW_CHCK_AMNT_FLAG = gW_CHCK_AMNT_FLAG;
	}

	public String getGW_CHCK_DUE_DATE_FLAG() {
		return GW_CHCK_DUE_DATE_FLAG;
	}

	public void setGW_CHCK_DUE_DATE_FLAG(String gW_CHCK_DUE_DATE_FLAG) {
		GW_CHCK_DUE_DATE_FLAG = gW_CHCK_DUE_DATE_FLAG;
	}
	


}
