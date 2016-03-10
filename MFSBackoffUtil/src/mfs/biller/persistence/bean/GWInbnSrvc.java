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
@IdClass(value = GWInbnSrvcPK.class)
@Table(name = "GW_INBN_SRVC")
@Cache(type = CacheType.NONE)
public class GWInbnSrvc implements Serializable {
    
	private static final long serialVersionUID = 1L;
	@Id
	private Integer  INBN_SRVC_ID;
	
	@Column(name = "INBN_SRVC_CODE", nullable = false)
	private String INBN_SRVC_CODE;
	
	@Column(name = "INBN_SRVC_NAME", nullable = false)
	private String INBN_SRVC_NAME;
	
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

	public String toString() {
		return INBN_SRVC_ID+"|"+INBN_SRVC_CODE+"|"+INBN_SRVC_NAME+"|"+ACT_FLAG+"|"+CRTD_BY+"|"+CRTD_DTTM+"|"+LAST_CHNG_BY+"|"+LAST_CHNG_DTTM;
	}

	public Integer getINBN_SRVC_ID() {
		return INBN_SRVC_ID;
	}

	public void setINBN_SRVC_ID(Integer iNBN_SRVC_ID) {
		INBN_SRVC_ID = iNBN_SRVC_ID;
	}

	public String getINBN_SRVC_CODE() {
		return INBN_SRVC_CODE;
	}

	public void setINBN_SRVC_CODE(String iNBN_SRVC_CODE) {
		INBN_SRVC_CODE = iNBN_SRVC_CODE;
	}

	public String getINBN_SRVC_NAME() {
		return INBN_SRVC_NAME;
	}

	public void setINBN_SRVC_NAME(String iNBN_SRVC_NAME) {
		INBN_SRVC_NAME = iNBN_SRVC_NAME;
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
