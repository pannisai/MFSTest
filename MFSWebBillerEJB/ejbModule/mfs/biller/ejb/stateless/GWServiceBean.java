package mfs.biller.ejb.stateless;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.GWServiceBeanLocal;
import mfs.biller.ejb.interfaces.GWServiceBeanRemote;
import mfs.biller.persistence.bean.GWService;
import mfs.biller.util.Timer;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

@Stateless(name = "GWServiceBean", mappedName = "mfs.biller.ejb.GWServiceBean")
@CallByReference
public class GWServiceBean implements GWServiceBeanRemote, GWServiceBeanLocal {

	private Logger log = Logger.getLogger("EJBGWSERVICE");
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public Collection<GWService> getGWServiceAll(String GW_SRVC_ID,
			String GW_SRVC_NAME) throws Exception {

		try {

			Timer timer = new Timer("-");
			log.info("GWServiceBean|getGWServiceAll|Time:" + timer.getStartTime());
			String sql = "SELECT * " + "FROM GW_OUTBOUND ";

			Vector<String> v = new Vector<String>();

			if (GW_SRVC_ID != null && !"".equals(GW_SRVC_ID))
				v.add("GW_SRVC_ID='" + GW_SRVC_ID + "'");

			if (GW_SRVC_NAME != null && !"".equals(GW_SRVC_NAME))
				v.add("GW_SRVC_NAME='" + GW_SRVC_NAME + "'");

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
			sql += " ORDER BY GW_SRVC_NAME desc";

			log.info("GWServiceBean|getGWServiceAll|sql:" + sql);

			Collection<GWService> colreturn = new Vector();

			Query query = em.createNativeQuery(sql, GWService.class);

			List list = (List) query.getResultList();
			log.info("GWServiceBean|getGWServiceAll|list.size():" + list.size());

			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				GWService mastTrnsPrt = (GWService) o;

				colreturn.add(mastTrnsPrt);
			}
			log.info("GWServiceBean|getGWServiceAll|Time:" + timer.getStopTime());
			return colreturn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("GWServiceBean|getGWServiceAll|Exception:" + e);
		}

		return new Vector();
	}

	public GWService findGWService(String GW_SRVC_ID, String GW_SRVC_NAME)
			throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info("GWServiceBean|findGWService|Time:" + timer.getStartTime());

			String sql = "SELECT * " + "FROM GW_OUTBOUND ";

			Vector<String> v = new Vector<String>();

			if (GW_SRVC_ID != null && !"".equals(GW_SRVC_ID))
				v.add("GW_SRVC_ID='" + GW_SRVC_ID + "'");

			if (GW_SRVC_NAME != null && !"".equals(GW_SRVC_NAME))
				v.add("GW_SRVC_NAME='" + GW_SRVC_NAME + "'");

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
			sql += " ORDER BY GW_SRVC_NAME desc";

			log.info("GWServiceBean|findGWService|sql:" + sql);

			Query query = em.createNativeQuery(sql, GWService.class);

			List<GWService> listRow = query.getResultList();

			GWService outBound = (GWService) listRow.get(0);

			log.info("GWServiceBean|findGWService|GWService:"
					+ outBound.toString());
			log.info("GWServiceBean|findGWService|Time:" + timer.getStopTime());

			return outBound;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("GWServiceBean|findGWService|Exception:" + e);
		}

		return new GWService();
	}
}