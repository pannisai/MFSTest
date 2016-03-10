package mfs.biller.persistence.bean;

import java.io.Serializable;

public class TransStatusPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer TRNS_STTS_CODE;
	
	@Override
    public boolean equals(Object obj) {
		return ((obj instanceof TransStatusPK) && 
				TRNS_STTS_CODE.equals(((TransStatusPK) obj).getTRNS_STTS_CODE()));
    }
 
    @Override
    public int hashCode() {
        return this.TRNS_STTS_CODE.hashCode();
    }

	public Integer  getTRNS_STTS_CODE() {
		return this.TRNS_STTS_CODE;
	}

	public void setTRNS_STTS_CODE(Integer  TRNS_STTS_CODE) {
		this.TRNS_STTS_CODE = TRNS_STTS_CODE;
	}
}
