package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Table(name="BANK_FDM_CODE")
@Cache(type=CacheType.NONE)
public class BankFdmCode implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BANK_MSGE_CODE", nullable=false)
	private String BANK_MSGE_CODE;
	
	@Column(name="BANK_MSGE_CODE_MSGE_TH", nullable=true)
	private String BANK_MSGE_CODE_MSGE_TH;
	
	@Column(name="BANK_MSGE_CODE_MSGE_EN", nullable=true)
	private String BANK_MSGE_CODE_MSGE_EN;
	
	@Column(name="BANK_MSGE_CODE_TYPE", nullable=true)
	private String BANK_MSGE_CODE_TYPE;
	
	@Column(name="BANK_MSGE_CODE_RETY_FLAG", nullable=true)
	private String BANK_MSGE_CODE_RETY_FLAG;
		
	@Column(name="ACT_FLAG", nullable=true)
	private String ACT_FLAG;
	
	@Column(name="CRTD_BY", nullable=true)
    private String CRTD_BY;
	
	@Column(name="CRTD_DTTM", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
    private Date CRTD_DTTM;
	
	@Column(name="LAST_CHNG_BY", nullable=true)
    private String LAST_CHNG_BY;
	
	@Column(name="LAST_CHNG_DTTM", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
    private Date LAST_CHNG_DTTM;
	
	public String toString() {
		return "BANK_MSGE_CODE:" + BANK_MSGE_CODE 
				+ "|BANK_MSGE_CODE_MSGE_TH:" + BANK_MSGE_CODE_MSGE_TH 
				+ "|BANK_MSGE_CODE_MSGE_EN:" + BANK_MSGE_CODE_MSGE_EN 
				+ "|BANK_MSGE_CODE_TYPE:" + BANK_MSGE_CODE_TYPE 
				+ "|BANK_MSGE_CODE_RETY_FLAG:" + BANK_MSGE_CODE_RETY_FLAG 
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|CRTD_BY:" + CRTD_BY 
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM;
	}

	

	public String getBANK_MSGE_CODE() {
		return BANK_MSGE_CODE;
	}



	public void setBANK_MSGE_CODE(String bANK_MSGE_CODE) {
		BANK_MSGE_CODE = bANK_MSGE_CODE;
	}



	public String getBANK_MSGE_CODE_MSGE_TH() {
		return BANK_MSGE_CODE_MSGE_TH;
	}



	public void setBANK_MSGE_CODE_MSGE_TH(String bANK_MSGE_CODE_MSGE_TH) {
		BANK_MSGE_CODE_MSGE_TH = bANK_MSGE_CODE_MSGE_TH;
	}



	public String getBANK_MSGE_CODE_MSGE_EN() {
		return BANK_MSGE_CODE_MSGE_EN;
	}



	public void setBANK_MSGE_CODE_MSGE_EN(String bANK_MSGE_CODE_MSGE_EN) {
		BANK_MSGE_CODE_MSGE_EN = bANK_MSGE_CODE_MSGE_EN;
	}



	public String getBANK_MSGE_CODE_TYPE() {
		return BANK_MSGE_CODE_TYPE;
	}



	public void setBANK_MSGE_CODE_TYPE(String bANK_MSGE_CODE_TYPE) {
		BANK_MSGE_CODE_TYPE = bANK_MSGE_CODE_TYPE;
	}



	public String getBANK_MSGE_CODE_RETY_FLAG() {
		return BANK_MSGE_CODE_RETY_FLAG;
	}



	public void setBANK_MSGE_CODE_RETY_FLAG(String bANK_MSGE_CODE_RETY_FLAG) {
		BANK_MSGE_CODE_RETY_FLAG = bANK_MSGE_CODE_RETY_FLAG;
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
