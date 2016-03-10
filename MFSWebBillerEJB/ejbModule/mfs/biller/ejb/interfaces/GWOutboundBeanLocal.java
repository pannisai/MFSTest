package mfs.biller.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Local;

import mfs.biller.persistence.bean.GWOutbound;

@Local
public interface GWOutboundBeanLocal  {
	public Collection<GWOutbound> getGWOutboundAll(String BLLR_MSTR_ID, String GW_OUTB_NAME) throws Exception;

	public GWOutbound findGWOutbound(String BLLR_MSTR_ID, String GW_OUTB_NAME) throws Exception;

}
