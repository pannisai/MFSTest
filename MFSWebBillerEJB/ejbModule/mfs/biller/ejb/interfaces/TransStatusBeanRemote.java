package mfs.biller.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.TransStatus;

@Remote
public interface TransStatusBeanRemote {
	  public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.TransStatusBean#mfs.biller.ejb.interfaces.TransStatusBeanRemote";

		public Collection<TransStatus>  getTransStatusAll(String TRNS_STTS_CODE, String TRNS_STTS_NAME) throws Exception;

		public TransStatus findTransStatus(String TRNS_STTS_CODE, String TRNS_STTS_NAME) throws Exception;
}
