package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface BankChannelLocal {
	public BankChannelBean findBankChannel(String BANK_CHNL_CODE, UserInfoBean user)throws Exception ;
	public void insertBankChannel(BankChannelBean bean, UserInfoBean user)throws IsExistException, Exception ;
	public List<BankChannelBean> searchBankChannelAll(BankChannelBean PARAM, UserInfoBean user)throws Exception ;
	public void updateBankChannel(BankChannelBean bean, UserInfoBean user)throws  Exception ;
	public boolean isExistBankChannelCode(String BANK_CHNL_CODE, UserInfoBean user)throws Exception;
}
