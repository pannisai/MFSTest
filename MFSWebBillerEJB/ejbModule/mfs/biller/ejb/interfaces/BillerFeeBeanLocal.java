package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BillerFee;
import mfs.biller.persistence.bean.BillerFeeParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface BillerFeeBeanLocal {
	
	public List<BillerFee> searchBillerFee(BillerFeeParam PARAM, UserInfoBean user)throws Exception;
	public BillerFee findBillerFee(int BLLR_FEE_ID, UserInfoBean user)throws Exception;
	public BillerFee findBillerFeeExpired(int BLLR_SRVC_ID, UserInfoBean user)throws Exception;
	public int insertBillerFee(BillerFee bean, UserInfoBean user)throws IsExistException, Exception;
	public void updateBillerFee(BillerFee bean, UserInfoBean user)throws IsExistException, Exception;
	public boolean isExistBillerFee(BillerFeeParam PARAM, UserInfoBean user)throws Exception;
}
