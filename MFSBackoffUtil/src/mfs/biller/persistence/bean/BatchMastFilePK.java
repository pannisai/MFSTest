package mfs.biller.persistence.bean;

import java.io.Serializable;

public class BatchMastFilePK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer BTCH_MAST_FILE_ID;
	
	@Override
    public boolean equals(Object obj) {
		return ((obj instanceof BatchMastFilePK) && 
				BTCH_MAST_FILE_ID.equals(((BatchMastFilePK) obj).getBTCH_MAST_FILE_ID()));
    }
 
    @Override
    public int hashCode() {
        return BTCH_MAST_FILE_ID.hashCode();
    }

	public Integer  getBTCH_MAST_FILE_ID() {
		return BTCH_MAST_FILE_ID;
	}

	public void setBTCH_MAST_FILE_ID(Integer iBTCH_MAST_FILE_ID) {
		this.BTCH_MAST_FILE_ID = iBTCH_MAST_FILE_ID;
	}
}
