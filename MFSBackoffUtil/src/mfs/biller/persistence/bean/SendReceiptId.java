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
@Table(name = "GW_RCPT_CONF")
@Cache(type = CacheType.NONE)
public class SendReceiptId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8466013173496034537L;

	@Id
	private Integer  GW_RCPT_CONF_ID;
	
	@Column(name = "GW_RCPT_CONF_NAME")
	private String GW_RCPT_CONF_NAME;

	public Integer getGW_RCPT_CONF_ID() {
		return GW_RCPT_CONF_ID;
	}

	public void setGW_RCPT_CONF_ID(Integer gW_RCPT_CONF_ID) {
		GW_RCPT_CONF_ID = gW_RCPT_CONF_ID;
	}

	public String getGW_RCPT_CONF_NAME() {
		return GW_RCPT_CONF_NAME;
	}

	public void setGW_RCPT_CONF_NAME(String gW_RCPT_CONF_NAME) {
		GW_RCPT_CONF_NAME = gW_RCPT_CONF_NAME;
	}
	
//	public boolean equals(Object obj) {
//		if (!(obj instanceof SendReceiptId))
//		return false;
//		if (obj == this)
//		return true;
//		return this.GW_RCPT_CONF_ID == ((SendReceiptId) obj).GW_RCPT_CONF_ID;
//		}
//		public int hashCode() {
//		int result = 0;
//		result = GW_RCPT_CONF_ID;
//		return result;
//		}


}
