package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.BankServiceDetail;
import mfs.biller.persistence.bean.BankServicebean;
import mfs.biller.persistence.bean.GWBankBean;
import mfs.biller.persistence.bean.GWBankDetail;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface GWBankLocal {
	 public GWBankBean findGWBank(int BANK_SRVC_ID, UserInfoBean user)throws Exception;
	 public int insertGWBank(GWBankBean bean, UserInfoBean user)throws  Exception;
	 public List<GWBankDetail> searchGWBankAll(GWBankDetail PARAM, UserInfoBean user)throws Exception;
	 public void updateGWBank(GWBankBean bean, UserInfoBean user)throws  Exception;
	 
}
