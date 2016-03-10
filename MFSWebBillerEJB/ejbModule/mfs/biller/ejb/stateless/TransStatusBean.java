package mfs.biller.ejb.stateless;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.TransStatusBeanLocal;
import mfs.biller.ejb.interfaces.TransStatusBeanRemote;
import mfs.biller.persistence.bean.TransStatus;
import mfs.biller.util.Timer;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

/**
 * Session Bean implementation class TransStatusBean
 */

@Stateless(name = "TransStatusBean", mappedName = "mfs.biller.ejb.TransStatusBean")
@CallByReference
public class TransStatusBean implements TransStatusBeanRemote,
		TransStatusBeanLocal {
	private Logger log = Logger.getLogger("EJBTRANSSTTS");
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public Collection<TransStatus> getTransStatusAll(String TRNS_STTS_CODE,
			String TRNS_STTS_NAME) throws Exception {

		try {

			Timer timer = new Timer("-");
			log.info("TransStatusBean|getTransStatusAll|Time:" + timer.getStartTime());
			String sql = "SELECT TRNS_STTS_CODE, TRNS_STTS_NAME, ACT_FLAG "
					+ "FROM GW_TRNS_STTS ";

			Vector<String> v = new Vector<String>();

			if (TRNS_STTS_CODE != null && !"".equals(TRNS_STTS_CODE))
				v.add("TRNS_STTS_CODE='" + TRNS_STTS_CODE + "'");

			if (TRNS_STTS_NAME != null && !"".equals(TRNS_STTS_NAME))
				v.add("TRNS_STTS_NAME='" + TRNS_STTS_NAME + "'");

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
			sql += " ORDER BY TRNS_STTS_NAME desc";

			log.info("TransStatusBean|getTransStatusAll|sql:" + sql);

			Collection<TransStatus> colreturn = new Vector();

			Query query = em.createNativeQuery(sql, TransStatus.class);

			List list = (List) query.getResultList();
			log.info("TransStatusBean|getTransStatusAll|list.size:"
					+ list.size());

			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				TransStatus mastTrnsPrt = (TransStatus) o;
				
				log.info("TransStatusBean|getTransStatusAll|TransStatus:"+mastTrnsPrt.getTRNS_STTS_CODE()+
						"|"+mastTrnsPrt.getTRNS_STTS_NAME() +"|"+mastTrnsPrt.getACT_FLAG());

				colreturn.add(mastTrnsPrt);
			}
			log.info("TransStatusBean|getTransStatusAll|Time:" + timer.getStopTime());
			return colreturn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("TransStatusBean|getTransStatusAll|Exception:" + e);
		}

		return new Vector();
	}

	public TransStatus findTransStatus(String TRNS_STTS_CODE,
			String TRNS_STTS_NAME) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info("TransStatusBean|findTransStatus|Time:" + timer.getStartTime());
			String sql = "SELECT TRNS_STTS_CODE, TRNS_STTS_NAME, ACT_FLAG "
					+ "FROM GW_TRNS_STTS ";

			Vector<String> v = new Vector<String>();

			if (TRNS_STTS_CODE != null && !"".equals(TRNS_STTS_CODE))
				v.add("TRNS_STTS_CODE='" + TRNS_STTS_CODE + "'");

			if (TRNS_STTS_NAME != null && !"".equals(TRNS_STTS_NAME))
				v.add("TRNS_STTS_NAME='" + TRNS_STTS_NAME + "'");

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
			sql += " ORDER BY TRNS_STTS_NAME desc";

			log.info("TransStatusBean|findTransStatus|sql:" + sql);

			Query query = em.createNativeQuery(sql, TransStatus.class);

			List<TransStatus> listRow = query.getResultList();

			TransStatus mastTrnsPrtn = (TransStatus) listRow.get(0);
			log.info("TransStatusBean|findTransStatus|TransStatus:"
					+ mastTrnsPrtn.getTRNS_STTS_CODE() + "|"
					+ mastTrnsPrtn.getTRNS_STTS_NAME() + "|"
					+ mastTrnsPrtn.getACT_FLAG());
			log.info("TransStatusBean|findTransStatus|Time:" + timer.getStopTime());
			return mastTrnsPrtn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("TransStatusBean|findTransStatus|Exception:" + e);
		}

		return new TransStatus();
	}
}
