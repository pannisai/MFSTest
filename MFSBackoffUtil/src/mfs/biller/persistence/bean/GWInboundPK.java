package mfs.biller.persistence.bean;

import java.io.Serializable;

public class GWInboundPK implements Serializable {


	private static final long serialVersionUID = 1L;
	private Integer INBN_SRVC_ID;
	private Integer SRCE_SRVC_ID;
	private Integer DEST_SRVC_ID;
	public Integer getINBN_SRVC_ID() {
		return INBN_SRVC_ID;
	}
	public void setINBN_SRVC_ID(Integer iNBN_SRVC_ID) {
		INBN_SRVC_ID = iNBN_SRVC_ID;
	}
	public Integer getSRCE_SRVC_ID() {
		return SRCE_SRVC_ID;
	}
	public void setSRCE_SRVC_ID(Integer sRCE_SRVC_ID) {
		SRCE_SRVC_ID = sRCE_SRVC_ID;
	}
	public Integer getDEST_SRVC_ID() {
		return DEST_SRVC_ID;
	}
	public void setDEST_SRVC_ID(Integer dEST_SRVC_ID) {
		DEST_SRVC_ID = dEST_SRVC_ID;
	}

}
