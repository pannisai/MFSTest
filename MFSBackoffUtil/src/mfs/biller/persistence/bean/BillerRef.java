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
@Table(name="BILLER_REF")
@Cache(type=CacheType.NONE)
public class BillerRef implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REF_ID", nullable=false)
	private Integer REF_ID;
	
	@Column(name="REF_LABL_TH", nullable=true)
	private String REF_LABL_TH;
	
	@Column(name="REF_LABL_EN", nullable=true)
	private String REF_LABL_EN;
	
	@Column(name="REF_REQU_FLAG", nullable=true)
	private String REF_REQU_FLAG;
	
	@Column(name="REF_ACT_FLAG", nullable=true)
	private String REF_ACT_FLAG;
	
	@Column(name="REF_TYPE", nullable=true)
	private String REF_TYPE;	
	
	@Column(name="REF_DEFT_VALE", nullable=true)
	private String REF_DEFT_VALE;
	
	@Column(name="REF_VALE_LENT_MAX", nullable=true)
	private Integer REF_VALE_LENT_MAX;
	
	@Column(name="REF_VALE_LENT_MIN", nullable=true)
	private Integer REF_VALE_LENT_MIN;
	
	@Column(name="REF_HIDN_FLAG", nullable=true)
	private String REF_HIDN_FLAG;
	
	@Column(name="BLLR_SRVC_ID", nullable=true)
	private Integer BLLR_SRVC_ID;
	
	@Column(name="BLLR_FORM_ID", nullable=false)
	private Integer BLLR_FORM_ID;
	
	@Column(name="REF_SEQ", nullable=true)
	private Integer REF_SEQ;
	
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
	
	@Column(name="REF_ALLW_KEY_IN", nullable=true)
	private String REF_ALLW_KEY_IN ;
	
	@Column(name="REF_PRIM_FLAG", nullable=true)
	private String REF_PRIM_FLAG ;
	
	@Column(name="REF_DATA_TYPE", nullable=true)
	private String REF_DATA_TYPE ;
	
	@Column(name="REF_DATA_FOMT", nullable=true)
	private String REF_DATA_FOMT ;
	
	@Column(name="REF_DUP_FLAG", nullable=true)
	private String REF_DUP_FLAG ;
	
	@Column(name="REF_BLIND_FOMT", nullable=true)
	private String REF_BLIND_FOMT ;
	
	@Column(name="REF_SRCH_FLAG", nullable=true)
	private String REF_SRCH_FLAG ;
	
	@Column(name="ENBL_DFLT_VALU", nullable=true)
	private String ENBL_DFLT_VALU ;
	
	@Column(name="DFLT_BLNK_VALU", nullable=true)
	private String DFLT_BLNK_VALU ;
	
	@Column(name="REF_SMS_FLAG", nullable=true)
	private String REF_SMS_FLAG ;
	
	public String toString() {
		return "REF_ID:" + REF_ID 
				+ "|REF_LABL_TH:" + REF_LABL_TH 
				+ "|REF_LABL_EN:" + REF_LABL_EN 
				+ "|REF_REQU_FLAG:" + REF_REQU_FLAG 
				+ "|REF_ACT_FLAG:" + REF_ACT_FLAG 
				+ "|REF_TYPE:" + REF_TYPE 
				+ "|REF_DEFT_VALE:" + REF_DEFT_VALE 
				+ "|REF_VALE_LENT_MAX:" + REF_VALE_LENT_MAX 
				+ "|REF_VALE_LENT_MIN:" + REF_VALE_LENT_MIN 
				+ "|REF_HIDN_FLAG:" + REF_HIDN_FLAG 
				+ "|BLLR_SRVC_ID:" + BLLR_SRVC_ID 
				+ "|BLLR_FORM_ID:" + BLLR_FORM_ID 
				+ "|REF_SEQ:" + REF_SEQ 
				+ "|REF_DATA_TYPE:" + REF_DATA_TYPE 
				+ "|REF_DATA_FOMT:" + REF_DATA_FOMT 
				+ "|REF_DUP_FLAG:" + REF_DUP_FLAG 
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|CRTD_BY:" + CRTD_BY 
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM
				+ "|REF_ALLW_KEY_IN:" + REF_ALLW_KEY_IN
				+ "|REF_PRIM_FLAG:" + REF_PRIM_FLAG		
				+ "|REF_BLIND_FOMT:" + REF_BLIND_FOMT
				+ "|REF_SRCH_FLAG:" + REF_SRCH_FLAG
				+ "|ENBL_DFLT_VALU:" + ENBL_DFLT_VALU
				+ "|DFLT_BLNK_VALU:" + DFLT_BLNK_VALU
				+ "|REF_SMS_FLAG:" + REF_SMS_FLAG;
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

	public String getREF_REQU_FLAG() {
		return REF_REQU_FLAG;
	}

	public void setREF_REQU_FLAG(String rEF_REQU_FLAG) {
		REF_REQU_FLAG = rEF_REQU_FLAG;
	}

	public String getREF_ACT_FLAG() {
		return REF_ACT_FLAG;
	}

	public void setREF_ACT_FLAG(String rEF_ACT_FLAG) {
		REF_ACT_FLAG = rEF_ACT_FLAG;
	}

	public String getREF_TYPE() {
		return REF_TYPE;
	}

	public void setREF_TYPE(String rEF_TYPE) {
		REF_TYPE = rEF_TYPE;
	}	

	public String getREF_DEFT_VALE() {
		return REF_DEFT_VALE;
	}

	public void setREF_DEFT_VALE(String rEF_DEFT_VALE) {
		REF_DEFT_VALE = rEF_DEFT_VALE;
	}

	public Integer getREF_VALE_LENT_MAX() {
		return REF_VALE_LENT_MAX;
	}

	public void setREF_VALE_LENT_MAX(Integer rEF_VALE_LENT_MAX) {
		REF_VALE_LENT_MAX = rEF_VALE_LENT_MAX;
	}

	public Integer getREF_VALE_LENT_MIN() {
		return REF_VALE_LENT_MIN;
	}

	public void setREF_VALE_LENT_MIN(Integer rEF_VALE_LENT_MIN) {
		REF_VALE_LENT_MIN = rEF_VALE_LENT_MIN;
	}

	public String getREF_HIDN_FLAG() {
		return REF_HIDN_FLAG;
	}

	public void setREF_HIDN_FLAG(String rEF_HIDN_FLAG) {
		REF_HIDN_FLAG = rEF_HIDN_FLAG;
	}

	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}

	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}

	public Integer getBLLR_FORM_ID() {
		return BLLR_FORM_ID;
	}

	public void setBLLR_FORM_ID(Integer bLLR_FORM_ID) {
		BLLR_FORM_ID = bLLR_FORM_ID;
	}

	public Integer getREF_SEQ() {
		return REF_SEQ;
	}

	public void setREF_SEQ(Integer rEF_SEQ) {
		REF_SEQ = rEF_SEQ;
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

	public String getREF_ALLW_KEY_IN() {
		return REF_ALLW_KEY_IN;
	}

	public void setREF_ALLW_KEY_IN(String rEF_ALLW_KEY_IN) {
		REF_ALLW_KEY_IN = rEF_ALLW_KEY_IN;
	}

	public String getREF_PRIM_FLAG() {
		return REF_PRIM_FLAG;
	}

	public void setREF_PRIM_FLAG(String rEF_PRIM_FLAG) {
		REF_PRIM_FLAG = rEF_PRIM_FLAG;
	}

	public String getREF_DATA_TYPE() {
		return REF_DATA_TYPE;
	}

	public void setREF_DATA_TYPE(String rEF_DATA_TYPE) {
		REF_DATA_TYPE = rEF_DATA_TYPE;
	}

	public String getREF_DATA_FOMT() {
		return REF_DATA_FOMT;
	}

	public void setREF_DATA_FOMT(String rEF_DATA_FOMT) {
		REF_DATA_FOMT = rEF_DATA_FOMT;
	}

	public String getREF_DUP_FLAG() {
		return REF_DUP_FLAG;
	}

	public void setREF_DUP_FLAG(String rEF_DUP_FLAG) {
		REF_DUP_FLAG = rEF_DUP_FLAG;
	}

	public String getREF_BLIND_FOMT() {
		return REF_BLIND_FOMT;
	}

	public void setREF_BLIND_FOMT(String rEF_BLIND_FOMT) {
		REF_BLIND_FOMT = rEF_BLIND_FOMT;
	}

	public String getREF_SRCH_FLAG() {
		return REF_SRCH_FLAG;
	}

	public void setREF_SRCH_FLAG(String rEF_SRCH_FLAG) {
		REF_SRCH_FLAG = rEF_SRCH_FLAG;
	}

	public String getENBL_DFLT_VALU() {
		return ENBL_DFLT_VALU;
	}

	public void setENBL_DFLT_VALU(String eNBL_DFLT_VALU) {
		ENBL_DFLT_VALU = eNBL_DFLT_VALU;
	}

	public String getDFLT_BLNK_VALU() {
		return DFLT_BLNK_VALU;
	}

	public void setDFLT_BLNK_VALU(String dFLT_BLNK_VALU) {
		DFLT_BLNK_VALU = dFLT_BLNK_VALU;
	}

	public String getREF_SMS_FLAG() {
		return REF_SMS_FLAG;
	}

	public void setREF_SMS_FLAG(String rEF_SMS_FLAG) {
		REF_SMS_FLAG = rEF_SMS_FLAG;
	}		
}
