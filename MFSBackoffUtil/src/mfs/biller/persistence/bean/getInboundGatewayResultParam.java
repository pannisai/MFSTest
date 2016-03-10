package mfs.biller.persistence.bean;

import java.io.Serializable;

public class getInboundGatewayResultParam  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8271776217609982665L;
	private Integer BLLR_CATG_ID;
	private Integer BLLR_MSTR_ID;
	private Integer BLLR_SRVC_ID;
	private Integer INBN_SRVC_ID;
	private Integer SRCE_SRVC_ID;
	private Integer DEST_SRVC_ID;
	private Integer PAGE_NO; 
	private Integer PAGE_SIZE;
	private String BLLR_SRVC_CODE;
	private String ACT_FLAG;
	
	
	public String getACT_FLAG() {
		return ACT_FLAG;
	}
	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}
	public Integer getBLLR_CATG_ID() {
		return BLLR_CATG_ID;
	}
	public void setBLLR_CATG_ID(Integer bLLR_CATG_ID) {
		BLLR_CATG_ID = bLLR_CATG_ID;
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
	public Integer getBLLR_MSTR_ID() {
		return BLLR_MSTR_ID;
	}
	public void setBLLR_MSTR_ID(Integer bLLR_MSTR_ID) {
		BLLR_MSTR_ID = bLLR_MSTR_ID;
	}
	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}
	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
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
	public String getBLLR_SRVC_CODE() {
		return BLLR_SRVC_CODE;
	}
	public void setBLLR_SRVC_CODE(String bLLR_SRVC_CODE) {
		BLLR_SRVC_CODE = bLLR_SRVC_CODE;
	}
	
	public String toString() {
		return "BLLR_CATG_ID:" + BLLR_CATG_ID + "|BLLR_MSTR_ID:"
				+ BLLR_MSTR_ID + "|BLLR_SRVC_ID:" + BLLR_SRVC_ID
				+ "|INBN_SRVC_ID:" + INBN_SRVC_ID + "|SRCE_SRVC_ID:" + SRCE_SRVC_ID
				+ "|DEST_SRVC_ID:" + DEST_SRVC_ID + "|PAGE_NO:" + PAGE_NO
				+ "|PAGE_SIZE:" + PAGE_SIZE + "|BLLR_SRVC_CODE:"
				+ BLLR_SRVC_CODE+"|ACT_FLAG:"+ACT_FLAG;
	}
}
