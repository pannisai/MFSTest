package mfs.biller.ejb.interfaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.GWInbnSrvc;
import mfs.biller.persistence.bean.GWInboundMap;
import mfs.biller.persistence.bean.ObjMapGWxml;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Remote
public interface GWInbnSrvcBeanRemote {
	  public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.GWInbnSrvcBean#mfs.biller.ejb.interfaces.GWInbnSrvcBeanRemote";

}
