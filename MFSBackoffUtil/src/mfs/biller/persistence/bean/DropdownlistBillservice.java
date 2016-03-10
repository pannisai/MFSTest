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
@Table(name = "BILL_SERVICE")
@Cache(type = CacheType.NONE)
public class DropdownlistBillservice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3763024925101994231L;

	@Id
	@Column(name = "BLLR_SRVC_ID")
	private Integer BLLR_SRVC_ID;
	
	@Column(name = "BLLR_SRVC_CODE")
	private String BLLR_SRVC_CODE;
	
	@Column(name = "BLLR_MSTR_ID")
	private Integer BLLR_MSTR_ID;
	
	@Column(name = "BLLR_SRVC_DESC")
	private String BLLR_SRVC_DESC;
	
	public Integer getBLLR_MSTR_ID() {
		return BLLR_MSTR_ID;
	}

	public void setBLLR_MSTR_ID(Integer bLLR_MSTR_ID) {
		BLLR_MSTR_ID = bLLR_MSTR_ID;
	}

	@Column(name = "BLLR_SRVC_NAME_EN")
	private String BLLR_SRVC_NAME_EN;
	
	@Column(name="BLLR_SRVC_STAT_DATE")
	@Temporal(TemporalType.DATE)
	 private Date BLLR_SRVC_STAT_DATE;
	
	
	@Column(name="BLLR_SRVC_EXPR_DATE")
	@Temporal(TemporalType.DATE)
	 private Date BLLR_SRVC_EXPR_DATE;
	
	
	
	
	
	

	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}

	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}

	public String getBLLR_SRVC_CODE() {
		return BLLR_SRVC_CODE;
	}

	public void setBLLR_SRVC_CODE(String bLLR_SRVC_CODE) {
		BLLR_SRVC_CODE = bLLR_SRVC_CODE;
	}

	public String getBLLR_SRVC_NAME_EN() {
		return BLLR_SRVC_NAME_EN;
	}

	public void setBLLR_SRVC_NAME_EN(String bLLR_SRVC_NAME_EN) {
		BLLR_SRVC_NAME_EN = bLLR_SRVC_NAME_EN;
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

	public String getBLLR_SRVC_DESC() {
		return BLLR_SRVC_DESC;
	}

	public void setBLLR_SRVC_DESC(String bLLR_SRVC_DESC) {
		BLLR_SRVC_DESC = bLLR_SRVC_DESC;
	}

//	public boolean equals(Object obj) {
//		if (!(obj instanceof DropdownlistBillservice))
//		return false;
//		if (obj == this)
//		return true;
//		return this.BLLR_SRVC_ID == ((DropdownlistBillservice) obj).BLLR_SRVC_ID;
//		}
//		public int hashCode() {
//		int result = 0;
//		result = BLLR_SRVC_ID;
//		return result;
//		}

}
