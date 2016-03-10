package mfs.biller.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.GWOutbound;

@Remote
public interface GWOutboundBeanRemote {
	  public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.GWOutboundBean#mfs.biller.ejb.interfaces.GWOutboundBeanRemote";

		public Collection<GWOutbound>  getGWOutboundAll(String GW_OUTB_ID, String GW_OUTB_NAME) throws Exception;

		public GWOutbound findGWOutbound(String GW_OUTB_ID, String GW_OUTB_NAME) throws Exception;
}
