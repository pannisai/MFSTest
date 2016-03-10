package mfs.biller.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.GWService;

@Remote
public interface GWServiceBeanRemote {
	  public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.GWServiceBean#mfs.biller.ejb.interfaces.GWServiceBeanRemote";

		public Collection<GWService>  getGWServiceAll(String GW_SRVC_ID, String GW_SRVC_NAME) throws Exception;

		public GWService findGWService(String GW_SRVC_ID, String GW_SRVC_NAME) throws Exception;
}
