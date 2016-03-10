package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Table(name="BILLER_SERVICE")
@Cache(type=CacheType.NONE)
public class BillerService implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BLLR_SRVC_ID", nullable=false)
	private Integer BLLR_SRVC_ID;
	
	@Column(name="BLLR_MSTR_ID", nullable=true)
	private Integer BLLR_MSTR_ID;
	
	@Column(name="BLLR_SRVC_DESC", nullable=true)
	private String BLLR_SRVC_DESC;
	
	@Column(name="BLLR_SRVC_CODE", nullable=true)
	private String BLLR_SRVC_CODE;
	
	@Column(name="BLLR_SRVC_NAME_TH", nullable=true)
	private String BLLR_SRVC_NAME_TH;
	
	@Column(name="BLLR_SRVC_NAME_FULL_TH", nullable=true)
	private String BLLR_SRVC_NAME_FULL_TH;
	
	@Column(name="BLLR_SRVC_NAME_EN", nullable=true)
	private String BLLR_SRVC_NAME_EN;
	
	@Column(name="BLLR_SRVC_NAME_FULL_EN", nullable=true)
	private String BLLR_SRVC_NAME_FULL_EN;
	
	@Column(name="BLLR_SRVC_STAT_DATE", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date BLLR_SRVC_STAT_DATE;
	
	@Column(name="BLLR_SRVC_EXPR_DATE", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date BLLR_SRVC_EXPR_DATE;
	
	@Column(name="BLLR_SRVC_MENU_SEQ", nullable=true)
	private Integer BLLR_SRVC_MENU_SEQ;
	
	@Column(name="BLLR_SRVC_GUST_MOBN_FLAG", nullable=true)
	private String BLLR_SRVC_GUST_MOBN_FLAG;
	
	@Column(name="BLLR_SRVC_BARC_FLAG", nullable=true)
	private String BLLR_SRVC_BARC_FLAG;
	
	@Column(name="BLLR_CATG_ID", nullable=true)
	private Integer BLLR_CATG_ID;
	
	@Column(name="BLLR_ADDR_1", nullable=true)
	private String BLLR_ADDR_1;
	
	@Column(name="BLLR_ADDR_2", nullable=true)
	private String BLLR_ADDR_2;
	
	@Column(name="BLLR_ADDR_3", nullable=true)
	private String BLLR_ADDR_3;
	
	@Column(name="BLLR_ADDR_EN_1", nullable=true)
	private String BLLR_ADDR_EN_1;
	
	@Column(name="BLLR_ADDR_EN_2", nullable=true)
	private String BLLR_ADDR_EN_2;
	
	@Column(name="BLLR_ADDR_EN_3", nullable=true)
	private String BLLR_ADDR_EN_3;
	
	@Column(name="BLLR_SERV_AGNT_CD", nullable=true)
	private String BLLR_SERV_AGNT_CD;
	
	@Column(name="BLLR_SERV_RCPT_NO_DIGIT", nullable=true)
	private Integer BLLR_SERV_RCPT_NO_DIGIT;
	
	@Column(name="BLLR_SERV_NAME_RCPT_TH", nullable=true)
	private String BLLR_SERV_NAME_RCPT_TH;
	
	@Column(name="BLLR_SERV_NAME_RCPT_EN", nullable=true)
	private String BLLR_SERV_NAME_RCPT_EN;
	
	@Column(name="BLLR_SERV_VAT_PERC", nullable=true)
	private BigDecimal BLLR_SERV_VAT_PERC;
	
	@Lob
	@Column(name="BLLR_SERV_POMO", nullable=true)
    private String BLLR_SERV_POMO;

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
	
	@Column(name="BLLR_SRVC_FETR", nullable=true)
    private String BLLR_SRVC_FETR;
	
	@Column(name="BLLR_INGT_ID", nullable=false)
	private Integer BLLR_INGT_ID;
	
	@Column(name="BLLR_FETR_ID", nullable=true)
	private Integer BLLR_FETR_ID;
	
	@Column(name="BLLR_CNCL_ONLN_FLAG", nullable=true)
	private String BLLR_CNCL_ONLN_FLAG;
	
	@Column(name="ADDT_CHNL_SRVC", nullable=true)
	private Integer ADDT_CHNL_SRVC;
	
	public String toString() {
		return "BLLR_SRVC_ID:" + BLLR_SRVC_ID 
				+ "|BLLR_MSTR_ID:" + BLLR_MSTR_ID 
				+ "|BLLR_SRVC_DESC:" + BLLR_SRVC_DESC 
				+ "|BLLR_SRVC_CODE:" + BLLR_SRVC_CODE 
				+ "|BLLR_SRVC_NAME_TH:" + BLLR_SRVC_NAME_TH 
				+ "|BLLR_SRVC_NAME_FULL_TH:" + BLLR_SRVC_NAME_FULL_TH 
				+ "|BLLR_SRVC_NAME_EN:" + BLLR_SRVC_NAME_EN 
				+ "|BLLR_SRVC_NAME_FULL_EN:" + BLLR_SRVC_NAME_FULL_EN 
				+ "|BLLR_SRVC_STAT_DATE:" + BLLR_SRVC_STAT_DATE 
				+ "|BLLR_SRVC_EXPR_DATE:" + BLLR_SRVC_EXPR_DATE 
				+ "|BLLR_SRVC_MENU_SEQ:" + BLLR_SRVC_MENU_SEQ 
				+ "|BLLR_SRVC_GUST_MOBN_FLAG:" + BLLR_SRVC_GUST_MOBN_FLAG 
				+ "|BLLR_SRVC_BARC_FLAG:" + BLLR_SRVC_BARC_FLAG 
				+ "|BLLR_CATG_ID:" + BLLR_CATG_ID 
				+ "|BLLR_ADDR_1:" + BLLR_ADDR_1 
				+ "|BLLR_ADDR_2:" + BLLR_ADDR_2 
				+ "|BLLR_ADDR_3:" + BLLR_ADDR_3 
				+ "|BLLR_ADDR_EN_1:" + BLLR_ADDR_EN_1 
				+ "|BLLR_ADDR_EN_2:" + BLLR_ADDR_EN_2 
				+ "|BLLR_ADDR_EN_3:" + BLLR_ADDR_EN_3 
				+ "|BLLR_SERV_AGNT_CD:" + BLLR_SERV_AGNT_CD 
				+ "|BLLR_SERV_RCPT_NO_DIGIT:" + BLLR_SERV_RCPT_NO_DIGIT 
				+ "|BLLR_SERV_NAME_RCPT_TH:" + BLLR_SERV_NAME_RCPT_TH 
				+ "|BLLR_SERV_NAME_RCPT_EN:" + BLLR_SERV_NAME_RCPT_EN 
				+ "|BLLR_SERV_VAT_PERC:" + BLLR_SERV_VAT_PERC 
				+ "|ACT_FLAG:" + ACT_FLAG 
				+ "|CRTD_BY:" + CRTD_BY 
				+ "|CRTD_DTTM:" + CRTD_DTTM 
				+ "|LAST_CHNG_BY:" + LAST_CHNG_BY 
				+ "|LAST_CHNG_DTTM:" + LAST_CHNG_DTTM
				+ "|BLLR_SRVC_FETR:" + BLLR_SRVC_FETR
				+ "|BLLR_INGT_ID:" + BLLR_INGT_ID
				+ "|BLLR_FETR_ID:" + BLLR_FETR_ID
				+ "|BLLR_CNCL_ONLN_FLAG:" + BLLR_CNCL_ONLN_FLAG
				+ "|ADDT_CHNL_SRVC:" + ADDT_CHNL_SRVC;
	}

	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}

	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}

	public Integer getBLLR_MSTR_ID() {
		return BLLR_MSTR_ID;
	}

	public void setBLLR_MSTR_ID(Integer bLLR_MSTR_ID) {
		BLLR_MSTR_ID = bLLR_MSTR_ID;
	}

	public String getBLLR_SRVC_DESC() {
		return BLLR_SRVC_DESC;
	}

	public void setBLLR_SRVC_DESC(String bLLR_SRVC_DESC) {
		BLLR_SRVC_DESC = bLLR_SRVC_DESC;
	}

	public String getBLLR_SRVC_CODE() {
		return BLLR_SRVC_CODE;
	}

	public void setBLLR_SRVC_CODE(String bLLR_SRVC_CODE) {
		BLLR_SRVC_CODE = bLLR_SRVC_CODE;
	}

	public String getBLLR_SRVC_NAME_TH() {
		return BLLR_SRVC_NAME_TH;
	}

	public void setBLLR_SRVC_NAME_TH(String bLLR_SRVC_NAME_TH) {
		BLLR_SRVC_NAME_TH = bLLR_SRVC_NAME_TH;
	}

	public String getBLLR_SRVC_NAME_FULL_TH() {
		return BLLR_SRVC_NAME_FULL_TH;
	}

	public void setBLLR_SRVC_NAME_FULL_TH(String bLLR_SRVC_NAME_FULL_TH) {
		BLLR_SRVC_NAME_FULL_TH = bLLR_SRVC_NAME_FULL_TH;
	}

	public String getBLLR_SRVC_NAME_EN() {
		return BLLR_SRVC_NAME_EN;
	}

	public void setBLLR_SRVC_NAME_EN(String bLLR_SRVC_NAME_EN) {
		BLLR_SRVC_NAME_EN = bLLR_SRVC_NAME_EN;
	}

	public String getBLLR_SRVC_NAME_FULL_EN() {
		return BLLR_SRVC_NAME_FULL_EN;
	}

	public void setBLLR_SRVC_NAME_FULL_EN(String bLLR_SRVC_NAME_FULL_EN) {
		BLLR_SRVC_NAME_FULL_EN = bLLR_SRVC_NAME_FULL_EN;
	}

	public Date getBLLR_SRVC_STAT_DATE() {
		return BLLR_SRVC_STAT_DATE;
	}

	public void setBLLR_SRVC_STAT_DATE(Date bLLR_SRVC_STAT_DATE) {
		BLLR_SRVC_STAT_DATE = bLLR_SRVC_STAT_DATE;
	}

	public Date getBLLR_SRVC_EXPR_DATE() {
		return BLLR_SRVC_EXPR_DATE;
	}

	public void setBLLR_SRVC_EXPR_DATE(Date bLLR_SRVC_EXPR_DATE) {
		BLLR_SRVC_EXPR_DATE = bLLR_SRVC_EXPR_DATE;
	}

	public Integer getBLLR_SRVC_MENU_SEQ() {
		return BLLR_SRVC_MENU_SEQ;
	}

	public void setBLLR_SRVC_MENU_SEQ(Integer bLLR_SRVC_MENU_SEQ) {
		BLLR_SRVC_MENU_SEQ = bLLR_SRVC_MENU_SEQ;
	}

	public String getBLLR_SRVC_GUST_MOBN_FLAG() {
		return BLLR_SRVC_GUST_MOBN_FLAG;
	}

	public void setBLLR_SRVC_GUST_MOBN_FLAG(String bLLR_SRVC_GUST_MOBN_FLAG) {
		BLLR_SRVC_GUST_MOBN_FLAG = bLLR_SRVC_GUST_MOBN_FLAG;
	}

	public String getBLLR_SRVC_BARC_FLAG() {
		return BLLR_SRVC_BARC_FLAG;
	}

	public void setBLLR_SRVC_BARC_FLAG(String bLLR_SRVC_BARC_FLAG) {
		BLLR_SRVC_BARC_FLAG = bLLR_SRVC_BARC_FLAG;
	}

	public Integer getBLLR_CATG_ID() {
		return BLLR_CATG_ID;
	}

	public void setBLLR_CATG_ID(Integer bLLR_CATG_ID) {
		BLLR_CATG_ID = bLLR_CATG_ID;
	}

	public String getBLLR_ADDR_1() {
		return BLLR_ADDR_1;
	}

	public void setBLLR_ADDR_1(String bLLR_ADDR_1) {
		BLLR_ADDR_1 = bLLR_ADDR_1;
	}

	public String getBLLR_ADDR_2() {
		return BLLR_ADDR_2;
	}

	public void setBLLR_ADDR_2(String bLLR_ADDR_2) {
		BLLR_ADDR_2 = bLLR_ADDR_2;
	}

	public String getBLLR_ADDR_3() {
		return BLLR_ADDR_3;
	}

	public void setBLLR_ADDR_3(String bLLR_ADDR_3) {
		BLLR_ADDR_3 = bLLR_ADDR_3;
	}

	public String getBLLR_ADDR_EN_1() {
		return BLLR_ADDR_EN_1;
	}

	public void setBLLR_ADDR_EN_1(String bLLR_ADDR_EN_1) {
		BLLR_ADDR_EN_1 = bLLR_ADDR_EN_1;
	}

	public String getBLLR_ADDR_EN_2() {
		return BLLR_ADDR_EN_2;
	}

	public void setBLLR_ADDR_EN_2(String bLLR_ADDR_EN_2) {
		BLLR_ADDR_EN_2 = bLLR_ADDR_EN_2;
	}

	public String getBLLR_ADDR_EN_3() {
		return BLLR_ADDR_EN_3;
	}

	public void setBLLR_ADDR_EN_3(String bLLR_ADDR_EN_3) {
		BLLR_ADDR_EN_3 = bLLR_ADDR_EN_3;
	}

	public String getBLLR_SERV_AGNT_CD() {
		return BLLR_SERV_AGNT_CD;
	}

	public void setBLLR_SERV_AGNT_CD(String bLLR_SERV_AGNT_CD) {
		BLLR_SERV_AGNT_CD = bLLR_SERV_AGNT_CD;
	}

	public Integer getBLLR_SERV_RCPT_NO_DIGIT() {
		return BLLR_SERV_RCPT_NO_DIGIT;
	}

	public void setBLLR_SERV_RCPT_NO_DIGIT(Integer bLLR_SERV_RCPT_NO_DIGIT) {
		BLLR_SERV_RCPT_NO_DIGIT = bLLR_SERV_RCPT_NO_DIGIT;
	}

	public String getBLLR_SERV_NAME_RCPT_TH() {
		return BLLR_SERV_NAME_RCPT_TH;
	}

	public void setBLLR_SERV_NAME_RCPT_TH(String bLLR_SERV_NAME_RCPT_TH) {
		BLLR_SERV_NAME_RCPT_TH = bLLR_SERV_NAME_RCPT_TH;
	}

	public String getBLLR_SERV_NAME_RCPT_EN() {
		return BLLR_SERV_NAME_RCPT_EN;
	}

	public void setBLLR_SERV_NAME_RCPT_EN(String bLLR_SERV_NAME_RCPT_EN) {
		BLLR_SERV_NAME_RCPT_EN = bLLR_SERV_NAME_RCPT_EN;
	}

	public BigDecimal getBLLR_SERV_VAT_PERC() {
		return BLLR_SERV_VAT_PERC;
	}

	public void setBLLR_SERV_VAT_PERC(BigDecimal bLLR_SERV_VAT_PERC) {
		BLLR_SERV_VAT_PERC = bLLR_SERV_VAT_PERC;
	}

	public String getBLLR_SERV_POMO() {
		return BLLR_SERV_POMO;
	}

	public void setBLLR_SERV_POMO(String bLLR_SERV_POMO) {
		BLLR_SERV_POMO = bLLR_SERV_POMO;
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

	public String getBLLR_SRVC_FETR() {
		return BLLR_SRVC_FETR;
	}

	public void setBLLR_SRVC_FETR(String bLLR_SRVC_FETR) {
		BLLR_SRVC_FETR = bLLR_SRVC_FETR;
	}

	public Integer getBLLR_INGT_ID() {
		return BLLR_INGT_ID;
	}

	public void setBLLR_INGT_ID(Integer bLLR_INGT_ID) {
		BLLR_INGT_ID = bLLR_INGT_ID;
	}

	public Integer getBLLR_FETR_ID() {
		return BLLR_FETR_ID;
	}

	public void setBLLR_FETR_ID(Integer bLLR_FETR_ID) {
		BLLR_FETR_ID = bLLR_FETR_ID;
	}

	public String getBLLR_CNCL_ONLN_FLAG() {
		return BLLR_CNCL_ONLN_FLAG;
	}

	public void setBLLR_CNCL_ONLN_FLAG(String bLLR_CNCL_ONLN_FLAG) {
		BLLR_CNCL_ONLN_FLAG = bLLR_CNCL_ONLN_FLAG;
	}

	public Integer getADDT_CHNL_SRVC() {
		return ADDT_CHNL_SRVC;
	}

	public void setADDT_CHNL_SRVC(Integer aDDT_CHNL_SRVC) {
		ADDT_CHNL_SRVC = aDDT_CHNL_SRVC;
	}
	
	
}
