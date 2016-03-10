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
public interface GWBankOutbMapBeanLocal {
	 public ObjMapGWxml findGWBankOutboundMap(int DATA_MAP_ID,  UserInfoBean user)
				throws Exception ;
	  public int insertGWBankOutbMap(ObjMapGWxml bean, UserInfoBean user)throws IsExistException, Exception;
	  public void updateGWBankOutbMap(ObjMapGWxml bean, UserInfoBean user)throws IsExistException, Exception;
}
