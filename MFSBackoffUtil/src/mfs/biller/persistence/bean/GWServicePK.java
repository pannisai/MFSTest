package mfs.biller.persistence.bean;

import java.io.Serializable;

public class GWServicePK implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer GW_SRVC_ID;
	private Integer GW_OUTB_ID;
	
	@Override
    public boolean equals(Object obj) {
		return ((obj instanceof GWServicePK) && 
				GW_SRVC_ID.equals(((GWServicePK) obj).getGW_SRVC_ID()) && 
				GW_OUTB_ID.equals(((GWServicePK) obj).getGW_OUTB_ID()));
    }
 
    @Override
    public int hashCode() {
        return GW_SRVC_ID.hashCode()+GW_OUTB_ID.hashCode();
    }

	public Integer getGW_SRVC_ID() {
		return GW_SRVC_ID;
	}

	public void setGW_SRVC_ID(Integer gGW_SRVC_ID) {
		GW_SRVC_ID = gGW_SRVC_ID;
	}

	public Integer getGW_OUTB_ID() {
		return GW_OUTB_ID;
	}

	public void setGW_OUTB_ID(Integer gW_OUTB_ID) {
		GW_OUTB_ID = gW_OUTB_ID;
	}
}
