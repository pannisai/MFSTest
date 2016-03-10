package mfs.biller.ejb.interfaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Local;

import mfs.biller.persistence.bean.GWInboundMap;
import mfs.biller.persistence.bean.ObjMapGWxml;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface GWInboundMapBeanLocal {
	  public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.GWInboundMapBean#mfs.biller.ejb.interfaces.GWInboundMapBeanRemote";

		public Collection<GWInboundMap>  getGWInboundMapAll(String DATA_MAP_ID, String DATA_MAP_NAME) throws Exception;
		public int insertGWInboundMap(ObjMapGWxml bean, UserInfoBean user)throws IsExistException, Exception;
		public void updateGWInboundMap(ObjMapGWxml bean, UserInfoBean user)throws IsExistException, Exception;
		public ObjMapGWxml findGWInboundMap(int DATA_MAP_ID,  UserInfoBean user)
				throws Exception ;

}
