package mfs.biller.persistence.bean;

import java.io.Serializable;

public class GWOutboundPK implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer GW_OUTB_ID;
	
	@Override
    public boolean equals(Object obj) {
		return ((obj instanceof GWOutboundPK) && 
				GW_OUTB_ID.equals(((GWOutboundPK) obj).getGW_OUTB_ID()));
    }
 
    @Override
    public int hashCode() {
        return GW_OUTB_ID.hashCode();
    }

	public Integer getGW_OUTB_ID() {
		return GW_OUTB_ID;
	}

	public void setGW_OUTB_ID(Integer gW_OUTB_ID) {
		GW_OUTB_ID = gW_OUTB_ID;
	}
}
