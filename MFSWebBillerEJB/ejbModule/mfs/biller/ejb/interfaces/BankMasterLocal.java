package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BankMasterBean;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface BankMasterLocal {
	 public BankMasterBean findBankMaster(String BANK_CODE, UserInfoBean user)throws Exception ;
	 public void insertBankMaster(BankMasterBean bean, UserInfoBean user)throws IsExistException,   Exception ;
	 public List<BankMasterBean> searchBankMasterAll(BankMasterBean PARAM, UserInfoBean user)throws Exception ;
	 public void updateBankMaster(BankMasterBean bean, UserInfoBean user)throws  Exception ;
	 public boolean isExistBankMstrCode(String BANK_CODE, UserInfoBean user)throws Exception;
}
