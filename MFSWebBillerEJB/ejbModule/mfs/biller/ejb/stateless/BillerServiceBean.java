package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.BillerServiceBeanLocal;
import mfs.biller.ejb.interfaces.BillerServiceBeanRemote;
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
import mfs.biller.util.DBUtil;
import mfs.biller.util.JpaResultUtil;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;
import mfs.exception.NotFoundDataException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

@Stateless(name = "BillerServiceBean", mappedName = "mfs.biller.ejb.BillerServiceBean")
@CallByReference
public class BillerServiceBean implements BillerServiceBeanRemote, BillerServiceBeanLocal {

	private static Logger log = Logger.getLogger("EJBBLLRSRVC");
	private String page = "BillerServiceBean";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	DBUtil dbUtil=new DBUtil();
	public List<BillerServiceDetail> searchBillerService(BillerServiceParam PARAM, UserInfoBean user) throws Exception {
		List<BillerServiceDetail> listDetail = new ArrayList<BillerServiceDetail>();
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBillerService|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBillerService|Param|" + PARAM.toString());

			String sql = "SELECT BS.BLLR_SRVC_ID, BS.BLLR_SRVC_NAME_EN, BS.BLLR_SRVC_MENU_SEQ, BS.BLLR_SRVC_BARC_FLAG, " 
			+ "BS.BLLR_SRVC_GUST_MOBN_FLAG, TO_CHAR(BS.BLLR_SRVC_STAT_DATE,'YYYY-MM-DD') AS BLLR_SRVC_STAT_DATE, TO_CHAR(BS.BLLR_SRVC_EXPR_DATE,'YYYY-MM-DD') AS BLLR_SRVC_EXPR_DATE, BS.LAST_CHNG_BY, TO_CHAR(BS.LAST_CHNG_DTTM, 'YYYY-MM-DD HH24:MI:SS') AS LAST_CHNG_DTTM, " 
			+ "BS.BLLR_CATG_ID, BC.BLLR_CATG_NAME, BC.BLLR_CATG_MENU_SEQ, " 
			+ "BS.BLLR_MSTR_ID, BM.BLLR_CODE , BS.BLLR_SRVC_CODE, " 
			+ "BS.BLLR_FETR_ID, BS.BLLR_CNCL_ONLN_FLAG " 
			+ "FROM BILLER_SERVICE BS " 
			+ "INNER JOIN BILLER_CATEGORY BC ON BS.BLLR_CATG_ID = BC.BLLR_CATG_ID "
			+ "INNER JOIN BILLER_MASTER BM ON BS.BLLR_MSTR_ID = BM.BLLR_MSTR_ID ";

			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(PARAM.getBLLR_CATG_ID())) {
				v.add("BS.BLLR_CATG_ID = " + PARAM.getBLLR_CATG_ID());
			}
			if (!ValidateUtil.isEmpty(PARAM.getBLLR_MSTR_ID())) {
				v.add("BS.BLLR_MSTR_ID = " + PARAM.getBLLR_MSTR_ID());
			}
			if (!ValidateUtil.isEmpty(PARAM.getBLLR_SRVC_CODE())) {
				v.add("BS.BLLR_SRVC_CODE = '" + PARAM.getBLLR_SRVC_CODE() + "'");
			}

			if (!v.isEmpty()) {
				sb.append(" WHERE (");
				for (int i = 0; i < v.size(); i++) {
					if (i != 0) {
						sb.append(" AND ");
					}
					sb.append(v.get(i));
				}
				sb.append(")");
			}
			sql += sb.toString();
			sql += " ORDER BY BLLR_SRVC_ID";

			log.info(user.getName() + "|" + page + "|searchBillerService|SQL:" + sql);

			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			BillerServiceDetail bean = null;
			for (Object[] item : list) {
				int x = 0;
				bean = new BillerServiceDetail();
				bean.setBLLR_SRVC_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBLLR_SRVC_NAME_EN(JpaResultUtil.getString(item, x++));
				bean.setBLLR_SRVC_MENU_SEQ(JpaResultUtil.getString(item, x++));
				bean.setBLLR_SRVC_BARC_FLAG(JpaResultUtil.getString(item, x++));
				bean.setBLLR_SRVC_GUST_MOBN_FLAG(JpaResultUtil.getString(item, x++));
				bean.setBLLR_SRVC_STAT_DATE(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd"));
				bean.setBLLR_SRVC_EXPR_DATE(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd"));
				bean.setLAST_CHNG_BY(JpaResultUtil.getString(item, x++));
				bean.setLAST_CHNG_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setBLLR_CATG_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBLLR_CATG_NAME(JpaResultUtil.getString(item, x++));
				bean.setBLLR_CATG_MENU_SEQ(JpaResultUtil.getString(item, x++));
				bean.setBLLR_MSTR_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBLLR_CODE(JpaResultUtil.getString(item, x++));
				bean.setBLLR_SRVC_CODE(JpaResultUtil.getString(item, x++));				
				bean.setBLLR_FETR_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBLLR_CNCL_ONLN_FLAG(JpaResultUtil.getString(item, x++));

				listDetail.add(bean);
			}

			log.info(user.getName() + "|" + page + "|searchBillerService|List.Size:" + listDetail.size());
			log.info(user.getName() + "|" + page + "|searchBillerService|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|searchBillerService|Exception:" + e.getMessage());
			throw e;
		}
		return listDetail;
	}

	public int insertInformation(BillerService bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertInformation|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertInformation|Param|" + bean.toString());

			Query query = em.createNativeQuery("SELECT SEQ_BILLER_SERVICE.nextval from DUAL");
			BigDecimal result = (BigDecimal) query.getSingleResult();
			int BLLR_SRVC_ID = result.intValue();

			log.info(user.getName() + "|" + page + "|insertInformation|BLLR_SRVC_ID:" + BLLR_SRVC_ID);

			String sql = "INSERT INTO BILLER_SERVICE(BLLR_SRVC_ID, BLLR_CATG_ID, BLLR_SRVC_CODE, BLLR_MSTR_ID, BLLR_SRVC_NAME_EN, BLLR_SRVC_NAME_FULL_EN, BLLR_SRVC_NAME_TH, BLLR_SRVC_NAME_FULL_TH, BLLR_SRVC_STAT_DATE, BLLR_SRVC_EXPR_DATE, BLLR_SRVC_MENU_SEQ, BLLR_SRVC_GUST_MOBN_FLAG, BLLR_SRVC_BARC_FLAG, BLLR_SERV_VAT_PERC, ACT_FLAG, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY, LAST_CHNG_DTTM ,BLLR_SRVC_FETR,BLLR_INGT_ID)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE,?,?)";
			int i = 0;
			query = em.createNativeQuery(sql);
			query.setParameter(++i, BLLR_SRVC_ID);
			query.setParameter(++i, bean.getBLLR_CATG_ID());
			query.setParameter(++i, bean.getBLLR_SRVC_CODE());
			query.setParameter(++i, bean.getBLLR_MSTR_ID());
			query.setParameter(++i, bean.getBLLR_SRVC_NAME_EN());
			query.setParameter(++i, bean.getBLLR_SRVC_NAME_FULL_EN());
			query.setParameter(++i, bean.getBLLR_SRVC_NAME_TH());
			query.setParameter(++i, bean.getBLLR_SRVC_NAME_FULL_TH());
			query.setParameter(++i, bean.getBLLR_SRVC_STAT_DATE());
			query.setParameter(++i, bean.getBLLR_SRVC_EXPR_DATE());
			query.setParameter(++i, bean.getBLLR_SRVC_MENU_SEQ());
			query.setParameter(++i, bean.getBLLR_SRVC_GUST_MOBN_FLAG());
			query.setParameter(++i, bean.getBLLR_SRVC_BARC_FLAG());
			query.setParameter(++i, bean.getBLLR_SERV_VAT_PERC());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, bean.getBLLR_SRVC_FETR());
			query.setParameter(++i, bean.getBLLR_INGT_ID());
			query.executeUpdate();

