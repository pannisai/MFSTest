package mfs.biller.ejb.interfaces;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.BillerChannel;
import mfs.biller.persistence.bean.BillerChannelParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Remote
public interface BillerChannelBeanRemote {
	
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.BillerChannelBean#mfs.biller.ejb.interfaces.BillerChannelBeanRemote";

	public Collection<BillerChannel>  getBillerChannelAll(String BLLR_CHNL_CODE, String BLLR_CODE) throws Exception;
	public BillerChannel findBillerChannel(String BLLR_CHNL_CODE, String BLLR_CODE) throws Exception;
	public List<BillerChannel> searchBillerChannel(BillerChannelParam PARAM, UserInfoBean user)throws Exception;
	public BillerChannel findBillerChannel(int BLLR_CHNL_ID, UserInfoBean user)throws Exception;
	public boolean isExistBllrChnlName(int BLLR_CHNL_ID, String BLLR_CHNL_NAME, UserInfoBean user)throws Exception;
	public boolean isExistBllrChnlCode(int BLLR_CHNL_ID, String BLLR_CHNL_CODE, UserInfoBean user)throws Exception;
	public int insertBillerChannel(BillerChannel bean, UserInfoBean user)throws IsExistException, Exception;
	public void updateBillerChannel(BillerChannel bean, UserInfoBean user)throws IsExistException, Exception;
	
}
