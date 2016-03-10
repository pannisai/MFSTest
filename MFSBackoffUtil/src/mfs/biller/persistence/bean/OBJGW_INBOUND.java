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

@Table(name = "GW_INBOUND")
@Cache(type = CacheType.NONE)
public class OBJGW_INBOUND  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 812907950693125443L;

	@Id
	private Integer INBN_SRVC_ID;
	
	@Column(name="SRCE_SRVC_ID")
	private Integer SRCE_SRVC_ID;

	@Column(name="DEST_SRVC_ID")
	private Integer DEST_SRVC_ID;

	@Column(name="INBN_SRVC_NAME")
	private String INBN_SRVC_NAME;

	@Column(name="GW_SRVC_ID")
	private Integer GW_SRVC_ID;

	@Column(name="GW_INBN_MAP_ID")
	private int GW_INBN_MAP_ID ;

	@Column(name="SEND_RCPT_FLAG")
	private String SEND_RCPT_FLAG;

	@Temporal(TemporalType.DATE)
	@Column(name="STRT_DATE")
	private Date STRT_DATE;

	
	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date END_DATE;

	@Column(name="ACT_FLAG")
	private String ACT_FLAG;

	@Column(name="CRTD_BY")
	private String CRTD_BY;
	
	@Temporal(TemporalType.DATE)
	@Column(name="CRTD_DTTM")
	private Date CRTD_DTTM ;
	

	@Column(name="LAST_CHNG_BY")
	private String LAST_CHNG_BY;

	@Column(name="LAST_CHNG_DTTM")
	@Temporal(TemporalType.DATE)
	private Date LAST_CHNG_DTTM;

	@Column(name="GW_RCPT_CONF_ID")
	private Integer GW_RCPT_CONF_ID;
	
	
	public String toString(){
		return "INBN_SRVC_ID:" + INBN_SRVC_ID 
				+ "|SRCE_SRVC_ID:" + SRCE_SRVC_ID 
				+ "|DEST_SRVC_ID:" + DEST_SRVC_ID 
				+ "|INBN_SRVC_NAME:" + INBN_SRVC_NAME 
				+ "|GW_SRVC_ID:" + GW_SRVC_ID 
				+ "|GW_INBN_MAP_ID:" + GW_INBN_MAP_ID 
				+ "|SEND_RCPT_FLAG:" + SEND_RCPT_FLAG 
				+ "|STRT_DATE:" + STRT_DATE 
				+ "|END_DATE:" + END_DATE 
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|CRTD_BY:" + CRTD_BY 
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM
		+ "|GW_RCPT_CONF_ID:" + GW_RCPT_CONF_ID;
	}


	public Integer getINBN_SRVC_ID() {
		return INBN_SRVC_ID;
	}

	public void setINBN_SRVC_ID(Integer iNBN_SRVC_ID) {
		INBN_SRVC_ID = iNBN_SRVC_ID;
	}

	public Integer getSRCE_SRVC_ID() {
		return SRCE_SRVC_ID;
	}

	public void setSRCE_SRVC_ID(Integer sRCE_SRVC_ID) {
		SRCE_SRVC_ID = sRCE_SRVC_ID;
	}

	public Integer getDEST_SRVC_ID() {
		return DEST_SRVC_ID;
	}

	public void setDEST_SRVC_ID(Integer dEST_SRVC_ID) {
		DEST_SRVC_ID = dEST_SRVC_ID;
	}

	public String getINBN_SRVC_NAME() {
		return INBN_SRVC_NAME;
	}

	public void setINBN_SRVC_NAME(String iNBN_SRVC_NAME) {
		INBN_SRVC_NAME = iNBN_SRVC_NAME;
	}

	public Integer getGW_SRVC_ID() {
		return GW_SRVC_ID;
	}

	public void setGW_SRVC_ID(Integer gW_SRVC_ID) {
		GW_SRVC_ID = gW_SRVC_ID;
	}

	public int getGW_INBN_MAP_ID() {
		return GW_INBN_MAP_ID;
	}

	public void setGW_INBN_MAP_ID(int gW_INBN_MAP_ID) {
		GW_INBN_MAP_ID = gW_INBN_MAP_ID;
	}

	public String getSEND_RCPT_FLAG() {
		return SEND_RCPT_FLAG;
	}

	public void setSEND_RCPT_FLAG(String sEND_RCPT_FLAG) {
		SEND_RCPT_FLAG = sEND_RCPT_FLAG;
	}

	public Date getSTRT_DATE() {
		return STRT_DATE;
	}

	public void setSTRT_DATE(Date sTRT_DATE) {
		STRT_DATE = sTRT_DATE;
	}

	public Date getEND_DATE() {
		return END_DATE;
	}

	public void setEND_DATE(Date eND_DATE) {
		END_DATE = eND_DATE;
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

	public Integer getGW_RCPT_CONF_ID() {
		return GW_RCPT_CONF_ID;
	}

	public void setGW_RCPT_CONF_ID(Integer gW_RCPT_CONF_ID) {
		GW_RCPT_CONF_ID = gW_RCPT_CONF_ID;
	}
	




}
