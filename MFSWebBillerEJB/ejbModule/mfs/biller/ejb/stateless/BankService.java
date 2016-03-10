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

import mfs.biller.ejb.interfaces.BankServiceLocal;
import mfs.biller.ejb.interfaces.BankServiceRemote;
import mfs.biller.persistence.bean.BankServiceDetail;
import mfs.biller.persistence.bean.BankServicebean;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.JpaResultUtil;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class BankMaster
 */
@Stateless(name = "BankService", mappedName = "mfs.biller.ejb.BankService")
@CallByReference
public class BankService implements BankServiceRemote, BankServiceLocal {

	private Logger log = Logger.getLogger("EJBBANKSERVICE");
	private String page = "BankService";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	
    public BankService() {
       
    }
    
    public List<BankServiceDetail> searchBankServiceAll(BankServicebean PARAM, UserInfoBean user)throws Exception{
    	List<BankServiceDetail> listDetail = new ArrayList<BankServiceDetail>();
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBankServiceAll|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBankServiceAll|Param|" + PARAM.toString());
			
			String sql = "SELECT T1.BANK_SRVC_ID,T1.BANK_SRVC_CODE,T1.BANK_CODE,T1.FDM_MTI_CODE,T1.BANK_SRVC_NAME ,T1.BANK_CUTF_TIME,T1.ACT_FLAG,T1.CRTD_BY,T1.CRTD_DTTM ,T1.LAST_CHNG_BY,T1.LAST_CHNG_DTTM  ,T2.BANK_NAME    "
						+ " FROM BANK_SERVICE T1 left join BANK_MASTER T2 ON T1.BANK_CODE = T2.BANK_CODE " ;
			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(PARAM.getBANK_SRVC_ID())){
				v.add(" BANK_SRVC_ID = "+PARAM.getBANK_SRVC_ID());
			}
			if (!ValidateUtil.isEmpty(PARAM.getBANK_CODE())){
				v.add(" T1.BANK_CODE = '"+PARAM.getBANK_CODE()+"' ");
			}
			if (!ValidateUtil.isEmpty(PARAM.getACT_FLAG())){
				v.add(" T1.ACT_FLAG = '"+PARAM.getACT_FLAG()+"' ");
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
			
			log.info(user.getName() + "|" + page + "|searchBankServiceAll|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			BankServiceDetail bean = null;
			log.info(user.getName() + "|" + page + "|searchBankServiceAll|List.Size:" + list.size());
			for (Object[] item : list){
				int x = 0;
				bean = new BankServiceDetail();
				bean.setBANK_SRVC_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBANK_SRVC_CODE(JpaResultUtil.getString(item, x++));
				bean.setBANK_CODE(JpaResultUtil.getString(item, x++));
				bean.setFDM_MTI_CODE(JpaResultUtil.getString(item, x++));
				bean.setBANK_SRVC_NAME(JpaResultUtil.getString(item, x++));
				bean.setBANK_CUTF_TIME(JpaResultUtil.getInteger(item, x++));
				bean.setACT_FLAG(JpaResultUtil.getString(item, x++));
				bean.setCRTD_BY(JpaResultUtil.getString(item, x++));
				bean.setCRTD_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setLAST_CHNG_BY(JpaResultUtil.getString(item, x++));
				bean.setLAST_CHNG_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setBANK_NAME(JpaResultUtil.getString(item, x++));
				
				listDetail.add(bean);
			}
			log.info(user.getName() + "|" + page + "|searchBankServiceAll|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|searchBankServiceAll|Exception:" + e.getMessage());
			throw e;
		}
		
		return listDetail;
	}
    public BankServicebean findBankService(int BANK_SRVC_ID, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBankService|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BANK_SERVICE WHERE BANK_SRVC_ID = '" + BANK_SRVC_ID+"'";
			log.info(user.getName() + "|" + page + "|findBankService|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BankServicebean.class);
			List<BankServicebean> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBankService|Time:" + timer.getStopTime());
			/*if ( (list != null) && (list.size() > 0)){
				return (BankMasterBean)list.get(0);
			}else{
				throw new Exception("Not Found Data");
			}	*/
			return (BankServicebean)list.get(0);
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|findBankService|Exception:" + e.getMessage());
			throw e;
		}
	}
    
   
	
