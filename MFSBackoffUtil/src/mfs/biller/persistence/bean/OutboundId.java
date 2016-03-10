package mfs.biller.persistence.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Table(name = "GW_OUTBOUND")
@Cache(type = CacheType.NONE)
public class OutboundId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2164190286984982754L;

	@Id
	private Integer  GW_OUTB_ID;
	
	@Column(name = "GW_OUTB_NAME")
	private String   GW_OUTB_NAME ;

	public Integer getGW_OUTB_ID() {
		return GW_OUTB_ID;
	}

	public void setGW_OUTB_ID(Integer gW_OUTB_ID) {
		GW_OUTB_ID = gW_OUTB_ID;
	}

	public String getGW_OUTB_NAME() {
		return GW_OUTB_NAME;
	}

	public void setGW_OUTB_NAME(String gW_OUTB_NAME) {
		GW_OUTB_NAME = gW_OUTB_NAME;
	}
//	public boolean equals(Object obj) {
//		if (!(obj instanceof OutboundId))
//		return false;
//		if (obj == this)
//		return true;
//		return this.GW_OUTB_ID == ((OutboundId) obj).GW_OUTB_ID;
//		}
//		public int hashCode() {
//		int result = 0;
//		result = GW_OUTB_ID;
//		return result;
//		}

	
}
