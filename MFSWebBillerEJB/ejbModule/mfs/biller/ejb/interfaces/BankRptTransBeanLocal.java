package mfs.biller.ejb.interfaces;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BankFdmCode;
import mfs.biller.persistence.bean.BankReportTransDetail;
import mfs.biller.persistence.bean.BankReportTransParam;
import mfs.biller.persistence.bean.BillerChannel;
import mfs.biller.persistence.bean.BillerFdmCode;
import mfs.biller.persistence.bean.BillerPymtCode;
import mfs.biller.persistence.bean.GWMasterTrans;
import mfs.biller.persistence.bean.GWMasterTransParam;
import mfs.biller.persistence.bean.ReportTransDetail;
import mfs.biller.persistence.bean.ReportTransParam;
import mfs.biller.persistence.bean.UserInfoBean;

@Local
public interface BankRptTransBeanLocal {
	
	public List<BankFdmCode> getBankFdmCodeAll(UserInfoBean user)throws Exception;
	public List<BankReportTransDetail> searchBankReportTrans(BankReportTransParam PARAM, UserInfoBean user)throws Exception;
	public BankReportTransDetail findBankMasterTrans(String TRNS_ID, UserInfoBean user)throws Exception ;
	public int countRowBankReportTrans(BankReportTransParam PARAM, UserInfoBean user)throws Exception;
}
