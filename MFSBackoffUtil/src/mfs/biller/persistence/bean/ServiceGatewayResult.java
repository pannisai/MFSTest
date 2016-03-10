package mfs.biller.persistence.bean;

import java.io.Serializable;

public class ServiceGatewayResult implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8727921495717852565L;
	private String GW_SRVC_ID;
	private String GW_SRVC_NAME;
	private String GW_SRVC_MAP_ID;
	private String GW_OUTB_ID;
	private String ACT_FLAG;
	private String LAST_CHNG_BY;
	private String LAST_CHNG_DTTM;
	private String DATA_MAP_NAME;
	
	private String GW_OUTB_NAME;
	public String getGW_SRVC_ID() {
		return GW_SRVC_ID;
	}
	public void setGW_SRVC_ID(String gW_SRVC_ID) {
		GW_SRVC_ID = gW_SRVC_ID;
	}
	public String getGW_SRVC_NAME() {
		return GW_SRVC_NAME;
	}
	public void setGW_SRVC_NAME(String gW_SRVC_NAME) {
		GW_SRVC_NAME = gW_SRVC_NAME;
	}
	public String getGW_SRVC_MAP_ID() {
		return GW_SRVC_MAP_ID;
	}
	public void setGW_SRVC_MAP_ID(String gW_SRVC_MAP_ID) {
		GW_SRVC_MAP_ID = gW_SRVC_MAP_ID;
	}
	public String getGW_OUTB_ID() {
		return GW_OUTB_ID;
	}
	public void setGW_OUTB_ID(String gW_OUTB_ID) {
		GW_OUTB_ID = gW_OUTB_ID;
	}
	public String getACT_FLAG() {
		return ACT_FLAG;
	}
	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}
	public String getLAST_CHNG_BY() {
		return LAST_CHNG_BY;
	}
	public void setLAST_CHNG_BY(String lAST_CHNG_BY) {
		LAST_CHNG_BY = lAST_CHNG_BY;
	}
	public String getLAST_CHNG_DTTM() {
		return LAST_CHNG_DTTM;
	}
	public void setLAST_CHNG_DTTM(String lAST_CHNG_DTTM) {
		LAST_CHNG_DTTM = lAST_CHNG_DTTM;
	}
	public String getGW_OUTB_NAME() {
		return GW_OUTB_NAME;
	}
	public void setGW_OUTB_NAME(String gW_OUTB_NAME) {
		GW_OUTB_NAME = gW_OUTB_NAME;
	}
	public String getDATA_MAP_NAME() {
		return DATA_MAP_NAME;
	}
	public void setDATA_MAP_NAME(String dATA_MAP_NAME) {
		DATA_MAP_NAME = dATA_MAP_NAME;
	}

}
