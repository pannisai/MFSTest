package mfs.biller.persistence.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Table(name = "BILLER_MASTER")
@Cache(type = CacheType.NONE)
public class DropdownlistBillMSRT implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 87883902965567287L;

	@Id
	private Integer	BLLR_MSTR_ID;
	
	@Column(name = "BLLR_CATG_ID")
	private Integer	BLLR_CATG_ID;
	
	@Column(name = "BLLR_CODE")
	private String	BLLR_CODE;
	
	public Integer getBLLR_MSTR_ID() {
		return BLLR_MSTR_ID;
	}
	public void setBLLR_MSTR_ID(Integer bLLR_MSTR_ID) {
		BLLR_MSTR_ID = bLLR_MSTR_ID;
	}
	public Integer getBLLR_CATG_ID() {
		return BLLR_CATG_ID;
	}
	public void setBLLR_CATG_ID(Integer bLLR_CATG_ID) {
		BLLR_CATG_ID = bLLR_CATG_ID;
	}
	public String getBLLR_CODE() {
		return BLLR_CODE;
	}
	public void setBLLR_CODE(String bLLR_CODE) {
		BLLR_CODE = bLLR_CODE;
	}
	
	
//	public boolean equals(Object obj) {
//		if (!(obj instanceof DropdownlistBillMSRT))
//		return false;
//		if (obj == this)
//		return true;
//		return this.BLLR_MSTR_ID == ((DropdownlistBillMSRT) obj).BLLR_MSTR_ID;
//		}
//		public int hashCode() {
//		int result = 0;
//		result = BLLR_MSTR_ID;
//		return result;
//		}
}
