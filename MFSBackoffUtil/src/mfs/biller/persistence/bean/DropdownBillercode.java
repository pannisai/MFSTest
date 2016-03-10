package mfs.biller.persistence.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class DropdownBillercode implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private Integer BLLR_MSTR_ID;
	
	@Column(name = "BLLR_CATG_ID", nullable = false)
	private Integer BLLR_CATG_ID;
	
	@Column(name = "BLLR_CODE", nullable = false)
	private String BLLR_CODE;

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
	

}
