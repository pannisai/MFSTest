package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.BillerChannelBeanLocal;
import mfs.biller.ejb.interfaces.BillerChannelBeanRemote;
import mfs.biller.persistence.bean.BillerChannel;
import mfs.biller.persistence.bean.BillerChannelParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;
import mfs.exception.IsExistException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

@Stateless(name = "BillerChannelBean", mappedName = "mfs.biller.ejb.BillerChannelBean")
@CallByReference
public class BillerChannelBean implements BillerChannelBeanRemote, BillerChannelBeanLocal {
	
	private Logger log = Logger.getLogger("EJBBLLRCHNL");
	private String page = "BillerChannelBean";
	
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public Collection<BillerChannel> getBillerChannelAll(String BLLR_CHNL_CODE, String BLLR_CHNL_NAME) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info("BillerChannelBean|getBillerChannelAll|Time:" + timer.getStartTime());
			String sql = "SELECT * "
					+ "FROM BILLER_CHANNEL ";

			Vector<String> v = new Vector<String>();

			if (BLLR_CHNL_CODE != null && !"".equals(BLLR_CHNL_CODE))
				v.add("BLLR_CHNL_CODE='" + BLLR_CHNL_CODE + "'");

			if (BLLR_CHNL_NAME != null && !"".equals(BLLR_CHNL_NAME))
				v.add("BLLR_CHNL_NAME='" + BLLR_CHNL_NAME + "'");

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
			sql += " ORDER BY BLLR_CHNL_CODE desc";

			log.info("BillerChannelBean|getBillerChannelAll|sql:" + sql);

			Collection<BillerChannel> colreturn = new Vector();

			Query query = em.createNativeQuery(sql, BillerChannel.class);

			List list = (List) query.getResultList();
			log.info("BillerChannelBean|getBillerChannelAll|list.size:"
					+ list.size());

			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				BillerChannel billerChannel = (BillerChannel) o;
				
				log.info("BillerChannelBean|getBillerChannelAll|BillerChannel:"+billerChannel.getBLLR_CHNL_ID()+
						"|"+billerChannel.getBLLR_CHNL_NAME()+"|"+billerChannel.getBLLR_CHNL_CODE());

