package mfs.biller.ejb.stateless;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.GWRcptConfBeanLocal;
import mfs.biller.ejb.interfaces.GWRcptConfBeanRemote;
import mfs.biller.persistence.bean.GWRcptConf;
import mfs.biller.util.Timer;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

@Stateless(name = "GWRcptConfBean", mappedName = "mfs.biller.ejb.GWRcptConfBean")
@CallByReference
public class GWRcptConfBean implements GWRcptConfBeanRemote,
		GWRcptConfBeanLocal {
	
	private Logger log = Logger.getLogger("EJBRCPTCONF");
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public Collection<GWRcptConf> getGWRcptConfAll(String GW_RCPT_CONF_ID,
			String GW_RCPT_CONF_NAME) throws Exception {

		try {

			Timer timer = new Timer("-");
			log.info("GWRcptConfBean|getGWRcptConfAll|Time:" + timer.getStartTime());
			
			String sql = "SELECT * "
					+ "FROM GW_RCPT_CONF ";

			Vector<String> v = new Vector<String>();

			if (GW_RCPT_CONF_ID != null && !"".equals(GW_RCPT_CONF_ID))
				v.add("GW_RCPT_CONF_ID='" + GW_RCPT_CONF_ID + "'");

			if (GW_RCPT_CONF_NAME != null && !"".equals(GW_RCPT_CONF_NAME))
				v.add("GW_RCPT_CONF_NAME='" + GW_RCPT_CONF_NAME + "'");

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
			sql += " ORDER BY GW_RCPT_CONF_NAME desc";

			log.info("GWRcptConfBean|getGWRcptConfAll|sql:" + sql);

			Collection<GWRcptConf> colreturn = new Vector();

			Query query = em.createNativeQuery(sql, GWRcptConf.class);

			List list = (List) query.getResultList();
			log.info("GWRcptConfBean|getGWRcptConfAll|list.size():" + list.size());

			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				GWRcptConf mastTrnsPrt = (GWRcptConf) o;

				colreturn.add(mastTrnsPrt);
			}
			log.info("GWRcptConfBean|getGWRcptConfAll|Time:" + timer.getStopTime());
			return colreturn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("GWRcptConfBean|getGWRcptConfAll|Exception:" + e);
		}

		return new Vector();
	}

	public GWRcptConf findGWRcptConf(String GW_RCPT_CONF_ID, String GW_RCPT_CONF_NAME)
			throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info("GWRcptConfBean|findGWRcptConf|Time:" + timer.getStartTime());
			String sql = "SELECT * "
					+ "FROM GW_RCPT_CONF ";

			Vector<String> v = new Vector<String>();

			if (GW_RCPT_CONF_ID != null && !"".equals(GW_RCPT_CONF_ID))
				v.add("GW_RCPT_CONF_ID='" + GW_RCPT_CONF_ID + "'");

			if (GW_RCPT_CONF_NAME != null && !"".equals(GW_RCPT_CONF_NAME))
				v.add("GW_RCPT_CONF_NAME='" + GW_RCPT_CONF_NAME + "'");

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
			sql += " ORDER BY GW_RCPT_CONF_NAME desc";

			log.info("GWRcptConfBean|findGWRcptConf|sql:" + sql);

			Query query = em.createNativeQuery(sql, GWRcptConf.class);

			List<GWRcptConf> listRow = query.getResultList();

			GWRcptConf outBound = (GWRcptConf) listRow.get(0);
			
			log.info("GWRcptConfBean|findGWRcptConf|GWRcptConf:"
					+ outBound.toString());
			log.info("GWRcptConfBean|findGWRcptConf|Time:" + timer.getStopTime());
			return outBound;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("GWRcptConfBean|findGWRcptConf|Exception:" + e);
		}

		return new GWRcptConf();
	}
}