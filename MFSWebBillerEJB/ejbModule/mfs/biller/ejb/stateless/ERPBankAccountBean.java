package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.ERPBankAccountBeanLocal;
import mfs.biller.ejb.interfaces.ERPBankAccountBeanRemote;
import mfs.biller.persistence.bean.ERPBankAccount;
import mfs.biller.persistence.bean.ERPBankAccountDtail;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.JpaResultUtil;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class BankMaster
 */
@Stateless(name = "ERPBankAccountBean", mappedName = "mfs.biller.ejb.ERPBankAccountBean")
@CallByReference
public class ERPBankAccountBean implements ERPBankAccountBeanLocal, ERPBankAccountBeanRemote {

	private Logger log = Logger.getLogger("EJBERPBankAccount");
	private String page = "ERPBankAccountBean";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;

	public ERPBankAccountBean() {
		
	}

	public ERPBankAccount findERPBankAccount(Integer BLLR_SRVC_FIN_ID, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findERPBankAccount|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_SERVICE_ACCT WHERE BLLR_SRVC_FIN_ID = " + BLLR_SRVC_FIN_ID;
			log.info(user.getName() + "|" + page + "|findERPBankAccount|SQL:" + sql);

			Query query = em.createNativeQuery(sql, ERPBankAccount.class);
			List<ERPBankAccount> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findERPBankAccount|Time:" + timer.getStopTime());
		
			return (ERPBankAccount) list.get(0);
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|findERPBankAccount|Exception:" + e.getMessage());
			throw e;
		}
	}

	

	public int insertERPBankAccount(ERPBankAccount bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertERPBankAccount|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertERPBankAccount|Param|" + bean.toString());

			Query query = em.createNativeQuery("SELECT SEQ_BILLER_SERVICE_ACCT.nextval from DUAL");
			BigDecimal result = (BigDecimal) query.getSingleResult();
			int BLLR_SRVC_FIN_ID = result.intValue();

			String sql = "INSERT INTO BILLER_SERVICE_ACCT (BLLR_SRVC_FIN_ID,BLLR_SRVC_ID,BLLR_ACCT_NO,BLLR_ACCT_NAME,"
					+ "BLLR_ACCT_BRAC_NAME,BLLR_ACCT_SEQ,BLLR_ACCT_PROD_TYPE,BLLR_ACCT_STRT_DTTM,BLLR_ACCT_STOP_DTTM,"
					+ "ACT_FLAG,CRTD_BY,CRTD_DTTM,LAST_CHNG_BY,LAST_CHNG_DTTM,BLLR_ACCT_BANK_CODE,BLLR_ERP_SUP_CODE,"
					+ "BLLR_ERP_SUP_SITE_CODE,BLLR_ERP_LINE_SUP_NUMB,BLLR_ERP_LINE_SUP_SITE_CODE,BLLR_ERP_CUST_CODE," 
					+ "BLLR_ERP_CUST_BILL_TO_CODE,BLLR_ERP_CUST_SHIP_TO_CODE)"
					+ " VALUES(?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE,?,?,?,?,?,?,?,?)";
			int i = 0;
			Query query1 = em.createNativeQuery(sql);
			query1.setParameter(++i, BLLR_SRVC_FIN_ID);
			query1.setParameter(++i, bean.getBLLR_SRVC_ID());
			query1.setParameter(++i, bean.getBLLR_ACCT_NO());
			query1.setParameter(++i, bean.getBLLR_ACCT_NAME());
			query1.setParameter(++i, bean.getBLLR_ACCT_BRAC_NAME());
			query1.setParameter(++i, bean.getBLLR_ACCT_SEQ());
			query1.setParameter(++i, bean.getBLLR_ACCT_PROD_TYPE());
			query1.setParameter(++i, bean.getBLLR_ACCT_STRT_DTTM());
			query1.setParameter(++i, bean.getBLLR_ACCT_STOP_DTTM());
			query1.setParameter(++i, bean.getACT_FLAG());
			query1.setParameter(++i, user.getName());
			query1.setParameter(++i, user.getName());
			query1.setParameter(++i, bean.getBLLR_ACCT_BANK_CODE());
			query1.setParameter(++i, bean.getBLLR_ERP_SUP_CODE());
			query1.setParameter(++i, bean.getBLLR_ERP_SUP_SITE_CODE());
			query1.setParameter(++i, bean.getBLLR_ERP_LINE_SUP_NUMB());
			query1.setParameter(++i, bean.getBLLR_ERP_LINE_SUP_SITE_CODE());
			query1.setParameter(++i, bean.getBLLR_ERP_CUST_CODE());
			query1.setParameter(++i, bean.getBLLR_ERP_CUST_BILL_TO_CODE());
			query1.setParameter(++i, bean.getBLLR_ERP_CUST_SHIP_TO_CODE());
			query1.executeUpdate();

			log.info(user.getName() + "|" + page + "|insertERPBankAccount|Success");
			log.info(user.getName() + "|" + page + "|insertERPBankAccount|Time|" + timer.getStopTime());

			return BLLR_SRVC_FIN_ID;
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|insertERPBankAccount|Exception:" + e.getMessage());
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

	public void updateERPBankAccount(ERPBankAccount bean, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateERPBankAccount|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateERPBankAccount|Param|" + bean.toString());

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_SERVICE_ACCT ")
			.append("SET BLLR_SRVC_ID = ? ")
			.append(", BLLR_ACCT_NO = ? ")
			.append(", BLLR_ACCT_NAME = ? ")
			.append(", BLLR_ACCT_BRAC_NAME = ? ")
			.append(", BLLR_ACCT_SEQ = ? ")
			.append(", BLLR_ACCT_PROD_TYPE = ? ")
			.append(", BLLR_ACCT_STRT_DTTM = ? ")
			.append(", BLLR_ACCT_STOP_DTTM = ? ")
			.append(", ACT_FLAG = ? ")
			.append(", LAST_CHNG_BY = ? ")
			.append(", LAST_CHNG_DTTM = SYSDATE ")
			.append(", BLLR_ACCT_BANK_CODE = ? ")
			.append(", BLLR_ERP_SUP_CODE = ? ")
			.append(", BLLR_ERP_SUP_SITE_CODE = ? ")
			.append(", BLLR_ERP_LINE_SUP_NUMB = ? ")
			.append(", BLLR_ERP_LINE_SUP_SITE_CODE = ? ")
			.append(", BLLR_ERP_CUST_CODE = ? ")
			.append(", BLLR_ERP_CUST_BILL_TO_CODE = ? ")
			.append(", BLLR_ERP_CUST_SHIP_TO_CODE = ? ")
			.append("WHERE BLLR_SRVC_FIN_ID = ? ");

			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBLLR_SRVC_ID());
			query.setParameter(++i, bean.getBLLR_ACCT_NO());
			query.setParameter(++i, bean.getBLLR_ACCT_NAME());
			query.setParameter(++i, bean.getBLLR_ACCT_BRAC_NAME());
			query.setParameter(++i, bean.getBLLR_ACCT_SEQ());
			query.setParameter(++i, bean.getBLLR_ACCT_PROD_TYPE());
			query.setParameter(++i, bean.getBLLR_ACCT_STRT_DTTM());
			query.setParameter(++i, bean.getBLLR_ACCT_STOP_DTTM());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBLLR_ACCT_BANK_CODE());
			query.setParameter(++i, bean.getBLLR_ERP_SUP_CODE());
			query.setParameter(++i, bean.getBLLR_ERP_SUP_SITE_CODE());
			query.setParameter(++i, bean.getBLLR_ERP_LINE_SUP_NUMB());
			query.setParameter(++i, bean.getBLLR_ERP_LINE_SUP_SITE_CODE());
			query.setParameter(++i, bean.getBLLR_ERP_CUST_CODE());
			query.setParameter(++i, bean.getBLLR_ERP_CUST_BILL_TO_CODE());
			query.setParameter(++i, bean.getBLLR_ERP_CUST_SHIP_TO_CODE());
			query.setParameter(++i, bean.getBLLR_SRVC_FIN_ID());
			query.executeUpdate();


			log.info(user.getName() + "|" + page + "|updateERPBankAccount|Success");
			log.info(user.getName() + "|" + page + "|updateERPBankAccount|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|updateERPBankAccount|Exception:" + e.getMessage());
			throw e;
		}

	}

	public List<ERPBankAccountDtail> searchERPBankAccount(Integer BLLR_SRVC_ID,String BLLR_ACCT_BANK_CODE,String ACT_FLAG, UserInfoBean user) throws Exception {
		List<ERPBankAccountDtail> listDetail = new ArrayList<ERPBankAccountDtail>();
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchERPBankAccount|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchERPBankAccount|BLLR_SRVC_ID|" + BLLR_SRVC_ID);

			String sql = "  SELECT T1.BLLR_SRVC_FIN_ID,T1.BLLR_SRVC_ID,T1.BLLR_ACCT_NO,T1.BLLR_ACCT_NAME,T1.BLLR_ACCT_BRAC_NAME,T1.BLLR_ACCT_SEQ,T1.BLLR_ACCT_PROD_TYPE,T1.BLLR_ACCT_STRT_DTTM,T1.BLLR_ACCT_STOP_DTTM,"
					+ "T1.ACT_FLAG,T1.CRTD_BY,T1.CRTD_DTTM,T1.LAST_CHNG_BY,T1.LAST_CHNG_DTTM,T1.BLLR_ACCT_BANK_CODE,T1.BLLR_ERP_SUP_CODE,T1.BLLR_ERP_SUP_SITE_CODE,T1.BLLR_ERP_LINE_SUP_NUMB,T1.BLLR_ERP_LINE_SUP_SITE_CODE,T3.BLLR_SRVC_NAME_EN,T2.BANK_NAME,T3.BLLR_SRVC_CODE  "
					+ " FROM  BILLER_SERVICE_ACCT T1 LEFT JOIN  BANK_MASTER T2 ON  T1.BLLR_ACCT_BANK_CODE = T2.BANK_CODE"
					+ " LEFT JOIN  BILLER_SERVICE T3 ON T3.BLLR_SRVC_ID =T1.BLLR_SRVC_ID   ";

			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(BLLR_SRVC_ID)) {
				v.add(" T1.BLLR_SRVC_ID = " + BLLR_SRVC_ID);
			}
			if (!ValidateUtil.isEmpty(BLLR_ACCT_BANK_CODE)) {
				v.add(" T1.BLLR_ACCT_BANK_CODE = " + BLLR_ACCT_BANK_CODE);
			}
			if (!ValidateUtil.isEmpty(ACT_FLAG)) {
				v.add(" T1.ACT_FLAG = '" + ACT_FLAG+"'");
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
			sql += " order by T3.BLLR_SRVC_CODE ,T1.BLLR_ACCT_SEQ";

			log.info(user.getName() + "|" + page + "|searchERPBankAccount|SQL:" + sql);

			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			ERPBankAccountDtail bean = null;
			for (Object[] item : list) {
				int x = 0;
				bean = new ERPBankAccountDtail();
				bean.setBLLR_SRVC_FIN_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBLLR_SRVC_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBLLR_ACCT_NO(JpaResultUtil.getString(item, x++));
				bean.setBLLR_ACCT_NAME(JpaResultUtil.getString(item, x++));
				bean.setBLLR_ACCT_BRAC_NAME(JpaResultUtil.getString(item, x++));
				bean.setBLLR_ACCT_SEQ(JpaResultUtil.getInteger(item, x++));
				bean.setBLLR_ACCT_PROD_TYPE(JpaResultUtil.getString(item, x++));
				bean.setBLLR_ACCT_STRT_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setBLLR_ACCT_STOP_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setACT_FLAG(JpaResultUtil.getString(item, x++));
				bean.setCRTD_BY(JpaResultUtil.getString(item, x++));
				bean.setCRTD_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setLAST_CHNG_BY(JpaResultUtil.getString(item, x++));
				bean.setLAST_CHNG_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setBLLR_ACCT_BANK_CODE(JpaResultUtil.getString(item, x++));
				bean.setBLLR_ERP_SUP_CODE(JpaResultUtil.getString(item, x++));
				bean.setBLLR_ERP_SUP_SITE_CODE(JpaResultUtil.getString(item, x++));
				bean.setBLLR_ERP_LINE_SUP_NUMB(JpaResultUtil.getString(item, x++));
				bean.setBLLR_ERP_LINE_SUP_SITE_CODE(JpaResultUtil.getString(item, x++));
				bean.setBLLR_SRVC_NAME_EN(JpaResultUtil.getString(item, x++));
				bean.setBANK_NAME(JpaResultUtil.getString(item, x++));
				bean.setBLLR_SRVC_CODE(JpaResultUtil.getString(item, x++));
				listDetail.add(bean);
			}
			log.info(user.getName() + "|" + page + "|searchERPBankAccount|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchERPBankAccount|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|searchERPBankAccount|Exception:" + e.getMessage());
			throw e;
		}

		return listDetail;
	}


}