				colreturn.add(billerChannel);
			}
			log.info("BillerChannelBean|getBillerChannelAll|Time:" + timer.getStopTime());
			return colreturn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("BillerChannelBean|getBillerChannelAll|Exception:" + e);
		}

		return new Vector();
	}

	public BillerChannel findBillerChannel(String BLLR_CHNL_CODE, String BLLR_CHNL_NAME) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info("BillerChannelBean|findBillerChannel|Time:" + timer.getStartTime());

			String sql = "SELECT BLLR_CHNL_ID, BLLR_CHNL_NAME, BLLR_CHNL_CODE  "
					+ "FROM BILLER_CHANNEL ";

			Vector<String> v = new Vector<String>();

			if (BLLR_CHNL_CODE != null && !"".equals(BLLR_CHNL_CODE))
				v.add("BLLR_CHNL_CODE='" + BLLR_CHNL_CODE + "'");

			if (BLLR_CHNL_NAME != null && !"".equals(BLLR_CHNL_NAME))
				v.add("BLLR_CHNL_NAME='" + BLLR_CHNL_NAME + "'");

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
			sql += " ORDER BY BLLR_CHNL_CODE desc";

			log.info("BillerChannelBean|findBillerChannel|sql:" + sql);

			Query query = em.createNativeQuery(sql, BillerChannel.class);

			List<BillerChannel> listRow = query.getResultList();

			BillerChannel mastTrnsPrtn = (BillerChannel) listRow.get(0);
			log.info("BillerChannelBean|findBillerChannel|BillerChannel:"
					+ mastTrnsPrtn.getBLLR_CHNL_ID() + "|"
					+ mastTrnsPrtn.getBLLR_CHNL_NAME() + "|"
					+ mastTrnsPrtn.getBLLR_CHNL_CODE());
			log.info("BillerChannelBean|findBillerChannel|Time:" + timer.getStopTime());
			return mastTrnsPrtn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("BillerChannelBean|findBillerChannel|Exception:" + e);
		}

		return new BillerChannel();
	}
	
	public List<BillerChannel> searchBillerChannel(BillerChannelParam PARAM, UserInfoBean user)throws Exception{
		List<BillerChannel> list = null;
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBillerChannel|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBillerChannel|Param|" + PARAM.toString());
			
			String sql = "SELECT * FROM BILLER_CHANNEL ";

			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(PARAM.getBLLR_CHNL_ID())){
				v.add("BLLR_CHNL_ID = " + PARAM.getBLLR_CHNL_ID());
			}
			if (!ValidateUtil.isEmpty(PARAM.getACT_FLAG())){
				v.add("ACT_FLAG = '" + PARAM.getACT_FLAG() + "'");
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
			sql += " ORDER BY BLLR_CHNL_ID";
			
			log.info(user.getName() + "|" + page + "|searchBillerChannel|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BillerChannel.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchBillerChannel|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchBillerChannel|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|searchBillerChannel|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}
	
	public BillerChannel findBillerChannel(int BLLR_CHNL_ID, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerChannel|Time:" +timer.getStartTime());
			String sql = "SELECT * FROM BILLER_CHANNEL WHERE BLLR_CHNL_ID = " + BLLR_CHNL_ID;
			log.info(user.getName() + "|" + page + "|findBillerChannel|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BillerChannel.class);
			List<BillerChannel> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerChannel|Time:" +timer.getStopTime());
			if ( (list != null) && (list.size() > 0)){
				return (BillerChannel)list.get(0);
			}else{
				throw new Exception("Not Found Data");
			}
				
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|findBillerChannel|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public boolean isExistBllrChnlName(int BLLR_CHNL_ID, String BLLR_CHNL_NAME, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistBllrChnlName|Time:" + timer.getStartTime());
			String sql = "SELECT count(*) FROM BILLER_CHANNEL WHERE BLLR_CHNL_NAME = '" + BLLR_CHNL_NAME + "' ";
			if (BLLR_CHNL_ID > 0){
				sql += " AND BLLR_CHNL_ID <> " + BLLR_CHNL_ID;
			}
			log.info(user.getName() + "|" + page + "|isExistBllrChnlName|SQL:" + sql);
			
			boolean bResult = true;
			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal)query.getSingleResult();
			if (result.intValue() > 0){
				bResult = true;
			}else{
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistBllrChnlName|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistBllrChnlName|Time:" + timer.getStopTime());
			return bResult;
	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|isExistBllrChnlName|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public boolean isExistBllrChnlCode(int BLLR_CHNL_ID, String BLLR_CHNL_CODE, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistBllrChnlCode|Time:" + timer.getStartTime());
			String sql = "SELECT count(*) FROM BILLER_CHANNEL WHERE BLLR_CHNL_CODE = '" + BLLR_CHNL_CODE + "' ";
			if (BLLR_CHNL_ID > 0){
				sql += " AND BLLR_CHNL_ID <> " + BLLR_CHNL_ID;
			}
			log.info(user.getName() + "|" + page + "|isExistBllrChnlCode|SQL:" + sql);
			
			boolean bResult = true;
			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal)query.getSingleResult();
			if (result.intValue() > 0){
				bResult = true;
			}else{
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistBllrChnlCode|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistBllrChnlCode|Time:" + timer.getStopTime());
			return bResult;
	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|isExistBllrChnlCode|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public int insertBillerChannel(BillerChannel bean, UserInfoBean user)throws IsExistException, Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBillerChannel|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBillerChannel|Param|" + bean.toString());
			
			if (isExistBllrChnlName(0, bean.getBLLR_CHNL_NAME(), user)){
				throw new IsExistException("BLLR_CHNL_NAME");
			}
			
			if (isExistBllrChnlCode(0, bean.getBLLR_CHNL_CODE(), user)){
				throw new IsExistException("BLLR_CHNL_CODE");
			}
			
			Query query = em.createNativeQuery("SELECT SEQ_BILLER_CHANNEL.nextval from DUAL");
			BigDecimal result = (BigDecimal)query.getSingleResult();
			int BLLR_CHNL_ID = result.intValue();
			
			log.info(user.getName() + "|" + page + "|insertBillerChannel|BLLR_CHNL_ID:" + BLLR_CHNL_ID);
			
			String sql = "INSERT INTO BILLER_CHANNEL(BLLR_CHNL_ID, BLLR_CHNL_NAME, BLLR_CHNL_CODE, ACT_FLAG, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY, LAST_CHNG_DTTM)"
					   + "VALUES(?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE)";
			int i = 0;
			query = em.createNativeQuery(sql);
			query.setParameter(++i, BLLR_CHNL_ID);
			query.setParameter(++i, bean.getBLLR_CHNL_NAME());
			query.setParameter(++i, bean.getBLLR_CHNL_CODE());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, user.getName());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|insertBillerChannel|Success");
			log.info(user.getName() + "|" + page + "|insertBillerChannel|Time|" + timer.getStopTime());
			return BLLR_CHNL_ID;
		
		}catch(IsExistException e){
			log.error(user.getName() + "|" + page + "|insertBillerChannel|IsExistException:" + e.getMessage());
			throw e;	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertBillerChannel|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public void updateBillerChannel(BillerChannel bean, UserInfoBean user)throws IsExistException, Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerChannel|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerChannel|Param|" + bean.toString());
			
			if (isExistBllrChnlName(bean.getBLLR_CHNL_ID(), bean.getBLLR_CHNL_NAME(), user)){
				throw new IsExistException("BLLR_CHNL_NAME");
			}
			
			if (isExistBllrChnlCode(bean.getBLLR_CHNL_ID(), bean.getBLLR_CHNL_CODE(), user)){
				throw new IsExistException("BLLR_CHNL_CODE");
			}
			
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_CHANNEL ")
					.append("SET BLLR_CHNL_NAME = ? ")
					.append(", BLLR_CHNL_CODE = ? ")
					.append(", ACT_FLAG = ? ")
					.append(", LAST_CHNG_BY = ? ")
					.append(", LAST_CHNG_DTTM = SYSDATE ")
					.append("WHERE BLLR_CHNL_ID = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBLLR_CHNL_NAME());
			query.setParameter(++i, bean.getBLLR_CHNL_CODE());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBLLR_CHNL_ID());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateBillerChannel|Success");
			log.info(user.getName() + "|" + page + "|updateBillerChannel|Time|" + timer.getStopTime());
		}catch(IsExistException e){
			log.error(user.getName() + "|" + page + "|updateBillerChannel|IsExistException:" + e.getMessage());
			throw e;	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateBillerChannel|Exception:" + e.getMessage());
			throw e;
		}
	}
}
