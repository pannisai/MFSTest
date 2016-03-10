package mfs.biller.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Local;

import mfs.biller.persistence.bean.GWRcptConf;

@Local
public interface GWRcptConfBeanLocal  {
	public Collection<GWRcptConf> getGWRcptConfAll(String GW_RCPT_CONF_ID, String GW_RCPT_CONF_NAME) throws Exception;

	public GWRcptConf findGWRcptConf(String GW_RCPT_CONF_ID, String GW_RCPT_CONF_NAME) throws Exception;

}
