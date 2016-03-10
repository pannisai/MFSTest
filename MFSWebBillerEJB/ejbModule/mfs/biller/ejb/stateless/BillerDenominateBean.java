package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.BillerDenominateBeanLocal;
import mfs.biller.ejb.interfaces.BillerDenominateBeanRemote;
import mfs.biller.persistence.bean.BillerDenominate;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class BankMaster
 */
@Stateless(name = "BillerDenominateBean", mappedName = "mfs.biller.ejb.BillerDenominateBean")
@CallByReference
public class BillerDenominateBean implements BillerDenominateBeanLocal, BillerDenominateBeanRemote {

	private Logger log = Logger.getLogger("EJBBILLERDENOMINATE");
	private String page = "BillerDenominate";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;

	public BillerDenominateBean() {
		
	}

	public BillerDenominate findBillerDenominate(Integer BLLR_DENM_ID, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerDenominate|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_DENOMINATE WHERE BLLR_DENM_ID = " + BLLR_DENM_ID;
			log.info(user.getName() + "|" + page + "|findBillerDenominate|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerDenominate.class);
			List<BillerDenominate> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerDenominate|Time:" + timer.getStopTime());
			/*
			 * if ( (list != null) && (list.size() > 0)){ return
			 * (BankMasterBean)list.get(0); }else{ throw new
			 * Exception("Not Found Data"); }
			 */
			return (BillerDenominate) list.get(0);
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|findBillerDenominate|Exception:" + e.getMessage());
			throw e;
		}
	}

	

	public int insertBillerDenominate(BillerDenominate bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBillerDenominate|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBillerDenominate|Param|" + bean.toString());

			Query query = em.createNativeQuery("SELECT SEQ_BILLER_DENOMINATE.nextval from DUAL");
			BigDecimal result = (BigDecimal) query.getSingleResult();
			int BLLR_DENM_ID = result.intValue();

			String sql = "INSERT INTO BILLER_DENOMINATE(BLLR_DENM_ID , BLLR_SRVC_ID , BLLR_DENM_RATE , BLLR_MAX_NO_MNTH , CRTD_BY , CRTD_DTTM , LAST_CHNG_BY , LAST_CHNG_DTTM, ACT_FLAG)" + "VALUES(?, ?, ?,  ?, ?, SYSDATE, ?, SYSDATE,?)";
			int i = 0;
			Query query1 = em.createNativeQuery(sql);
			query1.setParameter(++i, BLLR_DENM_ID);
			query1.setParameter(++i, bean.getBLLR_SRVC_ID());
			query1.setParameter(++i, bean.getBLLR_DENM_RATE());
			query1.setParameter(++i, bean.getBLLR_MAX_NO_MNTH());
			query1.setParameter(++i, user.getName());
			query1.setParameter(++i, user.getName());
			query1.setParameter(++i, bean.getACT_FLAG());
			query1.executeUpdate();

			BillerServiceBean BillerServiceBean = new BillerServiceBean();
			BillerServiceBean.updateBillerServiceCenter(bean.getBLLR_SRVC_ID(), user,em);
			log.info(user.getName() + "|" + page + "|insertBillerDenominate|Success");
			log.info(user.getName() + "|" + page + "|insertBillerDenominate|Time|" + timer.getStopTime());

			return BLLR_DENM_ID;
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|insertBillerDenominate|Exception:" + e.getMessage());
			throw e;
		}
	}

	public boolean isExistBankChannelCode(String BANK_CHNL_CODE, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistBankChannelCode|Time:" + timer.getStartTime());
			String sql = "SELECT count(*) FROM BANK_CHANNEL WHERE BANK_CHNL_CODE = " + BANK_CHNL_CODE + " ";
			log.info(user.getName() + "|" + page + "|isExistBankChannelCode|SQL:" + sql);

			boolean bResult = true;
			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal) query.getSingleResult();
			if (result.intValue() > 0) {
				bResult = true;
			} else {
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistBankChannelCode|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistBankChannelCode|Time:" + timer.getStopTime());
			return bResult;

		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|isExistBankChannelCode|Exception:" + e.getMessage());
			throw e;
		}
	}

	public void updateBillerDenominate(BillerDenominate bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerDenominate|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerDenominate|Param|" + bean.toString());

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_DENOMINATE ").append("SET BLLR_DENM_RATE = ? ").append(", LAST_CHNG_BY = ? ").append(", LAST_CHNG_DTTM = SYSDATE ").
			append(", ACT_FLAG = ? ").
			append("WHERE BLLR_DENM_ID = ? ");

			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBLLR_DENM_RATE());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getBLLR_DENM_ID());
			query.executeUpdate();

			Query query1 = em.createNativeQuery("SELECT BLLR_SRVC_ID " + " FROM  BILLER_DENOMINATE  " + " WHERE  BLLR_DENM_ID = " + bean.getBLLR_DENM_ID());

			BigDecimal result = (BigDecimal) query1.getSingleResult();
			int BLLR_SRVC_ID = result.intValue();

			BillerServiceBean BillerServiceBean = new BillerServiceBean();
			BillerServiceBean.updateBillerServiceCenter(BLLR_SRVC_ID, user,em);
			log.info(user.getName() + "|" + page + "|updateBillerDenominate|Success");
			log.info(user.getName() + "|" + page + "|updateBillerDenominate|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|updateBillerDenominate|Exception:" + e.getMessage());
			throw e;
		}

	}

	public List<BillerDenominate> searchBillerDenominateAll(Integer BLLR_SRVC_ID, UserInfoBean user) throws Exception {
		List<BillerDenominate> list = null;
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBillerDenominateAll|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBillerDenominateAll|BLLR_SRVC_ID|" + BLLR_SRVC_ID);

			String sql = "SELECT * FROM BILLER_DENOMINATE      ";

			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(BLLR_SRVC_ID)) {
				v.add(" BLLR_SRVC_ID = " + BLLR_SRVC_ID);
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
			sql += " order by BLLR_DENM_ID";

			log.info(user.getName() + "|" + page + "|searchBillerDenominateAll|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerDenominate.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchBillerDenominateAll|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchBillerDenominateAll|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|searchBillerDenominateAll|Exception:" + e.getMessage());
			throw e;
		}

		return list;
	}


}
