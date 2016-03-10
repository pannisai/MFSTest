package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.BillerDenominate;
import mfs.biller.persistence.bean.ERPBankAccount;
import mfs.biller.persistence.bean.ERPBankAccountDtail;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface ERPBankAccountBeanLocal {
	public ERPBankAccount findERPBankAccount(Integer BLLR_SRVC_FIN_ID, UserInfoBean user) throws Exception ;
	public int insertERPBankAccount(ERPBankAccount bean, UserInfoBean user) throws Exception ;
	public List<ERPBankAccountDtail> searchERPBankAccount(Integer BLLR_SRVC_ID,String BLLR_ACCT_BANK_CODE,String ACT_FLAG, UserInfoBean user) throws Exception ;
	public void updateERPBankAccount(ERPBankAccount bean, UserInfoBean user) throws Exception ;

}
