package mfs.biller.persistence.bean;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Table(name = "BILLER_FEE_MAST")
@Cache(type = CacheType.NONE)
public class BillerFeeMast implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BLLR_FEE_MAST_ID", nullable=false)
	private Integer BLLR_FEE_MAST_ID;
	
	@Column(name="FEE_TYPE", nullable=false)
	private String FEE_TYPE;
	
	@Column(name="FEE_DESC", nullable=true)
	private String FEE_DESC;
	
	@Column(name="FEE_WEIGHT", nullable=true)
	private String FEE_WEIGHT;
		
	public String toString() {
		return "BLLR_FEE_MAST_ID:" + BLLR_FEE_MAST_ID 
				+ "|FEE_TYPE:" + FEE_TYPE
				+ "|FEE_DESC:" + FEE_DESC
				+ "|FEE_WEIGHT:" + FEE_WEIGHT ;
	}

	public Integer getBLLR_FEE_MAST_ID() {
		return BLLR_FEE_MAST_ID;
	}

	public void setBLLR_FEE_MAST_ID(Integer bLLR_FEE_MAST_ID) {
		BLLR_FEE_MAST_ID = bLLR_FEE_MAST_ID;
	}

	public String getFEE_TYPE() {
		return FEE_TYPE;
	}

	public void setFEE_TYPE(String fEE_TYPE) {
		FEE_TYPE = fEE_TYPE;
	}

	public String getFEE_DESC() {
		return FEE_DESC;
	}

	public void setFEE_DESC(String fEE_DESC) {
		FEE_DESC = fEE_DESC;
	}

	public String getFEE_WEIGHT() {
		return FEE_WEIGHT;
	}

	public void setFEE_WEIGHT(String fEE_WEIGHT) {
		FEE_WEIGHT = fEE_WEIGHT;
	}
}
