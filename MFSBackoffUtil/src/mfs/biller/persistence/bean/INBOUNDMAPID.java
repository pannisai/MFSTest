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
@Table(name = "GW_INBOUND_MAP")
@Cache(type = CacheType.NONE)
public class INBOUNDMAPID implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2960232756598237532L;

	@Id
	private Integer  DATA_MAP_ID;
	
	@Column(name = "DATA_MAP_NAME")
	private String DATA_MAP_NAME;

	public Integer getDATA_MAP_ID() {
		return DATA_MAP_ID;
	}

	public void setDATA_MAP_ID(Integer dATA_MAP_ID) {
		DATA_MAP_ID = dATA_MAP_ID;
	}

	public String getDATA_MAP_NAME() {
		return DATA_MAP_NAME;
	}

	public void setDATA_MAP_NAME(String dATA_MAP_NAME) {
		DATA_MAP_NAME = dATA_MAP_NAME;
	}
	
	
//	public boolean equals(Object obj) {
//		if (!(obj instanceof INBOUNDMAPID))
//		return false;
//		if (obj == this)
//		return true;
//		return this.DATA_MAP_ID == ((INBOUNDMAPID) obj).DATA_MAP_ID;
//		}
//		public int hashCode() {
//		int result = 0;
//		result = DATA_MAP_ID;
//		return result;
//		}

}