			log.info(user.getName() + "|" + page + "|insertInformation|Success");
			log.info(user.getName() + "|" + page + "|insertInformation|Time|" + timer.getStopTime());

			BillerPaymentValidate BillerPaymentValidate = new BillerPaymentValidate();

			BillerPaymentValidate.setBLLR_PMNT_VALD_ID(BLLR_SRVC_ID);
			BillerPaymentValidate.setBLLR_SRVC_ID(BLLR_SRVC_ID);
			BillerPaymentValidate.setBLLR_PMNT_FULL_FLAG("Y");
			BillerPaymentValidate.setBLLR_PRES_FLAG("Y");
			BillerPaymentValidate.setBLLR_PMNT_PART_FLAG("N");
			BillerPaymentValidate.setBLLR_BILL_AMNT_FLAG("Y");
			BillerPaymentValidate.setBLLR_PMNT_OVER_FLAG("N");
			int MIN = 0;
			BigDecimal MIN1 = new BigDecimal(MIN);
			double MAX = 99999999.99;
			BigDecimal MAX1 = new BigDecimal(MAX);
			BillerPaymentValidate.setBLLR_PMNT_AMNT_MIN(MIN1);
			BillerPaymentValidate.setBLLR_PMNT_AMNT_MAX(MAX1);
			BillerPaymentValidate.setBLLR_NON_FDM_FEE_FLAG("N");
			BillerPaymentValidate.setBLLR_NON_FDM_COMS_FLAG("N");
			BillerPaymentValidate.setBLLR_OVER_DUE_FLAG("N");
			BillerPaymentValidate.setACT_FLAG("A");
			BillerPaymentValidate.setCRTD_BY(user.getName());
			BillerPaymentValidate.setLAST_CHNG_BY(user.getName());
			BillerPaymentValidate.setBLLR_MAX_NO_MNTH(0);
			BillerPaymentValidate.setBLLR_DENM_FLAG("Y");

			insertBillerPaymentValidate(BillerPaymentValidate, user);

			BillerForm BillerForm = new BillerForm();
			BillerForm.setBLLR_FORM_ID(BLLR_SRVC_ID);
			BillerForm.setBLLR_FORM_NAME(null);
			BillerForm.setBLLR_FORM_CAPT_TH(null);
			BillerForm.setBLLR_FORM_CAPT_EN(null);
			BillerForm.setBLLR_SRVC_ID(BLLR_SRVC_ID);
			BillerForm.setACT_FLAG("A");
			BillerForm.setCRTD_BY(user.getName());
			BillerForm.setLAST_CHNG_BY(user.getName());

			insertBillerForm(BillerForm, user);

