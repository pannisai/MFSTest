package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BillerBarcode;
import mfs.biller.persistence.bean.BillerBarcodeDetail;
import mfs.biller.persistence.bean.BillerBarcodeRef;
import mfs.biller.persistence.bean.BillerChannel;
import mfs.biller.persistence.bean.BillerForm;
import mfs.biller.persistence.bean.BillerPaymentValidate;
import mfs.biller.persistence.bean.BillerRef;
import mfs.biller.persistence.bean.BillerRefDetail;
import mfs.biller.persistence.bean.BillerRefParam;
import mfs.biller.persistence.bean.BillerService;
import mfs.biller.persistence.bean.BillerServiceChannel;
import mfs.biller.persistence.bean.BillerServiceDetail;
import mfs.biller.persistence.bean.BillerServiceImage;
import mfs.biller.persistence.bean.BillerServiceParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.NotFoundDataException;

@Local
public interface BillerServiceBeanLocal  {
	
	public List<BillerServiceDetail> searchBillerService(BillerServiceParam PARAM, UserInfoBean user)throws Exception;
	public int insertInformation(BillerService bean, UserInfoBean user)throws Exception;
	public int insertBillerPaymentValidate(BillerPaymentValidate bean, UserInfoBean user)throws Exception;
	public List<BillerChannel> searchBillerChannel(int BLLR_SRVC_ID, UserInfoBean user)throws Exception;
	public List<BillerServiceChannel> searchBillerServiceChannel(int BLLR_SRVC_ID, UserInfoBean user)throws Exception;
	public void insertBillerServiceChannel(BillerServiceChannel bean, UserInfoBean user)throws Exception;
	public int insertBillerForm(BillerForm bean, UserInfoBean user)throws Exception;
	public List<BillerRef> searchBillerRef(BillerRefParam PARAM, UserInfoBean user)throws Exception;
	public List<BillerBarcode> searchBillerBarcode(int BLLR_SRVC_ID, UserInfoBean user)throws Exception;
	public List<BillerBarcodeDetail> searchBillerBarcodeDetail(int BLLR_SRVC_ID, UserInfoBean user)throws Exception;
	public BillerForm findBillerForm(int BLLR_FORM_ID, UserInfoBean user)throws NotFoundDataException, Exception;
	public int insertBillerRef(BillerRef bean, UserInfoBean user)throws Exception;
	public BillerRefDetail findBillerRefDetail(int REF_ID, UserInfoBean user)throws NotFoundDataException, Exception;
	public List<BillerBarcode> getBillerBarcodeAll(UserInfoBean user)throws Exception;
	public int insertBillerBarcodeRef(BillerBarcodeRef bean, UserInfoBean user)throws Exception;
	public BillerService findBillerService(int BLLR_SRVC_ID, UserInfoBean user)throws NotFoundDataException, Exception;
	public void updateBillerService(BillerService bean, UserInfoBean user)throws Exception;
	public BillerPaymentValidate findBillerPaymentValidate(int BLLR_SRVC_ID, UserInfoBean user)throws NotFoundDataException, Exception;
	public void updateBillerPaymentValidate(BillerPaymentValidate bean, UserInfoBean user)throws Exception;
	public BillerServiceChannel findBillerServiceChannel(int BLLR_SRVC_ID, int BLLR_CHNL_ID, UserInfoBean user)throws NotFoundDataException, Exception;
	public void updateBillerServiceChannel(BillerServiceChannel bean, UserInfoBean user)throws Exception;
	public BillerForm findBillerFormBySrvcId(int BLLR_SRVC_ID, UserInfoBean user)throws NotFoundDataException, Exception;
	public void updateBillerForm(BillerForm bean, UserInfoBean user)throws Exception;
	public BillerRef findBillerRef(int REF_ID, UserInfoBean user)throws NotFoundDataException, Exception;
	public void updateBillerRef(BillerRef bean, UserInfoBean user)throws Exception;
	public BillerBarcodeRef findBillerBarcodeRef(int BARC_REF_ID, UserInfoBean user)throws NotFoundDataException, Exception;
	public void updateBillerBarcodeRef(BillerBarcodeRef bean, UserInfoBean user)throws Exception;
	public BillerServiceImage getBillerServiceImage(String serviceId) throws Exception;
	public Integer saveBillerImage(BillerServiceImage billerServiceImage, UserInfoBean user) throws Exception;
	public void updateBillerServiceImage(BillerServiceImage billerServiceImage, UserInfoBean user) throws Exception;
	
}
