package mfs.biller.persistence.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Table(name = "GW_SERVICE")
@Cache(type = CacheType.NONE)
public class MFSServiceId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -395949017736653688L;

	@Id
	private Integer  GW_SRVC_ID;
	
	@Column(name = "GW_SRVC_NAME")
	private String   GW_SRVC_NAME;

	public Integer getGW_SRVC_ID() {
		return GW_SRVC_ID;
	}

	public void setGW_SRVC_ID(Integer gW_SRVC_ID) {
		GW_SRVC_ID = gW_SRVC_ID;
	}

	public String getGW_SRVC_NAME() {
		return GW_SRVC_NAME;
	}

	public void setGW_SRVC_NAME(String gW_SRVC_NAME) {
		GW_SRVC_NAME = gW_SRVC_NAME;
	}
	
	
//	public boolean equals(Object obj) {
//		if (!(obj instanceof MFSServiceId))
//		return false;
//		if (obj == this)
//		return true;
//		return this.GW_SRVC_ID == ((MFSServiceId) obj).GW_SRVC_ID;
//		}
//		public int hashCode() {
//		int result = 0;
//		result = GW_SRVC_ID;
//		return result;
//		}
}
