package mfs.biller.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Local;

import mfs.biller.persistence.bean.GWService;

@Local
public interface GWServiceBeanLocal  {
	public Collection<GWService> getGWServiceAll(String GW_SRVC_ID, String GW_SRVC_NAME) throws Exception;

	public GWService findGWService(String GW_SRVC_ID, String GW_SRVC_NAME) throws Exception;

}
