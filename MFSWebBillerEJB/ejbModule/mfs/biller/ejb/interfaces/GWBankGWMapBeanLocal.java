package mfs.biller.ejb.interfaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Local;

import mfs.biller.persistence.bean.GWServiceMap;
import mfs.biller.persistence.bean.ObjMapGWxml;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface GWBankGWMapBeanLocal {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.GWBankGWMapBean#mfs.biller.ejb.interfaces.GWBankGWMapBeanRemote";

	public ObjMapGWxml findGWBankGWMap(int DATA_MAP_ID,  UserInfoBean user)
			throws Exception ;
	public int insertGWBankGWMap(ObjMapGWxml bean, UserInfoBean user)throws IsExistException, Exception;
	public void updateGWBankGWMap(ObjMapGWxml bean, UserInfoBean user)throws IsExistException, Exception;
}
