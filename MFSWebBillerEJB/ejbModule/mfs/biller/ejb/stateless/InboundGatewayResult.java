package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.InboundGatewayResultLocal;
import mfs.biller.ejb.interfaces.InboundGatewayResultRemote;
import mfs.biller.persistence.bean.GWInboundPK;
import mfs.biller.persistence.bean.OBJGW_INBOUND;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.persistence.bean.getInboundGatewayResult;
import mfs.biller.persistence.bean.getInboundGatewayResultParam;
import mfs.biller.util.ConfigLoader;
import mfs.biller.util.JpaResultUtil;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;
import mfs.exception.IsExistException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;


/**
 * Session Bean implementation class InboundGatewayResult
 */
@Stateless(name = "InboundGatewayResult", mappedName = "mfs.biller.ejb.InboundGatewayResult")
@CallByReference

public class InboundGatewayResult implements InboundGatewayResultRemote, InboundGatewayResultLocal {

    /**
     * Default constructor. 
     */
	private Logger log = Logger.getLogger("EJBGWINBOUND");
	private String page = "EJBGWINBOUND";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	private List<getInboundGatewayResult> listgetInboundGatewayResult;
	@SuppressWarnings("unchecked")
    public InboundGatewayResult() {
       
    }
	public ArrayList<getInboundGatewayResult> getInboundGatewayResult(getInboundGatewayResultParam Param, UserInfoBean user) throws Exception{
		List listgetInboundGatewayResult = null;
		ArrayList arraylist = null;
		arraylist = new ArrayList();
		ResultSet rs = null;

		Timer timer = new Timer("-");	
		log.info(user.getName() + "|" + page + "|getInboundGatewayResult|Time:" + timer.getStartTime());
		log.info(user.getName() + "|" + page + "|getInboundGatewayResult|Param:" + Param.toString());
		try{

			String sql = " SELECT T3.BLLR_SRVC_ID,T4.INBN_SRVC_ID,T3.BLLR_SRVC_DESC,T2.BLLR_CODE,T4.DEST_SRVC_ID,T2.BLLR_MSTR_NAME,T1.BLLR_CATG_NAME,T1.BLLR_CATG_ID,T4.SRCE_SRVC_ID,T4.ACT_FLAG,T4.LAST_CHNG_BY,T4.LAST_CHNG_DTTM  ,T3.BLLR_SRVC_CODE , srvc.GW_SRVC_NAME  ,map.DATA_MAP_NAME    ,T4.INBN_SRVC_NAME  ,T4.GW_SRVC_ID ,T4.GW_INBN_MAP_ID   " ;
				   sql = sql+ "  FROM   GW_INBOUND T4  left join   BILLER_SERVICE T3 on T4.INBN_SRVC_ID = T3.BLLR_SRVC_CODE left join  BILLER_MASTER T2  on T3.BLLR_MSTR_ID = T2.BLLR_MSTR_ID left join  BILLER_CATEGORY T1 on T3.BLLR_CATG_ID = T1.BLLR_CATG_ID  left join GW_SERVICE   srvc  on T4.GW_SRVC_ID =  srvc.GW_SRVC_ID  left join GW_INBOUND_MAP    map   on T4.GW_INBN_MAP_ID =  MAP.DATA_MAP_ID  ";
				   //sql = sql+ "WHERE ";
				 
					 
					 Vector<String> v = new Vector<String>();
						StringBuffer sb = new StringBuffer();
						if (!ValidateUtil.isEmpty(Param.getSRCE_SRVC_ID())){
							v.add(" T4.SRCE_SRVC_ID = " + Param.getSRCE_SRVC_ID());
						}
						if (!ValidateUtil.isEmpty(Param.getACT_FLAG())){
							v.add(" T4.ACT_FLAG = '"+Param.getACT_FLAG()+"'" );
						}
						if (!ValidateUtil.isEmpty(Param.getBLLR_SRVC_CODE())){
							v.add(" T3.BLLR_SRVC_CODE = " + Param.getBLLR_SRVC_CODE() );
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
						sql = sql + sb.toString();
						sql = sql + " ORDER BY T3.BLLR_SRVC_ID";
					 
					 
					 
					   if (Param.getPAGE_NO() == null) {
							Param.setPAGE_NO(1);
						}
						if (Param.getPAGE_SIZE() == null) {
							ConfigLoader conf = new ConfigLoader();
							//log.info("RptTransBean|getMasterTransAll|Default page size:" + Integer.valueOf(conf.getConfig("PAGE_SIZE")));				
							Param.setPAGE_SIZE(Integer.valueOf(conf.getConfig("PAGE_SIZE")));
						}
				   
				   sql = "SELECT * FROM " + "( " + "SELECT a.*, rownum r__ " + "FROM "
							+ "( " + sql + ") a " + "WHERE rownum < (("
							+ Param.getPAGE_NO() + " * " + Param.getPAGE_SIZE()
							+ ") + 1 ) " + ") " + "WHERE r__ >= ((("
							+ Param.getPAGE_NO() + "-1) * " + Param.getPAGE_SIZE()
							+ ") + 1) ";
				   log.info(user.getName() + "|" + page + "|getInboundGatewayResult|sql:" + sql);
			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|getInboundGatewayResult|list:" + list.size());
			for (Object[] item : list) {
				int x = 0;
				getInboundGatewayResult getInboundGatewayResult = new getInboundGatewayResult(); 
				
				getInboundGatewayResult.setBLLR_SRVC_ID(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setINBN_SRVC_ID(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setBLLR_SRVC_DESC(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setBLLR_CODE(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setDEST_SRVC_ID(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setBLLR_MSTR_NAME(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setBLLR_CATG_NAME(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setBLLR_CATG_ID(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setSRCE_SRVC_ID(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setACT_FLAG(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setLAST_CHNG_BY(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setLAST_CHNG_DTTM(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setBLLR_SRVC_CODE(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setGW_SRVC_NAME(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setDATA_MAP_NAME(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setINBN_SRVC_NAME(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setGW_SRVC_ID(JpaResultUtil.getString(item, x++));
				getInboundGatewayResult.setGW_INBN_MAP_ID(JpaResultUtil.getString(item, x++));
				
				arraylist.add(getInboundGatewayResult);
		}
			log.info(user.getName() + "|" + page + "|getInboundGatewayResult|Time:" + timer.getStopTime());
			return arraylist;
			
		}
		catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.error(user.getName() + "|" + page + "|getInboundGatewayResult|Exception:" + e);
		}
		return arraylist;
		
	}
	public static String chkNull( String value ) 
	{
		return (value == null)?(""):(value.trim());
	}
	public BigDecimal countRowAll(getInboundGatewayResultParam Param , UserInfoBean user)
			throws Exception {	
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|countRowAll|Time:" +timer.getStartTime());
			log.info(user.getName() + "|" + page + "|countRowAll|Param:" +Param.toString());
			String sql = " SELECT count(*)   " ;
			   sql = sql+ "  FROM   GW_INBOUND T4  left join   BILLER_SERVICE T3 on T4.INBN_SRVC_ID = T3.BLLR_SRVC_CODE left join  BILLER_MASTER T2  on T3.BLLR_MSTR_ID = T2.BLLR_MSTR_ID left join  BILLER_CATEGORY T1 on T3.BLLR_CATG_ID = T1.BLLR_CATG_ID  left join GW_SERVICE   srvc  on T4.GW_SRVC_ID =  srvc.GW_SRVC_ID  left join GW_INBOUND_MAP    map   on T4.GW_INBN_MAP_ID =  MAP.DATA_MAP_ID  ";
			   //sql = sql+ "WHERE ";
			 
				 
			   Vector<String> v = new Vector<String>();
				StringBuffer sb = new StringBuffer();
				if (!ValidateUtil.isEmpty(Param.getSRCE_SRVC_ID())){
					v.add(" T4.SRCE_SRVC_ID = " + Param.getSRCE_SRVC_ID());
				}
				if (!ValidateUtil.isEmpty(Param.getACT_FLAG())){
					v.add(" T4.ACT_FLAG = '"+Param.getACT_FLAG()+"'" );
				}
				if (!ValidateUtil.isEmpty(Param.getBLLR_SRVC_CODE())){
					v.add(" T3.BLLR_SRVC_CODE = " + Param.getBLLR_SRVC_CODE() );
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
				sql = sql + sb.toString();
				sql = sql + " ORDER BY T3.BLLR_SRVC_ID";
				 log.info(user.getName() + "|" + page + "|countRowAll|sql:" + sql);
				Query query = em.createNativeQuery(sql);
				List list = query.getResultList();
				log.info(user.getName() + "|" + page + "|countRowAll|list:" + list.size());
				BigDecimal numRow = (BigDecimal) list.get(0);
				log.info(user.getName() + "|" + page + "|countRowAll|Time:" +timer.getStopTime());
				return numRow;
			} catch (Exception e) {
				log.error(ExceptionUtils.getStackTrace(e));
				 log.error(user.getName() + "|" + page + "|countRowAll|Exception:" + e);
			}

			return new BigDecimal(0);
		}
	
	public OBJGW_INBOUND getInboundGatewayResultED(getInboundGatewayResultParam Param , UserInfoBean user) throws Exception{
		List listOBJGW_INBOUND = null;
		ResultSet rs = null;
		//int BLLR_SRVC_CODE = Integer.parseInt(Param.getBLLR_SRVC_CODE());
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|getInboundGatewayResultED|Time:" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|getInboundGatewayResultED|Param:" + Param);
			String sql = "SELECT *   "; 
					sql=sql+ " FROM GW_INBOUND  ";
					sql=sql+ " WHERE INBN_SRVC_ID = "+Param.getINBN_SRVC_ID()+" AND SRCE_SRVC_ID = "+Param.getSRCE_SRVC_ID()+" AND DEST_SRVC_ID = "+Param.getDEST_SRVC_ID()+" ";	
			
		
					log.info(user.getName() + "|" + page + "|getInboundGatewayResultED|sql:" + sql);
			Query query = em.createNativeQuery(sql,OBJGW_INBOUND.class);
			List<OBJGW_INBOUND> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|getInboundGatewayResultED|Time:" + timer.getStopTime());
			return (OBJGW_INBOUND)list.get(0);
		}
		catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.error(user.getName() + "|" + page + "|getInboundGatewayResultED|Exception:" + e);
			throw e;
		}
		
		//return (OBJGW_INBOUND)list.get(0);
	}
	
	public GWInboundPK insertGW_INBOUND(OBJGW_INBOUND bean, UserInfoBean user)throws IsExistException, Exception{
		
		GWInboundPK GWInboundPK =new GWInboundPK();
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertGW_INBOUND|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertGW_INBOUND|Param|" + bean.toString());
			
			if (isExistGWINBOUNDID( bean, user)){
				throw new IsExistException("INBN_SRVC_ID");
			}
			
			String sql = "INSERT INTO GW_INBOUND(INBN_SRVC_ID,SRCE_SRVC_ID,DEST_SRVC_ID,INBN_SRVC_NAME,GW_SRVC_ID,GW_INBN_MAP_ID,SEND_RCPT_FLAG,STRT_DATE,END_DATE,ACT_FLAG,CRTD_BY,CRTD_DTTM,LAST_CHNG_BY,LAST_CHNG_DTTM,GW_RCPT_CONF_ID)"
					   + "VALUES(?, ?, ?, ?, ?, ?, ?,?,?,?,?,SYSDATE,?,?,?)";
			int i = 0;
			Query query = em.createNativeQuery(sql);
			query.setParameter(++i, bean.getINBN_SRVC_ID());
			query.setParameter(++i, bean.getSRCE_SRVC_ID());
			query.setParameter(++i, bean.getDEST_SRVC_ID());
			query.setParameter(++i, bean.getINBN_SRVC_NAME());
			query.setParameter(++i, bean.getGW_SRVC_ID());
			query.setParameter(++i, bean.getGW_INBN_MAP_ID());
			query.setParameter(++i, bean.getSEND_RCPT_FLAG());
			query.setParameter(++i, bean.getSTRT_DATE());
			query.setParameter(++i, bean.getEND_DATE());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, bean.getGW_RCPT_CONF_ID());
			query.executeUpdate();
			
			GWInboundPK.setDEST_SRVC_ID(bean.getDEST_SRVC_ID());
			GWInboundPK.setINBN_SRVC_ID(bean.getINBN_SRVC_ID());
			GWInboundPK.setSRCE_SRVC_ID(bean.getSRCE_SRVC_ID());
			log.info(user.getName() + "|" + page + "|insertGW_INBOUND|Time|" + timer.getStopTime());
			return GWInboundPK;
		
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertGW_INBOUND|Exception:" + e.getMessage());
			throw e;
		}
		
	}
	public void updateGW_INBOUND(OBJGW_INBOUND bean, UserInfoBean user)throws IsExistException, Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateGW_INBOUND|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateGW_INBOUND|Param|" + bean.toString());
		
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE GW_INBOUND ")
					.append("SET SRCE_SRVC_ID = ? ")
					.append(", INBN_SRVC_NAME = ? ")
					.append(", GW_SRVC_ID = ? ")
					.append(", GW_INBN_MAP_ID = ? ")
					.append(", SEND_RCPT_FLAG = ? ")
					.append(", GW_RCPT_CONF_ID = ? ")
					.append(", ACT_FLAG = ? ")
					.append(", LAST_CHNG_BY = ? ")
					.append(", LAST_CHNG_DTTM = SYSDATE ")
					.append("WHERE INBN_SRVC_ID = ?  AND SRCE_SRVC_ID = ? AND DEST_SRVC_ID = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getSRCE_SRVC_ID());
			query.setParameter(++i, bean.getINBN_SRVC_NAME());
			query.setParameter(++i, bean.getGW_SRVC_ID());
			query.setParameter(++i, bean.getGW_INBN_MAP_ID());
			query.setParameter(++i, bean.getSEND_RCPT_FLAG());
			query.setParameter(++i, bean.getGW_RCPT_CONF_ID());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getINBN_SRVC_ID());
			query.setParameter(++i, bean.getSRCE_SRVC_ID());
			query.setParameter(++i, bean.getDEST_SRVC_ID());
			query.executeUpdate();
			log.info(user.getName() + "|" + page + "|updateGW_INBOUND|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateGW_INBOUND|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public boolean isExistGWINBOUNDID( OBJGW_INBOUND bean, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistGWINBOUNDID|Time:" + timer.getStartTime());
			String sql = "SELECT count(*) FROM GW_INBOUND WHERE INBN_SRVC_ID = "+bean.getINBN_SRVC_ID() +" AND SRCE_SRVC_ID = " +bean.getSRCE_SRVC_ID()+" AND DEST_SRVC_ID = "+bean.getDEST_SRVC_ID()+" ";
			log.info(user.getName() + "|" + page + "|isExistGWINBOUNDID|SQL:" + sql);
			
			boolean bResult = true;
			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal)query.getSingleResult();
			if (result.intValue() > 0){
				bResult = true;
			}else{
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistGWINBOUNDID|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistGWINBOUNDID|Time:" + timer.getStopTime());
			return bResult;
	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|isExistGWINBOUNDID|Exception:" + e.getMessage());
			throw e;
		}
	}
	}


