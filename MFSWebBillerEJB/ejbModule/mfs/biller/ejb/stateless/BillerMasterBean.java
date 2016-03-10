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

import mfs.biller.ejb.interfaces.BillerMasterBeanLocal;
import mfs.biller.ejb.interfaces.BillerMasterBeanRemote;
import mfs.biller.persistence.bean.BillerMaster;
import mfs.biller.persistence.bean.BillerMasterParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;
import mfs.exception.IsExistException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

@Stateless(name = "BillerMasterBean", mappedName = "mfs.biller.ejb.BillerMasterBean")
@CallByReference
public class BillerMasterBean implements BillerMasterBeanRemote, BillerMasterBeanLocal {
	
	private Logger log = Logger.getLogger("EJBBLLRMAST");
	private String page = "BillerMasterBean";
	
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public Collection<BillerMaster> getBillerMasterAll(String BLLR_MSTR_ID, String BLLR_CODE) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info("BillerMasterBean|getBillerMasterAll|Time:" + timer.getStartTime());
			String sql = "SELECT BLLR_MSTR_ID, BLLR_CODE "
					+ "FROM BILLER_MASTER ";

			Vector<String> v = new Vector<String>();

			if (BLLR_MSTR_ID != null && !"".equals(BLLR_MSTR_ID))
				v.add("BLLR_MSTR_ID='" + BLLR_MSTR_ID + "'");

			if (BLLR_CODE != null && !"".equals(BLLR_CODE))
				v.add("BLLR_CODE='" + BLLR_CODE + "'");

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
			sql += " ORDER BY BLLR_MSTR_ID ";

			log.info("BillerMasterBean|getBillerMasterAll|sql:" + sql);

			Collection<BillerMaster> colreturn = new Vector();

			Query query = em.createNativeQuery(sql, BillerMaster.class);

			List list = (List) query.getResultList();
			log.info("BillerMasterBean|getBillerMasterAll|list.list:"
					+ list.size());

			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				BillerMaster mastTrnsPrt = (BillerMaster) o;

