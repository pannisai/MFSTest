package mfs.biller.persistence.bean;

import java.io.Serializable;

public class GWRcptConfPK implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer GW_RCPT_CONF_ID;
	
	@Override
    public boolean equals(Object obj) {
		return ((obj instanceof GWRcptConfPK) && 
				GW_RCPT_CONF_ID.equals(((GWRcptConfPK) obj).getGW_RCPT_CONF_ID()));
    }
 
    @Override
    public int hashCode() {
        return GW_RCPT_CONF_ID.hashCode();
    }

	public Integer getGW_RCPT_CONF_ID() {
		return GW_RCPT_CONF_ID;
	}

	public void setGW_RCPT_CONF_ID(Integer gGW_RCPT_CONF_ID) {
		GW_RCPT_CONF_ID = gGW_RCPT_CONF_ID;
	}
}
