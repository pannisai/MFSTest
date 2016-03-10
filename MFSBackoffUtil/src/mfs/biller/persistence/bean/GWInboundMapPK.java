package mfs.biller.persistence.bean;

import java.io.Serializable;

public class GWInboundMapPK  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer DATA_MAP_ID;
	
	@Override
    public boolean equals(Object obj) {
		return ((obj instanceof GWInboundMapPK) && 
				DATA_MAP_ID.equals(((GWInboundMapPK) obj).getDATA_MAP_ID()));
    }
 
    @Override
    public int hashCode() {
        return DATA_MAP_ID.hashCode();
    }

	public Integer getDATA_MAP_ID() {
		return DATA_MAP_ID;
	}

	public void setDATA_MAP_ID(Integer iDATA_MAP_ID) {
		DATA_MAP_ID = iDATA_MAP_ID;
	}
}
