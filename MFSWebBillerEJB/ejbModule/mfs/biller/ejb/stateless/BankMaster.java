package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.BankMasterLocal;
import mfs.biller.ejb.interfaces.BankMasterRemote;
import mfs.biller.persistence.bean.BankMasterBean;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;
import mfs.exception.IsExistException;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class BankMaster
 */
@Stateless(name = "BankMaster", mappedName = "mfs.biller.ejb.BankMaster")
@CallByReference
public class BankMaster implements BankMasterRemote, BankMasterLocal {

	private Logger log = Logger.getLogger("EJBBankMaster");
	private String page = "BankMaster";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	
    public BankMaster() {
       
    }
    
    public List<BankMasterBean> searchBankMasterAll(BankMasterBean PARAM, UserInfoBean user)throws Exception{
		List<BankMasterBean> list = null;
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBankMasterAll|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBankMasterAll|Param|" + PARAM.toString());
			
			String sql = "SELECT * FROM BANK_MASTER      ";

			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(PARAM.getBANK_CODE())){
				v.add(" BANK_CODE = '"+PARAM.getBANK_CODE()+"'");
			}
			if (!ValidateUtil.isEmpty(PARAM.getACT_FLAG())){
				v.add(" ACT_FLAG = '"+PARAM.getACT_FLAG()+"'");
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
			sql += " order by BANK_CODE";
			
			log.info(user.getName() + "|" + page + "|searchBankMasterAll|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BankMasterBean.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchBankMasterAll|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchBankMasterAll|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|searchBankMasterAll|Exception:" + e.getMessage());
			throw e;
		}
		
		return list;
	}
    public BankMasterBean findBankMaster(String BANK_CODE, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBankMaster|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BANK_MASTER WHERE BANK_CODE = " + BANK_CODE;
			log.info(user.getName() + "|" + page + "|findBankMaster|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BankMasterBean.class);
			List<BankMasterBean> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBankMaster|Time:" + timer.getStopTime());
			/*if ( (list != null) && (list.size() > 0)){
				return (BankMasterBean)list.get(0);
			}else{
				throw new Exception("Not Found Data");
			}	*/
			return (BankMasterBean)list.get(0);
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|findBankMaster|Exception:" + e.getMessage());
			throw e;
		}
	}
    
   
	
	public void insertBankMaster(BankMasterBean bean, UserInfoBean user)throws IsExistException,  Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBankMaster|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBankMaster|Param|" + bean.toString());
			

			if (isExistBankMstrCode(bean.getBANK_CODE(), user)){
				throw new IsExistException("BANK_CODE");
			}

			String sql = "INSERT INTO BANK_MASTER(BANK_CODE , BANK_NAME , BANK_FULL_NAME_TH , BANK_FULL_NAME_EN , ACT_FLAG , CRTD_BY , CRTD_DTTM , LAST_CHNG_BY , LAST_CHNG_DTTM )"
					   + "VALUES(?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE)";
			int i = 0;
			Query query1 = em.createNativeQuery(sql);
			query1.setParameter(++i, bean.getBANK_CODE());
			query1.setParameter(++i, bean.getBANK_NAME());
			query1.setParameter(++i, bean.getBANK_FULL_NAME_TH());
			query1.setParameter(++i, bean.getBANK_FULL_NAME_EN());
			query1.setParameter(++i, bean.getACT_FLAG());
			query1.setParameter(++i, user.getName());
			query1.setParameter(++i, user.getName());
			query1.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|insertBankMaster|Success");
			log.info(user.getName() + "|" + page + "|insertBankMaster|Time|" + timer.getStopTime());
		}catch(IsExistException e){
			log.error(user.getName() + "|" + page + "|insertBankMaster|IsExistException:" + e.getMessage());
			throw e;
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertBankMaster|Exception:" + e.getMessage());
			throw e;
		}
	}
	public boolean isExistBankMstrCode(String BANK_CODE, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistBankMstrCode|Time:" + timer.getStartTime());
			String sql = "SELECT count(*) FROM BANK_MASTER WHERE BANK_CODE = " + BANK_CODE + " ";
			log.info(user.getName() + "|" + page + "|isExistBankMstrCode|SQL:" + sql);
			
			boolean bResult = true;
			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal)query.getSingleResult();
			if (result.intValue() > 0){
				bResult = true;
			}else{
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistBankMstrCode|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistBankMstrCode|Time:" + timer.getStopTime());
			return bResult;
	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|isExistBankMstrCode|Exception:" + e.getMessage());
			throw e;
		}
	}
	public void updateBankMaster(BankMasterBean bean, UserInfoBean user)throws  Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBankMaster|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBankMaster|Param|" + bean.toString());
			

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BANK_MASTER ")
					.append("SET BANK_NAME = ? ")
					.append(", BANK_FULL_NAME_TH = ? ")
					.append(", BANK_FULL_NAME_EN = ? ")
					.append(", ACT_FLAG = ? ")
					.append(", CRTD_BY = ? ")
					.append(", LAST_CHNG_BY = ? ")
					.append(", LAST_CHNG_DTTM = SYSDATE ")
					.append("WHERE BANK_CODE = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBANK_NAME());
			query.setParameter(++i, bean.getBANK_FULL_NAME_TH());
			query.setParameter(++i, bean.getBANK_FULL_NAME_EN());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getCRTD_BY());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBANK_CODE());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateBankMaster|Success");
			log.info(user.getName() + "|" + page + "|updateBankMaster|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateBankMaster|Exception:" + e.getMessage());
			throw e;
		}
	}
}
