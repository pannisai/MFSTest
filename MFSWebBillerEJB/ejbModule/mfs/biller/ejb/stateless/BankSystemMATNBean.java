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

import mfs.biller.ejb.interfaces.BankSystemMATNBeanLocal;
import mfs.biller.ejb.interfaces.BankSystemMATNBeanRemote;
import mfs.biller.persistence.bean.BankSystemMATN;
import mfs.biller.persistence.bean.BankSystemMATNDetail;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.JpaResultUtil;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class BankMaster
 */
@Stateless(name = "BankSystemMATNBean", mappedName = "mfs.biller.ejb.BankSystemMATNBean")
@CallByReference
public class BankSystemMATNBean implements BankSystemMATNBeanLocal, BankSystemMATNBeanRemote {

	private Logger log = Logger.getLogger("EJBBankSystemMATN");
	private String page = "BankSystemMATNBean";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	
    public BankSystemMATNBean() {
       
    }
    
    public List<BankSystemMATNDetail> searchBankSystemMATNAll(BankSystemMATNDetail PARAM, UserInfoBean user)throws Exception{
    	List<BankSystemMATNDetail> listDetail = new ArrayList<BankSystemMATNDetail>();
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBankSystemMATNAll|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBankSystemMATNAll|Param|" + PARAM.toString());
			
			String sql = " SELECT T1.BANK_SRVC_ID , T2.BANK_SRVC_NAME ,T1.BANK_SYSM_MATN_ID ,T1.BANK_SYSM_MATN_NAME ,T1.BANK_STAR_TIME ,T1.BANK_END_TIME ,T1.ACT_FLAG ,T1.LAST_CHNG_BY ,T1.LAST_CHNG_DTTM    "
						+ " FROM  BANK_SYSTEM_MAINTENANCE T1 LEFT JOIN  BANK_SERVICE T2 ON T1.BANK_SRVC_ID = T2.BANK_SRVC_ID " ;
			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(PARAM.getBANK_SRVC_ID())){
				v.add(" T1.BANK_SRVC_ID = '"+PARAM.getBANK_SRVC_ID()+"'");
			}
			if (!ValidateUtil.isEmpty(PARAM.getACT_FLAG())){
				v.add(" T1.ACT_FLAG = '"+PARAM.getACT_FLAG()+"'");
			}
		
			if (!v.isEmpty()) {
				sb.append(" WHERE (");
				for (int i = 0; i < v.size(); i++){
					if (i != 0) {
						sb.append(" AND ");
					}
					sb.append(v.get(i));
				}
				sb.append(")");
			}
			sql += sb.toString();
			sql += " order by T1.BANK_SRVC_ID";
			
			log.info(user.getName() + "|" + page + "|searchBankSystemMATNAll|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			BankSystemMATNDetail bean = null;
			log.info(user.getName() + "|" + page + "|searchBankSystemMATNAll|List.Size:" + list.size());
			for (Object[] item : list){
				int x = 0;
				bean = new BankSystemMATNDetail();
				bean.setBANK_SRVC_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBANK_SRVC_NAME(JpaResultUtil.getString(item, x++));
				bean.setBANK_SYSM_MATN_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBANK_SYSM_MATN_NAME(JpaResultUtil.getString(item, x++));
				bean.setBANK_STAR_TIME(JpaResultUtil.getInteger(item, x++));
				bean.setBANK_END_TIME(JpaResultUtil.getInteger(item, x++));
				bean.setACT_FLAG(JpaResultUtil.getString(item, x++));
				bean.setLAST_CHNG_BY(JpaResultUtil.getString(item, x++));
				bean.setLAST_CHNG_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				
				listDetail.add(bean);
			}
			log.info(user.getName() + "|" + page + "|searchBankSystemMATNAll|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|searchBankSystemMATNAll|Exception:" + e.getMessage());
			throw e;
		}
		
