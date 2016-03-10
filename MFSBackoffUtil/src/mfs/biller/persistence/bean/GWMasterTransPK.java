package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.Date;

public class GWMasterTransPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String TRNS_ID;
	private Date TRNS_DTTM;
	private String TRNS_DEST_CODE;
	private String r__;
	
	@Override
    public boolean equals(Object obj) {
		return ((obj instanceof GWMasterTransPK) && 
				TRNS_ID.equals(((GWMasterTransPK) obj).getTRNS_ID()) &&
				TRNS_DTTM.compareTo(((GWMasterTransPK) obj).getTRNS_DTTM()) == 0 && 
				TRNS_DEST_CODE.equals(((GWMasterTransPK) obj).getTRNS_DEST_CODE())&& 
				r__.equals(((GWMasterTransPK) obj).getR__())
    			);
    }
 
    @Override
    public int hashCode() {
        return this.TRNS_ID.hashCode() + TRNS_DTTM.hashCode() + TRNS_DEST_CODE.hashCode()+r__.hashCode();
    }

	public String getTRNS_ID() {
		return this.TRNS_ID;
	}

	public void setTRNS_ID(String tRNS_ID) {
		this.TRNS_ID = tRNS_ID;
	}

	public Date getTRNS_DTTM() {
		return this.TRNS_DTTM;
	}

	public void setTRNS_DTTM(Date tRNS_DTTM) {
		this.TRNS_DTTM = tRNS_DTTM;
	}

	public String getTRNS_DEST_CODE() {
		return this.TRNS_DEST_CODE;
	}

	public void setTRNS_DEST_CODE(String tRNS_DEST_CODE) {
		this.TRNS_DEST_CODE = tRNS_DEST_CODE;
	}

	public String getR__() {
		return r__;
	}

	public void setR__(String r__) {
		this.r__ = r__;
	}
	
}
