package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.Date;



public class GWOutboundDtail implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private Integer  GW_OUTB_ID;	
	
	private String GW_OUTB_NAME;	
	
	private Integer GW_OUTB_MAP_ID;	
	
	private String ACT_FLAG;	
	
	private String CRTD_BY;

	private Date CRTD_DTTM;	
	
	private String LAST_CHNG_BY;

	private Date LAST_CHNG_DTTM;
	private String DATA_MAP_NAME;

	public Integer getGW_OUTB_ID() {
		return GW_OUTB_ID;
	}

	public void setGW_OUTB_ID(Integer gW_OUTB_ID) {
		GW_OUTB_ID = gW_OUTB_ID;
	}

	public String getGW_OUTB_NAME() {
		return GW_OUTB_NAME;
	}

	public void setGW_OUTB_NAME(String gW_OUTB_NAME) {
		GW_OUTB_NAME = gW_OUTB_NAME;
	}

	public Integer getGW_OUTB_MAP_ID() {
		return GW_OUTB_MAP_ID;
	}

	public void setGW_OUTB_MAP_ID(Integer gW_OUTB_MAP_ID) {
		GW_OUTB_MAP_ID = gW_OUTB_MAP_ID;
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

	public String getDATA_MAP_NAME() {
		return DATA_MAP_NAME;
	}

	public void setDATA_MAP_NAME(String dATA_MAP_NAME) {
		DATA_MAP_NAME = dATA_MAP_NAME;
	}

	public String toString() {
		return GW_OUTB_NAME+"|"+GW_OUTB_NAME+"|"+GW_OUTB_MAP_ID+"|"+ACT_FLAG+"|"+CRTD_BY+"|"+CRTD_DTTM+"|"+LAST_CHNG_BY+"|"+LAST_CHNG_DTTM +"|"+DATA_MAP_NAME+"|"+DATA_MAP_NAME ;
	}
}
