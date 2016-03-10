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

import mfs.biller.ejb.interfaces.ServiceGatewayLocal;
import mfs.biller.ejb.interfaces.ServiceGatewayRemote;
import mfs.biller.persistence.bean.GWService;
import mfs.biller.persistence.bean.ServiceGatewayResult;
import mfs.biller.persistence.bean.ServiceGatewayResultParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.ConfigLoader;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;
import mfs.exception.IsExistException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

@Stateless(name = "ServiceGateway", mappedName = "mfs.biller.ejb.ServiceGateway")
@CallByReference
public class ServiceGateway implements ServiceGatewayRemote, ServiceGatewayLocal {

    public ServiceGateway() {
    }
  
    private Logger log = Logger.getLogger("EJBGWSERVICE");
	private String page = "EJBGWSERVICE";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public ArrayList<ServiceGatewayResult> getServiceGatewayResult(ServiceGatewayResultParam Param , UserInfoBean user ) throws Exception{
		List listServiceGatewayResult = null;
		ArrayList arraylist = null;
		arraylist = new ArrayList();
		ResultSet rs = null;
		
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|getServiceGatewayResult|Time:" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|getServiceGatewayResult|Param:" + Param);
			String sql = "SELECT T2.GW_SRVC_ID,T2.GW_SRVC_NAME,T2.GW_SRVC_MAP_ID,T2.GW_OUTB_ID,T2.ACT_FLAG,T2.LAST_CHNG_BY,T2.LAST_CHNG_DTTM ,T1.GW_OUTB_NAME ,map.DATA_MAP_NAME "; 
				   sql = sql+  "  FROM GW_SERVICE T2 LEFT JOIN  GW_OUTBOUND T1 on T2. GW_OUTB_ID = T1.GW_OUTB_ID     LEFT JOIN  GW_SERVICE_MAP    map   on map.DATA_MAP_ID= T2.GW_SRVC_MAP_ID  ";				   
				  /* if(Param.getGW_OUTB_ID()==null && Param.getGW_SRVC_ID() ==null){
				   sql = sql+  "WHERE T1.GW_OUTB_ID = T2.GW_OUTB_ID  ";
				   }else {					  
				   sql = sql+  "WHERE T1.GW_OUTB_ID = T2.GW_OUTB_ID ";
					   if(Param.getGW_OUTB_ID()!=null){						   
						   sql = sql+  " AND T1.GW_OUTB_ID = "+Param.getGW_OUTB_ID()+" ";
					   }
					   if(Param.getGW_SRVC_ID()!=null){						   
						   sql = sql+  " AND T2.GW_SRVC_ID = "+Param.getGW_SRVC_ID()+" ";
					   }					   
				   }
				   */
				   
				   
				   Vector<String> v = new Vector<String>();
					StringBuffer sb = new StringBuffer();
					if (!ValidateUtil.isEmpty(Param.getGW_OUTB_ID())){
						v.add(" T1.GW_OUTB_ID = " + Param.getGW_OUTB_ID());
					}
					if (!ValidateUtil.isEmpty(Param.getACT_FLAG())){
						v.add(" T2.ACT_FLAG = '"+Param.getACT_FLAG()+"'" );
					}
					if (!ValidateUtil.isEmpty(Param.getGW_SRVC_ID())){
						v.add(" T2.GW_SRVC_ID = " + Param.getGW_SRVC_ID() );
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
				   
				   sql=sql+(" order by  T2.GW_SRVC_ID");	
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
	   log.info(user.getName() + "|" + page + "|getServiceGatewayResult|sql:" + sql);
	
	   	Query query = em.createNativeQuery(sql);
		List list = query.getResultList();
		log.info(user.getName() + "|" + page + "|getServiceGatewayResult|list:" + list.size());
		for(int i= 0;i<list.size();i++){
			Object[] obj=(Object[])list.get(i);
			ServiceGatewayResult ServiceGatewayResult = new ServiceGatewayResult(); 
			
			ServiceGatewayResult.setGW_SRVC_ID(chkNull(OBJchkNull((obj[0]))));
			ServiceGatewayResult.setGW_SRVC_NAME(chkNull(OBJchkNull(obj[1])));
			ServiceGatewayResult.setGW_SRVC_MAP_ID(chkNull(OBJchkNull(obj[2])));
			ServiceGatewayResult.setGW_OUTB_ID(chkNull(OBJchkNull(obj[3])));
			ServiceGatewayResult.setACT_FLAG(chkNull(OBJchkNull(obj[4])));
			ServiceGatewayResult.setDATA_MAP_NAME(chkNull(OBJchkNull(obj[8])));
			String GW_OUTB_NAME="";
			if(obj[7]==null){
				GW_OUTB_NAME="";
			}else{
				GW_OUTB_NAME=obj[7].toString();
			}
			
			ServiceGatewayResult.setGW_OUTB_NAME(chkNull(GW_OUTB_NAME));
			
			String LAST_CHNG_BY="";
			String LAST_CHNG_DTTM="";
			if(obj[5]==null){
				 LAST_CHNG_BY="";
			}else{
				LAST_CHNG_BY=obj[5].toString();
			}
			ServiceGatewayResult.setLAST_CHNG_BY(chkNull(LAST_CHNG_BY));
			if(obj[6]==null){
				 LAST_CHNG_DTTM="";
			}
			else{
				LAST_CHNG_DTTM=obj[6].toString();
			}
			ServiceGatewayResult.setLAST_CHNG_DTTM(chkNull(LAST_CHNG_DTTM));
			arraylist.add(ServiceGatewayResult);
	}
		log.info(user.getName() + "|" + page + "|getServiceGatewayResult|Time:" + timer.getStopTime());
		return arraylist;
		
			
		}
		catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.error(user.getName() + "|" + page + "|getServiceGatewayResult|Exception:" + e);
		}
		return arraylist;
		
	}
	public static String chkNull( String value ) 
	{
		return (value == null)?(""):(value.trim());
	}
	public static String OBJchkNull( Object value ) 
	{
		return (value == null)?(""):(value.toString()); 
	}
	
	public BigDecimal countRowAll(ServiceGatewayResultParam Param , UserInfoBean user)
			throws Exception {	
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|countRowAll|Time:" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|countRowAll|Param:" + Param);

			String sql = " SELECT count(*) "; 
			   sql = sql+  "  FROM GW_SERVICE T2 LEFT JOIN  GW_OUTBOUND T1 on T2. GW_OUTB_ID = T1.GW_OUTB_ID     LEFT JOIN  GW_SERVICE_MAP    map   on map.DATA_MAP_ID= T2.GW_SRVC_MAP_ID  ";				   
			  /* if(Param.getGW_OUTB_ID()==null && Param.getGW_SRVC_ID() ==null){
			   sql = sql+  "WHERE T1.GW_OUTB_ID = T2.GW_OUTB_ID  ";
			   }else {					  
			   sql = sql+  "WHERE T1.GW_OUTB_ID = T2.GW_OUTB_ID ";
				   if(Param.getGW_OUTB_ID()!=null){						   
					   sql = sql+  " AND T1.GW_OUTB_ID = "+Param.getGW_OUTB_ID()+" ";
				   }
				   if(Param.getGW_SRVC_ID()!=null){						   
					   sql = sql+  " AND T2.GW_SRVC_ID = "+Param.getGW_SRVC_ID()+" ";
				   }					   
			   }
			   */
			   
			   
			   Vector<String> v = new Vector<String>();
				StringBuffer sb = new StringBuffer();
				if (!ValidateUtil.isEmpty(Param.getGW_OUTB_ID())){
					v.add(" T1.GW_OUTB_ID = " + Param.getGW_OUTB_ID());
				}
				if (!ValidateUtil.isEmpty(Param.getACT_FLAG())){
					v.add(" T2.ACT_FLAG = '"+Param.getACT_FLAG()+"'" );
				}
				if (!ValidateUtil.isEmpty(Param.getGW_SRVC_ID())){
					v.add(" T2.GW_SRVC_ID = " + Param.getGW_SRVC_ID() );
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
			   
			   sql=sql+(" order by  T2.GW_SRVC_ID");	
				   log.info(user.getName() + "|" + page + "|countRowAll|sql:" + sql);

				Query query = em.createNativeQuery(sql);
				List list = query.getResultList();
				log.info(user.getName() + "|" + page + "|countRowAll|list:" + list.size());
				BigDecimal numRow = (BigDecimal) list.get(0);
				log.info(user.getName() + "|" + page + "|countRowAll|Time:" + timer.getStopTime());
				return numRow;
			} catch (Exception e) {
				log.error(ExceptionUtils.getStackTrace(e));
				log.error(user.getName() + "|" + page + "|countRowAll|Exception:" + e);
			}

			return new BigDecimal(0);
		}
	public int insertServiceGateway(GWService bean, UserInfoBean user)throws IsExistException, Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertServiceGateway|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertServiceGateway|Param|" + bean.toString());
			
		
			
			Query query = em.createNativeQuery("SELECT SEQ_GW_SERVICE.nextval from DUAL");
			BigDecimal result = (BigDecimal)query.getSingleResult();
			int GW_SRVC_ID = result.intValue();
			
			log.info(user.getName() + "|" + page + "|insertServiceGateway|BLLR_CATG_ID:" + GW_SRVC_ID);
			
			String sql = "INSERT INTO GW_SERVICE(GW_SRVC_ID, GW_OUTB_ID, GW_SRVC_NAME, GW_SRVC_MAP_ID, ACT_FLAG, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY,LAST_CHNG_DTTM,GW_CHCK_ACL_FLAG,GW_CHCK_AMNT_FLAG,GW_CHCK_DUE_DATE_FLAG )"
					   + "VALUES(?, ?, ?, ?, ?, ?, ? , ? , SYSDATE, ?, ?, ?  )";
			int i = 0;
			query = em.createNativeQuery(sql);
			query.setParameter(++i, GW_SRVC_ID);
			query.setParameter(++i, bean.getGW_OUTB_ID());
			query.setParameter(++i, bean.getGW_SRVC_NAME());
			query.setParameter(++i, bean.getGW_SRVC_MAP_ID());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getCRTD_BY());
			query.setParameter(++i, bean.getCRTD_DTTM());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, bean.getGW_CHCK_ACL_FLAG());
			query.setParameter(++i, bean.getGW_CHCK_AMNT_FLAG());
			query.setParameter(++i, bean.getGW_CHCK_DUE_DATE_FLAG());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|insertServiceGateway|Success");
			log.info(user.getName() + "|" + page + "|insertServiceGateway|Time|" + timer.getStopTime());
			return GW_SRVC_ID;
			
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertServiceGateway|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public GWService searchServiceGateway(int PARAM, UserInfoBean user)throws Exception{
		
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchServiceGateway|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchServiceGateway|Param|" + PARAM);
			
			String sql = "SELECT * FROM GW_SERVICE  WHERE GW_SRVC_ID = "+PARAM+"";			
			sql += " ORDER BY GW_SRVC_ID";
			
			log.info(user.getName() + "|" + page + "|searchServiceGateway|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, GWService.class);
			List<GWService> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchServiceGateway|list:" + list.size());
			log.info(user.getName() + "|" + page + "|searchServiceGateway|Time|" + timer.getStopTime());
			return (GWService)list.get(0);
			
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|searchServiceGateway|Exception:" + e.getMessage());
			throw e;
		}
	
	}
	
	public void updateServiceGateway(GWService bean, UserInfoBean user)throws IsExistException, Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateServiceGateway|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateServiceGateway|Param|" + bean.toString());
			
			
			
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE GW_SERVICE ")
					.append("SET GW_SRVC_NAME = ? ")
					.append(", GW_SRVC_MAP_ID = ? ")
					.append(", GW_OUTB_ID = ? ")
					.append(", ACT_FLAG = ? ")
					.append(", LAST_CHNG_BY = ? ")
					.append(", LAST_CHNG_DTTM = SYSDATE ")
					.append("WHERE GW_SRVC_ID = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getGW_SRVC_NAME());
			query.setParameter(++i, bean.getGW_SRVC_MAP_ID());
			query.setParameter(++i, bean.getGW_OUTB_ID());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getGW_SRVC_ID());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateServiceGateway|Success");
			log.info(user.getName() + "|" + page + "|updateServiceGateway|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateServiceGateway|Exception:" + e.getMessage());
			throw e;
		}
	}


}
