package mfs.biller.ejb.stateless;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.GWInbnSrvcBeanLocal;
import mfs.biller.ejb.interfaces.GWInbnSrvcBeanRemote;
import mfs.biller.persistence.bean.GWInbnSrvc;
import mfs.biller.util.Timer;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

@Stateless(name = "GWInbnSrvcBean", mappedName = "mfs.biller.ejb.GWInbnSrvcBean")
@CallByReference
public class GWInbnSrvcBean implements GWInbnSrvcBeanRemote,
		GWInbnSrvcBeanLocal {
	
	private Logger log = Logger.getLogger("EJBINBNSRVC");
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	Timer timer = new Timer("-");
	@SuppressWarnings("unchecked")
	public Collection<GWInbnSrvc> getGWInbnSrvcAll(String INBN_SRVC_ID,
			String INBN_SRVC_NAME) throws Exception {

		try {
			log.info("GWInbnSrvcBean|getGWInbnSrvcAll|Time:" + timer.getStartTime());
			String sql = "SELECT * "
					+ "FROM GW_INBN_SRVC ";

			Vector<String> v = new Vector<String>();

			if (INBN_SRVC_ID != null && !"".equals(INBN_SRVC_ID))
				v.add("INBN_SRVC_ID='" + INBN_SRVC_ID + "'");

			if (INBN_SRVC_NAME != null && !"".equals(INBN_SRVC_NAME))
				v.add("INBN_SRVC_NAME='" + INBN_SRVC_NAME + "'");

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
			sql += " ORDER BY INBN_SRVC_NAME desc";

			log.info("GWInbnSrvcBean|getGWInbnSrvcAll|sql:" + sql);

			Collection<GWInbnSrvc> colreturn = new Vector();

			Query query = em.createNativeQuery(sql, GWInbnSrvc.class);

			List list = (List) query.getResultList();
			log.info("GWInbnSrvcBean|getGWInbnSrvcAll|list.size():" + list.size());

			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				GWInbnSrvc mastTrnsPrt = (GWInbnSrvc) o;

				colreturn.add(mastTrnsPrt);
			}
			log.info("GWInbnSrvcBean|getGWInbnSrvcAll|Time:" + timer.getStopTime());
			return colreturn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("GWInbnSrvcBean|getGWInbnSrvcAll|Exception:" + e);
		}

		return new Vector();
	}

	public GWInbnSrvc findGWInbnSrvc(String INBN_SRVC_ID, String INBN_SRVC_NAME)
			throws Exception {
		try {
			log.info("GWInbnSrvcBean|findGWInbnSrvc|Time:" + timer.getStartTime());
			String sql = "SELECT * "
					+ "FROM GW_INBN_SRVC ";

			Vector<String> v = new Vector<String>();

			if (INBN_SRVC_ID != null && !"".equals(INBN_SRVC_ID))
				v.add("INBN_SRVC_ID='" + INBN_SRVC_ID + "'");

			if (INBN_SRVC_NAME != null && !"".equals(INBN_SRVC_NAME))
				v.add("INBN_SRVC_NAME='" + INBN_SRVC_NAME + "'");

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
			sql += " ORDER BY INBN_SRVC_NAME desc";

			log.info("GWInbnSrvcBean|findGWInbnSrvc|sql:" + sql);

			Query query = em.createNativeQuery(sql, GWInbnSrvc.class);

			List<GWInbnSrvc> listRow = query.getResultList();

			GWInbnSrvc outBound = (GWInbnSrvc) listRow.get(0);
			
			log.info("GWInbnSrvcBean|findGWInbnSrvc|GWInbnSrvc:"
					+ outBound.toString());
			log.info("GWInbnSrvcBean|findGWInbnSrvc|Time:" + timer.getStopTime());
			return outBound;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("GWInbnSrvcBean|findGWInbnSrvc|Exception:" + e);
		}

		return new GWInbnSrvc();
	}
}