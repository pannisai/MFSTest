package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.BillerBarcodeBeanLocal;
import mfs.biller.ejb.interfaces.BillerBarcodeBeanRemote;
import mfs.biller.persistence.bean.BillerBarcode;
import mfs.biller.persistence.bean.BillerBarcodeParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;

import org.apache.log4j.Logger;

@Stateless(name = "BillerBarcodeBean", mappedName = "mfs.biller.ejb.BillerBarcodeBean")
@CallByReference
public class BillerBarcodeBean implements BillerBarcodeBeanLocal, BillerBarcodeBeanRemote {

	private Logger log = Logger.getLogger("EJBBLLRBARC");
	private String page = "BillerBarcodeBean";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;

	public List<BillerBarcode> getBillerBarcodeAll(UserInfoBean user) throws Exception {
		List<BillerBarcode> list = null;
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|getBillerBarcodeAll|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_BARCODE ORDER BY BARC_ID ";

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

	public List<BillerBarcode> searchBillerBarcode(BillerBarcodeParam PARAM, UserInfoBean user) throws Exception {
		List<BillerBarcode> list = null;
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBillerBarcode|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBillerBarcode|Param|" + PARAM.toString());

//			String sql = "SELECT * FROM BILLER_BARCODE ";
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT  bb.*,bs.BLLR_SRVC_NAME_EN " + " FROM " + " BILLER_BARCODE " + " bb");

			sql.append(" LEFT JOIN BILLER_SERVICE bs ON bb.BLLR_SRVC_ID=bs.BLLR_SRVC_ID");
			sql.append(" WHERE 1=1");
			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(PARAM.getBLLR_SRVC_ID())) {
				v.add("bb.BLLR_SRVC_ID = " + PARAM.getBLLR_SRVC_ID());
			}
			if (!ValidateUtil.isEmpty(PARAM.getBARC_ID())) {
				v.add("bb.BARC_ID = " + PARAM.getBARC_ID());
			}
			if (!ValidateUtil.isEmpty(PARAM.getACT_FLAG())) {
				v.add("bb.ACT_FLAG = '" + PARAM.getACT_FLAG() + "'");
			}

			if (!v.isEmpty()) {
				sb.append(" AND (");
				for (int i = 0; i < v.size(); i++) {
					if (i != 0) {
						sb.append(" AND ");
					}
					sb.append(v.get(i));
				}
				sb.append(")");
			}
			sql.append(sb.toString());
			sql.append(" ORDER BY BARC_ID");

			log.info(user.getName() + "|" + page + "|searchBillerBarcode|SQL:" + sql.toString());

			Query query = em.createNativeQuery(sql.toString(), BillerBarcode.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchBillerBarcode|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchBillerBarcode|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|searchBillerBarcode|Exception:" + e.getMessage());
			throw e;
		}

		return list;
	}

	public BillerBarcode findBillerBarcode(int BARC_ID, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerBarcode|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_BARCODE WHERE BARC_ID = " + BARC_ID;
			log.info(user.getName() + "|" + page + "|findBillerBarcode|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerBarcode.class);
			List<BillerBarcode> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerBarcode|Time:" + timer.getStopTime());
			if ((list != null) && (list.size() > 0)) {
				return (BillerBarcode) list.get(0);
			} else {
				throw new Exception("Not Found Data");
			}

		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|findBillerBarcode|Exception:" + e.getMessage());
			throw e;
		}
	}

