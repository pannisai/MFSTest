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
@Table(name = "GW_BANK")
@Cache(type = CacheType.NONE)
public class GWBankBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5389635130626900216L;

	@Id
	private Integer  BANK_SRVC_ID         ;
	
	@Column(name = "INB_MAP_ID")
	private Integer INB_MAP_ID          ;
	
	@Column(name = "GW_MAP_ID")
	private Integer  GW_MAP_ID  ;
	
	@Column(name = "OUTB_MAP_ID")
	private Integer OUTB_MAP_ID  ;
	
	@Column(name = "ACT_FLAG")
	private String ACT_FLAG           ;
	
	@Column(name = "CRTD_BY")
	private String CRTD_BY            ;
	
	@Column(name = "CRTD_DTTM")
	@Temporal(TemporalType.TIMESTAMP)
	private  Date CRTD_DTTM          ;
	
	@Column(name = "LAST_CHNG_BY")
	private  String LAST_CHNG_BY       ;
	
	@Column(name = "LAST_CHNG_DTTM")
	@Temporal(TemporalType.TIMESTAMP)
	private  Date LAST_CHNG_DTTM     ;


	
	

	public Integer getBANK_SRVC_ID() {
		return BANK_SRVC_ID;
	}





	public void setBANK_SRVC_ID(Integer bANK_SRVC_ID) {
		BANK_SRVC_ID = bANK_SRVC_ID;
	}





	public Integer getINB_MAP_ID() {
		return INB_MAP_ID;
	}





	public void setINB_MAP_ID(Integer iNB_MAP_ID) {
		INB_MAP_ID = iNB_MAP_ID;
	}





	public Integer getGW_MAP_ID() {
		return GW_MAP_ID;
	}





	public void setGW_MAP_ID(Integer gW_MAP_ID) {
		GW_MAP_ID = gW_MAP_ID;
	}





	public Integer getOUTB_MAP_ID() {
		return OUTB_MAP_ID;
	}





	public void setOUTB_MAP_ID(Integer oUTB_MAP_ID) {
		OUTB_MAP_ID = oUTB_MAP_ID;
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





	public String toString(){
		return "BANK_SRVC_ID:" + BANK_SRVC_ID 
				+ "|INB_MAP_ID:" + INB_MAP_ID 
				+ "|GW_MAP_ID:" + GW_MAP_ID 
				+ "|OUTB_MAP_ID:" + OUTB_MAP_ID 
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|CRTD_BY:" + CRTD_BY 
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM;
	}

}
