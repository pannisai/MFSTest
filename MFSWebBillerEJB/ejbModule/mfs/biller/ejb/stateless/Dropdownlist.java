package mfs.biller.ejb.stateless;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.DropdownlistLocal;
import mfs.biller.ejb.interfaces.DropdownlistRemote;
import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.BankMasterBean;
import mfs.biller.persistence.bean.BankServicebean;
import mfs.biller.persistence.bean.BillerCategory;
import mfs.biller.persistence.bean.BillerChannel;
import mfs.biller.persistence.bean.BillerFeeMast;
import mfs.biller.persistence.bean.BillerIntegration;
import mfs.biller.persistence.bean.BillerMaster;
import mfs.biller.persistence.bean.BillerRefDataType;
import mfs.biller.persistence.bean.DropdownlistBillMSRT;
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
import mfs.biller.util.JpaResultUtil;
import mfs.biller.util.Timer;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

@Stateless(name = "Dropdownlist", mappedName = "mfs.biller.ejb.Dropdownlist")
@CallByReference
public class Dropdownlist implements DropdownlistRemote, DropdownlistLocal {

	public Dropdownlist() {
		
	}

	private Logger log = Logger.getLogger("EJBDROPDOWNLIST");
	private String page = "EJBDROPDOWNLIST";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<BillerCategory> getDropdownlistCATG() throws Exception {
		List listBillerCategory = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getDropdownlistCATG|Time:" + timer.getStartTime());
			String sql = "SELECT BLLR_CATG_ID,BLLR_CATG_NAME,BLLR_CATG_DESC,ACT_FLAG,CRTD_BY,CRTD_DTTM,LAST_CHNG_BY,LAST_CHNG_DTTM " + "FROM BILLER_CATEGORY  order by BLLR_CATG_ID  ASC ";
			log.info("|" + page + "|getDropdownlistCATG|SQL:" + sql);
			Query query = em.createNativeQuery(sql, BillerCategory.class);
			listBillerCategory = (List) query.getResultList();
			log.info("|" + page + "|getDropdownlistCATG|List:" + listBillerCategory.size());
			/*
			 * if ( (listBillerCategory != null) && (listBillerCategory.size() >
			 * 0)){ return listBillerCategory; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getDropdownlistCATG|Time:" + timer.getStopTime());
			return listBillerCategory;
		} catch (Exception e) {
			log.error("|" + page + "|findBillerCatalog|Exception:" + e.getMessage());
			throw e;
		}
		// return listBillerCategory;
	}

	public List<DropdownlistBillMSRT> getDropdownlistMSTR(BillerCategory BillerCategory) throws Exception {
		List listDropdownlistBillMSRT = null;

		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getDropdownlistMSTR|Time|" + timer.getStartTime());
			log.info("|" + page + "|getDropdownlistMSTR|Param|" + BillerCategory.toString());
			String sql = "";
			String a = "";
			if (BillerCategory.getBLLR_CATG_ID() == null) {
				a = "";
			} else {
				a = String.valueOf(BillerCategory.getBLLR_CATG_ID());
			}
			if (a.equals("")) {
				sql = "SELECT t1.BLLR_MSTR_ID ,t1.BLLR_CODE " + "FROM BILLER_MASTER t1    order by BLLR_MSTR_ID ";
			} else {
				sql = "SELECT t1.BLLR_MSTR_ID ,t2.BLLR_CATG_ID ,t1.BLLR_CODE " + "FROM BILLER_MASTER t1 , BILLER_SERVICE T2   WHERE  t1.BLLR_MSTR_ID = t2.BLLR_MSTR_ID and   T2.BLLR_CATG_ID = '" + BillerCategory.getBLLR_CATG_ID() + "' group by t1.BLLR_MSTR_ID,t2.BLLR_CATG_ID ,t1.BLLR_CODE order by BLLR_MSTR_ID";
			}
			log.info("|" + page + "|getDropdownlistMSTR|SQL:" + sql);
			Query query = em.createNativeQuery(sql, DropdownlistBillMSRT.class);
			listDropdownlistBillMSRT = (List) query.getResultList();
			log.info("|" + page + "|getDropdownlistMSTR|List:" + listDropdownlistBillMSRT.size());
			/*
			 * if ( (listDropdownlistBillMSRT != null) &&
			 * (listDropdownlistBillMSRT.size() > 0)){ return
			 * listDropdownlistBillMSRT; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getDropdownlistMSTR|Time|" + timer.getStopTime());
			return listDropdownlistBillMSRT;
		} catch (Exception e) {
			log.error("|" + page + "|getDropdownlistMSTR|Exception:" + e.getMessage());
			throw e;
		}
		// return listDropdownlistBillMSRT;

	}

	public List<DropdownlistBillservice> getDropdownlistSERVICE(BillerCategory BillerCategory, DropdownlistBillMSRT DropdownlistBillMSRT) throws Exception {
		List listDropdownlistBillservice = null;

		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getDropdownlistSERVICE|Time|" + timer.getStartTime());
			log.info("|" + page + "|getDropdownlistSERVICE|Param|" + BillerCategory.toString());
			String sql = "";
			String a = "";
			String b = "";
			if (BillerCategory.getBLLR_CATG_ID() == null) {
				a = "";
			} else {
				a = String.valueOf(BillerCategory.getBLLR_CATG_ID());
			}
			if (DropdownlistBillMSRT.getBLLR_MSTR_ID() == null) {
				b = "";
			} else {
				b = String.valueOf(DropdownlistBillMSRT.getBLLR_MSTR_ID());
			}
			if ((a.equals("")) && (b.equals(""))) {
				sql = "SELECT BLLR_SRVC_ID,BLLR_SRVC_CODE,BLLR_SRVC_DESC,BLLR_SRVC_NAME_EN ,BLLR_SRVC_STAT_DATE , BLLR_SRVC_EXPR_DATE ,BLLR_MSTR_ID " + " FROM BILLER_SERVICE  order by BLLR_SRVC_ID";

			} else if ((!a.equals("")) && (!b.equals(""))) {
				sql = "SELECT t1.BLLR_SRVC_ID, t1.BLLR_SRVC_CODE ,t1.BLLR_SRVC_DESC, t1.BLLR_SRVC_NAME_EN  ,t1.BLLR_SRVC_STAT_DATE , t1.BLLR_SRVC_EXPR_DATE , t1.BLLR_MSTR_ID " + " FROM BILLER_SERVICE  t1   where  t1.bllr_mstr_id = '" + b + "'  and t1.BLLR_CATG_ID   = '" + a + "' order by BLLR_SRVC_ID ";

			} else if ((a.equals("")) || (b.equals(""))) {
				if (!b.equals("")) {
					sql = "SELECT t1.BLLR_SRVC_ID, t1.BLLR_SRVC_CODE ,t1.BLLR_SRVC_DESC, t1.BLLR_SRVC_NAME_EN  ,t1.BLLR_SRVC_STAT_DATE , t1.BLLR_SRVC_EXPR_DATE ,t1.BLLR_MSTR_ID " + " FROM BILLER_SERVICE  t1  where  t1.bllr_mstr_id = '" + b + "'   order by BLLR_SRVC_ID ";

				} else if (!a.equals("")) {

					sql = "SELECT t1.BLLR_SRVC_ID, t1.BLLR_SRVC_CODE ,t1.BLLR_SRVC_DESC, t1.BLLR_SRVC_NAME_EN  ,t1.BLLR_SRVC_STAT_DATE , t1.BLLR_SRVC_EXPR_DATE ,t1.BLLR_MSTR_ID " + " FROM BILLER_SERVICE  t1  where  t1.BLLR_CATG_ID = '" + a + "' order by BLLR_SRVC_ID";

				}
			}
			log.info("|" + page + "|getDropdownlistSERVICE|SQL:" + sql);
			Query query = em.createNativeQuery(sql, DropdownlistBillservice.class);
			listDropdownlistBillservice = (List) query.getResultList();
			log.info("|" + page + "|getDropdownlistMSTR|List:" + listDropdownlistBillservice.size());
			/*
			 * if ( (listDropdownlistBillservice != null) &&
			 * (listDropdownlistBillservice.size() > 0)){ return
			 * listDropdownlistBillservice; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getDropdownlistSERVICE|Time|" + timer.getStopTime());
			return listDropdownlistBillservice;
		} catch (Exception e) {
			log.error("|" + page + "|getDropdownlistSERVICE|Exception:" + e.getMessage());
			throw e;
		}
		// return listDropdownlistBillservice;

	}

	public List<MFSServiceId> getMFSServiceId() throws Exception {
		List listMFSServiceId = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getMFSServiceId|Time:" + timer.getStartTime());
			String sql = "select  GW_SRVC_ID,GW_SRVC_NAME   " + "FROM GW_SERVICE  order by GW_SRVC_ID";
			log.info("|" + page + "|getMFSServiceId|SQL:" + sql);
			Query query = em.createNativeQuery(sql, MFSServiceId.class);
			listMFSServiceId = (List) query.getResultList();
			log.info("|" + page + "|getDropdownlistMSTR|List:" + listMFSServiceId.size());
			/*
			 * if ( (listMFSServiceId != null) && (listMFSServiceId.size() >
			 * 0)){ return listMFSServiceId; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getMFSServiceId|Time:" + timer.getStopTime());
			return listMFSServiceId;
		} catch (Exception e) {
			log.error("|" + page + "|getMFSServiceId|Exception:" + e.getMessage());
			throw e;
		}
		// return listMFSServiceId;

	}

	public List<INBOUNDMAPID> getINBOUNDMAPID() throws Exception {
		List listINBOUNDMAPID = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getINBOUNDMAPID|Time:" + timer.getStartTime());
			String sql = "select  DATA_MAP_ID,DATA_MAP_NAME   " + "FROM GW_INBOUND_MAP  order by DATA_MAP_ID";
			log.info("|" + page + "|getINBOUNDMAPID|SQL:" + sql);
			Query query = em.createNativeQuery(sql, INBOUNDMAPID.class);
			listINBOUNDMAPID = (List) query.getResultList();
			log.info("|" + page + "|getDropdownlistMSTR|List:" + listINBOUNDMAPID.size());
			/*
			 * if ( (listINBOUNDMAPID != null) && (listINBOUNDMAPID.size() >
			 * 0)){ return listINBOUNDMAPID; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getINBOUNDMAPID|Time:" + timer.getStopTime());
			return listINBOUNDMAPID;
		} catch (Exception e) {
			log.error("|" + page + "|getMFSServiceId|Exception:" + e.getMessage());
			throw e;
		}
		// return listINBOUNDMAPID;

	}

	public List<SendReceiptId> getSendReceiptId() throws Exception {
		List listSendReceiptId = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getSendReceiptId|Time:" + timer.getStartTime());
			String sql = "select GW_RCPT_CONF_ID,GW_RCPT_CONF_NAME   " + "FROM GW_RCPT_CONF  order by GW_RCPT_CONF_ID";
			log.info("|" + page + "|getSendReceiptId|SQL:" + sql);
			Query query = em.createNativeQuery(sql, SendReceiptId.class);
			listSendReceiptId = (List) query.getResultList();
			log.info("|" + page + "|getDropdownlistMSTR|List:" + listSendReceiptId.size());
			/*
			 * if ( (listSendReceiptId != null) && (listSendReceiptId.size() >
			 * 0)){ return listSendReceiptId; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getSendReceiptId|Time:" + timer.getStopTime());
			return listSendReceiptId;
		} catch (Exception e) {
			log.error("|" + page + "|getMFSServiceId|Exception:" + e.getMessage());
			throw e;
		}
		// return listSendReceiptId;

	}

	public List<GWServiceMap> getServicemapId() throws Exception {
		List listSendReceiptId = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getServicemapId|Time:" + timer.getStartTime());
			String sql = "select DATA_MAP_ID,DATA_MAP_NAME ,DATA_MAP_XML_SRC_ID    " + "FROM GW_SERVICE_MAP  order by DATA_MAP_ID";
			log.info("|" + page + "|getServicemapId|SQL:" + sql);
			Query query = em.createNativeQuery(sql, GWServiceMap.class);
			listSendReceiptId = (List) query.getResultList();
			log.info("|" + page + "|getDropdownlistMSTR|List:" + listSendReceiptId.size());
			/*
			 * if ( (listSendReceiptId != null) && (listSendReceiptId.size() >
			 * 0)){ return listSendReceiptId; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getServicemapId|Time:" + timer.getStopTime());
			return listSendReceiptId;
		} catch (Exception e) {
			log.error("|" + page + "|getServicemapId|Exception:" + e.getMessage());
			throw e;
		}
		// return listSendReceiptId;

	}

	public List<SendReceipt> getSendReceipt() throws Exception {
		List listSendReceipt = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getSendReceipt|Time:" + timer.getStartTime());
			String sql = "select DISTINCT( INBN_SRVC_ID),SEND_RCPT_FLAG " + "FROM GW_INBOUND  order by INBN_SRVC_ID";
			log.info("|" + page + "|getSendReceipt|SQL:" + sql);
			Query query = em.createNativeQuery(sql, SendReceipt.class);
			listSendReceipt = (List) query.getResultList();
			log.info("|" + page + "|getDropdownlistMSTR|List:" + listSendReceipt.size());
			/*
			 * if ( (listSendReceipt != null) && (listSendReceipt.size() > 0)){
			 * return listSendReceipt; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getSendReceipt|Time:" + timer.getStopTime());
			return listSendReceipt;
		} catch (Exception e) {
			log.error("|" + page + "|getSendReceipt|Exception:" + e.getMessage());
			throw e;
		}
		// return listSendReceipt;

	}

	public List<OutboundId> getOutboundId() throws Exception {
		List listOutboundId = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getOutboundId|Time:" + timer.getStartTime());
			String sql = "SELECT GW_OUTB_ID,GW_OUTB_NAME    " + "FROM GW_OUTBOUND order by GW_OUTB_ID ";
			log.info("|" + page + "|getOutboundId|SQL:" + sql);
			Query query = em.createNativeQuery(sql, OutboundId.class);
			listOutboundId = (List) query.getResultList();
			log.info("|" + page + "|getDropdownlistMSTR|List:" + listOutboundId.size());
			/*
			 * if ( (listOutboundId != null) && (listOutboundId.size() > 0)){
			 * return listOutboundId; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getOutboundId|Time:" + timer.getStopTime());
			return listOutboundId;
		} catch (Exception e) {
			log.error("|" + page + "|getOutboundId|Exception:" + e.getMessage());
			throw e;
		}
		// return listOutboundId;

	}

	public List<BillerChannel> getPaymentChanel() throws Exception {
		List listBillerChannel = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getPaymentChanel|Time:" + timer.getStartTime());
			String sql = "SELECT  BLLR_CHNL_ID,BLLR_CHNL_NAME,BLLR_CHNL_CODE   " + "FROM BILLER_CHANNEL  order by BLLR_CHNL_ID ASC ";
			log.info("|" + page + "|getPaymentChanel|SQL:" + sql);
			Query query = em.createNativeQuery(sql, BillerChannel.class);
			listBillerChannel = (List) query.getResultList();
			log.info("|" + page + "|getDropdownlistMSTR|List:" + listBillerChannel.size());
			/*
			 * if ( (listBillerChannel != null) && (listBillerChannel.size() >
			 * 0)){ return listBillerChannel; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getPaymentChanel|Time:" + timer.getStopTime());
			return listBillerChannel;
		} catch (Exception e) {
			log.error("|" + page + "|getPaymentChanel|Exception:" + e.getMessage());
			throw e;
		}
		// return listBillerChannel;

	}

	public List<GWOutboundMap> getOutboundMapId() throws Exception {
		List listGWOutboundMap = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getOutboundMapId|Time:" + timer.getStartTime());
			String sql = "SELECT  DATA_MAP_ID,DATA_MAP_NAME,DATA_MAP_XML_SRC_ID   " + "FROM  GW_OUTBOUND_MAP order by DATA_MAP_ID ";
			log.info("|" + page + "|getOutboundMapId|SQL:" + sql);
			Query query = em.createNativeQuery(sql, GWOutboundMap.class);
			listGWOutboundMap = (List) query.getResultList();
			log.info("|" + page + "|getDropdownlistMSTR|List:" + listGWOutboundMap.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getOutboundMapId|Time:" + timer.getStopTime());
			return listGWOutboundMap;
		} catch (Exception e) {
			log.error("|" + page + "|getOutboundMapId|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public Collection<BillerMaster> getBillerMasterAll(Integer BLLR_CATG_ID) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info("EJBDROPDOWNLIST|getBillerMasterAll|Time:" + timer.getStartTime());
			String sql = "SELECT * " + " FROM BILLER_MASTER ";
			// String sql =
			// "SELECT t1.BLLR_MSTR_ID ,t2.BLLR_CATG_ID ,t1.BLLR_CODE "
			// +
			// " FROM BILLER_MASTER t1 left join BILLER_SERVICE T2 on t1.BLLR_MSTR_ID = t2.BLLR_MSTR_ID ";

			Vector<String> v = new Vector<String>();

			// if (BLLR_CATG_ID != null &&!"".equals(BLLR_CATG_ID))
			// v.add(" T2.BLLR_CATG_ID = " + BLLR_CATG_ID + "");

			StringBuffer sb = new StringBuffer();

			if (!v.isEmpty()) {
				sb.append(" WHERE  ");
				for (int i = 0; i < v.size(); i++) {
					if (i != 0) {
						sb.append(" AND ");
					}
					sb.append(v.get(i));
				}
			}

			sql = sql + sb.toString();
			sql += " order by BLLR_MSTR_ID ";

			log.info("EJBDROPDOWNLIST|getBillerMasterAll|sql:" + sql);

			Collection<BillerMaster> colreturn = new Vector();

			Query query = em.createNativeQuery(sql, BillerMaster.class);

			List list = (List) query.getResultList();
			log.info("EJBDROPDOWNLIST|getBillerMasterAll|list.list:" + list.size());

			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				BillerMaster mastTrnsPrt = (BillerMaster) o;

				colreturn.add(mastTrnsPrt);
			}
			log.info("EJBDROPDOWNLIST|getBillerMasterAll|Time:" + timer.getStopTime());
			return colreturn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("EJBDROPDOWNLIST|getBillerMasterAll|Exception:" + e);
		}

		return new Vector();
	}

	public List<GWService> getServiceGateway() throws Exception {
		List listGWService = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getServiceGateway|Time:" + timer.getStartTime());
			String sql = "SELECT  *   " + "FROM  GW_SERVICE order by GW_SRVC_ID ";
			log.info("|" + page + "|getServiceGateway|SQL:" + sql);
			Query query = em.createNativeQuery(sql, GWService.class);
			listGWService = (List) query.getResultList();
			log.info("|" + page + "|getServiceGateway|List:" + listGWService.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getServiceGateway|Time:" + timer.getStopTime());
			return listGWService;
		} catch (Exception e) {
			log.error("|" + page + "|getServiceGateway|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<BankMasterBean> getBankCode() throws Exception {
		List listBankMasterBean = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getBankCode|Time:" + timer.getStartTime());
			String sql = "SELECT  *   " + " FROM  BANK_MASTER   order by BANK_CODE   ";
			log.info("|" + page + "|getBankCode|SQL:" + sql);
			Query query = em.createNativeQuery(sql, BankMasterBean.class);
			listBankMasterBean = (List) query.getResultList();
			log.info("|" + page + "|getBankCode|List:" + listBankMasterBean.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getBankCode|Time:" + timer.getStopTime());
			return listBankMasterBean;
		} catch (Exception e) {
			log.error("|" + page + "|getBankCode|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<BankServicebean> getBankService() throws Exception {
		List listBankServicebean = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getBankCode|Time:" + timer.getStartTime());
			String sql = "SELECT  *   " + " FROM  BANK_SERVICE   order by BANK_SRVC_ID   ";
			log.info("|" + page + "|getBankCode|SQL:" + sql);
			Query query = em.createNativeQuery(sql, BankServicebean.class);
			listBankServicebean = (List) query.getResultList();
			log.info("|" + page + "|getBankCode|List:" + listBankServicebean.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getBankCode|Time:" + timer.getStopTime());
			return listBankServicebean;
		} catch (Exception e) {
			log.error("|" + page + "|getBankCode|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<BankMasterBean> getBankStatus() throws Exception {
		List listBankMasterBean = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getBankStatus|Time:" + timer.getStartTime());
			String sql = "SELECT  ACT_FLAG   " + " FROM  BANK_MASTER   group by ACT_FLAG  order by ACT_FLAG      ";
			log.info("|" + page + "|getBankStatus|SQL:" + sql);
			Query query = em.createNativeQuery(sql, BankMasterBean.class);
			listBankMasterBean = (List) query.getResultList();
			log.info("|" + page + "|getBankStatus|List:" + listBankMasterBean.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getBankStatus|Time:" + timer.getStopTime());
			return listBankMasterBean;
		} catch (Exception e) {
			log.error("|" + page + "|getBankStatus|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<BankChannelBean> getBankChannel() throws Exception {
		List listBankChannelBean = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getBankCode|Time:" + timer.getStartTime());
			String sql = "SELECT  *   " + " FROM  BANK_CHANNEL   order by BANK_CHNL_CODE   ";
			log.info("|" + page + "|getBankCode|SQL:" + sql);
			Query query = em.createNativeQuery(sql, BankChannelBean.class);
			listBankChannelBean = (List) query.getResultList();
			log.info("|" + page + "|getBankCode|List:" + listBankChannelBean.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getBankCode|Time:" + timer.getStopTime());
			return listBankChannelBean;
		} catch (Exception e) {
			log.error("|" + page + "|getBankCode|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<BankChannelBean> getBankChannelStatus() throws Exception {
		List listBankChannelBean = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getBankStatus|Time:" + timer.getStartTime());
			String sql = "SELECT  ACT_FLAG   " + " FROM  BANK_CHANNEL   group by ACT_FLAG  order by ACT_FLAG      ";
			log.info("|" + page + "|getBankStatus|SQL:" + sql);
			Query query = em.createNativeQuery(sql, BankChannelBean.class);
			listBankChannelBean = (List) query.getResultList();
			log.info("|" + page + "|getBankStatus|List:" + listBankChannelBean.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getBankStatus|Time:" + timer.getStopTime());
			return listBankChannelBean;
		} catch (Exception e) {
			log.error("|" + page + "|getBankStatus|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<BankServicebean> getBankServiceStatus() throws Exception {
		List listBankServicebean = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getBankStatus|Time:" + timer.getStartTime());
			String sql = "SELECT  ACT_FLAG   " + " FROM  BANK_SERVICE   group by ACT_FLAG  order by ACT_FLAG      ";
			log.info("|" + page + "|getBankStatus|SQL:" + sql);
			Query query = em.createNativeQuery(sql, BankServicebean.class);
			listBankServicebean = (List) query.getResultList();
			log.info("|" + page + "|getBankStatus|List:" + listBankServicebean.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getBankStatus|Time:" + timer.getStopTime());
			return listBankServicebean;
		} catch (Exception e) {
			log.error("|" + page + "|getBankStatus|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<GWBankBean> getGWBankStatus() throws Exception {
		List listGWBankBean = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getBankStatus|Time:" + timer.getStartTime());
			String sql = "SELECT  ACT_FLAG   " + " FROM  GW_BANK   group by ACT_FLAG  order by ACT_FLAG      ";
			log.info("|" + page + "|getBankStatus|SQL:" + sql);
			Query query = em.createNativeQuery(sql, GWBankBean.class);
			listGWBankBean = (List) query.getResultList();
			log.info("|" + page + "|getBankStatus|List:" + listGWBankBean.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getBankStatus|Time:" + timer.getStopTime());
			return listGWBankBean;
		} catch (Exception e) {
			log.error("|" + page + "|getBankStatus|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<BankServicebean> getGWBankService() throws Exception {
		List listBankServicebean = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getBankCode|Time:" + timer.getStartTime());
			String sql = "select * from bank_service T2  where not  exists(select * from GW_BANK T1 where T2.BANK_SRVC_ID = T1.BANK_SRVC_ID)  order by BANK_SRVC_ID   ";
			log.info("|" + page + "|getBankCode|SQL:" + sql);
			Query query = em.createNativeQuery(sql, BankServicebean.class);
			listBankServicebean = (List) query.getResultList();
			log.info("|" + page + "|getBankCode|List:" + listBankServicebean.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getBankCode|Time:" + timer.getStopTime());
			return listBankServicebean;
		} catch (Exception e) {
			log.error("|" + page + "|getBankCode|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<GWBankDetail> getGWInboundMapID() throws Exception {
		List<GWBankDetail> listDetail = new ArrayList<GWBankDetail>();
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getBankCode|Time:" + timer.getStartTime());
			String sql = " select  DATA_MAP_ID, DATA_MAP_NAME from  GW_BANK_INB_MAP T2    order by DATA_MAP_ID   ";
			log.info("|" + page + "|getBankCode|SQL:" + sql);

			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			GWBankDetail bean = null;

			log.info("|" + page + "|getBankCode|List:" + listDetail.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			for (Object[] item : list) {
				int x = 0;
				bean = new GWBankDetail();
				bean.setINB_MAP_ID(JpaResultUtil.getInteger(item, x++));
				bean.setINB_DATA_MAP_NAME(JpaResultUtil.getString(item, x++));

				listDetail.add(bean);
			}
			log.info("|" + page + "|getBankCode|Time:" + timer.getStopTime());
			return listDetail;
		} catch (Exception e) {
			log.error("|" + page + "|getBankCode|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<GWBankDetail> getGWServiceMapID() throws Exception {
		List<GWBankDetail> listDetail = new ArrayList<GWBankDetail>();
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getBankCode|Time:" + timer.getStartTime());
			String sql = " select  DATA_MAP_ID, DATA_MAP_NAME from  GW_BANK_GW_MAP T2    order by DATA_MAP_ID   ";
			log.info("|" + page + "|getBankCode|SQL:" + sql);
			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			GWBankDetail bean = null;

			log.info("|" + page + "|getBankCode|List:" + listDetail.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			for (Object[] item : list) {
				int x = 0;
				bean = new GWBankDetail();
				bean.setGW_MAP_ID(JpaResultUtil.getInteger(item, x++));
				bean.setGW_DATA_MAP_NAME(JpaResultUtil.getString(item, x++));

				listDetail.add(bean);
			}
			log.info("|" + page + "|getBankCode|Time:" + timer.getStopTime());
			return listDetail;
		} catch (Exception e) {
			log.error("|" + page + "|getBankCode|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<GWBankDetail> getGWOutboundMapID() throws Exception {
		List<GWBankDetail> listDetail = new ArrayList<GWBankDetail>();
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getBankCode|Time:" + timer.getStartTime());
			String sql = "  select  DATA_MAP_ID, DATA_MAP_NAME from  GW_BANK_OUTB_MAP T2    order by DATA_MAP_ID   ";
			log.info("|" + page + "|getBankCode|SQL:" + sql);
			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			GWBankDetail bean = null;

			log.info("|" + page + "|getBankCode|List:" + listDetail.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			for (Object[] item : list) {
				int x = 0;
				bean = new GWBankDetail();
				bean.setOUTB_MAP_ID(JpaResultUtil.getInteger(item, x++));
				bean.setOUTB_DATA_MAP_NAME(JpaResultUtil.getString(item, x++));

				listDetail.add(bean);
			}
			log.info("|" + page + "|getBankCode|Time:" + timer.getStopTime());
			return listDetail;
		} catch (Exception e) {
			log.error("|" + page + "|getBankCode|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<BillerIntegration> getBillerIntegration() throws Exception {
		List listBillerIntegration = null;
		try {

			Timer timer = new Timer("-");
			log.info("|" + page + "|getBankStatus|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_INTEGRATION  ORDER BY BLLR_INGT_ID      ";
			log.info("|" + page + "|getBankStatus|SQL:" + sql);
			Query query = em.createNativeQuery(sql, BillerIntegration.class);
			listBillerIntegration = (List) query.getResultList();
			log.info("|" + page + "|getBankStatus|List:" + listBillerIntegration.size());
			/*
			 * if ( (listGWOutboundMap != null) && (listGWOutboundMap.size() >
			 * 0)){ return listGWOutboundMap; }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			log.info("|" + page + "|getBankStatus|Time:" + timer.getStopTime());
			return listBillerIntegration;
		} catch (Exception e) {
			log.error("|" + page + "|getBankStatus|Exception:" + e.getMessage());
			throw e;
		}
		// return listGWOutboundMap;

	}

	public List<DropdownlistBillservice> getDropdownlistInquiryService(List service) throws Exception {
		List listDropdownlistBillservice = null;
		try {
			Timer timer = new Timer("-");
			log.info("|" + page + "|getDropdownlistInquiryService|Time|" + timer.getStartTime());
			log.info("|" + page + "|getDropdownlistInquiryService|Param|" + service.size());
			String sql = "";
			sql = "SELECT BLLR_SRVC_ID,BLLR_SRVC_CODE,BLLR_SRVC_DESC,BLLR_SRVC_NAME_EN ,BLLR_SRVC_STAT_DATE , BLLR_SRVC_EXPR_DATE ,BLLR_MSTR_ID " + " FROM BILLER_SERVICE  ";

			List<Integer> service1 = service;
			if (service1.size() != 0) {
				StringBuffer sb = new StringBuffer();
				sb.append(" WHERE  BLLR_SRVC_ID in (" + "");
				for (int i = 0; i < service1.size(); i++) {
					if (i != 0) {
						sb.append(" , ");
					}
					sb.append(service1.get(i).intValue());
				}
				sb.append(" ) ");
				sql = sql + sb.toString();
			}
			sql = sql + " ORDER BY BLLR_SRVC_ID ";
			log.info("|" + page + "|getDropdownlistInquiryService|SQL:" + sql);
			Query query = em.createNativeQuery(sql, DropdownlistBillservice.class);
			listDropdownlistBillservice = (List) query.getResultList();
			log.info("|" + page + "|getDropdownlistInquiryService|List:" + listDropdownlistBillservice.size());

			log.info("|" + page + "|getDropdownlistInquiryService|Time|" + timer.getStopTime());
			return listDropdownlistBillservice;
		} catch (Exception e) {
			log.error("|" + page + "|getDropdownlistInquiryService|Exception:" + e.getMessage());
			throw e;
		}
	}

	public List<BillerRefDataType> getBillerRefDataType(UserInfoBean userInfo) throws Exception {
		List<BillerRefDataType> billerRefDataTypes = null;
		try {
//			EntityManagerFactory factory;
			/* Test */
//			System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
//			System.setProperty("javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
			
//			factory = Persistence.createEntityManagerFactory("mfs.db.MFSDAO");
//			EntityManager em = factory.createEntityManager();
			/* Test */
			
			Timer timer = new Timer("-");
			log.info(userInfo.getName() + "|" + page + "|getBillerRefDataType|Time|" + timer.getStartTime());

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append(" FROM BILLER_REF_DATA_TYPE ");
			sql.append(" WHERE ACT_FLAG='A'");
			sql.append(" ORDER BY BILL_REF_DATA_TYPE_ID ");
			log.info("|" + page + "|getBillerRefDataType|SQL:" + sql);
			Query query = em.createNativeQuery(sql.toString(), BillerRefDataType.class);
			billerRefDataTypes =  query.getResultList();
		
			log.info(userInfo.getName() + "|" + page + "|getBillerRefDataType|SQL:" + sql);
			log.info("|" + page + "|getBillerRefDataType|Time:" + timer.getStopTime());
			return billerRefDataTypes;
		} catch (Exception e) {
			log.error("|" + page + "|getBillerRefDataType|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public List<BillerFeeMast> getBillerFeeMast() throws Exception {
		List listBillerFeeMast = null;
		try {
			Timer timer = new Timer("-");
			log.info("|" + page + "|getBillerFeeMast|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_FEE_MAST ORDER BY BLLR_FEE_MAST_ID ";
			log.info("|" + page + "|getBillerFeeMast|SQL:" + sql);
			Query query = em.createNativeQuery(sql, BillerFeeMast.class);
			listBillerFeeMast = (List) query.getResultList();
			log.info("|" + page + "|getBillerFeeMast|List:" + listBillerFeeMast.size());

			log.info("|" + page + "|getBillerFeeMast|Time:" + timer.getStopTime());
			return listBillerFeeMast;
		} catch (Exception e) {
			log.error("|" + page + "|getBillerFeeMast|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public static void main(String[] args){
		Dropdownlist dl=new Dropdownlist();
		try {
			dl.getBillerRefDataType(new UserInfoBean());
		} catch (Exception e) {
		
		}
	}
}
