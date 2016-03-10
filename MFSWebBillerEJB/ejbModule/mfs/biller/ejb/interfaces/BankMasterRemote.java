package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.BankMasterBean;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Remote
public interface BankMasterRemote {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.BankMaster#mfs.biller.ejb.interfaces.BankMasterRemote";
	 public BankMasterBean findBankMaster(String BANK_CODE, UserInfoBean user)throws Exception ;
	 public void insertBankMaster(BankMasterBean bean, UserInfoBean user)throws IsExistException,   Exception ;
	 public List<BankMasterBean> searchBankMasterAll(BankMasterBean PARAM, UserInfoBean user)throws Exception ;
	 public void updateBankMaster(BankMasterBean bean, UserInfoBean user)throws  Exception ;
	 public boolean isExistBankMstrCode(String BANK_CODE, UserInfoBean user)throws Exception;
}