				colreturn.add(mastTrnsPrt);
			}
			log.info("BillerMasterBean|getBillerMasterAll|Time:" + timer.getStopTime());
			return colreturn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("BillerMasterBean|getBillerMasterAll|Exception:" + e);
		}

		return new Vector();
	}

	public BillerMaster findBillerMaster(String BLLR_MSTR_ID, String BLLR_CODE)throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info("BillerMasterBean|findBillerMaster|Time:" + timer.getStartTime());
			String sql = "SELECT BLLR_MSTR_ID, BLLR_CODE "
					+ "FROM BILLER_MASTER ";

			Vector<String> v = new Vector<String>();

			if (BLLR_MSTR_ID != null && !"".equals(BLLR_MSTR_ID))
				v.add("BLLR_MSTR_ID='" + BLLR_MSTR_ID + "'");

			if (BLLR_CODE != null && !"".equals(BLLR_CODE))
				v.add("BLLR_CODE='" + BLLR_CODE + "'");

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
			sql += " ORDER BY BLLR_CODE desc";

			log.info("BillerMasterBean|findBillerMaster|sql:" + sql);

			Query query = em.createNativeQuery(sql, BillerMaster.class);

			List<BillerMaster> listRow = query.getResultList();

			BillerMaster mastTrnsPrtn = (BillerMaster) listRow.get(0);
			log.info("BillerMasterBean|findBillerMaster|BillerMaster:"
					+ mastTrnsPrtn.getBLLR_MSTR_ID() + "|"
					+ mastTrnsPrtn.getBLLR_CODE());
			log.info("BillerMasterBean|findBillerMaster|Time:" + timer.getStopTime());
			return mastTrnsPrtn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("BillerMasterBean|findBillerMaster|Exception:" + e);
		}

		return new BillerMaster();
	}
	
	public List<BillerMaster> searchBillerMaster(BillerMasterParam PARAM, UserInfoBean user)throws Exception{
		List<BillerMaster> list = null;
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBillerMaster|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBillerMaster|Param|" + PARAM.toString());
			
			String sql = "SELECT * FROM BILLER_MASTER ";

			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(PARAM.getBLLR_MSTR_ID())){
				v.add("BLLR_MSTR_ID = " + PARAM.getBLLR_MSTR_ID());
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
			sql += " ORDER BY BLLR_MSTR_ID";
			
			log.info(user.getName() + "|" + page + "|searchBillerMaster|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BillerMaster.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchBillerMaster|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchBillerMaster|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|searchBillerMaster|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}
	
	public BillerMaster findBillerMaster(int BLLR_MSTR_ID, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerMaster|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_MASTER WHERE BLLR_MSTR_ID = " + BLLR_MSTR_ID;
			log.info(user.getName() + "|" + page + "|findBillerMaster|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BillerMaster.class);
			List<BillerMaster> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerMaster|Time:" + timer.getStopTime());
			if ( (list != null) && (list.size() > 0)){
				return (BillerMaster)list.get(0);
			}else{
				throw new Exception("Not Found Data");
			}	
			
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|findBillerMaster|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public boolean isExistBllrMstrId(int BLLR_MSTR_ID, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistBllrMstrId|Time:" + timer.getStartTime());
			String sql = "SELECT count(*) FROM BILLER_MASTER WHERE BLLR_MSTR_ID = " + BLLR_MSTR_ID + " ";
			log.info(user.getName() + "|" + page + "|isExistBllrMstrId|SQL:" + sql);
			
			boolean bResult = true;
			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal)query.getSingleResult();
			if (result.intValue() > 0){
				bResult = true;
			}else{
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistBllrMstrId|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistBllrMstrId|Time:" + timer.getStopTime());
			return bResult;
	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|isExistBllrMstrId|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public boolean isExistBllrCode(int BLLR_MSTR_ID, String BLLR_CODE, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistBllrCode|Time:" + timer.getStartTime());
			String sql = "SELECT count(*) FROM BILLER_MASTER WHERE BLLR_CODE = '" + BLLR_CODE + "' ";
			if (BLLR_MSTR_ID > 0){
				sql += " AND BLLR_MSTR_ID <> " + BLLR_MSTR_ID;
			}
			log.info(user.getName() + "|" + page + "|isExistBllrCode|SQL:" + sql);
			
			boolean bResult = true;
			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal)query.getSingleResult();
			if (result.intValue() > 0){
				bResult = true;
			}else{
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistBllrCode|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistBllrCode|Time:" + timer.getStopTime());
			return bResult;
	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|isExistBllrCode|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public void insertBillerMaster(BillerMaster bean, UserInfoBean user)throws IsExistException, Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBillerMaster|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBillerMaster|Param|" + bean.toString());
			
			if (isExistBllrMstrId(bean.getBLLR_MSTR_ID(), user)){
				throw new IsExistException("BLLR_MSTR_ID");
			}
			
			if (isExistBllrCode(0, bean.getBLLR_CODE(), user)){
				throw new IsExistException("BLLR_CODE");
			}
			
			String sql = "INSERT INTO BILLER_MASTER(BLLR_MSTR_ID, BLLR_MSTR_NAME, NAME_THA, BLLR_CODE, BLLR_TAX_NUMB, COMP_CODE, ACT_FLAG, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY, LAST_CHNG_DTTM, BLLR_TAX_NUMB2)"
					   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, ?)";
			int i = 0;
			Query query = em.createNativeQuery(sql);
			query.setParameter(++i, bean.getBLLR_MSTR_ID());
			query.setParameter(++i, bean.getBLLR_MSTR_NAME());
			query.setParameter(++i, bean.getNAME_THA());
			query.setParameter(++i, bean.getBLLR_CODE());
			query.setParameter(++i, bean.getBLLR_TAX_NUMB());
			query.setParameter(++i, bean.getCOMP_CODE());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, bean.getBLLR_TAX_NUMB2());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|insertBillerMaster|Success");
			log.info(user.getName() + "|" + page + "|insertBillerMaster|Time|" + timer.getStopTime());
		}catch(IsExistException e){
			log.error(user.getName() + "|" + page + "|insertBillerMaster|IsExistException:" + e.getMessage());
			throw e;
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertBillerMaster|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public void updateBillerMaster(BillerMaster bean, UserInfoBean user)throws IsExistException, Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerMaster|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerMaster|Param|" + bean.toString());
			
			if (isExistBllrCode(bean.getBLLR_MSTR_ID(), bean.getBLLR_CODE(), user)){
				throw new IsExistException("BLLR_CODE");
			}
			
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_MASTER ")
					.append("SET BLLR_MSTR_NAME = ? ")
					.append(", NAME_THA = ? ")
					.append(", BLLR_CODE = ? ")
					.append(", BLLR_TAX_NUMB = ? ")
					.append(", COMP_CODE = ? ")
					.append(", ACT_FLAG = ? ")
					.append(", LAST_CHNG_BY = ? ")
					.append(", LAST_CHNG_DTTM = SYSDATE ")
					.append(", BLLR_TAX_NUMB2 = ? ")
					.append("WHERE BLLR_MSTR_ID = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBLLR_MSTR_NAME());
			query.setParameter(++i, bean.getNAME_THA());
			query.setParameter(++i, bean.getBLLR_CODE());
			query.setParameter(++i, bean.getBLLR_TAX_NUMB());
			query.setParameter(++i, bean.getCOMP_CODE());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getLAST_CHNG_BY());			
			query.setParameter(++i, bean.getBLLR_TAX_NUMB2());
			query.setParameter(++i, bean.getBLLR_MSTR_ID());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateBillerMaster|Success");
			log.info(user.getName() + "|" + page + "|updateBillerMaster|Time|" + timer.getStopTime());
		}catch(IsExistException e){
			log.error(user.getName() + "|" + page + "|updateBillerMaster|IsExistException:" + e.getMessage());
			throw e;
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateBillerMaster|Exception:" + e.getMessage());
			throw e;
		}
	}
}
