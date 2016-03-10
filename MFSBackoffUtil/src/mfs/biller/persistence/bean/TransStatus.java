package mfs.biller.persistence.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@IdClass(value = TransStatusPK.class)
@Table(name = "GW_TRNS_STTS")
@Cache(type = CacheType.NONE)
public class TransStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer  TRNS_STTS_CODE;

	@Column(name = "TRNS_STTS_NAME", nullable = false)
	private String TRNS_STTS_NAME;

	@Column(name = "ACT_FLAG", nullable = false)
	private String ACT_FLAG ;

	public Integer  getTRNS_STTS_CODE() {
		return this.TRNS_STTS_CODE;
	}

	public void setTRNS_STTS_CODE(Integer TRNS_STTS_CODE) {
		this.TRNS_STTS_CODE = TRNS_STTS_CODE;
	}

	public String getTRNS_STTS_NAME() {
		return TRNS_STTS_NAME;
	}

	public void setTRNS_STTS_NAME(String TRNS_STTS_NAME) {
		this.TRNS_STTS_NAME = TRNS_STTS_NAME;
	}

	public String getACT_FLAG  () {
		return this.ACT_FLAG  ;
	}

	public void setACT_FLAG  (String ACT_FLAG  ) {
		this.ACT_FLAG   = ACT_FLAG  ;
	}
}
