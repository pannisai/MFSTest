package mfs.biller.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Local;

import mfs.biller.persistence.bean.GWInbnSrvc;

@Local
public interface GWInbnSrvcBeanLocal  {
	public Collection<GWInbnSrvc> getGWInbnSrvcAll(String INBN_SRVC_ID, String INBN_SRVC_NAME) throws Exception;

	public GWInbnSrvc findGWInbnSrvc(String INBN_SRVC_ID, String INBN_SRVC_NAME) throws Exception;

}
