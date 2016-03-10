package mfs.biller.persistence.bean;

import java.io.Serializable;

public class getInboundGatewayResult  implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7419616943071014190L;
	private String BLLR_SRVC_ID;
	private String INBN_SRVC_ID;
	private String BLLR_SRVC_DESC;
	private String BLLR_CODE;
	private String DEST_SRVC_ID;
	private String BLLR_MSTR_NAME;
	private String BLLR_CATG_NAME;
	private String BLLR_CATG_ID;
	private String SRCE_SRVC_ID;
	private String ACT_FLAG;
	private String LAST_CHNG_BY;
	private String LAST_CHNG_DTTM;
	private String BLLR_SRVC_CODE;
	private String GW_SRVC_NAME  ;
	private String DATA_MAP_NAME  ;
	private String INBN_SRVC_NAME;
	private String GW_SRVC_ID;
	private String GW_INBN_MAP_ID;
	
	
	public String getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}
	public void setBLLR_SRVC_ID(String bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}
	public String getINBN_SRVC_ID() {
		return INBN_SRVC_ID;
	}
	public void setINBN_SRVC_ID(String iNBN_SRVC_ID) {
		INBN_SRVC_ID = iNBN_SRVC_ID;
	}
	public String getBLLR_SRVC_DESC() {
		return BLLR_SRVC_DESC;
	}
	public void setBLLR_SRVC_DESC(String bLLR_SRVC_DESC) {
		BLLR_SRVC_DESC = bLLR_SRVC_DESC;
	}
	public String getBLLR_CODE() {
		return BLLR_CODE;
	}
	public void setBLLR_CODE(String bLLR_CODE) {
		BLLR_CODE = bLLR_CODE;
	}
	public String getDEST_SRVC_ID() {
		return DEST_SRVC_ID;
	}
	public void setDEST_SRVC_ID(String dEST_SRVC_ID) {
		DEST_SRVC_ID = dEST_SRVC_ID;
	}
	public String getBLLR_MSTR_NAME() {
		return BLLR_MSTR_NAME;
	}
	public void setBLLR_MSTR_NAME(String bLLR_MSTR_NAME) {
		BLLR_MSTR_NAME = bLLR_MSTR_NAME;
	}
	public String getBLLR_CATG_NAME() {
		return BLLR_CATG_NAME;
	}
	public void setBLLR_CATG_NAME(String bLLR_CATG_NAME) {
		BLLR_CATG_NAME = bLLR_CATG_NAME;
	}
	public String getBLLR_CATG_ID() {
		return BLLR_CATG_ID;
	}
	public void setBLLR_CATG_ID(String bLLR_CATG_ID) {
		BLLR_CATG_ID = bLLR_CATG_ID;
	}
	public String getSRCE_SRVC_ID() {
		return SRCE_SRVC_ID;
	}
	public void setSRCE_SRVC_ID(String sRCE_SRVC_ID) {
		SRCE_SRVC_ID = sRCE_SRVC_ID;
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
	
	public String getBLLR_SRVC_CODE() {
		return BLLR_SRVC_CODE;
	}
	public void setBLLR_SRVC_CODE(String bLLR_SRVC_CODE) {
		BLLR_SRVC_CODE = bLLR_SRVC_CODE;
	}
	
	public String getGW_SRVC_NAME() {
		return GW_SRVC_NAME;
	}
	public void setGW_SRVC_NAME(String gW_SRVC_NAME) {
		GW_SRVC_NAME = gW_SRVC_NAME;
	}
	public String getDATA_MAP_NAME() {
		return DATA_MAP_NAME;
	}
	public void setDATA_MAP_NAME(String dATA_MAP_NAME) {
		DATA_MAP_NAME = dATA_MAP_NAME;
	}
	
	public String getINBN_SRVC_NAME() {
		return INBN_SRVC_NAME;
	}
	public void setINBN_SRVC_NAME(String iNBN_SRVC_NAME) {
		INBN_SRVC_NAME = iNBN_SRVC_NAME;
	}
	public String getGW_SRVC_ID() {
		return GW_SRVC_ID;
	}
	public void setGW_SRVC_ID(String gW_SRVC_ID) {
		GW_SRVC_ID = gW_SRVC_ID;
	}
	public String getGW_INBN_MAP_ID() {
		return GW_INBN_MAP_ID;
	}
	public void setGW_INBN_MAP_ID(String gW_INBN_MAP_ID) {
		GW_INBN_MAP_ID = gW_INBN_MAP_ID;
	}
	public String toString() {
		return "BLLR_SRVC_ID:" + BLLR_SRVC_ID + "|INBN_SRVC_ID:"
				+ INBN_SRVC_ID + "|BLLR_SRVC_DESC:" + BLLR_SRVC_DESC
				+ "|BLLR_CODE:" + BLLR_CODE + "|DEST_SRVC_ID:" + DEST_SRVC_ID
				+ "|BLLR_MSTR_NAME:" + BLLR_MSTR_NAME + "|BLLR_CATG_NAME:" + BLLR_CATG_NAME
				+ "|BLLR_CATG_ID:" + BLLR_CATG_ID + "|SRCE_SRVC_ID:"
				+ SRCE_SRVC_ID+"|ACT_FLAG:" + ACT_FLAG 
				+ "|LAST_CHNG_BY:"+ LAST_CHNG_BY
				+"|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM 
				+"|BLLR_SRVC_CODE:" + BLLR_SRVC_CODE
				+"|DATA_MAP_NAME:" + DATA_MAP_NAME 
				+"|GW_SRVC_NAME:" + GW_SRVC_NAME 		
				+"|INBN_SRVC_NAME:" + INBN_SRVC_NAME 
				+"|GW_SRVC_ID:" + GW_SRVC_ID 
				+"|GW_INBN_MAP_ID:" + GW_INBN_MAP_ID ;
	}
	

}
