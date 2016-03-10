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
@IdClass(value = GWRcptConfPK.class)
@Table(name = "GW_RCPT_CONF")
@Cache(type = CacheType.NONE)
public class GWRcptConf implements Serializable {
    
	private static final long serialVersionUID = 1L;
	@Id
	private Integer  GW_RCPT_CONF_ID;
	
	@Column(name = "GW_RCPT_MAP_ID", nullable = false)
	private Integer GW_RCPT_MAP_ID;
	
	@Column(name = "GW_RCPT_URL", nullable = false)
	private String GW_RCPT_URL;

	@Column(name = "ACT_FLAG", nullable = false)
	private String ACT_FLAG;
	
	@Column(name = "CRTD_BY", nullable = false)
	private String CRTD_BY;

	@Column(name = "CRTD_DTTM", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date CRTD_DTTM;
	
	@Column(name = "LAST_CHNG_BY", nullable = false)
	private String LAST_CHNG_BY;

	@Column(name = "LAST_CHNG_DTTM", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date LAST_CHNG_DTTM;
	
	@Column(name = "GW_RCPT_CONF_NAME", nullable = false)
	private String GW_RCPT_CONF_NAME;

	public String toString() {
		return GW_RCPT_CONF_ID+"|"+GW_RCPT_MAP_ID+"|"+GW_RCPT_URL+
				ACT_FLAG+"|"+CRTD_BY+"|"+CRTD_DTTM+"|"+LAST_CHNG_BY+"|"+LAST_CHNG_DTTM+"|"+
				GW_RCPT_CONF_NAME;
	}

	public Integer getGW_RCPT_CONF_ID() {
		return GW_RCPT_CONF_ID;
	}

	public void setGW_RCPT_CONF_ID(Integer gW_RCPT_CONF_ID) {
		GW_RCPT_CONF_ID = gW_RCPT_CONF_ID;
	}

	public Integer getGW_RCPT_MAP_ID() {
		return GW_RCPT_MAP_ID;
	}

	public void setGW_RCPT_MAP_ID(Integer gW_RCPT_MAP_ID) {
		GW_RCPT_MAP_ID = gW_RCPT_MAP_ID;
	}

	public String getGW_RCPT_URL() {
		return GW_RCPT_URL;
	}

	public void setGW_RCPT_URL(String gW_RCPT_URL) {
		GW_RCPT_URL = gW_RCPT_URL;
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

	public String getGW_RCPT_CONF_NAME() {
		return GW_RCPT_CONF_NAME;
	}

	public void setGW_RCPT_CONF_NAME(String gW_RCPT_CONF_NAME) {
		GW_RCPT_CONF_NAME = gW_RCPT_CONF_NAME;
	}
}
