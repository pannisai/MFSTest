package mfs.biller.ejb.interfaces;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BillerMaster;
import mfs.biller.persistence.bean.BillerMasterParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface BillerMasterBeanLocal {
	
	public Collection<BillerMaster> getBillerMasterAll(String BLLR_MSTR_ID, String BLLR_CODE) throws Exception;
	public BillerMaster findBillerMaster(String BLLR_MSTR_ID, String BLLR_CODE) throws Exception;
	public List<BillerMaster> searchBillerMaster(BillerMasterParam PARAM, UserInfoBean user)throws Exception;
	public BillerMaster findBillerMaster(int BLLR_MSTR_ID, UserInfoBean user)throws Exception;
	public boolean isExistBllrMstrId(int BLLR_MSTR_ID, UserInfoBean user)throws Exception;
	public boolean isExistBllrCode(int BLLR_MSTR_ID, String BLLR_CODE, UserInfoBean user)throws Exception;
	public void insertBillerMaster(BillerMaster bean, UserInfoBean user)throws IsExistException, Exception;
	public void updateBillerMaster(BillerMaster bean, UserInfoBean user)throws IsExistException, Exception;

}
