package mfs.biller.ejb.stateless;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.GWOutboundBeanLocal;
import mfs.biller.ejb.interfaces.GWOutboundBeanRemote;
import mfs.biller.persistence.bean.GWOutbound;
import mfs.biller.util.Timer;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

@Stateless(name = "GWOutboundBean", mappedName = "mfs.biller.ejb.GWOutboundBean")
@CallByReference
public class GWOutboundBean implements GWOutboundBeanRemote,
		GWOutboundBeanLocal {
	
	private Logger log = Logger.getLogger("EJBOUTBOND");
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public Collection<GWOutbound> getGWOutboundAll(String GW_OUTB_ID,
			String GW_OUTB_NAME) throws Exception {

		try {

			Timer timer = new Timer("-");
			log.info("GWOutboundBean|getGWOutboundAll|Time:" + timer.getStartTime());
			String sql = "SELECT * "
					+ "FROM GW_OUTBOUND ";

			Vector<String> v = new Vector<String>();

			if (GW_OUTB_ID != null && !"".equals(GW_OUTB_ID))
				v.add("GW_OUTB_ID='" + GW_OUTB_ID + "'");

			if (GW_OUTB_NAME != null && !"".equals(GW_OUTB_NAME))
				v.add("GW_OUTB_NAME='" + GW_OUTB_NAME + "'");

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
			sql += " ORDER BY GW_OUTB_NAME desc";

			log.info("GWOutboundBean|getGWOutboundAll|sql:" + sql);

			Collection<GWOutbound> colreturn = new Vector();

			Query query = em.createNativeQuery(sql, GWOutbound.class);

			List list = (List) query.getResultList();
			log.info("GWOutboundBean|getGWOutboundAll|list.size():" + list.size());

			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				GWOutbound mastTrnsPrt = (GWOutbound) o;

				colreturn.add(mastTrnsPrt);
			}
			log.info("GWOutboundBean|getGWOutboundAll|Time:" + timer.getStopTime());
			return colreturn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("GWOutboundBean|getGWOutboundAll|Exception:" + e);
		}

		return new Vector();
	}

	public GWOutbound findGWOutbound(String GW_OUTB_ID, String GW_OUTB_NAME)
			throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info("GWOutboundBean|findGWOutbound|Time:" + timer.getStartTime());
			String sql = "SELECT * "
					+ "FROM GW_OUTBOUND ";

			Vector<String> v = new Vector<String>();

			if (GW_OUTB_ID != null && !"".equals(GW_OUTB_ID))
				v.add("GW_OUTB_ID='" + GW_OUTB_ID + "'");

			if (GW_OUTB_NAME != null && !"".equals(GW_OUTB_NAME))
				v.add("GW_OUTB_NAME='" + GW_OUTB_NAME + "'");

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
			sql += " ORDER BY GW_OUTB_NAME desc";

			log.info("GWOutboundBean|findGWOutbound|sql:" + sql);

			Query query = em.createNativeQuery(sql, GWOutbound.class);

			List<GWOutbound> listRow = query.getResultList();

			GWOutbound outBound = (GWOutbound) listRow.get(0);
			
			log.info("GWOutboundBean|findGWOutbound|GWOutbound:"
					+ outBound.toString());
			log.info("GWOutboundBean|findGWOutbound|Time:" + timer.getStopTime());
			return outBound;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("GWOutboundBean|findGWOutbound|Exception:" + e);
		}

		return new GWOutbound();
	}
}