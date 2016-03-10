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

import mfs.biller.ejb.interfaces.GWBankLocal;
import mfs.biller.ejb.interfaces.GWBankRemote;
import mfs.biller.persistence.bean.GWBankBean;
import mfs.biller.persistence.bean.GWBankDetail;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.JpaResultUtil;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class BankMaster
 */
@Stateless(name = "GWBank", mappedName = "mfs.biller.ejb.GWBank")
@CallByReference
public class GWBank implements GWBankRemote, GWBankLocal {

	private Logger log = Logger.getLogger("EJBGWBank");
	private String page = "GWBank";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	
    public GWBank() {
       
    }
    
    public List<GWBankDetail> searchGWBankAll(GWBankDetail PARAM, UserInfoBean user)throws Exception{
    	List<GWBankDetail> listDetail = new ArrayList<GWBankDetail>();
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchGWBankAll|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchGWBankAll|Param|" + PARAM.toString());
			
			String sql = "SELECT  T1.BANK_SRVC_ID ,T2.BANK_SRVC_NAME , T1.INB_MAP_ID ,T3.DATA_MAP_NAME ,T1.GW_MAP_ID ,T4.DATA_MAP_NAME ,T1.OUTB_MAP_ID ,T5.DATA_MAP_NAME ,T1.ACT_FLAG,T1.LAST_CHNG_BY,T1.LAST_CHNG_DTTM   "
						+ " FROM  GW_BANK T1  "
						+ " LEFT JOIN  BANK_SERVICE T2  ON  T1.BANK_SRVC_ID = T2.BANK_SRVC_ID  "
						+ " LEFT JOIN GW_BANK_INB_MAP T3 ON T1.INB_MAP_ID = T3.DATA_MAP_ID "
						+ " LEFT JOIN GW_BANK_GW_MAP T4 ON T1.GW_MAP_ID = T4.DATA_MAP_ID "
						+ " LEFT JOIN GW_BANK_OUTB_MAP T5 ON T1.OUTB_MAP_ID = T5.DATA_MAP_ID " ;
			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(PARAM.getBANK_SRVC_ID())){
				v.add(" T1.BANK_SRVC_ID = "+PARAM.getBANK_SRVC_ID());
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
			
			log.info(user.getName() + "|" + page + "|searchGWBankAll|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			GWBankDetail bean = null;
			log.info(user.getName() + "|" + page + "|searchGWBankAll|List.Size:" + list.size());
			for (Object[] item : list){
				int x = 0;
				bean = new GWBankDetail();
				bean.setBANK_SRVC_ID(JpaResultUtil.getInteger(item, x++));
				bean.setBANK_SRVC_NAME(JpaResultUtil.getString(item, x++));
				bean.setINB_MAP_ID(JpaResultUtil.getInteger(item, x++));
				bean.setINB_DATA_MAP_NAME(JpaResultUtil.getString(item, x++));
				bean.setGW_MAP_ID(JpaResultUtil.getInteger(item, x++));
				bean.setGW_DATA_MAP_NAME(JpaResultUtil.getString(item, x++));
				bean.setOUTB_MAP_ID(JpaResultUtil.getInteger(item, x++));
				bean.setOUTB_DATA_MAP_NAME(JpaResultUtil.getString(item, x++));
				bean.setACT_FLAG(JpaResultUtil.getString(item, x++));
				bean.setLAST_CHNG_BY(JpaResultUtil.getString(item, x++));
				bean.setLAST_CHNG_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				
				listDetail.add(bean);
			}
			log.info(user.getName() + "|" + page + "|searchGWBankAll|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|searchGWBankAll|Exception:" + e.getMessage());
			throw e;
		}
		
		return listDetail;
	}
    public GWBankBean findGWBank(int BANK_SRVC_ID, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findGWBank|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM GW_BANK WHERE BANK_SRVC_ID = '" + BANK_SRVC_ID+"'";
			log.info(user.getName() + "|" + page + "|findGWBank|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, GWBankBean.class);
			List<GWBankBean> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findGWBank|Time:" + timer.getStopTime());
			/*if ( (list != null) && (list.size() > 0)){
				return (BankMasterBean)list.get(0);
			}else{
				throw new Exception("Not Found Data");
			}	*/
			return (GWBankBean)list.get(0);
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|findGWBank|Exception:" + e.getMessage());
			throw e;
		}
	}
    
   
	
	public int insertGWBank(GWBankBean bean, UserInfoBean user)throws  Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertGWBank|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertGWBank|Param|" + bean.toString());
			
			
			String sql = "INSERT INTO GW_BANK(BANK_SRVC_ID , INB_MAP_ID , GW_MAP_ID , OUTB_MAP_ID , ACT_FLAG , CRTD_BY , CRTD_DTTM , LAST_CHNG_BY , LAST_CHNG_DTTM  )"
					   + "VALUES(?, ?, ?, ?, ?, ?,  SYSDATE, ?, SYSDATE)";
			int i = 0;
			Query query1 = em.createNativeQuery(sql);
			query1.setParameter(++i, bean.getBANK_SRVC_ID());
			query1.setParameter(++i, bean.getINB_MAP_ID());
			query1.setParameter(++i, bean.getGW_MAP_ID());
			query1.setParameter(++i, bean.getOUTB_MAP_ID());
			query1.setParameter(++i, bean.getACT_FLAG());
			query1.setParameter(++i, user.getName());
			query1.setParameter(++i, user.getName());
			query1.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|insertGWBank|Success");
			log.info(user.getName() + "|" + page + "|insertGWBank|Time|" + timer.getStopTime());
		return bean.getBANK_SRVC_ID();
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertGWBank|Exception:" + e.getMessage());
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
	public void updateGWBank(GWBankBean bean, UserInfoBean user)throws  Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateGWBank|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateGWBank|Param|" + bean.toString());
			

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE GW_BANK ")
					.append("SET INB_MAP_ID = ? ")
					.append(", GW_MAP_ID = ? ")
					.append(", OUTB_MAP_ID = ? ")
					.append(", ACT_FLAG = ? ")
					.append(", CRTD_BY = ? ")
					.append(", LAST_CHNG_BY = ? ")
					.append(", LAST_CHNG_DTTM = SYSDATE ")
					.append("WHERE BANK_SRVC_ID = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getINB_MAP_ID());
			query.setParameter(++i, bean.getGW_MAP_ID());
			query.setParameter(++i, bean.getOUTB_MAP_ID());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getCRTD_BY());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBANK_SRVC_ID());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateGWBank|Success");
			log.info(user.getName() + "|" + page + "|updateGWBank|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateGWBank|Exception:" + e.getMessage());
			throw e;
		}
	}
}
