package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.BankServiceDetail;
import mfs.biller.persistence.bean.BankServicebean;
import mfs.biller.persistence.bean.BankSystemMATN;
import mfs.biller.persistence.bean.BankSystemMATNDetail;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface BankSystemMATNBeanLocal {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.BankSystemMATNBean#mfs.biller.ejb.interfaces.BankSystemMATNBeanRemote";
	 public List<BankSystemMATNDetail> searchBankSystemMATNAll(BankSystemMATNDetail PARAM, UserInfoBean user)throws Exception;
	 public BankSystemMATN findBankSystemMATN(int BANK_SRVC_ID, UserInfoBean user)throws Exception;
	 public int insertBankSystemMATN(BankSystemMATN bean, UserInfoBean user)throws  Exception;
	 public void updateBankSystemMATN(BankSystemMATN bean, UserInfoBean user)throws  Exception;
	
}
