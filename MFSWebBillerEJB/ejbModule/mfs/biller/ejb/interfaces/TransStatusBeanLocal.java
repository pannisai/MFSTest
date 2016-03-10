package mfs.biller.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Local;

import mfs.biller.persistence.bean.TransStatus;

//import org.apache.commons.collections.map.MultiKeyMap;
@Local
public interface TransStatusBeanLocal {
	public Collection<TransStatus> getTransStatusAll(String TRNS_STTS_CODE, String TRNS_STTS_NAME) throws Exception;

	public TransStatus findTransStatus(String TRNS_STTS_CODE, String TRNS_STTS_NAME) throws Exception;

}