		return listDetail;
	}
    public BankSystemMATN findBankSystemMATN(int BANK_SYSM_MATN_ID, UserInfoBean user)throws Exception{
    	BankSystemMATN bean = new BankSystemMATN();
    	try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBankSystemMATN|Time:" + timer.getStartTime());
			String sql = " SELECT T1.BANK_SRVC_ID ,T1.BANK_SYSM_MATN_ID ,T1.BANK_SYSM_MATN_NAME ,T1.BANK_STAR_TIME ,T1.BANK_END_TIME ,T1.ACT_FLAG ,T1.LAST_CHNG_BY ,T1.LAST_CHNG_DTTM    "
					+ " FROM  BANK_SYSTEM_MAINTENANCE T1 LEFT JOIN  BANK_SERVICE T2 ON T1.BANK_SRVC_ID = T2.BANK_SRVC_ID " ;
		Vector<String> v = new Vector<String>();
		StringBuffer sb = new StringBuffer();
		if (!ValidateUtil.isEmpty(BANK_SYSM_MATN_ID)){
			v.add(" T1.BANK_SYSM_MATN_ID = "+BANK_SYSM_MATN_ID);
		}
	
		if (!v.isEmpty()) {
			sb.append(" WHERE (");
			for (int i = 0; i < v.size(); i++){
				if (i != 0) {
					sb.append(" AND ");
				}
				sb.append(v.get(i));
			}
			sb.append(")");
		}
		sql += sb.toString();
		sql += " order by T1.BANK_SRVC_ID";
		
		log.info(user.getName() + "|" + page + "|findBankSystemMATN|SQL:" + sql);
		
		Query query = em.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		
		log.info(user.getName() + "|" + page + "|findBankSystemMATN|List.Size:" + list.size());
		for (Object[] item : list){
			int x = 0;
			bean = new BankSystemMATN();
			bean.setBANK_SRVC_ID(JpaResultUtil.getInteger(item, x++));
			//bean.setBANK_SRVC_NAME(JpaResultUtil.getString(item, x++));
			bean.setBANK_SYSM_MATN_ID(JpaResultUtil.getInteger(item, x++));
			bean.setBANK_SYSM_MATN_NAME(JpaResultUtil.getString(item, x++));
			bean.setBANK_STAR_TIME(JpaResultUtil.getInteger(item, x++));
			bean.setBANK_END_TIME(JpaResultUtil.getInteger(item, x++));
			bean.setACT_FLAG(JpaResultUtil.getString(item, x++));
			bean.setLAST_CHNG_BY(JpaResultUtil.getString(item, x++));
			bean.setLAST_CHNG_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
			
		}
		
		log.info(user.getName() + "|" + page + "|findBankSystemMATN|Time|" + timer.getStopTime());
	}catch(Exception e){
		log.error(user.getName() + "|" + page + "|findBankSystemMATN|Exception:" + e.getMessage());
		throw e;
	}
		return bean;
	
		
	}
    
   
	
	public int insertBankSystemMATN(BankSystemMATN bean, UserInfoBean user)throws  Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBankSystemMATN|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBankSystemMATN|Param|" + bean.toString());
			
			Query query = em.createNativeQuery("SELECT SEQ_BANK_SYSTEM_MAINTENANCE.nextval from DUAL");
			BigDecimal result = (BigDecimal)query.getSingleResult();
			int BANK_SYSM_MATN_ID = result.intValue();
			
			String sql = "INSERT INTO BANK_SYSTEM_MAINTENANCE(BANK_SYSM_MATN_ID , BANK_SRVC_ID , BANK_STAR_TIME , BANK_END_TIME , BANK_SYSM_MATN_NAME ,  ACT_FLAG , CRTD_BY , CRTD_DTTM , LAST_CHNG_BY , LAST_CHNG_DTTM )"
					   + "VALUES(?, ?, ?, ?, ?, ?, ?,  SYSDATE, ?, SYSDATE)";
			int i = 0;
			Query query1 = em.createNativeQuery(sql);
			query1.setParameter(++i, BANK_SYSM_MATN_ID);
			query1.setParameter(++i, bean.getBANK_SRVC_ID());
			query1.setParameter(++i, bean.getBANK_STAR_TIME());
			query1.setParameter(++i, bean.getBANK_END_TIME());
			query1.setParameter(++i, bean.getBANK_SYSM_MATN_NAME());
			query1.setParameter(++i, bean.getACT_FLAG());
			query1.setParameter(++i, user.getName());
			query1.setParameter(++i, user.getName());
			query1.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|insertBankSystemMATN|Success");
			log.info(user.getName() + "|" + page + "|insertBankSystemMATN|Time|" + timer.getStopTime());
		return BANK_SYSM_MATN_ID;
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertBankSystemMATN|Exception:" + e.getMessage());
			throw e;
		}
	}
	public boolean isExistBankServiceCode(String BANK_CHNL_CODE, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistBllrMstrId|Time:" + timer.getStartTime());
			String sql = "SELECT count(*) FROM BANK_CHANNEL WHERE BANK_CHNL_CODE = " + BANK_CHNL_CODE + " ";
			log.info(user.getName() + "|" + page + "|isExistBllrMstrId|SQL:" + sql);
			
			boolean bResult = true;
			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal)query.getSingleResult();
			if (result.intValue() > 0){
				bResult = true;
			}else{
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistBllrMstrId|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistBllrMstrId|Time:" + timer.getStopTime());
			return bResult;
	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|isExistBllrMstrId|Exception:" + e.getMessage());
			throw e;
		}
	}
	public void updateBankSystemMATN(BankSystemMATN bean, UserInfoBean user)throws  Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBankSystemMATN|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBankSystemMATN|Param|" + bean.toString());
			

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BANK_SYSTEM_MAINTENANCE ")
					.append("SET BANK_SRVC_ID = ? ")
					.append(", BANK_STAR_TIME = ? ")
					.append(", BANK_END_TIME = ? ")
					.append(", BANK_SYSM_MATN_NAME = ? ")
					.append(", ACT_FLAG = ? ")
					.append(", CRTD_BY = ? ")
					.append(", LAST_CHNG_BY = ? ")
					.append(", LAST_CHNG_DTTM = SYSDATE ")
					.append("WHERE BANK_SYSM_MATN_ID = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBANK_SRVC_ID());
			query.setParameter(++i, bean.getBANK_STAR_TIME());
			query.setParameter(++i, bean.getBANK_END_TIME());
			query.setParameter(++i, bean.getBANK_SYSM_MATN_NAME());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getCRTD_BY());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBANK_SYSM_MATN_ID());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateBankSystemMATN|Success");
			log.info(user.getName() + "|" + page + "|updateBankSystemMATN|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateBankSystemMATN|Exception:" + e.getMessage());
			throw e;
		}
	}
}
