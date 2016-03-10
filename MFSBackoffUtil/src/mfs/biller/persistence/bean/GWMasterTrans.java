package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.math.BigDecimal;
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
@IdClass(value = GWMasterTransPK.class)
@Table(name = "GW_MAST_TRNS_01")
@Cache(type = CacheType.NONE)
public class GWMasterTrans implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String TRNS_ID;
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	private Date TRNS_DTTM;
	@Id
	private String TRNS_DEST_CODE;
	@Id
	private String r__;

	@Column(name = "TRNS_SRCE_CODE", nullable = false)
	private String TRNS_SRCE_CODE;

	@Column(name = "TRNS_SRCE_CHNL_CODE", nullable = true)
	private String TRNS_SRCE_CHNL_CODE;

	@Column(name = "TRNS_TYPE_CODE", nullable = true)
	private String TRNS_TYPE_CODE;

	@Column(name = "TRNS_REF_ID", nullable = false)
	private String TRNS_REF_ID;

	@Column(name = "TRNS_SRVC_CODE", nullable = true)
	private String TRNS_SRVC_CODE;

//	@OneToOne(fetch=FetchType.EAGER )
//	@JoinColumn(name = "TRNS_SRVC_CODE", referencedColumnName = "BLLR_SRVC_CODE", insertable = false, updatable = false)
//	private String billerService;


	@Column(name = "TRNS_FUNC_CODE", nullable = true)
	private String TRNS_FUNC_CODE;

	@Column(name = "TRNS_COMP_CODE", nullable = true)
	private String TRNS_COMP_CODE;

	@Column(name = "TRNS_REF1", nullable = true)
	private String TRNS_REF1;

	@Column(name = "TRNS_REF2", nullable = true)
	private String TRNS_REF2;

	@Column(name = "TRNS_REF3", nullable = true)
	private String TRNS_REF3;

	@Column(name = "TRNS_REF4", nullable = true)
	private String TRNS_REF4;

	@Column(name = "TRNS_REF5", nullable = true)
	private String TRNS_REF5;

	@Column(name = "TRNS_REF6", nullable = true)
	private String TRNS_REF6;

	@Column(name = "TRNS_PAID_AMNT", nullable = true)
	private BigDecimal TRNS_PAID_AMNT;

	@Column(name = "TRNS_VAT_AMNT", nullable = true)
	private BigDecimal TRNS_VAT_AMNT;

	@Column(name = "TRNS_MIN_AMNT", nullable = true)
	private BigDecimal TRNS_MIN_AMNT;

	@Column(name = "TRNS_MAX_AMNT", nullable = true)
	private BigDecimal TRNS_MAX_AMNT;

	@Column(name = "TRNS_VAT_RATE", nullable = true)
	private BigDecimal TRNS_VAT_RATE;

	@Column(name = "TRNS_FEE_AMNT", nullable = true)
	private BigDecimal TRNS_FEE_AMNT;

	@Column(name = "TRNS_FEE_TYPE", nullable = true)
	private String TRNS_FEE_TYPE;

	@Column(name = "TRNS_COMM_AMNT", nullable = true)
	private BigDecimal TRNS_COMM_AMNT;

	@Column(name = "TRNS_TOTL_AMNT", nullable = true)
	private BigDecimal TRNS_TOTL_AMNT;

	@Column(name = "TRNS_CRNT_BLNC", nullable = true)
	private BigDecimal TRNS_CRNT_BLNC;

	@Column(name = "TRNS_PAID_AMNT_TYPE", nullable = true)
	private String TRNS_PAID_AMNT_TYPE;

	@Column(name = "TRNS_USER_CODE", nullable = true)
	private String TRNS_USER_CODE;

	@Column(name = "TRNS_USER_GRUP_CODE", nullable = true)
	private String TRNS_USER_GRUP_CODE;

	@Column(name = "TRNS_USER_BRNC_CODE", nullable = true)
	private String TRNS_USER_BRNC_CODE;

	@Column(name = "TRNS_USER_TMNL_CODE", nullable = true)
	private String TRNS_USER_TMNL_CODE;

	@Column(name = "TRNS_ZONE_CODE", nullable = true)
	private String TRNS_ZONE_CODE;

	@Column(name = "TRNS_BRNC_CODE", nullable = true)
	private String TRNS_BRNC_CODE;

	@Column(name = "TRNS_SUB_BRNC_CODE", nullable = true)
	private String TRNS_SUB_BRNC_CODE;

	@Column(name = "TRNS_CUST_NAME_TH", nullable = true)
	private String TRNS_CUST_NAME_TH;

	@Column(name = "TRNS_CUST_NAME_EN", nullable = true)
	private String TRNS_CUST_NAME_EN;

	@Column(name = "TRNS_CUST_ADDR_1", nullable = true)
	private String TRNS_CUST_ADDR_1;

	@Column(name = "TRNS_CUST_ADDR_2", nullable = true)
	private String TRNS_CUST_ADDR_2;

	@Column(name = "TRNS_CUST_ACCT", nullable = true)
	private String TRNS_CUST_ACCT;

	@Column(name = "TRNS_INV_NUMB", nullable = true)
	private String TRNS_INV_NUMB;

	@Column(name = "TRNS_DUE_DATE", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date TRNS_DUE_DATE;

	@Column(name = "TRNS_INV_TAX_ID", nullable = true)
	private String TRNS_INV_TAX_ID;

	@Column(name = "TRNS_CHNL_CODE", nullable = true)
	private String TRNS_CHNL_CODE;

	@Column(name = "TRNS_RCPT_NUMB", nullable = true)
	private String TRNS_RCPT_NUMB;

	@Column(name = "TRNS_RCPT_DTTM", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date TRNS_RCPT_DTTM;

	@Column(name = "TRNS_STTS_CODE", nullable = true)
	private String TRNS_STTS_CODE;

	@Column(name = "TRNS_BARC_CHK_DIGI", nullable = true)
	private String TRNS_BARC_CHK_DIGI;

	@Column(name = "TRNS_PYMT_DTTM", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date TRNS_PYMT_DTTM;

	@Column(name = "TRNS_REVS_DTTM", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date TRNS_REVS_DTTM;

	@Column(name = "TRNS_REMK", nullable = true)
	private String TRNS_REMK;

	@Column(name = "CRTD_BY", nullable = true)
	private String CRTD_BY;

	@Column(name = "CRTD_DTTM", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date CRTD_DTTM;

	@Column(name = "LAST_CHNG_BY", nullable = true)
	private String LAST_CHNG_BY;

	@Column(name = "LAST_CHNG_DTTM", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date LAST_CHNG_DTTM;
	
	@Column(name = "TRNS_FIELD2", nullable = true)
	private String TRNS_FIELD2;

	// @Column(name = "TRNS_ADV_AMNT_FLAG", nullable = true)
	// private boolean TRNS_ADV_AMNT_FLAG;

	// @Column(name = "TRNS_PART_AMNT_FLAG", nullable = true)
	// private boolean TRNS_PART_AMNT_FLAG;

	@Column(name = "TRNS_EXTR_AMNT", nullable = true)
	private String TRNS_EXTR_AMNT;	
	private String BLLR_SRVC_NAME_EN;	
	private String CHNL_CODE;
	private String RCPT_CRTD_GRUP_CODE;
	private String RCPT_TOTL_AMNT;
	private String REV_STATUS;

	public String toString() {
		return TRNS_ID + "|" + TRNS_DTTM + "|" + TRNS_DEST_CODE + "|" + TRNS_SRCE_CODE + "|" + TRNS_SRCE_CHNL_CODE + "|" + TRNS_TYPE_CODE + "|" + TRNS_REF_ID + "|" + TRNS_SRVC_CODE + "|" + TRNS_FUNC_CODE + "|" + TRNS_COMP_CODE + "|" + TRNS_REF1 + "|" + TRNS_REF2 + "|" + TRNS_REF3 + "|" + TRNS_REF4 + "|" + TRNS_REF5 + "|" + TRNS_REF6 + "|" + TRNS_PAID_AMNT + "|" + TRNS_VAT_AMNT + "|" + TRNS_MIN_AMNT + "|" + TRNS_MAX_AMNT + "|" + TRNS_VAT_RATE + "|" + TRNS_FEE_AMNT + "|" + TRNS_FEE_TYPE + "|" + TRNS_COMM_AMNT + "|" + TRNS_TOTL_AMNT + "|" + TRNS_CRNT_BLNC + "|" + TRNS_PAID_AMNT_TYPE + "|" + TRNS_USER_CODE + "|" + TRNS_USER_GRUP_CODE + "|" + TRNS_USER_BRNC_CODE + "|" + TRNS_USER_TMNL_CODE + "|" + TRNS_ZONE_CODE + "|" + TRNS_BRNC_CODE + "|" + TRNS_SUB_BRNC_CODE + "|" + TRNS_CUST_NAME_TH + "|" + TRNS_CUST_NAME_EN + "|" + TRNS_CUST_ADDR_1 + "|" + TRNS_CUST_ADDR_2 + "|" + TRNS_CUST_ACCT + "|" + TRNS_INV_NUMB + "|" + TRNS_DUE_DATE + "|" + TRNS_INV_TAX_ID + "|" + TRNS_CHNL_CODE + "|" + TRNS_RCPT_NUMB + "|" + TRNS_RCPT_DTTM + "|" + TRNS_STTS_CODE + "|" + TRNS_BARC_CHK_DIGI + "|" + TRNS_PYMT_DTTM + "|" + TRNS_REVS_DTTM + "|" + TRNS_REMK + "|" + CRTD_BY + "|" + CRTD_DTTM + "|" + LAST_CHNG_BY + "|" + LAST_CHNG_DTTM + "|" + TRNS_EXTR_AMNT;
	}

	public BigDecimal getTRNS_MIN_AMNT() {
		return TRNS_MIN_AMNT;
	}

	public void setTRNS_MIN_AMNT(BigDecimal tRNS_MIN_AMNT) {
		TRNS_MIN_AMNT = tRNS_MIN_AMNT;
	}

	public BigDecimal getTRNS_MAX_AMNT() {
		return TRNS_MAX_AMNT;
	}

	public void setTRNS_MAX_AMNT(BigDecimal tRNS_MAX_AMNT) {
		TRNS_MAX_AMNT = tRNS_MAX_AMNT;
	}

	public String getTRNS_REF6() {
		return TRNS_REF6;
	}

	public void setTRNS_REF6(String tRNS_REF6) {
		TRNS_REF6 = tRNS_REF6;
	}

	public String getTRNS_ID() {
		return TRNS_ID;
	}

	public void setTRNS_ID(String tRNS_ID) {
		TRNS_ID = tRNS_ID;
	}

	public Date getTRNS_DTTM() {
		return TRNS_DTTM;
	}

	public void setTRNS_DTTM(Date tRNS_DTTM) {
		TRNS_DTTM = tRNS_DTTM;
	}

	public String getTRNS_DEST_CODE() {
		return TRNS_DEST_CODE;
	}

	public void setTRNS_DEST_CODE(String tRNS_DEST_CODE) {
		TRNS_DEST_CODE = tRNS_DEST_CODE;
	}

	public String getTRNS_SRCE_CODE() {
		return TRNS_SRCE_CODE;
	}

	public void setTRNS_SRCE_CODE(String tRNS_SRCE_CODE) {
		TRNS_SRCE_CODE = tRNS_SRCE_CODE;
	}

	public String getTRNS_SRCE_CHNL_CODE() {
		return TRNS_SRCE_CHNL_CODE;
	}

	public void setTRNS_SRCE_CHNL_CODE(String tRNS_SRCE_CHNL_CODE) {
		TRNS_SRCE_CHNL_CODE = tRNS_SRCE_CHNL_CODE;
	}

	public String getTRNS_TYPE_CODE() {
		return TRNS_TYPE_CODE;
	}

	public void setTRNS_TYPE_CODE(String tRNS_TYPE_CODE) {
		TRNS_TYPE_CODE = tRNS_TYPE_CODE;
	}

	public String getTRNS_REF_ID() {
		return TRNS_REF_ID;
	}

	public void setTRNS_REF_ID(String tRNS_REF_ID) {
		TRNS_REF_ID = tRNS_REF_ID;
	}

	public String getTRNS_SRVC_CODE() {
		return TRNS_SRVC_CODE;
	}

	public void setTRNS_SRVC_CODE(String tRNS_SRVC_CODE) {
		TRNS_SRVC_CODE = tRNS_SRVC_CODE;
	}

	public String getTRNS_FUNC_CODE() {
		return TRNS_FUNC_CODE;
	}

	public void setTRNS_FUNC_CODE(String tRNS_FUNC_CODE) {
		TRNS_FUNC_CODE = tRNS_FUNC_CODE;
	}

	public String getTRNS_COMP_CODE() {
		return TRNS_COMP_CODE;
	}

	public void setTRNS_COMP_CODE(String tRNS_COMP_CODE) {
		TRNS_COMP_CODE = tRNS_COMP_CODE;
	}

	public String getTRNS_REF1() {
		return TRNS_REF1;
	}

	public void setTRNS_REF1(String tRNS_REF1) {
		TRNS_REF1 = tRNS_REF1;
	}

	public String getTRNS_REF2() {
		return TRNS_REF2;
	}

	public void setTRNS_REF2(String tRNS_REF2) {
		TRNS_REF2 = tRNS_REF2;
	}

	public String getTRNS_REF3() {
		return TRNS_REF3;
	}

	public void setTRNS_REF3(String tRNS_REF3) {
		TRNS_REF3 = tRNS_REF3;
	}

	public String getTRNS_REF4() {
		return TRNS_REF4;
	}

	public void setTRNS_REF4(String tRNS_REF4) {
		TRNS_REF4 = tRNS_REF4;
	}

	public String getTRNS_REF5() {
		return TRNS_REF5;
	}

	public void setTRNS_REF5(String tRNS_REF5) {
		TRNS_REF5 = tRNS_REF5;
	}

	public BigDecimal getTRNS_PAID_AMNT() {
		return TRNS_PAID_AMNT;
	}

	public void setTRNS_PAID_AMNT(BigDecimal tRNS_PAID_AMNT) {
		TRNS_PAID_AMNT = tRNS_PAID_AMNT;
	}

	public BigDecimal getTRNS_VAT_AMNT() {
		return TRNS_VAT_AMNT;
	}

	public void setTRNS_VAT_AMNT(BigDecimal tRNS_VAT_AMNT) {
		TRNS_VAT_AMNT = tRNS_VAT_AMNT;
	}

	public BigDecimal getTRNS_VAT_RATE() {
		return TRNS_VAT_RATE;
	}

	public void setTRNS_VAT_RATE(BigDecimal tRNS_VAT_RATE) {
		TRNS_VAT_RATE = tRNS_VAT_RATE;
	}

	public BigDecimal getTRNS_FEE_AMNT() {
		return TRNS_FEE_AMNT;
	}

	public void setTRNS_FEE_AMNT(BigDecimal tRNS_FEE_AMNT) {
		TRNS_FEE_AMNT = tRNS_FEE_AMNT;
	}

	public String getTRNS_FEE_TYPE() {
		return TRNS_FEE_TYPE;
	}

	public void setTRNS_FEE_TYPE(String tRNS_FEE_TYPE) {
		TRNS_FEE_TYPE = tRNS_FEE_TYPE;
	}

	public BigDecimal getTRNS_COMM_AMNT() {
		return TRNS_COMM_AMNT;
	}

	public void setTRNS_COMM_AMNT(BigDecimal tRNS_COMM_AMNT) {
		TRNS_COMM_AMNT = tRNS_COMM_AMNT;
	}

	public BigDecimal getTRNS_TOTL_AMNT() {
		return TRNS_TOTL_AMNT;
	}

	public void setTRNS_TOTL_AMNT(BigDecimal tRNS_TOTL_AMNT) {
		TRNS_TOTL_AMNT = tRNS_TOTL_AMNT;
	}

	public BigDecimal getTRNS_CRNT_BLNC() {
		return TRNS_CRNT_BLNC;
	}

	public void setTRNS_CRNT_BLNC(BigDecimal tRNS_CRNT_BLNC) {
		TRNS_CRNT_BLNC = tRNS_CRNT_BLNC;
	}

	public String getTRNS_PAID_AMNT_TYPE() {
		return TRNS_PAID_AMNT_TYPE;
	}

	public void setTRNS_PAID_AMNT_TYPE(String tRNS_PAID_AMNT_TYPE) {
		TRNS_PAID_AMNT_TYPE = tRNS_PAID_AMNT_TYPE;
	}

	public String getTRNS_USER_CODE() {
		return TRNS_USER_CODE;
	}

	public void setTRNS_USER_CODE(String tRNS_USER_CODE) {
		TRNS_USER_CODE = tRNS_USER_CODE;
	}

	public String getTRNS_USER_GRUP_CODE() {
		return TRNS_USER_GRUP_CODE;
	}

	public void setTRNS_USER_GRUP_CODE(String tRNS_USER_GRUP_CODE) {
		TRNS_USER_GRUP_CODE = tRNS_USER_GRUP_CODE;
	}

	public String getTRNS_USER_BRNC_CODE() {
		return TRNS_USER_BRNC_CODE;
	}

	public void setTRNS_USER_BRNC_CODE(String tRNS_USER_BRNC_CODE) {
		TRNS_USER_BRNC_CODE = tRNS_USER_BRNC_CODE;
	}

	public String getTRNS_USER_TMNL_CODE() {
		return TRNS_USER_TMNL_CODE;
	}

	public void setTRNS_USER_TMNL_CODE(String tRNS_USER_TMNL_CODE) {
		TRNS_USER_TMNL_CODE = tRNS_USER_TMNL_CODE;
	}

	public String getTRNS_ZONE_CODE() {
		return TRNS_ZONE_CODE;
	}

	public void setTRNS_ZONE_CODE(String tRNS_ZONE_CODE) {
		TRNS_ZONE_CODE = tRNS_ZONE_CODE;
	}

	public String getTRNS_BRNC_CODE() {
		return TRNS_BRNC_CODE;
	}

	public void setTRNS_BRNC_CODE(String tRNS_BRNC_CODE) {
		TRNS_BRNC_CODE = tRNS_BRNC_CODE;
	}

	public String getTRNS_SUB_BRNC_CODE() {
		return TRNS_SUB_BRNC_CODE;
	}

	public void setTRNS_SUB_BRNC_CODE(String tRNS_SUB_BRNC_CODE) {
		TRNS_SUB_BRNC_CODE = tRNS_SUB_BRNC_CODE;
	}

	public String getTRNS_CUST_NAME_TH() {
		return TRNS_CUST_NAME_TH;
	}

	public void setTRNS_CUST_NAME_TH(String tRNS_CUST_NAME_TH) {
		TRNS_CUST_NAME_TH = tRNS_CUST_NAME_TH;
	}

	public String getTRNS_CUST_NAME_EN() {
		return TRNS_CUST_NAME_EN;
	}

	public void setTRNS_CUST_NAME_EN(String tRNS_CUST_NAME_EN) {
		TRNS_CUST_NAME_EN = tRNS_CUST_NAME_EN;
	}

	public String getTRNS_CUST_ADDR_1() {
		return TRNS_CUST_ADDR_1;
	}

	public void setTRNS_CUST_ADDR_1(String tRNS_CUST_ADDR_1) {
		TRNS_CUST_ADDR_1 = tRNS_CUST_ADDR_1;
	}

	public String getTRNS_CUST_ADDR_2() {
		return TRNS_CUST_ADDR_2;
	}

	public void setTRNS_CUST_ADDR_2(String tRNS_CUST_ADDR_2) {
		TRNS_CUST_ADDR_2 = tRNS_CUST_ADDR_2;
	}

	// public String getTRNS_CUST_ADDR_3() {
	// return TRNS_CUST_ADDR_3;
	// }
	//
	// public void setTRNS_CUST_ADDR_3(String tRNS_CUST_ADDR_3) {
	// TRNS_CUST_ADDR_3 = tRNS_CUST_ADDR_3;
	// }

	public String getTRNS_CUST_ACCT() {
		return TRNS_CUST_ACCT;
	}

	public void setTRNS_CUST_ACCT(String tRNS_CUST_ACCT) {
		TRNS_CUST_ACCT = tRNS_CUST_ACCT;
	}

	public String getTRNS_INV_NUMB() {
		return TRNS_INV_NUMB;
	}

	public void setTRNS_INV_NUMB(String tRNS_INV_NUMB) {
		TRNS_INV_NUMB = tRNS_INV_NUMB;
	}

	public Date getTRNS_DUE_DATE() {
		return TRNS_DUE_DATE;
	}

	public void setTRNS_DUE_DATE(Date tRNS_DUE_DATE) {
		TRNS_DUE_DATE = tRNS_DUE_DATE;
	}

	public String getTRNS_INV_TAX_ID() {
		return TRNS_INV_TAX_ID;
	}

	public void setTRNS_INV_TAX_ID(String tRNS_INV_TAX_ID) {
		TRNS_INV_TAX_ID = tRNS_INV_TAX_ID;
	}

	public String getTRNS_CHNL_CODE() {
		return TRNS_CHNL_CODE;
	}

	public void setTRNS_CHNL_CODE(String tRNS_CHNL_CODE) {
		TRNS_CHNL_CODE = tRNS_CHNL_CODE;
	}

	public String getTRNS_RCPT_NUMB() {
		return TRNS_RCPT_NUMB;
	}

	public void setTRNS_RCPT_NUMB(String tRNS_RCPT_NUMB) {
		TRNS_RCPT_NUMB = tRNS_RCPT_NUMB;
	}

	public Date getTRNS_RCPT_DTTM() {
		return TRNS_RCPT_DTTM;
	}

	public void setTRNS_RCPT_DTTM(Date tRNS_RCPT_DTTM) {
		TRNS_RCPT_DTTM = tRNS_RCPT_DTTM;
	}

	public String getTRNS_STTS_CODE() {
		return TRNS_STTS_CODE;
	}

	public void setTRNS_STTS_CODE(String tRNS_STTS_CODE) {
		TRNS_STTS_CODE = tRNS_STTS_CODE;
	}

	public Date getTRNS_PYMT_DTTM() {
		return TRNS_PYMT_DTTM;
	}

	public void setTRNS_PYMT_DTTM(Date tRNS_PYMT_DTTM) {
		TRNS_PYMT_DTTM = tRNS_PYMT_DTTM;
	}

	public Date getTRNS_REVS_DTTM() {
		return TRNS_REVS_DTTM;
	}

	public void setTRNS_REVS_DTTM(Date tRNS_REVS_DTTM) {
		TRNS_REVS_DTTM = tRNS_REVS_DTTM;
	}

	public String getTRNS_REMK() {
		return TRNS_REMK;
	}

	public void setTRNS_REMK(String tRNS_REMK) {
		TRNS_REMK = tRNS_REMK;
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

	public String getTRNS_BARC_CHK_DIGI() {
		return TRNS_BARC_CHK_DIGI;
	}

	public void setTRNS_BARC_CHK_DIGI(String tRNS_BARC_CHK_DIGI) {
		TRNS_BARC_CHK_DIGI = tRNS_BARC_CHK_DIGI;
	}

	public String getTRNS_EXTR_AMNT() {
		return TRNS_EXTR_AMNT;
	}

	public void setTRNS_EXTR_AMNT(String tRNS_EXTR_AMNT) {
		TRNS_EXTR_AMNT = tRNS_EXTR_AMNT;
	}

	public String getBLLR_SRVC_NAME_EN() {
		return BLLR_SRVC_NAME_EN;
	}

	public void setBLLR_SRVC_NAME_EN(String bLLR_SRVC_NAME_EN) {
		BLLR_SRVC_NAME_EN = bLLR_SRVC_NAME_EN;
	}

	public String getCHNL_CODE() {
		return CHNL_CODE;
	}

	public void setCHNL_CODE(String cHNL_CODE) {
		CHNL_CODE = cHNL_CODE;
	}

	public String getRCPT_CRTD_GRUP_CODE() {
		return RCPT_CRTD_GRUP_CODE;
	}

	public void setRCPT_CRTD_GRUP_CODE(String rCPT_CRTD_GRUP_CODE) {
		RCPT_CRTD_GRUP_CODE = rCPT_CRTD_GRUP_CODE;
	}

	public String getRCPT_TOTL_AMNT() {
		return RCPT_TOTL_AMNT;
	}

	public void setRCPT_TOTL_AMNT(String rCPT_TOTL_AMNT) {
		RCPT_TOTL_AMNT = rCPT_TOTL_AMNT;
	}

	public String getREV_STATUS() {
		return REV_STATUS;
	}

	public void setREV_STATUS(String rEV_STATUS) {
		REV_STATUS = rEV_STATUS;
	}

	public String getR__() {
		return r__;
	}

	public void setR__(String r__) {
		this.r__ = r__;
	}

	public String getTRNS_FIELD2() {
		return TRNS_FIELD2;
	}

	public void setTRNS_FIELD2(String tRNS_FIELD2) {
		TRNS_FIELD2 = tRNS_FIELD2;
	}
	
}
