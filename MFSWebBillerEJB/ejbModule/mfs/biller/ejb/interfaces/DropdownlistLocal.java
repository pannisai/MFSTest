package mfs.biller.ejb.interfaces;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.BankMasterBean;
import mfs.biller.persistence.bean.BankServicebean;
import mfs.biller.persistence.bean.BillerCategory;
import mfs.biller.persistence.bean.BillerChannel;
import mfs.biller.persistence.bean.BillerIntegration;
import mfs.biller.persistence.bean.BillerMaster;
import mfs.biller.persistence.bean.BillerRefDataType;
import mfs.biller.persistence.bean.DropdownlistBillMSRT;
import mfs.biller.persistence.bean.BillerFeeMast;
import mfs.biller.persistence.bean.DropdownlistBillservice;
import mfs.biller.persistence.bean.GWBankBean;
import mfs.biller.persistence.bean.GWBankDetail;
import mfs.biller.persistence.bean.GWOutboundMap;
import mfs.biller.persistence.bean.GWService;
import mfs.biller.persistence.bean.GWServiceMap;
import mfs.biller.persistence.bean.INBOUNDMAPID;
import mfs.biller.persistence.bean.MFSServiceId;
import mfs.biller.persistence.bean.OutboundId;
import mfs.biller.persistence.bean.SendReceipt;
import mfs.biller.persistence.bean.SendReceiptId;
import mfs.biller.persistence.bean.UserInfoBean;

@Local
public interface DropdownlistLocal {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.Dropdownlist#mfs.biller.session.stateless.DropdownlistRemote";
	public List<BillerCategory> getDropdownlistCATG() throws Exception;
	public List<DropdownlistBillservice> getDropdownlistSERVICE(BillerCategory BillerCategory,DropdownlistBillMSRT DropdownlistBillMSRT) throws Exception;
	public List<DropdownlistBillMSRT> getDropdownlistMSTR(BillerCategory BillerCategory) throws Exception;
	public List<MFSServiceId> getMFSServiceId() throws Exception;
	public List<INBOUNDMAPID> getINBOUNDMAPID() throws Exception;
	public List<SendReceiptId> getSendReceiptId() throws Exception;
	public List<OutboundId> getOutboundId() throws Exception;
	public List<BillerChannel> getPaymentChanel() throws Exception ;
	public List<GWOutboundMap> getOutboundMapId() throws Exception;
	public List<SendReceipt> getSendReceipt() throws Exception;
	public List<GWServiceMap> getServicemapId() throws Exception;
	public Collection<BillerMaster> getBillerMasterAll(Integer BLLR_CATG_ID) throws Exception ;
	public List<GWService> getServiceGateway() throws Exception;
	public List<BankMasterBean> getBankCode() throws Exception ;
	public List<BankMasterBean> getBankStatus() throws Exception;
	public List<BankChannelBean> getBankChannel() throws Exception;
	public List<BankChannelBean> getBankChannelStatus() throws Exception ;
	List<BankServicebean> getBankService() throws Exception ;
	public List<BankServicebean> getBankServiceStatus() throws Exception ;
	public List<GWBankBean> getGWBankStatus() throws Exception ;
	public List<BankServicebean> getGWBankService() throws Exception ;
	public List<GWBankDetail> getGWInboundMapID() throws Exception ;
	public List<GWBankDetail> getGWServiceMapID() throws Exception ;
	public List<GWBankDetail> getGWOutboundMapID() throws Exception ;
	public List<BillerIntegration> getBillerIntegration() throws Exception;
	public List<DropdownlistBillservice> getDropdownlistInquiryService(List service) throws Exception ;	
	public List<BillerRefDataType> getBillerRefDataType(UserInfoBean userInfo) throws Exception ;
	public List<BillerFeeMast> getBillerFeeMast() throws Exception ;
	
}
