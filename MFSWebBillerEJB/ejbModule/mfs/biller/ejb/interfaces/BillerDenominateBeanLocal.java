package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.BillerDenominate;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface BillerDenominateBeanLocal {
	public BillerDenominate findBillerDenominate(Integer BLLR_DENM_ID, UserInfoBean user)throws Exception;
	public int insertBillerDenominate(BillerDenominate bean, UserInfoBean user)throws  Exception;
	public void updateBillerDenominate(BillerDenominate bean, UserInfoBean user)throws  Exception;
	public List<BillerDenominate> searchBillerDenominateAll(Integer BLLR_SRVC_ID, UserInfoBean user)throws Exception;
}
