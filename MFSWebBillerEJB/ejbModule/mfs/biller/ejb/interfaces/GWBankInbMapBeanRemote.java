package mfs.biller.ejb.interfaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.GWBankInbMap;
import mfs.biller.persistence.bean.GWInboundMap;
import mfs.biller.persistence.bean.ObjMapGWxml;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Remote
public interface GWBankInbMapBeanRemote {
	 public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.GWBankInbMapBean#mfs.biller.ejb.interfaces.GWBankInbMapBeanRemote";

	  public Collection<GWBankInbMap> getGWInboundMapAll(String DATA_MAP_ID,
				String DATA_MAP_NAME) throws Exception ;
		public int insertGWBankInbMap(ObjMapGWxml bean, UserInfoBean user)throws IsExistException, Exception;
		public void updateGWBankInbMap(ObjMapGWxml bean, UserInfoBean user)throws IsExistException, Exception;
		public ObjMapGWxml findGWBankInbMap(int DATA_MAP_ID,  UserInfoBean user)
				throws Exception  ;
}
