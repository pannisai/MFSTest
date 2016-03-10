package mfs.biller.persistence.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Table(name="BILLER_REF_DATA_TYPE")
@Cache(type=CacheType.NONE)
public class BillerRefDataType implements Serializable{
	@Id
	@Column(name = "BILL_REF_DATA_TYPE_ID", nullable = false)
	private Integer BILL_REF_DATA_TYPE_ID;
	
	@Column(name = "BILL_REF_DATA_TYPE_NAME", nullable = true)
	private String BILL_REF_DATA_TYPE_NAME;
	
	@Column(name = "BILL_REF_DATA_TYPE_FOMT", nullable = true)
	private String BILL_REF_DATA_TYPE_FOMT;
	
	@Column(name = "ACT_FLAG", nullable = true)
	private String ACT_FLAG;
	
	public String toString() {
		return "BILL_REF_DATA_TYPE_ID:" + BILL_REF_DATA_TYPE_ID 
				+ "|BILL_REF_DATA_TYPE_NAME:" + BILL_REF_DATA_TYPE_NAME 
				+ "|BILL_REF_DATA_TYPE_FOMT:" + BILL_REF_DATA_TYPE_FOMT 
				+ "|ACT_FLAG:" + ACT_FLAG ;
				
	}

	public Integer getBILL_REF_DATA_TYPE_ID() {
		return BILL_REF_DATA_TYPE_ID;
	}

	public void setBILL_REF_DATA_TYPE_ID(Integer bILL_REF_DATA_TYPE_ID) {
		BILL_REF_DATA_TYPE_ID = bILL_REF_DATA_TYPE_ID;
	}

	public String getBILL_REF_DATA_TYPE_NAME() {
		return BILL_REF_DATA_TYPE_NAME;
	}

	public void setBILL_REF_DATA_TYPE_NAME(String bILL_REF_DATA_TYPE_NAME) {
		BILL_REF_DATA_TYPE_NAME = bILL_REF_DATA_TYPE_NAME;
	}

	public String getBILL_REF_DATA_TYPE_FOMT() {
		return BILL_REF_DATA_TYPE_FOMT;
	}

	public void setBILL_REF_DATA_TYPE_FOMT(String bILL_REF_DATA_TYPE_FOMT) {
		BILL_REF_DATA_TYPE_FOMT = bILL_REF_DATA_TYPE_FOMT;
	}

	public String getACT_FLAG() {
		return ACT_FLAG;
	}

	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}
	
}
