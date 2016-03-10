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

import mfs.biller.ejb.interfaces.OutboundGatewayLocal;
import mfs.biller.ejb.interfaces.OutboundGatewayRemote;
import mfs.biller.persistence.bean.GWOutbound;
import mfs.biller.persistence.bean.GWOutboundDtail;
import mfs.biller.persistence.bean.OutboundGatewayParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.ConfigLoader;
import mfs.biller.util.JpaResultUtil;
import mfs.biller.util.Timer;
import mfs.exception.IsExistException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

/**
 * Session Bean implementation class OutboundGateway
 */
@Stateless(name = "OutboundGateway", mappedName = "mfs.biller.ejb.OutboundGateway")
@CallByReference
public class OutboundGateway implements OutboundGatewayRemote, OutboundGatewayLocal {

    /**
     * Default constructor. 
     */
    public OutboundGateway() {
       
    }
    
    private Logger log = Logger.getLogger("EJBGWOUTBOUND");
	private String page = "EJBGWOUTBOUND";
    @PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public List<GWOutboundDtail> getOutboundGateway(OutboundGatewayParam Param , UserInfoBean user) throws Exception{
		List<GWOutboundDtail> listDetail = new ArrayList<GWOutboundDtail>();
		ResultSet rs = null;
		
		try{

		    Timer timer = new Timer("-");
			log.error(user.getName() + "|" + page + "|getOutboundGateway|Time:" + timer.getStartTime());
			log.error(user.getName() + "|" + page + "|getOutboundGateway|Param:" + Param);
			String sql = "SELECT GW_OUTB_ID ,GW_OUTB_NAME,GW_OUTB_MAP_ID,ACT_FLAG,CRTD_BY ,CRTD_DTTM,LAST_CHNG_BY,LAST_CHNG_DTTM   ,map.DATA_MAP_NAME     "; 
					sql=sql+ " FROM GW_OUTBOUND  T1 left join  GW_OUTBOUND_MAP    map   on map.DATA_MAP_ID =  T1.GW_OUTB_MAP_ID  ";
					
					Vector<String> v = new Vector<String>();

					if (Param.getGW_OUTB_ID() != null && !"".equals(Param.getGW_OUTB_ID()))
						v.add(" GW_OUTB_ID = '" + Param.getGW_OUTB_ID() + "'");
					if (Param.getACT_FLAG() != null && !"".equals(Param.getACT_FLAG()))
						v.add(" ACT_FLAG = '" + Param.getACT_FLAG() + "'");
					StringBuffer sb = new StringBuffer();
					if (!v.isEmpty()) {
						sb.append(" WHERE ");
						for (int i = 0; i < v.size(); i++) {
							if (i != 0) {
								sb.append(" AND ");
							}
							sb.append(v.get(i));
						}
					}

					sql = sql + sb.toString();
					sql += " order by GW_OUTB_ID ";			
		
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
			log.error(user.getName() + "|" + page + "|getOutboundGateway|sql:" + sql);
			
			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			log.error(user.getName() + "|" + page + "|getOutboundGateway|list:" + list.size());
			
			GWOutboundDtail bean = null;
			for (Object[] item : list){
				int x = 0;
				bean = new GWOutboundDtail();
				bean.setGW_OUTB_ID(JpaResultUtil.getInteger(item, x++));
				bean.setGW_OUTB_NAME(JpaResultUtil.getString(item, x++));
				bean.setGW_OUTB_MAP_ID(JpaResultUtil.getInteger(item, x++));
				bean.setACT_FLAG(JpaResultUtil.getString(item, x++));
				bean.setCRTD_BY(JpaResultUtil.getString(item, x++));
				bean.setCRTD_DTTM(JpaResultUtil.getDate(item, x++ , "yyyy-MM-dd HH:mm:ss"));
				bean.setLAST_CHNG_BY(JpaResultUtil.getString(item, x++));
				bean.setLAST_CHNG_DTTM(JpaResultUtil.getDate(item, x++ , "yyyy-MM-dd HH:mm:ss"));
				bean.setDATA_MAP_NAME(JpaResultUtil.getString(item, x++));
				
				listDetail.add(bean);
			
			
		}
			log.error(user.getName() + "|" + page + "|getOutboundGateway|Time:" + timer.getStopTime());
		}
		catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.error(user.getName() + "|" + page + "|getOutboundGateway|Exception:" + e.getMessage());
		}
		
