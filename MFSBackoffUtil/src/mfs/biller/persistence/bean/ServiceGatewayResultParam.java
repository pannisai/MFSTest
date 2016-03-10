package mfs.biller.persistence.bean;

import java.io.Serializable;

public class ServiceGatewayResultParam  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5563976743565476060L;
	private Integer PAGE_NO; 
	private Integer PAGE_SIZE;
	private Integer GW_SRVC_ID;
	private Integer GW_OUTB_ID;
	private String ACT_FLAG;
	
	public String getACT_FLAG() {
		return ACT_FLAG;
	}
	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
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
	public String toString() {
		return "PAGE_NO:" + PAGE_NO 
				+ "|PAGE_SIZE:"+ PAGE_SIZE 
				+ "|GW_SRVC_ID:"+ GW_SRVC_ID 
				+ "|GW_OUTB_ID:" + GW_OUTB_ID
				+ "|ACT_FLAG:" + ACT_FLAG ;
	}

}
