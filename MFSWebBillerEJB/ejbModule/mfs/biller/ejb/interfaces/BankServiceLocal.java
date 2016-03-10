package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.BankServiceDetail;
import mfs.biller.persistence.bean.BankServicebean;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface BankServiceLocal {
	 public BankServicebean findBankService(int BANK_SRVC_ID, UserInfoBean user)throws Exception;
	 public List<BankServiceDetail> searchBankServiceAll(BankServicebean PARAM, UserInfoBean user)throws Exception;
	 public int insertBankService(BankServicebean bean, UserInfoBean user)throws  Exception ;
	 public void updateBankService(BankServicebean bean, UserInfoBean user)throws  Exception;
}
