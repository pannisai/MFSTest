package mfs.biller.ejb.interfaces;

import javax.ejb.Local;

import mfs.biller.persistence.bean.GWXmlDataSrcBean;
import mfs.biller.persistence.bean.UserInfoBean;

@Local
public interface GWXmlDataSrcLocal {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.GWXmlDataSrc#mfs.biller.ejb.interfaces.GWXmlDataSrcRemote";
	public GWXmlDataSrcBean findGWXmlDataSrc(int XML_DATA_ID,  UserInfoBean user)
			throws Exception;
	public int insertGWXmlDataSrc(GWXmlDataSrcBean bean, UserInfoBean user)throws Exception;
	public void updateGWXmlDataSrc(GWXmlDataSrcBean bean, UserInfoBean user)throws Exception;

}
