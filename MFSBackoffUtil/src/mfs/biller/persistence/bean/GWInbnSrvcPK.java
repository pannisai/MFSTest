package mfs.biller.persistence.bean;

import java.io.Serializable;

public class GWInbnSrvcPK implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer INBN_SRVC_ID;
	
	@Override
    public boolean equals(Object obj) {
		return ((obj instanceof GWInbnSrvcPK) && 
				INBN_SRVC_ID.equals(((GWInbnSrvcPK) obj).getINBN_SRVC_ID()));
    }
 
    @Override
    public int hashCode() {
        return INBN_SRVC_ID.hashCode();
    }

	public Integer getINBN_SRVC_ID() {
		return INBN_SRVC_ID;
	}

	public void setINBN_SRVC_ID(Integer gINBN_SRVC_ID) {
		INBN_SRVC_ID = gINBN_SRVC_ID;
	}
}
