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

@Table(name = "GW_INBOUND")
@Cache(type = CacheType.NONE)
public class SendReceipt  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4190275172979232569L;
	@Id
	private Integer INBN_SRVC_ID;
	
	

	@Column(name="SEND_RCPT_FLAG")
	private String SEND_RCPT_FLAG;

	



	public Integer getINBN_SRVC_ID() {
		return INBN_SRVC_ID;
	}

	public void setINBN_SRVC_ID(Integer iNBN_SRVC_ID) {
		INBN_SRVC_ID = iNBN_SRVC_ID;
	}

	



	public String getSEND_RCPT_FLAG() {
		return SEND_RCPT_FLAG;
	}

	public void setSEND_RCPT_FLAG(String sEND_RCPT_FLAG) {
		SEND_RCPT_FLAG = sEND_RCPT_FLAG;
	}

	
//	public boolean equals(Object obj) {
//		if (!(obj instanceof SendReceipt))
//		return false;
//		if (obj == this)
//		return true;
//		return this.INBN_SRVC_ID == ((SendReceipt) obj).INBN_SRVC_ID;
//		}
//		public int hashCode() {
//		int result = 0;
//		result = INBN_SRVC_ID;
//		return result;
//		}
//





}