			return BLLR_SRVC_ID;

		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|insertInformation|Exception:" + e.getMessage());
			throw e;
		}
	}

	public int insertBillerPaymentValidate(BillerPaymentValidate bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertPaymentValidate|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertPaymentValidate|Param|" + bean.toString());

			String sql = "INSERT INTO BILLER_PAYMENT_VALIDATE(BLLR_PMNT_VALD_ID, BLLR_SRVC_ID, BLLR_PMNT_FULL_FLAG, BLLR_PRES_FLAG, BLLR_PMNT_PART_FLAG, BLLR_BILL_AMNT_FLAG, BLLR_PMNT_OVER_FLAG, BLLR_PMNT_AMNT_MIN, BLLR_PMNT_AMNT_MAX, BLLR_NON_FDM_FEE_FLAG, BLLR_NON_FDM_COMS_FLAG, BLLR_OVER_DUE_FLAG, ACT_FLAG, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY, LAST_CHNG_DTTM ,BLLR_MAX_NO_MNTH ,BLLR_DENM_FLAG)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE,?,?)";
			int i = 0;
			Query query = em.createNativeQuery(sql);
			query.setParameter(++i, bean.getBLLR_PMNT_VALD_ID());
			query.setParameter(++i, bean.getBLLR_SRVC_ID());
			query.setParameter(++i, bean.getBLLR_PMNT_FULL_FLAG());
			query.setParameter(++i, bean.getBLLR_PRES_FLAG());
			query.setParameter(++i, bean.getBLLR_PMNT_PART_FLAG());
			query.setParameter(++i, bean.getBLLR_BILL_AMNT_FLAG());
			query.setParameter(++i, bean.getBLLR_PMNT_OVER_FLAG());
			query.setParameter(++i, bean.getBLLR_PMNT_AMNT_MIN());
			query.setParameter(++i, bean.getBLLR_PMNT_AMNT_MAX());
			query.setParameter(++i, bean.getBLLR_NON_FDM_FEE_FLAG());
			query.setParameter(++i, bean.getBLLR_NON_FDM_COMS_FLAG());
			query.setParameter(++i, bean.getBLLR_OVER_DUE_FLAG());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, bean.getBLLR_MAX_NO_MNTH());
			query.setParameter(++i, bean.getBLLR_DENM_FLAG());
			query.executeUpdate();

			log.info(user.getName() + "|" + page + "|insertPaymentValidate|Success");
			log.info(user.getName() + "|" + page + "|insertPaymentValidate|Time|" + timer.getStopTime());

			return bean.getBLLR_PMNT_VALD_ID();

		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|insertPaymentValidate|Exception:" + e.getMessage());
			throw e;
		}
	}

	public List<BillerChannel> searchBillerChannel(int BLLR_SRVC_ID, UserInfoBean user) throws Exception {
		List<BillerChannel> list = null;
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBillerChannel|Time:" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBillerChannel|Param|BLLR_SRVC_ID:" + BLLR_SRVC_ID);

			String sql = "SELECT * FROM BILLER_CHANNEL WHERE ACT_FLAG = 'A' " + "AND BLLR_CHNL_ID NOT IN ( SELECT BLLR_CHNL_ID FROM BILLER_SERVICE_CHANNEL WHERE BLLR_SRVC_ID = " + BLLR_SRVC_ID + " ) " + "ORDER BY BLLR_CHNL_ID";

			log.info(user.getName() + "|" + page + "|searchBillerChannel|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerChannel.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchBillerChannel|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchBillerChannel|Time:" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|searchBillerChannel|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}

	public List<BillerServiceChannel> searchBillerServiceChannel(int BLLR_SRVC_ID, UserInfoBean user) throws Exception {
		List<BillerServiceChannel> list = null;
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBillerServiceChannel|Time:" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBillerServiceChannel|Param|BLLR_SRVC_ID:" + BLLR_SRVC_ID);

			String sql = "SELECT * FROM BILLER_SERVICE_CHANNEL WHERE BLLR_SRVC_ID = " + BLLR_SRVC_ID + " " + "ORDER BY BLLR_SRVC_ID";

			log.info(user.getName() + "|" + page + "|searchBillerServiceChannel|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerServiceChannel.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchBillerServiceChannel|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchBillerServiceChannel|Time:" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|searchBillerServiceChannel|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}

	public void insertBillerServiceChannel(BillerServiceChannel bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBillerServiceChannel|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBillerServiceChannel|Param|" + bean.toString());

			String sql = "INSERT INTO BILLER_SERVICE_CHANNEL(BLLR_SRVC_ID, BLLR_CHNL_ID, BLLR_SRVC_CHNL_BARC_FLAG, BLLR_SRVC_CHNL_KEYN_FLAG, BLLR_SRVC_CHNL_CODE, ACT_FLAG, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY, LAST_CHNG_DTTM)" + "VALUES(?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE)";
			int i = 0;
			Query query = em.createNativeQuery(sql);
			query.setParameter(++i, bean.getBLLR_SRVC_ID());
			query.setParameter(++i, bean.getBLLR_CHNL_ID());
			query.setParameter(++i, bean.getBLLR_SRVC_CHNL_BARC_FLAG());
			query.setParameter(++i, bean.getBLLR_SRVC_CHNL_KEYN_FLAG());
			query.setParameter(++i, bean.getBLLR_SRVC_CHNL_CODE());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, user.getName());
			query.executeUpdate();

			log.info(user.getName() + "|" + page + "|insertBillerServiceChannel|Success");
			log.info(user.getName() + "|" + page + "|insertBillerServiceChannel|Time|" + timer.getStopTime());

			updateBillerServiceCenter(bean.getBLLR_SRVC_ID(), user,em);

		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|insertBillerServiceChannel|Exception:" + e.getMessage());
			throw e;
		}
	}

	public int insertBillerForm(BillerForm bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBillerForm|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBillerForm|Param|" + bean.toString());

			String sql = "INSERT INTO BILLER_FORM(BLLR_FORM_ID, BLLR_FORM_NAME, BLLR_FORM_CAPT_TH, BLLR_FORM_CAPT_EN, BLLR_SRVC_ID, ACT_FLAG, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY, LAST_CHNG_DTTM)" + "VALUES(?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE)";
			int i = 0;
			Query query = em.createNativeQuery(sql);
			query.setParameter(++i, bean.getBLLR_FORM_ID());
			query.setParameter(++i, bean.getBLLR_FORM_NAME());
			query.setParameter(++i, bean.getBLLR_FORM_CAPT_TH());
			query.setParameter(++i, bean.getBLLR_FORM_CAPT_EN());
			query.setParameter(++i, bean.getBLLR_SRVC_ID());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, user.getName());
			query.executeUpdate();

			log.info(user.getName() + "|" + page + "|insertBillerForm|Success");
			log.info(user.getName() + "|" + page + "|insertBillerForm|Time|" + timer.getStopTime());

			return bean.getBLLR_FORM_ID();

		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|insertBillerForm|Exception:" + e.getMessage());
			throw e;
		}
	}

	public List<BillerRef> searchBillerRef(BillerRefParam PARAM, UserInfoBean user) throws Exception {
		List<BillerRef> list = null;
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBillerRef|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBillerRef|Param|" + PARAM.toString());

			String sql = "SELECT * FROM BILLER_REF ";

			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(PARAM.getBLLR_SRVC_ID())) {
				v.add("BLLR_SRVC_ID = " + PARAM.getBLLR_SRVC_ID());
			}
			if (!ValidateUtil.isEmpty(PARAM.getBLLR_FORM_ID())) {
				v.add("BLLR_FORM_ID = " + PARAM.getBLLR_FORM_ID());
			}

			if (!v.isEmpty()) {
				sb.append(" WHERE (");
				for (int i = 0; i < v.size(); i++) {
					if (i != 0) {
						sb.append(" AND ");
					}
					sb.append(v.get(i));
				}
				sb.append(")");
			}
			sql += sb.toString();
			sql += " ORDER BY REF_SEQ";

			log.info(user.getName() + "|" + page + "|searchBillerRef|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerRef.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchBillerRef|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchBillerRef|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|searchBillerRef|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}

	public List<BillerBarcode> searchBillerBarcode(int BLLR_SRVC_ID, UserInfoBean user) throws Exception {
		List<BillerBarcode> list = null;
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBillerBarcode|Time:" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBillerBarcode|Param|BLLR_SRVC_ID:" + BLLR_SRVC_ID);

			String sql = "SELECT DISTINCT BB.* " + "FROM BILLER_BARCODE BB " + "INNER JOIN BILLER_BARCODE_REF BBR ON BB.BARC_ID = BBR.BARC_ID " + "INNER JOIN BILLER_REF BR ON BBR.REF_ID = BR.REF_ID " + "WHERE BR.BLLR_SRVC_ID = " + BLLR_SRVC_ID + " " + "ORDER BY BB.BARC_ID";

			log.info(user.getName() + "|" + page + "|searchBillerBarcode|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerBarcode.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchBillerBarcode|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchBillerBarcode|Time:" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|searchBillerBarcode|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}

	public List<BillerBarcodeDetail> searchBillerBarcodeDetail(int BLLR_SRVC_ID, UserInfoBean user) throws Exception {
		List<BillerBarcodeDetail> listDetail = new ArrayList<BillerBarcodeDetail>();
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBillerService|Time:" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBillerService|Param|BLLR_SRVC_ID:" + BLLR_SRVC_ID);

			String sql = "SELECT BR.REF_ID, BR.REF_LABL_TH, BR.REF_LABL_EN, " + "BBR.BARC_ID, BB.BARC_NAME, BBR.BARC_REF_ID, BBR.BARC_LINE_ID, BBR.BARC_PART_FLAG, " + "BBR.BARC_PART_STAT, BBR.BARC_PART_LENT, BBR.BARC_REMV_CHAR, BBR.ACT_FLAG " + "FROM BILLER_BARCODE_REF BBR " + "INNER JOIN BILLER_BARCODE BB ON BB.BARC_ID = BBR.BARC_ID " + "INNER JOIN BILLER_REF BR ON BBR.REF_ID = BR.REF_ID " + "WHERE BR.BLLR_SRVC_ID = " + BLLR_SRVC_ID + " " + "ORDER BY BR.REF_ID";

			log.info(user.getName() + "|" + page + "|searchBillerBarcodeDetail|SQL:" + sql);

			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			BillerBarcodeDetail bean = null;
			for (Object[] item : list) {
				int x = 0;
				bean = new BillerBarcodeDetail();
				bean.setREF_ID(JpaResultUtil.getInteger(item, x++));
				bean.setREF_LABL_TH(JpaResultUtil.getString(item, x++));
				bean.setREF_LABL_EN(JpaResultUtil.getString(item, x++));
				bean.setBARC_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBARC_NAME(JpaResultUtil.getString(item, x++));
				bean.setBARC_REF_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBARC_LINE_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBARC_PART_FLAG(JpaResultUtil.getString(item, x++));
				bean.setBARC_PART_STAT(JpaResultUtil.getInteger(item, x++));
				bean.setBARC_PART_LENT(JpaResultUtil.getInteger(item, x++));
				bean.setBARC_REMV_CHAR(JpaResultUtil.getString(item, x++));
				bean.setACT_FLAG(JpaResultUtil.getString(item, x++));
				listDetail.add(bean);
			}

			log.info(user.getName() + "|" + page + "|searchBillerBarcodeDetail|List.Size:" + listDetail.size());
			log.info(user.getName() + "|" + page + "|searchBillerService|Time:" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|searchBillerBarcodeDetail|Exception:" + e.getMessage());
			throw e;
		}
		return listDetail;
	}

	public BillerForm findBillerForm(int BLLR_FORM_ID, UserInfoBean user) throws NotFoundDataException, Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerForm|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_FORM WHERE BLLR_FORM_ID = " + BLLR_FORM_ID;
			log.info(user.getName() + "|" + page + "|findBillerForm|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerForm.class);
			List<BillerForm> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerForm|Time:" + timer.getStopTime());
			if ((list != null) && (list.size() > 0)) {
				return (BillerForm) list.get(0);
			} else {
				throw new NotFoundDataException("");
			}
		} catch (NotFoundDataException e) {
			log.error(user.getName() + "|" + page + "|findBillerForm|NotFoundDataException:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|findBillerForm|Exception:" + e.getMessage());
			throw e;
		}
	}

	public int insertBillerRef(BillerRef bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBillerRef|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBillerRef|Param|" + bean.toString());

			Query query = em.createNativeQuery("SELECT SEQ_BILLER_REF.nextval from DUAL");
			BigDecimal result = (BigDecimal) query.getSingleResult();
			int REF_ID = result.intValue();

			log.info(user.getName() + "|" + page + "|insertBillerRef|REF_ID:" + REF_ID);

			String sql = "INSERT INTO BILLER_REF(REF_ID, BLLR_SRVC_ID, BLLR_FORM_ID, REF_SEQ, REF_LABL_TH, REF_LABL_EN, REF_TYPE, REF_DEFT_VALE, REF_VALE_LENT_MIN, REF_VALE_LENT_MAX, REF_REQU_FLAG, REF_HIDN_FLAG, ACT_FLAG, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY, LAST_CHNG_DTTM,REF_ALLW_KEY_IN,REF_PRIM_FLAG,REF_DATA_TYPE,REF_DUP_FLAG,REF_DATA_FOMT)" +
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE,?,?,?,?,?)";

			int i = 0;
			query = em.createNativeQuery(sql);
			query.setParameter(++i, REF_ID);
			query.setParameter(++i, bean.getBLLR_SRVC_ID());
			query.setParameter(++i, bean.getBLLR_FORM_ID());
			query.setParameter(++i, bean.getREF_SEQ());
			query.setParameter(++i, bean.getREF_LABL_TH());
			query.setParameter(++i, bean.getREF_LABL_EN());
			query.setParameter(++i, bean.getREF_TYPE());
			query.setParameter(++i, bean.getREF_DEFT_VALE());
			query.setParameter(++i, bean.getREF_VALE_LENT_MIN());
			query.setParameter(++i, bean.getREF_VALE_LENT_MAX());
			query.setParameter(++i, bean.getREF_REQU_FLAG());			
			query.setParameter(++i, bean.getREF_HIDN_FLAG());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, bean.getREF_ALLW_KEY_IN());
			query.setParameter(++i, bean.getREF_PRIM_FLAG());
			query.setParameter(++i, bean.getREF_DATA_TYPE());
			query.setParameter(++i, bean.getREF_DUP_FLAG());
			query.setParameter(++i, bean.getREF_DATA_FOMT());
			query.executeUpdate();

			log.info(user.getName() + "|" + page + "|insertBillerRef|Success");
			log.info(user.getName() + "|" + page + "|insertBillerRef|Time|" + timer.getStopTime());

			updateBillerServiceCenter(bean.getBLLR_SRVC_ID(), user,em);

			return REF_ID;

		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|insertBillerRef|Exception:" + e.getMessage());
			throw e;
		}
	}

	public BillerRefDetail findBillerRefDetail(int REF_ID, UserInfoBean user) throws NotFoundDataException, Exception {
		BillerRefDetail bean = null;
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerRefDetail|Time:" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|findBillerRefDetail|Param|REF_ID:" + REF_ID);

			String sql = "SELECT BR.REF_ID, BR.REF_LABL_TH, BR.REF_LABL_EN, BR.BLLR_FORM_ID, BF.BLLR_FORM_NAME " + "FROM BILLER_REF BR " + "INNER JOIN BILLER_FORM BF ON BR.BLLR_FORM_ID = BF.BLLR_FORM_ID " + "WHERE BR.REF_ID = " + REF_ID;

			log.info(user.getName() + "|" + page + "|findBillerRefDetail|SQL:" + sql);

			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();

			if ((list == null) || (list.isEmpty())) {
				throw new NotFoundDataException("");
			}
			Object[] item = list.get(0);

			int x = 0;
			bean = new BillerRefDetail();
			bean.setREF_ID(JpaResultUtil.getInteger(item, x++));
			bean.setREF_LABL_TH(JpaResultUtil.getString(item, x++));
			bean.setREF_LABL_EN(JpaResultUtil.getString(item, x++));
			bean.setBLLR_FORM_ID(JpaResultUtil.getInteger(item, x++));
			bean.setBLLR_FORM_NAME(JpaResultUtil.getString(item, x++));
			log.info(user.getName() + "|" + page + "|findBillerRefDetail|Time:" + timer.getStopTime());
			return bean;

		} catch (NotFoundDataException e) {
			log.error(user.getName() + "|" + page + "|findBillerRefDetail|NotFoundDataException:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|findBillerRefDetail|Exception:" + e.getMessage());
			throw e;
		}
	}

	public List<BillerBarcode> getBillerBarcodeAll(UserInfoBean user) throws Exception {
		List<BillerBarcode> list = null;
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|getBillerBarcodeAll|Time:" + timer.getStartTime());

			String sql = "SELECT * FROM BILLER_BARCODE WHERE ACT_FLAG = 'A' " + "ORDER BY BARC_ID";

			log.info(user.getName() + "|" + page + "|getBillerBarcodeAll|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerBarcode.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|getBillerBarcodeAll|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|getBillerBarcodeAll|Time:" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|getBillerBarcodeAll|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}

	public int insertBillerBarcodeRef(BillerBarcodeRef bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBillerBarcodeRef|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBillerBarcodeRef|Param|" + bean.toString());

			Query query = em.createNativeQuery("SELECT SEQ_BILLER_BARCODE_REF.nextval from DUAL");
			BigDecimal result = (BigDecimal) query.getSingleResult();
			int BARC_REF_ID = result.intValue();

			log.info(user.getName() + "|" + page + "|insertBillerBarcodeRef|BARC_REF_ID:" + BARC_REF_ID);

			String sql = "INSERT INTO BILLER_BARCODE_REF(BARC_REF_ID, REF_ID, BARC_ID, BARC_LINE_ID, BARC_REMV_CHAR, BARC_PART_FLAG, BARC_PART_STAT, BARC_PART_LENT, ACT_FLAG, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY, LAST_CHNG_DTTM )" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE)";

			int i = 0;
			query = em.createNativeQuery(sql);
			query.setParameter(++i, BARC_REF_ID);
			query.setParameter(++i, bean.getREF_ID());
			query.setParameter(++i, bean.getBARC_ID());
			query.setParameter(++i, bean.getBARC_LINE_ID());
			query.setParameter(++i, bean.getBARC_REMV_CHAR());
			query.setParameter(++i, bean.getBARC_PART_FLAG());
			query.setParameter(++i, bean.getBARC_PART_STAT());
			query.setParameter(++i, bean.getBARC_PART_LENT());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, user.getName());
			query.executeUpdate();

			log.info(user.getName() + "|" + page + "|insertBillerBarcodeRef|Success");
			log.info(user.getName() + "|" + page + "|insertBillerBarcodeRef|Time|" + timer.getStopTime());

			Query query1 = em.createNativeQuery("SELECT T1.BLLR_SRVC_ID " + " FROM  BILLER_SERVICE T1 LEFT JOIN BILLER_REF T2 ON  T1.BLLR_SRVC_ID = T2.BLLR_SRVC_ID " + " WHERE T2.REF_ID = (SELECT T4.REF_ID " + "  FROM   BILLER_BARCODE_REF T3   LEFT JOIN BILLER_REF T4 ON T4.REF_ID = T3.REF_ID " + " WHERE    T3.BARC_REF_ID = " + BARC_REF_ID + " )");

			BigDecimal result1 = (BigDecimal) query1.getSingleResult();
			int BLLR_SRVC_ID = result1.intValue();

			updateBillerServiceCenter(BLLR_SRVC_ID, user,em);

			return BARC_REF_ID;

		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|insertBillerBarcodeRef|Exception:" + e.getMessage());
			throw e;
		}
	}

	public BillerService findBillerService(int BLLR_SRVC_ID, UserInfoBean user) throws NotFoundDataException, Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerService|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_SERVICE WHERE BLLR_SRVC_ID = " + BLLR_SRVC_ID;
			log.info(user.getName() + "|" + page + "|findBillerService|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerService.class);
			List<BillerService> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerService|Time:" + timer.getStopTime());
			if ((list != null) && (list.size() > 0)) {
				return (BillerService) list.get(0);
			} else {
				throw new NotFoundDataException("");
			}

		} catch (NotFoundDataException e) {
			log.error(user.getName() + "|" + page + "|findBillerService|NotFoundDataException:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|findBillerService|Exception:" + e.getMessage());
			throw e;
		}
	}

	public void updateBillerService(BillerService bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerService|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerService|Param|" + bean.toString());

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_SERVICE ").append("SET BLLR_CATG_ID = ? ").append(", BLLR_SRVC_CODE = ? ").append(", BLLR_MSTR_ID = ? ").append(", BLLR_SRVC_NAME_EN = ? ").append(", BLLR_SRVC_NAME_FULL_EN = ? ").append(", BLLR_SRVC_NAME_TH = ? ").append(", BLLR_SRVC_NAME_FULL_TH = ? ").append(", BLLR_SRVC_STAT_DATE = ? ").append(", BLLR_SRVC_EXPR_DATE = ? ").append(", BLLR_SRVC_MENU_SEQ = ? ").append(", BLLR_SRVC_GUST_MOBN_FLAG = ? ").append(", BLLR_SRVC_BARC_FLAG = ? ").append(", BLLR_SERV_VAT_PERC = ? ")
			// .append(", ACT_FLAG = ? ")			
			.append(", LAST_CHNG_BY = ? ").append(", LAST_CHNG_DTTM = SYSDATE ").append(", BLLR_SRVC_FETR = ? ").append(", BLLR_INGT_ID = ? ")
			.append(", BLLR_CNCL_ONLN_FLAG = ? ").append(", BLLR_FETR_ID = ? ")
			.append("WHERE BLLR_SRVC_ID = ? ");

			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBLLR_CATG_ID());
			query.setParameter(++i, bean.getBLLR_SRVC_CODE());
			query.setParameter(++i, bean.getBLLR_MSTR_ID());
			query.setParameter(++i, bean.getBLLR_SRVC_NAME_EN());
			query.setParameter(++i, bean.getBLLR_SRVC_NAME_FULL_EN());
			query.setParameter(++i, bean.getBLLR_SRVC_NAME_TH());
			query.setParameter(++i, bean.getBLLR_SRVC_NAME_FULL_TH());
			query.setParameter(++i, bean.getBLLR_SRVC_STAT_DATE());
			query.setParameter(++i, bean.getBLLR_SRVC_EXPR_DATE());
			query.setParameter(++i, bean.getBLLR_SRVC_MENU_SEQ());
			query.setParameter(++i, bean.getBLLR_SRVC_GUST_MOBN_FLAG());
			query.setParameter(++i, bean.getBLLR_SRVC_BARC_FLAG());
			query.setParameter(++i, bean.getBLLR_SERV_VAT_PERC());
			// query.setParameter(++i, bean.getACT_FLAG());					
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBLLR_SRVC_FETR());
			query.setParameter(++i, bean.getBLLR_INGT_ID());
			query.setParameter(++i, bean.getBLLR_CNCL_ONLN_FLAG());
			query.setParameter(++i, bean.getBLLR_FETR_ID());
			query.setParameter(++i, bean.getBLLR_SRVC_ID());
			query.executeUpdate();

			log.info(user.getName() + "|" + page + "|updateBillerService|Success");
			log.info(user.getName() + "|" + page + "|updateBillerService|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|updateBillerService|Exception:" + e.getMessage());
			throw e;
		}
	}

	public BillerPaymentValidate findBillerPaymentValidate(int BLLR_SRVC_ID, UserInfoBean user) throws NotFoundDataException, Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerPaymentValidate|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_PAYMENT_VALIDATE WHERE BLLR_SRVC_ID = " + BLLR_SRVC_ID;
			log.info(user.getName() + "|" + page + "|findBillerPaymentValidate|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerPaymentValidate.class);
			List<BillerPaymentValidate> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerPaymentValidate|Time:" + timer.getStopTime());
			if ((list != null) && (list.size() > 0)) {
				return (BillerPaymentValidate) list.get(0);
			} else {
				throw new NotFoundDataException("");
			}

		} catch (NotFoundDataException e) {
			log.error(user.getName() + "|" + page + "|findBillerPaymentValidate|NotFoundDataException:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|findBillerPaymentValidate|Exception:" + e.getMessage());
			throw e;
		}
	}

	public void updateBillerPaymentValidate(BillerPaymentValidate bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerPaymentValidate|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerPaymentValidate|Param|" + bean.toString());

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_PAYMENT_VALIDATE ").append("SET BLLR_PMNT_FULL_FLAG = ? ").append(", BLLR_PRES_FLAG = ? ").append(", BLLR_PMNT_PART_FLAG = ? ").append(", BLLR_BILL_AMNT_FLAG = ? ").append(", BLLR_PMNT_OVER_FLAG = ? ").append(", BLLR_PMNT_AMNT_MIN = ? ").append(", BLLR_PMNT_AMNT_MAX = ? ").append(", BLLR_NON_FDM_FEE_FLAG = ? ").append(", BLLR_NON_FDM_COMS_FLAG = ? ").append(", BLLR_OVER_DUE_FLAG = ? ").append(", LAST_CHNG_BY = ? ").append(", LAST_CHNG_DTTM = SYSDATE ").append(", BLLR_MAX_NO_MNTH = ? ").append(", BLLR_DENM_FLAG = ? ").append("WHERE BLLR_PMNT_VALD_ID = ? ");

			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBLLR_PMNT_FULL_FLAG());
			query.setParameter(++i, bean.getBLLR_PRES_FLAG());
			query.setParameter(++i, bean.getBLLR_PMNT_PART_FLAG());
			query.setParameter(++i, bean.getBLLR_BILL_AMNT_FLAG());
			query.setParameter(++i, bean.getBLLR_PMNT_OVER_FLAG());
			query.setParameter(++i, bean.getBLLR_PMNT_AMNT_MIN());
			query.setParameter(++i, bean.getBLLR_PMNT_AMNT_MAX());
			query.setParameter(++i, bean.getBLLR_NON_FDM_FEE_FLAG());
			query.setParameter(++i, bean.getBLLR_NON_FDM_COMS_FLAG());
			query.setParameter(++i, bean.getBLLR_OVER_DUE_FLAG());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBLLR_MAX_NO_MNTH());
			query.setParameter(++i, bean.getBLLR_DENM_FLAG());
			query.setParameter(++i, bean.getBLLR_PMNT_VALD_ID());
			query.executeUpdate();

			updateBillerServiceCenter(bean.getBLLR_PMNT_VALD_ID(), user,em);

			log.info(user.getName() + "|" + page + "|updateBillerPaymentValidate|Success");
			log.info(user.getName() + "|" + page + "|updateBillerPaymentValidate|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|updateBillerPaymentValidate|Exception:" + e.getMessage());
			throw e;
		}
	}

	public BillerServiceChannel findBillerServiceChannel(int BLLR_SRVC_ID, int BLLR_CHNL_ID, UserInfoBean user) throws NotFoundDataException, Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerServiceChannel|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_SERVICE_CHANNEL WHERE BLLR_SRVC_ID = " + BLLR_SRVC_ID + " AND BLLR_CHNL_ID = " + BLLR_CHNL_ID;
			log.info(user.getName() + "|" + page + "|findBillerServiceChannel|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerServiceChannel.class);
			List<BillerServiceChannel> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerServiceChannel|Time:" + timer.getStopTime());
			if ((list != null) && (list.size() > 0)) {
				return (BillerServiceChannel) list.get(0);
			} else {
				throw new NotFoundDataException("");
			}

		} catch (NotFoundDataException e) {
			log.error(user.getName() + "|" + page + "|findBillerServiceChannel|NotFoundDataException:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|findBillerServiceChannel|Exception:" + e.getMessage());
			throw e;
		}
	}

	public void updateBillerServiceChannel(BillerServiceChannel bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerServiceChannel|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerServiceChannel|Param|" + bean.toString());

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_SERVICE_CHANNEL ").append("SET BLLR_SRVC_CHNL_KEYN_FLAG = ? ").append(", BLLR_SRVC_CHNL_BARC_FLAG = ? ").append(", ACT_FLAG = ? ").append(", LAST_CHNG_BY = ? ").append(", LAST_CHNG_DTTM = SYSDATE ").append("WHERE BLLR_SRVC_ID = ? ").append("AND BLLR_CHNL_ID = ? ");

			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBLLR_SRVC_CHNL_KEYN_FLAG());
			query.setParameter(++i, bean.getBLLR_SRVC_CHNL_BARC_FLAG());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBLLR_SRVC_ID());
			query.setParameter(++i, bean.getBLLR_CHNL_ID());
			query.executeUpdate();

			updateBillerServiceCenter(bean.getBLLR_SRVC_ID(), user,em);

			log.info(user.getName() + "|" + page + "|updateBillerServiceChannel|Success");
			log.info(user.getName() + "|" + page + "|updateBillerServiceChannel|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|updateBillerServiceChannel|Exception:" + e.getMessage());
			throw e;
		}
	}

	public BillerForm findBillerFormBySrvcId(int BLLR_SRVC_ID, UserInfoBean user) throws NotFoundDataException, Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerFormBySrvcId|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_FORM WHERE BLLR_SRVC_ID = " + BLLR_SRVC_ID;
			log.info(user.getName() + "|" + page + "|findBillerFormBySrvcId|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerForm.class);
			List<BillerForm> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerFormBySrvcId|Time:" + timer.getStopTime());
			log.info(user.getName() + "|" + page + "|updateBillerForm|Time|" + timer.getStopTime());
			if ((list != null) && (list.size() > 0)) {
				return (BillerForm) list.get(0);
			} else {
				throw new NotFoundDataException("");
			}

		} catch (NotFoundDataException e) {
			log.error(user.getName() + "|" + page + "|findBillerFormBySrvcId|NotFoundDataException:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|findBillerFormBySrvcId|Exception:" + e.getMessage());
			throw e;
		}
	}

	public void updateBillerForm(BillerForm bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerForm|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerForm|Param|" + bean.toString());

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_FORM ").append("SET BLLR_FORM_NAME = ? ").append(", BLLR_FORM_CAPT_TH = ? ").append(", BLLR_FORM_CAPT_EN = ? ").append(", LAST_CHNG_BY = ? ").append(", LAST_CHNG_DTTM = SYSDATE ").append("WHERE BLLR_FORM_ID = ? ");

			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBLLR_FORM_NAME());
			query.setParameter(++i, bean.getBLLR_FORM_CAPT_TH());
			query.setParameter(++i, bean.getBLLR_FORM_CAPT_EN());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBLLR_FORM_ID());
			query.executeUpdate();

			updateBillerServiceCenter(bean.getBLLR_FORM_ID(), user,em);

			log.info(user.getName() + "|" + page + "|updateBillerForm|Success");
			log.info(user.getName() + "|" + page + "|updateBillerForm|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|updateBillerForm|Exception:" + e.getMessage());
			throw e;
		}
	}

	public BillerRef findBillerRef(int REF_ID, UserInfoBean user) throws NotFoundDataException, Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerRef|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_REF WHERE REF_ID = " + REF_ID;
			log.info(user.getName() + "|" + page + "|findBillerRef|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerRef.class);
			List<BillerRef> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerRef|Time:" + timer.getStopTime());
			if ((list != null) && (list.size() > 0)) {
				return (BillerRef) list.get(0);
			} else {
				throw new NotFoundDataException("");
			}

		} catch (NotFoundDataException e) {
			log.error(user.getName() + "|" + page + "|findBillerRef|NotFoundDataException:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|findBillerRef|Exception:" + e.getMessage());
			throw e;
		}
	}

	public void updateBillerRef(BillerRef bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerRef|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerRef|Param|" + bean.toString());

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_REF ").append("SET REF_SEQ = ? ")
			.append(", REF_LABL_TH = ? ")
			.append(", REF_LABL_EN = ? ")
			.append(", REF_TYPE = ? ")
			.append(", REF_DEFT_VALE = ? ")
			.append(", REF_VALE_LENT_MIN = ? ")
			.append(", REF_VALE_LENT_MAX = ? ")
			.append(", REF_REQU_FLAG = ? ")
			.append(", REF_HIDN_FLAG = ? ")
			.append(", ACT_FLAG = ? ")
			.append(", LAST_CHNG_BY = ? ")
			.append(", LAST_CHNG_DTTM = SYSDATE ")
			.append(", REF_ALLW_KEY_IN = ? ")
			.append(", REF_PRIM_FLAG = ? ")
			.append(", REF_DATA_TYPE = ? ")
			.append(", REF_DUP_FLAG = ? ")
			.append(", REF_DATA_FOMT = ? ")				
			.append(", REF_SRCH_FLAG = ? ")
			.append(", ENBL_DFLT_VALU = ? ")
			.append(", DFLT_BLNK_VALU = ? ")
			.append(", REF_SMS_FLAG = ? ")
			.append(", REF_BLIND_FOMT = ? ")		
			.append("WHERE REF_ID = ? ");
	
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getREF_SEQ());
			query.setParameter(++i, bean.getREF_LABL_TH());
			query.setParameter(++i, bean.getREF_LABL_EN());
			query.setParameter(++i, bean.getREF_TYPE());
			query.setParameter(++i, bean.getREF_DEFT_VALE());
			query.setParameter(++i, bean.getREF_VALE_LENT_MIN());
			query.setParameter(++i, bean.getREF_VALE_LENT_MAX());
			query.setParameter(++i, bean.getREF_REQU_FLAG());
			query.setParameter(++i, bean.getREF_HIDN_FLAG());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getREF_ALLW_KEY_IN());
			query.setParameter(++i, bean.getREF_PRIM_FLAG());
			query.setParameter(++i, bean.getREF_DATA_TYPE());
			query.setParameter(++i, bean.getREF_DUP_FLAG());
			query.setParameter(++i, bean.getREF_DATA_FOMT());
			query.setParameter(++i, bean.getREF_SRCH_FLAG());
			query.setParameter(++i, bean.getENBL_DFLT_VALU());
			query.setParameter(++i, bean.getDFLT_BLNK_VALU());
			query.setParameter(++i, bean.getREF_SMS_FLAG());
			query.setParameter(++i, bean.getREF_BLIND_FOMT());			
			query.setParameter(++i, bean.getREF_ID());
			query.executeUpdate();
	
			Query query1 = em.createNativeQuery("SELECT T1.BLLR_SRVC_ID " + " FROM  BILLER_SERVICE T1 LEFT JOIN BILLER_REF T2 ON  T1.BLLR_SRVC_ID = T2.BLLR_SRVC_ID " + " WHERE  T2.REF_ID = " + bean.getREF_ID());

			BigDecimal result = (BigDecimal) query1.getSingleResult();
			int BLLR_SRVC_ID = result.intValue();

			updateBillerServiceCenter(BLLR_SRVC_ID, user,em);

			log.info(user.getName() + "|" + page + "|updateBillerRef|Success");
			log.info(user.getName() + "|" + page + "|updateBillerRef|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|updateBillerRef|Exception:" + e.getMessage());
			throw e;
		}
	}

	public BillerBarcodeRef findBillerBarcodeRef(int BARC_REF_ID, UserInfoBean user) throws NotFoundDataException, Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerBarcodeRef|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_BARCODE_REF WHERE BARC_REF_ID = " + BARC_REF_ID;
			log.info(user.getName() + "|" + page + "|findBillerBarcodeRef|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerBarcodeRef.class);
			List<BillerBarcodeRef> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerBarcodeRef|Time:" + timer.getStopTime());
			if ((list != null) && (list.size() > 0)) {
				return (BillerBarcodeRef) list.get(0);
			} else {
				throw new NotFoundDataException("");
			}

		} catch (NotFoundDataException e) {
			log.error(user.getName() + "|" + page + "|findBillerBarcodeRef|NotFoundDataException:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|findBillerBarcodeRef|Exception:" + e.getMessage());
			throw e;
		}
	}

	public void updateBillerBarcodeRef(BillerBarcodeRef bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerBarcodeRef|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerBarcodeRef|Param|" + bean.toString());

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_BARCODE_REF ").append("SET BARC_LINE_ID = ? ").append(", BARC_ID = ? ").append(", BARC_REMV_CHAR = ? ").append(", BARC_PART_FLAG = ? ").append(", ACT_FLAG = ? ").append(", BARC_PART_STAT = ? ").append(", BARC_PART_LENT = ? ").append(", LAST_CHNG_BY = ? ").append(", LAST_CHNG_DTTM = SYSDATE ").append("WHERE BARC_REF_ID = ? ");

			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBARC_LINE_ID());
			query.setParameter(++i, bean.getBARC_ID());
			query.setParameter(++i, bean.getBARC_REMV_CHAR());
			query.setParameter(++i, bean.getBARC_PART_FLAG());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getBARC_PART_STAT());
			query.setParameter(++i, bean.getBARC_PART_LENT());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBARC_REF_ID());
			query.executeUpdate();

			/*
			 * String sql1 = "SELECT T1.BLLR_SRVC_ID " +
			 * " FROM  BILLER_SERVICE T1 LEFT JOIN BILLER_REF T2 ON  T1.BLLR_SRVC_ID = T2.BLLR_SRVC_ID "
			 * + " WHERE T2.REF_ID = (SELECT T4.REF_ID " +
			 * "  FROM   BILLER_BARCODE_REF T3   LEFT JOIN BILLER_REF T4 ON T4.REF_ID = T3.REF_ID "
			 * + " WHERE    T3.BARC_REF_ID = "+bean.getBARC_REF_ID()+" )";
			 */

			Query query1 = em.createNativeQuery("SELECT T1.BLLR_SRVC_ID " + " FROM  BILLER_SERVICE T1 LEFT JOIN BILLER_REF T2 ON  T1.BLLR_SRVC_ID = T2.BLLR_SRVC_ID " + " WHERE T2.REF_ID = (SELECT T4.REF_ID " + "  FROM   BILLER_BARCODE_REF T3   LEFT JOIN BILLER_REF T4 ON T4.REF_ID = T3.REF_ID " + " WHERE    T3.BARC_REF_ID = " + bean.getBARC_REF_ID() + ")");

			BigDecimal result = (BigDecimal) query1.getSingleResult();
			int BLLR_SRVC_ID = result.intValue();

			updateBillerServiceCenter(BLLR_SRVC_ID, user,em);

			log.info(user.getName() + "|" + page + "|updateBillerBarcodeRef|Success");
			log.info(user.getName() + "|" + page + "|updateBillerBarcodeRef|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|updateBillerBarcodeRef|Exception:" + e.getMessage());
			throw e;
		}
	}

	public void updateBillerServiceCenter(Integer BLLR_SRVC_ID, UserInfoBean user,EntityManager em) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerServiceCenter|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerServiceCenter|Param|" + BLLR_SRVC_ID);

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_SERVICE ").append("SET LAST_CHNG_BY = ? ").append(", LAST_CHNG_DTTM = SYSDATE ").append("WHERE BLLR_SRVC_ID = ? ");
		
			int i = 0;
			log.debug("SB::" + sb.toString());
			log.debug("EM::" + em);
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, BLLR_SRVC_ID);
			query.executeUpdate();

			log.info(user.getName() + "|" + page + "|updateBillerServiceCenter|Success");
			log.info(user.getName() + "|" + page + "|updateBillerServiceCenter|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|updateBillerServiceCenter|Exception:" + e.getMessage());
			throw e;
		}
	}

	public BillerServiceImage getBillerServiceImage(String serviceId) throws Exception {
		BillerServiceImage billerServiceImage = new BillerServiceImage();
		
		try {
			Timer timer = new Timer("-");
			log.info("BillerServiceBean|getBillerServiceImage|serviceId:" + serviceId);
			log.info("BillerServiceBean|getBillerServiceImage|Time:" + timer.getStartTime());

			List<Object> params = new ArrayList<Object>();
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT "); 
			sql.append(" BLLR_SRVC_ID,"); 
			sql.append(" BLLR_SRVC_CODE,"); 
			sql.append(" BLLR_SRVC_WEB_ICON,"); 
			sql.append(" BLLR_SRVC_WEB_INST,"); 
			sql.append(" BLLR_SRVC_MOB_ICON,"); 
			sql.append(" CRTD_BY,"); 
			sql.append(" CRTD_DTTM,");
			sql.append(" LAST_CHNG_BY,"); 
			sql.append(" LAST_CHNG_DTTM");
			sql.append(" FROM BILLER_SERVICE_IMAGE BSI");
			sql.append(" WHERE BLLR_SRVC_ID=?");
			params.add(serviceId);			
			
			log.info("BillerServiceBean|getBillerServiceImage|sql:" + sql.toString());
			Query query = em.createNativeQuery(sql.toString(), BillerServiceImage.class);
			dbUtil.setParams(query, params);	
			List<BillerServiceImage> results=query.getResultList();		
			log.info("Results Size::"+results.size());
			if ((results != null) && (results.size() > 0)) {
				billerServiceImage=(BillerServiceImage)results.get(0);
			} else {
				throw new NotFoundDataException("");
			}			
			log.info("BillerServiceBean|getBillerServiceImage|Time:" + timer.getStopTime());

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}

		return billerServiceImage;
	}
	
	public Integer saveBillerImage(BillerServiceImage billerServiceImage, UserInfoBean user) throws Exception {

		try {
			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|saveBillerImage|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|saveBillerImage|Param|" + billerServiceImage.toString());
			billerServiceImage.setCreateBy(user.getName());
			billerServiceImage.setCreateDate(new Date());
			billerServiceImage.setLastChangeBy(user.getName());
			billerServiceImage.setLastChangeDate(new Date());
			
			em.persist(billerServiceImage);

			log.info(user.getName() + "|" + page + "|saveBillerImage|Success");
			log.info(user.getName() + "|" + page + "|saveBillerImage|Time|" + timer.getStopTime());

			return billerServiceImage.getBllrSrvcId();

		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|saveBillerImage|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public void updateBillerServiceImage(BillerServiceImage billerServiceImage, UserInfoBean user) throws Exception {
		try {
			/* Test */
//			EntityManagerFactory factory;	
//			System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
//			System.setProperty("javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
//			factory = Persistence.createEntityManagerFactory("mfs.db.MFSDAO");
//			EntityManager em = factory.createEntityManager();
//			log.debug("EM:"+em);
			/* Test */
			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerServiceImage|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerServiceImage|Param|" + billerServiceImage.toString());
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_SERVICE_IMAGE ");
			sb.append(" SET");
			if(!ArrayUtils.isEmpty(billerServiceImage.getBllrSrvcWebIcon())){
				sb.append(" BLLR_SRVC_WEB_ICON=?,");
				params.add(billerServiceImage.getBllrSrvcWebIcon());
			}	
			if(!ArrayUtils.isEmpty(billerServiceImage.getBllrSrvcWebInst())){
				sb.append(" BLLR_SRVC_WEB_INST=?,");
				params.add(billerServiceImage.getBllrSrvcWebInst());
			}	
			if(!ArrayUtils.isEmpty(billerServiceImage.getBllrSrvcMobIcon())){
				sb.append(" BLLR_SRVC_MOB_ICON=?,");
				params.add(billerServiceImage.getBllrSrvcMobIcon());
			}
			sb.append(" LAST_CHNG_BY = ?,");
			params.add(user.getName());
			sb.append(" LAST_CHNG_DTTM = SYSDATE ");
			sb.append(" WHERE BLLR_SRVC_ID = ? ");
			params.add(billerServiceImage.getBllrSrvcId());
		
			log.debug("Sql::" + sb.toString());
			log.debug("EM::" + em);
			Query query = em.createNativeQuery(sb.toString());
			dbUtil.setParams(query, params);	
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateBillerServiceImage|Success");
			log.info(user.getName() + "|" + page + "|updateBillerServiceImage|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|updateBillerServiceImage|Exception:" + e.getMessage());
			throw e;
		}
	}
	
//	public static void main(String[] args){
//		PropertyConfigurator.configure("log4j.properties");
//		log.debug("Start");
//		BillerServiceBean bsb=new BillerServiceBean();
//		try {
////			BillerServiceImage bsi=bsb.getBillerServiceImage("1");
////			log.debug("BillerServiceImage:"+bsi);
////			log.debug("jose:"+bsi.getBllrSrvcCode());
////			log.debug("image:"+bsi.getBllrSrvcWebIcon().length);
//			BillerServiceImage bsi=new BillerServiceImage();
//			bsi.setBllrSrvcId(1);
//			bsi.setBllrSrvcCode("0001");
//			bsi.setCreateBy("jose");
//			bsi.setLastChangeBy("jose");
//			File file=new File("D:\\JoseWork\\Dtac\\Specification\\MFS Web Agent\\Icon\\20150513\\icon-cs-2-0014.gif");
//			FileInputStream fis=new FileInputStream(file);
//			bsi.setBllrSrvcWebIcon(FileUtils.readFileToByteArray(file));
//			bsi.setBllrSrvcWebInst(FileUtils.readFileToByteArray(file));
//			UserInfoBean user=new UserInfoBean();
//			user.setName("jose");
//			bsb.updateBillerServiceImage(bsi, user);
//			} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}