	public int insertBillerBarcode(BillerBarcode bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBillerBarcode|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBillerBarcode|Param|" + bean.toString());

			Query query = em.createNativeQuery("SELECT SEQ_BILLER_BARCODE.nextval from DUAL");
			BigDecimal result = (BigDecimal) query.getSingleResult();
			int BARC_ID = result.intValue();

			log.info(user.getName() + "|" + page + "|insertBillerBarcode|BARC_ID:" + BARC_ID);

			String sql = "INSERT INTO BILLER_BARCODE(BARC_ID, BARC_NAME, BARC_LINE_NO, BARC_CRRG_RETN_FLAG, BARC_LINE_MAX, BARC_PERF_FLAG, BARC_PERF_VALUE, BARC_EFFT_DATE, ACT_FLAG, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY, LAST_CHNG_DTTM,BARC_TYPE,BLLR_SRVC_ID,BARC_NEW_LINE_POST,BARC_SRVC_CODE,BARC_EXPR_DATE)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE,?,?,?,?,?)";
			int i = 0;
			query = em.createNativeQuery(sql);
			query.setParameter(++i, BARC_ID);
			query.setParameter(++i, bean.getBARC_NAME());
			query.setParameter(++i, bean.getBARC_LINE_NO());
			query.setParameter(++i, bean.getBARC_CRRG_RETN_FLAG());
			query.setParameter(++i, bean.getBARC_LINE_MAX());
			query.setParameter(++i, bean.getBARC_PERF_FLAG());
			query.setParameter(++i, bean.getBARC_PERF_VALUE());
			query.setParameter(++i, bean.getBARC_EFFT_DATE());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, bean.getBARC_TYPE());
			query.setParameter(++i, bean.getBLLR_SRVC_ID());
			query.setParameter(++i, bean.getBARC_NEW_LINE_POST());
			query.setParameter(++i, bean.getBARC_SRVC_CODE());
			query.setParameter(++i, bean.getBARC_EXPR_DATE());
			query.executeUpdate();

			log.info(user.getName() + "|" + page + "|insertBillerBarcode|Success");
			log.info(user.getName() + "|" + page + "|insertBillerBarcode|Time|" + timer.getStopTime());
			return BARC_ID;

		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|insertBillerBarcode|Exception:" + e.getMessage());
			throw e;
		}
	}

	public void updateBillerBarcode(BillerBarcode bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerBarcode|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerBarcode|Param|" + bean.toString());

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_BARCODE ").append("SET BARC_NAME = ? ").append(", BARC_LINE_NO = ? ").append(", BARC_CRRG_RETN_FLAG = ? ").append(", BARC_LINE_MAX = ? ").append(", BARC_PERF_FLAG = ? ").append(", BARC_PERF_VALUE = ? ").append(", BARC_EFFT_DATE = ? ").append(", ACT_FLAG = ? ").append(", LAST_CHNG_BY = ? ").append(", LAST_CHNG_DTTM = SYSDATE ")
			.append(", BARC_TYPE = ? ")
			.append(", BLLR_SRVC_ID = ? ")
			.append(", BARC_NEW_LINE_POST = ? ")
			.append(", BARC_SRVC_CODE = ? ")
			.append(", BARC_EXPR_DATE = ? ")
			.append("WHERE BARC_ID = ? ");

			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBARC_NAME());
			query.setParameter(++i, bean.getBARC_LINE_NO());
			query.setParameter(++i, bean.getBARC_CRRG_RETN_FLAG());
			query.setParameter(++i, bean.getBARC_LINE_MAX());
			query.setParameter(++i, bean.getBARC_PERF_FLAG());
			query.setParameter(++i, bean.getBARC_PERF_VALUE());
			query.setParameter(++i, bean.getBARC_EFFT_DATE());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBARC_TYPE());
			query.setParameter(++i, bean.getBLLR_SRVC_ID());
			query.setParameter(++i, bean.getBARC_NEW_LINE_POST());
			query.setParameter(++i, bean.getBARC_SRVC_CODE());
			query.setParameter(++i, bean.getBARC_EXPR_DATE());
			query.setParameter(++i, bean.getBARC_ID());
			query.executeUpdate();

			log.info(user.getName() + "|" + page + "|updateBillerBarcode|Success");
			log.info(user.getName() + "|" + page + "|updateBillerBarcode|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|updateBillerBarcode|Exception:" + e.getMessage());
			throw e;
		}
	}
}
