package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BillerBarcode;
import mfs.biller.persistence.bean.BillerBarcodeParam;
import mfs.biller.persistence.bean.UserInfoBean;

@Local
public interface BillerBarcodeBeanLocal {
	
	public List<BillerBarcode> getBillerBarcodeAll(UserInfoBean user)throws Exception;
	public List<BillerBarcode> searchBillerBarcode(BillerBarcodeParam PARAM, UserInfoBean user)throws Exception;
	public BillerBarcode findBillerBarcode(int BARC_ID, UserInfoBean user)throws Exception;
	public int insertBillerBarcode(BillerBarcode bean, UserInfoBean user)throws Exception;
	public void updateBillerBarcode(BillerBarcode bean, UserInfoBean user)throws Exception;
	
}
