package mfs.biller.ejb.interfaces;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.BillerChannel;
import mfs.biller.persistence.bean.BillerFdmCode;
import mfs.biller.persistence.bean.BillerPymtCode;
import mfs.biller.persistence.bean.GWMasterTrans;
import mfs.biller.persistence.bean.GWMasterTransParam;
import mfs.biller.persistence.bean.ReportTransDetail;
import mfs.biller.persistence.bean.ReportTransParam;
import mfs.biller.persistence.bean.UserInfoBean;

@Remote
public interface RptTransBeanRemote {
	
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.RptTransBean#mfs.biller.ejb.interfaces.RptTransBeanRemote";
	
	public Collection<GWMasterTrans> getMasterTransAll(GWMasterTransParam Param)throws Exception;
	public GWMasterTrans findMasterTrans(String TRNS_ID, Date TRNS_DTTM, String TRNS_DEST_CODE, String TABLE_NAME)throws Exception;
	public BigDecimal countRowAll(GWMasterTransParam Param) throws Exception;
	public BigDecimal getTotalAmount(GWMasterTransParam Param) throws Exception;
	
	public List<BillerChannel> getBillerChannelAll(UserInfoBean user)throws Exception;
	public List<BillerFdmCode> getBillerFdmCodeAll(UserInfoBean user)throws Exception;
	public List<BillerPymtCode> getBillerPymtCodeAll(UserInfoBean user)throws Exception;
	public List<ReportTransDetail> searchReportTrans(ReportTransParam PARAM, UserInfoBean user)throws Exception;
	public int countRowReportTrans(ReportTransParam PARAM, UserInfoBean user)throws Exception;
	
}
