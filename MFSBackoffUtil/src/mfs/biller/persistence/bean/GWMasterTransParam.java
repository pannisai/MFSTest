package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GWMasterTransParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private String  TRNS_ID;
	private Date FROM_DTTM;
	private Date TO_DTTM;
	private String TRNS_DEST_CODE ;
	private String TRNS_SRCE_CHNL_CODE;
	private String TRNS_STTS_CODE;
	private String TABLE_NAME;
	private Integer PAGE_NO; 
	private Integer PAGE_SIZE;
	private String TRNS_SRVC_CODE;
	private String BRANCH;
	private String EXPORT;
	private String CHANNEL_SERVICE;
	private String NETWORK_SERVICE;
	private List Listparam =new ArrayList<String>();
	
	public List getListparam() {
		return Listparam;
	}
	public void setListparam(List listparam) {
		Listparam = listparam;
	}
	public String getTRNS_SRVC_CODE() {
		return TRNS_SRVC_CODE;
	}
	public void setTRNS_SRVC_CODE(String tRNS_SRVC_CODE) {
		TRNS_SRVC_CODE = tRNS_SRVC_CODE;
	}
	public String getTRNS_ID() {
		return TRNS_ID;
	}
	public void setTRNS_ID(String tRNS_ID) {
		TRNS_ID = tRNS_ID;
	}
	public Date getFROM_DTTM() {
		return FROM_DTTM;
	}
	public void setFROM_DTTM(Date fROM_DTTM) {
		FROM_DTTM = fROM_DTTM;
	}
	public Date getTO_DTTM() {
		return TO_DTTM;
	}
	public void setTO_DTTM(Date tO_DTTM) {
		TO_DTTM = tO_DTTM;
	}
	public String getTRNS_DEST_CODE() {
		return TRNS_DEST_CODE;
	}
	public void setTRNS_DEST_CODE(String tTRNS_DEST_CODE) {
		TRNS_DEST_CODE = tTRNS_DEST_CODE;
	}
	public String getTRNS_SRCE_CHNL_CODE() {
		return TRNS_SRCE_CHNL_CODE;
	}
	public void setTRNS_SRCE_CHNL_CODE(String tTRNS_SRCE_CHNL_CODE) {
		TRNS_SRCE_CHNL_CODE = tTRNS_SRCE_CHNL_CODE;
	}
	public String getTRNS_STTS_CODE() {
		return TRNS_STTS_CODE;
	}
	public void setTRNS_STTS_CODE(String tRNS_STTS_CODE) {
		TRNS_STTS_CODE = tRNS_STTS_CODE;
	}
	public Integer getPAGE_NO() {
		return PAGE_NO;
	}
	public void setPAGE_NO(Integer pAGE_NO) {
		PAGE_NO = pAGE_NO;
	}
	public Integer getPAGE_SIZE() {
		return PAGE_SIZE;
	}
	public void setPAGE_SIZE(Integer pAGE_SIZE) {
		PAGE_SIZE = pAGE_SIZE;
	}
	public String getTABLE_NAME() {
		return TABLE_NAME;
	}
	public void setTABLE_NAME(String tABLE_NAME) {
		TABLE_NAME = tABLE_NAME;
	}
	public String getBRANCH() {
		return BRANCH;
	}
	public void setBRANCH(String bRANCH) {
		BRANCH = bRANCH;
	}
	public String toString() {
		return "TRNS_ID:"+TRNS_ID
				+"|"+"FROM_DTTM:"+FROM_DTTM
				+"|"+"TO_DTTM:"+TO_DTTM
				+"|"+"TRNS_SRCE_CHNL_CODE:"+TRNS_SRCE_CHNL_CODE
				+"|"+"TRNS_STTS_CODE:"+TRNS_STTS_CODE
				+"|"+"TABLE_NAME:"+TABLE_NAME
				+"|"+"PAGE_NO:"+PAGE_NO
				+"|"+"PAGE_SIZE:"+PAGE_SIZE
				+"|"+"BRANCH:"+BRANCH;
	}
	public String getEXPORT() {
		return EXPORT;
	}
	public void setEXPORT(String eXPORT) {
		EXPORT = eXPORT;
	}
	public String getCHANNEL_SERVICE() {
		return CHANNEL_SERVICE;
	}
	public void setCHANNEL_SERVICE(String cHANNEL_SERVICE) {
		CHANNEL_SERVICE = cHANNEL_SERVICE;
	}
	public String getNETWORK_SERVICE() {
		return NETWORK_SERVICE;
	}
	public void setNETWORK_SERVICE(String nETWORK_SERVICE) {
		NETWORK_SERVICE = nETWORK_SERVICE;
	}
	
}
