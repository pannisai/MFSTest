package mfs.biller.persistence.bean;

import java.io.Serializable;

public class BillerBarcodeDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer REF_ID;
	private String REF_LABL_TH;
	private String REF_LABL_EN;
	private Integer BARC_ID;
	private String BARC_NAME;
	private Integer BARC_REF_ID;
	private Integer BARC_LINE_ID;
	private String BARC_PART_FLAG;
	private Integer BARC_PART_STAT;
	private Integer BARC_PART_LENT;
	private String BARC_REMV_CHAR;
	private String ACT_FLAG;
	
	public String toString() {
		return "REF_ID:" + REF_ID 
				+ "|REF_LABL_TH:" + REF_LABL_TH 
				+ "|REF_LABL_EN:" + REF_LABL_EN 
				+ "|BARC_ID:" + BARC_ID 
				+ "|BARC_NAME:" + BARC_NAME 
				+ "|BARC_REF_ID:" + BARC_REF_ID 
				+ "|BARC_LINE_ID:" + BARC_LINE_ID 
				+ "|BARC_PART_FLAG:" + BARC_PART_FLAG 
				+ "|BARC_PART_STAT:" + BARC_PART_STAT 
				+ "|BARC_PART_LENT:" + BARC_PART_LENT 
				+ "|BARC_REMV_CHAR:" + BARC_REMV_CHAR 
				+ "|ACT_FLAG:" + ACT_FLAG;
	}

	public Integer getREF_ID() {
		return REF_ID;
	}

	public void setREF_ID(Integer rEF_ID) {
		REF_ID = rEF_ID;
	}

	public String getREF_LABL_TH() {
		return REF_LABL_TH;
	}

	public void setREF_LABL_TH(String rEF_LABL_TH) {
		REF_LABL_TH = rEF_LABL_TH;
	}

	public String getREF_LABL_EN() {
		return REF_LABL_EN;
	}

	public void setREF_LABL_EN(String rEF_LABL_EN) {
		REF_LABL_EN = rEF_LABL_EN;
	}

	public Integer getBARC_ID() {
		return BARC_ID;
	}

	public void setBARC_ID(Integer bARC_ID) {
		BARC_ID = bARC_ID;
	}

	public String getBARC_NAME() {
		return BARC_NAME;
	}

	public void setBARC_NAME(String bARC_NAME) {
		BARC_NAME = bARC_NAME;
	}

	public Integer getBARC_REF_ID() {
		return BARC_REF_ID;
	}

	public void setBARC_REF_ID(Integer bARC_REF_ID) {
		BARC_REF_ID = bARC_REF_ID;
	}

	public Integer getBARC_LINE_ID() {
		return BARC_LINE_ID;
	}

	public void setBARC_LINE_ID(Integer bARC_LINE_ID) {
		BARC_LINE_ID = bARC_LINE_ID;
	}

	public String getBARC_PART_FLAG() {
		return BARC_PART_FLAG;
	}

	public void setBARC_PART_FLAG(String bARC_PART_FLAG) {
		BARC_PART_FLAG = bARC_PART_FLAG;
	}

	public Integer getBARC_PART_STAT() {
		return BARC_PART_STAT;
	}

	public void setBARC_PART_STAT(Integer bARC_PART_STAT) {
		BARC_PART_STAT = bARC_PART_STAT;
	}

	public Integer getBARC_PART_LENT() {
		return BARC_PART_LENT;
	}

	public void setBARC_PART_LENT(Integer bARC_PART_LENT) {
		BARC_PART_LENT = bARC_PART_LENT;
	}

	public String getBARC_REMV_CHAR() {
		return BARC_REMV_CHAR;
	}

	public void setBARC_REMV_CHAR(String bARC_REMV_CHAR) {
		BARC_REMV_CHAR = bARC_REMV_CHAR;
	}

	public String getACT_FLAG() {
		return ACT_FLAG;
	}

	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}
	
}
