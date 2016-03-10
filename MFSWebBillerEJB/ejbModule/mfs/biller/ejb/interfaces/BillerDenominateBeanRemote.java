package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.BillerDenominate;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Remote
public interface BillerDenominateBeanRemote {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.BillerDenominateBean#mfs.biller.ejb.interfaces.BillerDenominateBeanRemote";
	public BillerDenominate findBillerDenominate(Integer BLLR_DENM_ID, UserInfoBean user)throws Exception;
	public int insertBillerDenominate(BillerDenominate bean, UserInfoBean user)throws  Exception;
	public void updateBillerDenominate(BillerDenominate bean, UserInfoBean user)throws  Exception;
	public List<BillerDenominate> searchBillerDenominateAll(Integer BLLR_SRVC_ID, UserInfoBean user)throws Exception;
}