	public int insertBankService(BankServicebean bean, UserInfoBean user)throws  Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBankService|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBankService|Param|" + bean.toString());
			
			Query query = em.createNativeQuery("SELECT SEQ_BANK_SERVICE.nextval from DUAL");
			BigDecimal result = (BigDecimal)query.getSingleResult();
			int BANK_SRVC_ID = result.intValue();
			
			String sql = "INSERT INTO BANK_SERVICE(BANK_SRVC_ID , BANK_SRVC_CODE , BANK_CODE , FDM_MTI_CODE , BANK_SRVC_NAME , BANK_CUTF_TIME , ACT_FLAG , CRTD_BY , CRTD_DTTM , LAST_CHNG_BY , LAST_CHNG_DTTM )"
					   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE)";
			int i = 0;
			Query query1 = em.createNativeQuery(sql);
			query1.setParameter(++i, BANK_SRVC_ID);
			query1.setParameter(++i, bean.getBANK_SRVC_CODE());
			query1.setParameter(++i, bean.getBANK_CODE());
			query1.setParameter(++i, bean.getFDM_MTI_CODE());
			query1.setParameter(++i, bean.getBANK_SRVC_NAME());
			query1.setParameter(++i, bean.getBANK_CUTF_TIME());
			query1.setParameter(++i, bean.getACT_FLAG());
			query1.setParameter(++i, user.getName());
			query1.setParameter(++i, user.getName());
			query1.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|insertBankService|Success");
			log.info(user.getName() + "|" + page + "|insertBankService|Time|" + timer.getStopTime());
		return BANK_SRVC_ID;
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertBankService|Exception:" + e.getMessage());
			throw e;
		}
	}
	public boolean isExistBankServiceCode(String BANK_CHNL_CODE, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistBankServiceCode|Time:" + timer.getStartTime());
			String sql = "SELECT count(*) FROM BANK_CHANNEL WHERE BANK_CHNL_CODE = " + BANK_CHNL_CODE + " ";
			log.info(user.getName() + "|" + page + "|isExistBankServiceCode|SQL:" + sql);
			
			boolean bResult = true;
			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal)query.getSingleResult();
			if (result.intValue() > 0){
				bResult = true;
			}else{
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistBankServiceCode|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistBankServiceCode|Time:" + timer.getStopTime());
			return bResult;
	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|isExistBankServiceCode|Exception:" + e.getMessage());
			throw e;
		}
	}
	public void updateBankService(BankServicebean bean, UserInfoBean user)throws  Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBankService|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBankService|Param|" + bean.toString());
			

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BANK_SERVICE ")
					.append("SET BANK_SRVC_CODE = ? ")
					.append(", BANK_CODE = ? ")
					.append(", FDM_MTI_CODE = ? ")
					.append(", BANK_SRVC_NAME = ? ")
					.append(", BANK_CUTF_TIME = ? ")
					.append(", ACT_FLAG = ? ")
					.append(", CRTD_BY = ? ")
					.append(", LAST_CHNG_BY = ? ")
					.append(", LAST_CHNG_DTTM = SYSDATE ")
					.append("WHERE BANK_SRVC_ID = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBANK_SRVC_CODE());
			query.setParameter(++i, bean.getBANK_CODE());
			query.setParameter(++i, bean.getFDM_MTI_CODE());
			query.setParameter(++i, bean.getBANK_SRVC_NAME());
			query.setParameter(++i, bean.getBANK_CUTF_TIME());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getCRTD_BY());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBANK_SRVC_ID());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateBankService|Success");
			log.info(user.getName() + "|" + page + "|updateBankService|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateBankService|Exception:" + e.getMessage());
			throw e;
		}
	}
}
