package mfs.bcm.process.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Table(name="VIEW_BILLER_BLIND_REF")
@Cache(type = CacheType.NONE)
public class BillerBlindRefView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 457264355683424051L;
	
	@Id
	@Column(name="BLLR_SRVC_ID", unique=true, nullable=false, precision=10)
	private long bllrSrvcId;
	
	@Column(name="REF_TYPE", length=10)
	private String refType;
	
	@Column(name="REF_VALE_LENT_MIN", precision=10)
	private BigDecimal refValeLentMin;
	
	@Column(name="REF_VALE_LENT_MAX", precision=10)
	private BigDecimal refValeLentMax;
	
	@Column(name="REF_BLIND_FOMT", length=100)
	private String refBlindFomt;

	public long getBllrSrvcId() {
		return bllrSrvcId;
	}

	public void setBllrSrvcId(long bllrSrvcId) {
		this.bllrSrvcId = bllrSrvcId;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public BigDecimal getRefValeLentMin() {
		return refValeLentMin;
	}

	public void setRefValeLentMin(BigDecimal refValeLentMin) {
		this.refValeLentMin = refValeLentMin;
	}

	public BigDecimal getRefValeLentMax() {
		return refValeLentMax;
	}

	public void setRefValeLentMax(BigDecimal refValeLentMax) {
		this.refValeLentMax = refValeLentMax;
	}

	public String getRefBlindFomt() {
		return refBlindFomt;
	}

	public void setRefBlindFomt(String refBlindFomt) {
		this.refBlindFomt = refBlindFomt;
	}

}