		return listDetail;
		
	}
	public static String chkNull( String value ) 
	{
		return (value == null)?(""):(value.trim());
	}
	public BigDecimal countRowAll(OutboundGatewayParam Param , UserInfoBean user) throws Exception {

		try {
			Timer timer = new Timer("-");
			log.error(user.getName() + "|" + page + "|countRowAll|Time:" + timer.getStartTime());
			log.error(user.getName() + "|" + page + "|countRowAll|Param:" + Param);
			String sql = " SELECT count(*)     "; 
			sql=sql+ " FROM GW_OUTBOUND  T1 left join  GW_OUTBOUND_MAP    map   on map.DATA_MAP_ID =  T1.GW_OUTB_MAP_ID  ";
			
			Vector<String> v = new Vector<String>();

			if (Param.getGW_OUTB_ID() != null && !"".equals(Param.getGW_OUTB_ID()))
				v.add(" GW_OUTB_ID = '" + Param.getGW_OUTB_ID() + "'");
			if (Param.getACT_FLAG() != null && !"".equals(Param.getACT_FLAG()))
				v.add(" ACT_FLAG = '" + Param.getACT_FLAG() + "'");
			StringBuffer sb = new StringBuffer();
			if (!v.isEmpty()) {
				sb.append(" WHERE ");
				for (int i = 0; i < v.size(); i++) {
					if (i != 0) {
						sb.append(" AND ");
					}
					sb.append(v.get(i));
				}
			}

			sql = sql + sb.toString();
			sql += " order by GW_OUTB_ID ";	
			
					log.error(user.getName() + "|" + page + "|countRowAll|sql:" + sql);
			Query query = em.createNativeQuery(sql);
			List list = query.getResultList();
			log.error(user.getName() + "|" + page + "|countRowAll|list:" + list.size());
			BigDecimal numRow = (BigDecimal) list.get(0);
			log.error(user.getName() + "|" + page + "|countRowAll|Time:" + timer.getStopTime());
			return numRow;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.error(user.getName() + "|" + page + "|countRowAll|Exception:" + e.getMessage());
		}

		return new BigDecimal(0);
	}
	
	public int insertOutboundGateway(GWOutbound bean, UserInfoBean user)throws IsExistException, Exception{
		try{
			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertOutboundGateway|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertOutboundGateway|Param|" + bean.toString());
			
		
			
			Query query = em.createNativeQuery("SELECT SEQ_GW_OUTBOUND.nextval from DUAL");
			BigDecimal result = (BigDecimal)query.getSingleResult();
			int GW_OUTB_ID = result.intValue();
			
			log.info(user.getName() + "|" + page + "|insertOutboundGateway|BLLR_CATG_ID:" + GW_OUTB_ID);
			
			String sql = "INSERT INTO GW_OUTBOUND(GW_OUTB_ID, GW_OUTB_NAME, GW_OUTB_MAP_ID, ACT_FLAG, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY, LAST_CHNG_DTTM )"
					   + "VALUES(?, ?, ?, ?, ?, ?, ? , SYSDATE )";
			int i = 0;
			query = em.createNativeQuery(sql);
			query.setParameter(++i, GW_OUTB_ID);
			query.setParameter(++i, bean.getGW_OUTB_NAME());
			query.setParameter(++i, bean.getGW_OUTB_MAP_ID());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, bean.getCRTD_DTTM());
			query.setParameter(++i, user.getName());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|insertOutboundGateway|Success");
			log.info(user.getName() + "|" + page + "|insertOutboundGateway|Time|" + timer.getStopTime());
			return GW_OUTB_ID;
			
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertOutboundGateway|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public GWOutbound searchOutboundGateway(int PARAM, UserInfoBean user)throws Exception{
		
		try{
			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchOutboundGateway|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchOutboundGateway|Param|" + PARAM);
			
			String sql = "SELECT * FROM GW_OUTBOUND  WHERE GW_OUTB_ID = "+PARAM+"";			
			sql += " ORDER BY GW_OUTB_ID";
			
			log.info(user.getName() + "|" + page + "|searchOutboundGateway|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, GWOutbound.class);
			List<GWOutbound> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchOutboundGateway|Time|" + timer.getStopTime());
			return (GWOutbound)list.get(0);
			
			
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|searchOutboundGateway|Exception:" + e.getMessage());
			throw e;
		}
		
	}
	
	public void updateOutboundGateway(GWOutbound bean, UserInfoBean user)throws IsExistException, Exception{
		try{
			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateOutboundGateway|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateOutboundGateway|Param|" + bean.toString());
			
			
			
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE GW_OUTBOUND ")
					.append("SET GW_OUTB_NAME = ? ")
					.append(", GW_OUTB_MAP_ID = ? ")
					.append(", ACT_FLAG = ? ")
					.append(", LAST_CHNG_BY = ? ")
					.append(", LAST_CHNG_DTTM = SYSDATE ")
					.append("WHERE GW_OUTB_ID = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getGW_OUTB_NAME());
			query.setParameter(++i, bean.getGW_OUTB_MAP_ID());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getGW_OUTB_ID());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateOutboundGateway|Success");
			log.info(user.getName() + "|" + page + "|updateOutboundGateway|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateOutboundGateway|Exception:" + e.getMessage());
			throw e;
		}
	}

}
