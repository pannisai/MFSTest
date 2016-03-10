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
@Table(name="BILLER_INTEGRATION")
@Cache(type=CacheType.NONE)
public class BillerIntegration implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BLLR_INGT_ID", nullable=false)
	private Integer BLLR_INGT_ID;
	
	@Column(name="BLLR_INGT_NAME", nullable=true)
	private String BLLR_INGT_NAME;
	
	public String toString() {
		return "BLLR_INGT_ID:" + BLLR_INGT_ID 
				+ "|BLLR_INGT_NAME:" + BLLR_INGT_NAME ;
	}

	public Integer getBLLR_INGT_ID() {
		return BLLR_INGT_ID;
	}

	public void setBLLR_INGT_ID(Integer bLLR_INGT_ID) {
		BLLR_INGT_ID = bLLR_INGT_ID;
	}

	public String getBLLR_INGT_NAME() {
		return BLLR_INGT_NAME;
	}

	public void setBLLR_INGT_NAME(String bLLR_INGT_NAME) {
		BLLR_INGT_NAME = bLLR_INGT_NAME;
	}

	
	
}
