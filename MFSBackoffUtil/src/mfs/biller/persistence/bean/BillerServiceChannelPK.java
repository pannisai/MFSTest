package mfs.biller.persistence.bean;

import java.io.Serializable;

public class BillerServiceChannelPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer BLLR_SRVC_ID;
	private Integer BLLR_CHNL_ID;
	
	@Override
    public boolean equals(Object obj) {
		return ((obj instanceof BillerServiceChannelPK) && 
				BLLR_SRVC_ID.equals(((BillerServiceChannelPK) obj).getBLLR_SRVC_ID()) &&
				BLLR_CHNL_ID.equals(((BillerServiceChannelPK) obj).getBLLR_CHNL_ID())
    			);
    }
 
    @Override
    public int hashCode() {
        return this.BLLR_SRVC_ID.hashCode() + BLLR_CHNL_ID.hashCode();
    }
	
	
	public Integer getBLLR_SRVC_ID() {
		return BLLR_SRVC_ID;
	}
	public void setBLLR_SRVC_ID(Integer bLLR_SRVC_ID) {
		BLLR_SRVC_ID = bLLR_SRVC_ID;
	}
	public Integer getBLLR_CHNL_ID() {
		return BLLR_CHNL_ID;
	}
	public void setBLLR_CHNL_ID(Integer bLLR_CHNL_ID) {
		BLLR_CHNL_ID = bLLR_CHNL_ID;
	}

}
