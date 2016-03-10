package mfs.biller.ejb.interfaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Local;

import mfs.biller.persistence.bean.GWServiceMap;
import mfs.biller.persistence.bean.ObjMapGWxml;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface GWServiceMapBeanLocal {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.GWServiceMapBean#mfs.biller.ejb.interfaces.GWServiceMapBeanRemote";

	  public ObjMapGWxml findGWServiceMap(int DATA_MAP_ID,  UserInfoBean user)
				throws Exception;
	  public int insertGWServiceMap(ObjMapGWxml bean, UserInfoBean user)throws IsExistException, Exception;
	  public void updateGWServiceMap(ObjMapGWxml bean, UserInfoBean user)throws IsExistException, Exception;
	  public Collection<GWServiceMap> getGWServiceMapAll(String DATA_MAP_ID,
				String DATA_MAP_NAME) throws Exception ;
}
