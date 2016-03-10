package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.BillerCategory;
import mfs.biller.persistence.bean.BillerCategoryParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Remote
public interface BillerCatalogBeanRemote {
	
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.BillerCatalogBean#mfs.biller.ejb.interfaces.BillerCatalogBeanRemote";
	
	public List<BillerCategory> searchBillerCatalog(BillerCategoryParam PARAM, UserInfoBean user)throws Exception;
	public BillerCategory findBillerCatalog(int BLLR_CATG_ID, UserInfoBean user)throws Exception;
	public boolean isExistBllrCatgName(int BLLR_CATG_ID, String BLLR_CATG_NAME, UserInfoBean user)throws Exception;
	public boolean isExistBillMenuSeq(int BLLR_CATG_ID, int BILL_MENU_SEQ, UserInfoBean user)throws Exception;
	public int insertBillerCatalog(BillerCategory bean, UserInfoBean user)throws IsExistException, Exception;
	public void updateBillerCatalog(BillerCategory bean, UserInfoBean user)throws IsExistException, Exception;
}
