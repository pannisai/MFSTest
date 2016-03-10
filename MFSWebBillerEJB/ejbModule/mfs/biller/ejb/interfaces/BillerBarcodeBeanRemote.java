package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.BillerBarcode;
import mfs.biller.persistence.bean.BillerBarcodeParam;
import mfs.biller.persistence.bean.UserInfoBean;

@Remote
public interface BillerBarcodeBeanRemote {
	
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.BillerBarcodeBean#mfs.biller.ejb.interfaces.BillerBarcodeBeanRemote";
	
	public List<BillerBarcode> getBillerBarcodeAll(UserInfoBean user)throws Exception;
	public List<BillerBarcode> searchBillerBarcode(BillerBarcodeParam PARAM, UserInfoBean user)throws Exception;
	public BillerBarcode findBillerBarcode(int BARC_ID, UserInfoBean user)throws Exception;
	public int insertBillerBarcode(BillerBarcode bean, UserInfoBean user)throws Exception;
	public void updateBillerBarcode(BillerBarcode bean, UserInfoBean user)throws Exception;
	
}
