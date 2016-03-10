package mfs.biller.ejb.interfaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Local;

import mfs.biller.persistence.bean.GWOutbound;
import mfs.biller.persistence.bean.GWOutboundMap;
import mfs.biller.persistence.bean.ObjMapGWxml;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface GWOutboundMapBeanLocal {

	  public ObjMapGWxml findGWOutboundMap(int DATA_MAP_ID, UserInfoBean user)
				throws Exception;
	  public Collection<GWOutboundMap> getGWOutboundMapAll(String DATA_MAP_ID,
				String DATA_MAP_NAME) throws Exception ;
	  public int insertGWOutboundMap(ObjMapGWxml bean, UserInfoBean user)throws IsExistException, Exception;
	  public void updateGWOutboundMap(ObjMapGWxml bean, UserInfoBean user)throws IsExistException, Exception;
}
