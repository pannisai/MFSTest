package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.BillerFee;
import mfs.biller.persistence.bean.BillerFeeParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Remote
public interface BillerFeeBeanRemote {
	
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.BillerFeeBean#mfs.biller.ejb.interfaces.BillerFeeBeanRemote";	
	public List<BillerFee> searchBillerFee(BillerFeeParam PARAM, UserInfoBean user)throws Exception;
	public BillerFee findBillerFee(int BLLR_FEE_ID, UserInfoBean user)throws Exception;
	public BillerFee findBillerFeeExpired(int BLLR_SRVC_ID, UserInfoBean user)throws Exception;
	public int insertBillerFee(BillerFee bean, UserInfoBean user)throws IsExistException, Exception;
	public void updateBillerFee(BillerFee bean, UserInfoBean user)throws IsExistException, Exception;
	public boolean isExistBillerFee(BillerFeeParam PARAM, UserInfoBean user)throws Exception;
}
