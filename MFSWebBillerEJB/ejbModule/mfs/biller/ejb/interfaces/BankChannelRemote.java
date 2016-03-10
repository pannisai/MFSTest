package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Remote
public interface BankChannelRemote {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.BankChannel#mfs.biller.ejb.interfaces.BankChannelRemote";
	public BankChannelBean findBankChannel(String BANK_CHNL_CODE, UserInfoBean user)throws Exception ;
	public void insertBankChannel(BankChannelBean bean, UserInfoBean user)throws IsExistException, Exception ;
	public List<BankChannelBean> searchBankChannelAll(BankChannelBean PARAM, UserInfoBean user)throws Exception ;
	public void updateBankChannel(BankChannelBean bean, UserInfoBean user)throws  Exception ;
	public boolean isExistBankChannelCode(String BANK_CHNL_CODE, UserInfoBean user)throws Exception;
}
