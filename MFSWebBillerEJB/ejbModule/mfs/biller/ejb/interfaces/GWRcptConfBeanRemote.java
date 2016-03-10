package mfs.biller.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.GWRcptConf;

@Remote
public interface GWRcptConfBeanRemote {
	  public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.GWRcptConfBean#mfs.biller.ejb.interfaces.GWRcptConfBeanRemote";

		public Collection<GWRcptConf>  getGWRcptConfAll(String GW_RCPT_CONF_ID, String GW_RCPT_CONF_NAME) throws Exception;

		public GWRcptConf findGWRcptConf(String GW_RCPT_CONF_ID, String GW_RCPT_CONF_NAME) throws Exception;
}
