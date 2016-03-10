package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.BankChannelLocal;
import mfs.biller.ejb.interfaces.BankChannelRemote;
import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;
import mfs.exception.IsExistException;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class BankMaster
 */
@Stateless(name = "BankChannel", mappedName = "mfs.biller.ejb.BankChannel")
@CallByReference
public class BankChannel implements BankChannelRemote, BankChannelLocal {

	private Logger log = Logger.getLogger("EJBBankChannel");
	private String page = "BankChannel";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	
    public BankChannel() {
       
    }
    
    public List<BankChannelBean> searchBankChannelAll(BankChannelBean PARAM, UserInfoBean user)throws Exception{
		List<BankChannelBean> list = null;
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBankChannelAll|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBankChannelAll|Param|" + PARAM.toString());
			
			String sql = "SELECT * FROM BANK_CHANNEL     ";

			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(PARAM.getBANK_CHNL_CODE())){
				v.add(" BANK_CHNL_CODE = '"+PARAM.getBANK_CHNL_CODE()+"'");
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
			sql += " order by BANK_CHNL_CODE";
			
			log.info(user.getName() + "|" + page + "|searchBankChannelAll|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BankChannelBean.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchBankChannelAll|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchBankChannelAll|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|searchBankChannelAll|Exception:" + e.getMessage());
			throw e;
		}
		
		return list;
	}
    public BankChannelBean findBankChannel(String BANK_CHNL_CODE, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBankChannel|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BANK_CHANNEL WHERE BANK_CHNL_CODE = '"+BANK_CHNL_CODE+"'";
			log.info(user.getName() + "|" + page + "|findBankChannel|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BankChannelBean.class);
			List<BankChannelBean> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBankChannel|Time:" + timer.getStopTime());
			/*if ( (list != null) && (list.size() > 0)){
				return (BankMasterBean)list.get(0);
			}else{
				throw new Exception("Not Found Data");
			}	*/
			return (BankChannelBean)list.get(0);
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|findBankChannel|Exception:" + e.getMessage());
			throw e;
		}
	}
    
   
	
	public void insertBankChannel(BankChannelBean bean, UserInfoBean user)throws IsExistException, Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBankChannel|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBankChannel|Param|" + bean.toString());
			
			if (isExistBankChannelCode(bean.getBANK_CHNL_CODE(), user)){
				throw new IsExistException("BANK_CHNL_CODE");
			}
			
			String sql = "INSERT INTO BANK_CHANNEL(BANK_CHNL_CODE , BANK_CHNL_NAME , BANK_CHNL_FULL_NAME_TH , BANK_CHNL_FULL_NAME_EN , ACT_FLAG , CRTD_BY , CRTD_DTTM , LAST_CHNG_BY , LAST_CHNG_DTTM )"
					   + "VALUES(?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE)";
			int i = 0;
			Query query1 = em.createNativeQuery(sql);
			query1.setParameter(++i, bean.getBANK_CHNL_CODE());
			query1.setParameter(++i, bean.getBANK_CHNL_NAME());
			query1.setParameter(++i, bean.getBANK_CHNL_FULL_NAME_TH());
			query1.setParameter(++i, bean.getBANK_CHNL_FULL_NAME_EN());
			query1.setParameter(++i, bean.getACT_FLAG());
			query1.setParameter(++i, user.getName());
			query1.setParameter(++i, user.getName());
			query1.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|insertBankChannel|Success");
			log.info(user.getName() + "|" + page + "|insertBankChannel|Time|" + timer.getStopTime());
		}catch(IsExistException e){
			log.error(user.getName() + "|" + page + "|insertBankChannel|IsExistException:" + e.getMessage());
			throw e;
		
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertBankChannel|Exception:" + e.getMessage());
			throw e;
		}
	}
	public boolean isExistBankChannelCode(String BANK_CHNL_CODE, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistBankChannelCode|Time:" + timer.getStartTime());
			String sql = "SELECT count(*) FROM BANK_CHANNEL WHERE BANK_CHNL_CODE = " + BANK_CHNL_CODE + " ";
			log.info(user.getName() + "|" + page + "|isExistBankChannelCode|SQL:" + sql);
			
			boolean bResult = true;
			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal)query.getSingleResult();
			if (result.intValue() > 0){
				bResult = true;
			}else{
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistBankChannelCode|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistBankChannelCode|Time:" + timer.getStopTime());
			return bResult;
	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|isExistBankChannelCode|Exception:" + e.getMessage());
			throw e;
		}
	}
	public void updateBankChannel(BankChannelBean bean, UserInfoBean user)throws  Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBankChannel|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBankChannel|Param|" + bean.toString());
			

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BANK_CHANNEL ")
					.append("SET BANK_CHNL_NAME = ? ")
					.append(", BANK_CHNL_FULL_NAME_TH = ? ")
					.append(", BANK_CHNL_FULL_NAME_EN = ? ")
					.append(", ACT_FLAG = ? ")
					.append(", CRTD_BY = ? ")
					.append(", LAST_CHNG_BY = ? ")
					.append(", LAST_CHNG_DTTM = SYSDATE ")
					.append("WHERE BANK_CHNL_CODE = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBANK_CHNL_NAME());
			query.setParameter(++i, bean.getBANK_CHNL_FULL_NAME_TH());
			query.setParameter(++i, bean.getBANK_CHNL_FULL_NAME_EN());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getCRTD_BY());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBANK_CHNL_CODE());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateBankChannel|Success");
			log.info(user.getName() + "|" + page + "|updateBankChannel|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateBankChannel|Exception:" + e.getMessage());
			throw e;
		}
	}
}
