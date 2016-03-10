package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.BankServiceDetail;
import mfs.biller.persistence.bean.BankServicebean;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Remote
public interface BankServiceRemote {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.BankService#mfs.biller.ejb.interfaces.BankServiceRemote";
	
	 public BankServicebean findBankService(int BANK_SRVC_ID, UserInfoBean user)throws Exception;
	 public List<BankServiceDetail> searchBankServiceAll(BankServicebean PARAM, UserInfoBean user)throws Exception;
	 public int insertBankService(BankServicebean bean, UserInfoBean user)throws  Exception ;
	 public void updateBankService(BankServicebean bean, UserInfoBean user)throws  Exception;
}
