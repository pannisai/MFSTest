package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.GWXmlDataSrcLocal;
import mfs.biller.ejb.interfaces.GWXmlDataSrcRemote;
import mfs.biller.persistence.bean.GWXmlDataSrcBean;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.Timer;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

/**
 * Session Bean implementation class GWDataXmlSrc
 */
@Stateless(name = "GWXmlDataSrc", mappedName = "mfs.biller.ejb.GWXmlDataSrc")
@CallByReference
public class GWXmlDataSrc implements GWXmlDataSrcRemote, GWXmlDataSrcLocal {

    /**
     * Default constructor. 
     */
    public GWXmlDataSrc() {
       
    }
    private Logger log = Logger.getLogger("GWXmlDataSrc");
	private String page = "GWXmlDataSrc";
	
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	@SuppressWarnings("unchecked")
    public GWXmlDataSrcBean findGWXmlDataSrc(int XML_DATA_ID,  UserInfoBean user)
			throws Exception {
		ArrayList arraylist = null;
		try {

			Timer timer = new Timer("-");
			log.info("GWXmlDataSrc|findGWXmlDataSrc|Time:" + timer.getStartTime());
			String sql = "SELECT *  FROM  GW_XML_DATA_SRC WHERE XML_DATA_ID = "+XML_DATA_ID+" ";

			sql += " ORDER BY XML_DATA_ID ASC";

			log.info("GWXmlDataSrc|findGWXmlDataSrc|sql:" + sql);

			Query query = em.createNativeQuery(sql,GWXmlDataSrcBean.class);
			List<GWXmlDataSrcBean> list = query.getResultList();
			log.info("GWXmlDataSrc|findGWXmlDataSrc|Time:" + timer.getStopTime());
			return (GWXmlDataSrcBean)list.get(0);

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info(user.getName() + "|findGWInboundMap|Exception:" + e.getMessage());
			throw e;
		}

	}
	
	

	public int insertGWXmlDataSrc(GWXmlDataSrcBean bean, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertGWXmlDataSrc|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertGWXmlDataSrc|Param|" + bean.toString());
			
			Query query = em.createNativeQuery("SELECT SEQ_GW_XML_DATA_SRC.nextval from DUAL");
			BigDecimal result = (BigDecimal)query.getSingleResult();
			int XML_DATA_ID = result.intValue();
			
			log.info(user.getName() + "|" + page + "|insertGWXmlDataSrc|BARC_ID:" + XML_DATA_ID);
			
			String sql = "INSERT INTO GW_XML_DATA_SRC(XML_DATA_ID, XML_DATA_TYPE, XML_DATA_SRC, XML_DATA_VRSN, XML_DATA_DESC, XML_DATA_STTS )"
					   + "VALUES(?, ?, ?, ?, ?, ?)";
			int i = 0;
			query = em.createNativeQuery(sql);
			query.setParameter(++i, XML_DATA_ID);
			query.setParameter(++i, bean.getXML_DATA_TYPE());
			query.setParameter(++i, bean.getXML_DATA_SRC());
			query.setParameter(++i, bean.getXML_DATA_VRSN());
			query.setParameter(++i, bean.getXML_DATA_DESC());
			query.setParameter(++i, bean.getXML_DATA_STTS());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|insertGWXmlDataSrc|Success");
			log.info(user.getName() + "|" + page + "|insertGWXmlDataSrc|Time|" + timer.getStopTime());
			return XML_DATA_ID;
			
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertGWXmlDataSrc|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public void updateGWXmlDataSrc(GWXmlDataSrcBean bean, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateGWXmlDataSrc|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateGWXmlDataSrc|Param|" + bean.toString());
			
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE GW_XML_DATA_SRC ")
					.append("SET XML_DATA_TYPE = ? ")
					.append(", XML_DATA_SRC = ? ")
					.append(", XML_DATA_VRSN = ? ")
					.append(", XML_DATA_DESC = ? ")
					.append(", XML_DATA_STTS = ? ")
					.append("WHERE XML_DATA_ID = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getXML_DATA_TYPE());
			query.setParameter(++i, bean.getXML_DATA_SRC());
			query.setParameter(++i, bean.getXML_DATA_VRSN());
			query.setParameter(++i, bean.getXML_DATA_DESC());
			query.setParameter(++i, bean.getXML_DATA_STTS());
			query.setParameter(++i, bean.getXML_DATA_ID());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateGWXmlDataSrc|Success");
			log.info(user.getName() + "|" + page + "|updateGWXmlDataSrc|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateGWXmlDataSrc|Exception:" + e.getMessage());
			throw e;
		}
	}

}
