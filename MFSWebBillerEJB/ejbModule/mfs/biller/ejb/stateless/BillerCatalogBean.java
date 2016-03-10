package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.BillerCatalogBeanLocal;
import mfs.biller.ejb.interfaces.BillerCatalogBeanRemote;
import mfs.biller.persistence.bean.BillerCategory;
import mfs.biller.persistence.bean.BillerCategoryParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;
import mfs.exception.IsExistException;

import org.apache.log4j.Logger;

@Stateless(name = "BillerCatalogBean", mappedName = "mfs.biller.ejb.BillerCatalogBean")
@CallByReference
public class BillerCatalogBean implements BillerCatalogBeanLocal, BillerCatalogBeanRemote{
	
	private Logger log = Logger.getLogger("EJBBLLRCATG");
	private String page = "BillerCatalogBean";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	
	public List<BillerCategory> searchBillerCatalog(BillerCategoryParam PARAM, UserInfoBean user)throws Exception{
		List<BillerCategory> list = null;
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBillerCatalog|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBillerCatalog|Param|" + PARAM.toString());
			
			String sql = "SELECT * FROM BILLER_CATEGORY ";

			Vector<String> v = new Vector<String>();
			StringBuffer sb = new StringBuffer();
			if (!ValidateUtil.isEmpty(PARAM.getBLLR_CATG_ID())){
				v.add("BLLR_CATG_ID = " + PARAM.getBLLR_CATG_ID());
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
			sql += " ORDER BY BLLR_CATG_MENU_SEQ";
			
			log.info(user.getName() + "|" + page + "|searchBillerCatalog|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BillerCategory.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchBillerCatalog|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchBillerCatalog|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|searchBillerCatalog|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}
	
	public BillerCategory findBillerCatalog(int BLLR_CATG_ID, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerCatalog|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_CATEGORY WHERE BLLR_CATG_ID = " + BLLR_CATG_ID;
			log.info(user.getName() + "|" + page + "|findBillerCatalog|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BillerCategory.class);
			List<BillerCategory> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerCatalog|Time:" + timer.getStopTime());
			if ( (list != null) && (list.size() > 0)){
				return (BillerCategory)list.get(0);
			}else{
				throw new Exception("Not Found Data");
			}
			
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|findBillerCatalog|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public boolean isExistBllrCatgName(int BLLR_CATG_ID, String BLLR_CATG_NAME, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistBllrCatgName|Time:" + timer.getStartTime());
			String sql = "SELECT count(*) FROM BILLER_CATEGORY WHERE BLLR_CATG_NAME = '" + BLLR_CATG_NAME + "' ";
			if (BLLR_CATG_ID > 0){
				sql += " AND BLLR_CATG_ID <> " + BLLR_CATG_ID;
			}
			log.info(user.getName() + "|" + page + "|isExistBllrCatgName|SQL:" + sql);
			
			boolean bResult = true;
			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal)query.getSingleResult();
			if (result.intValue() > 0){
				bResult = true;
			}else{
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistBllrCatgName|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistBllrCatgName|Time:" + timer.getStopTime());
			return bResult;
	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|isExistBllrCatgName|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public boolean isExistBillMenuSeq(int BLLR_CATG_ID, int BLLR_CATG_MENU_SEQ, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistBillMenuSeq|Time:" + timer.getStartTime());
			String sql = "SELECT count(*) FROM BILLER_CATEGORY WHERE BLLR_CATG_MENU_SEQ = " + BLLR_CATG_MENU_SEQ + " ";
			if (BLLR_CATG_ID > 0){
				sql += " AND BLLR_CATG_ID <> " + BLLR_CATG_ID;
			}
			log.info(user.getName() + "|" + page + "|isExistBillMenuSeq|SQL:" + sql);
			
			boolean bResult = true;
			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal)query.getSingleResult();
			if (result.intValue() > 0){
				bResult = true;
			}else{
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistBillMenuSeq|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistBillMenuSeq|Time:" + timer.getStopTime());
			return bResult;
	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|isExistBillMenuSeq|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public int insertBillerCatalog(BillerCategory bean, UserInfoBean user)throws IsExistException, Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBillerCatalog|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBillerCatalog|Param|" + bean.toString());
			
			if (isExistBllrCatgName(0, bean.getBLLR_CATG_NAME(), user)){
				throw new IsExistException("BLLR_CATG_NAME");
			}
			
			if (isExistBillMenuSeq(0, bean.getBLLR_CATG_MENU_SEQ(), user)){
				throw new IsExistException("BLLR_CATG_MENU_SEQ");
			}
			
			Query query = em.createNativeQuery("SELECT SEQ_BILLER_CATEGORY.nextval from DUAL");
			BigDecimal result = (BigDecimal)query.getSingleResult();
			int BLLR_CATG_ID = result.intValue();
			
			log.info(user.getName() + "|" + page + "|insertBillerCatalog|BLLR_CATG_ID:" + BLLR_CATG_ID);
			
			String sql = "INSERT INTO BILLER_CATEGORY(BLLR_CATG_ID, BLLR_CATG_NAME, BLLR_CATG_DESC, ACT_FLAG, BLLR_CATG_MENU_SEQ, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY, LAST_CHNG_DTTM)"
					   + "VALUES(?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE)";
			int i = 0;
			query = em.createNativeQuery(sql);
			query.setParameter(++i, BLLR_CATG_ID);
			query.setParameter(++i, bean.getBLLR_CATG_NAME());
			query.setParameter(++i, bean.getBLLR_CATG_DESC());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getBLLR_CATG_MENU_SEQ());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, user.getName());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|insertBillerCatalog|Success");
			log.info(user.getName() + "|" + page + "|insertBillerCatalog|Time|" + timer.getStopTime());
			return BLLR_CATG_ID;
			
		}catch(IsExistException e){
			log.error(user.getName() + "|" + page + "|insertBillerCatalog|IsExistException:" + e.getMessage());
			throw e;
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertBillerCatalog|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public void updateBillerCatalog(BillerCategory bean, UserInfoBean user)throws IsExistException, Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerCatalog|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerCatalog|Param|" + bean.toString());
			
			if (isExistBllrCatgName(bean.getBLLR_CATG_ID(), bean.getBLLR_CATG_NAME(), user)){
				throw new IsExistException("BLLR_CATG_NAME");
			}
			
			if (isExistBillMenuSeq(bean.getBLLR_CATG_ID(), bean.getBLLR_CATG_MENU_SEQ(), user)){
				throw new IsExistException("BLLR_CATG_MENU_SEQ");
			}
			
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_CATEGORY ")
					.append("SET BLLR_CATG_NAME = ? ")
					.append(", BLLR_CATG_DESC = ? ")
					.append(", ACT_FLAG = ? ")
					.append(", BLLR_CATG_MENU_SEQ = ? ")
					.append(", LAST_CHNG_BY = ? ")
					.append(", LAST_CHNG_DTTM = SYSDATE ")
					.append("WHERE BLLR_CATG_ID = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBLLR_CATG_NAME());
			query.setParameter(++i, bean.getBLLR_CATG_DESC());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getBLLR_CATG_MENU_SEQ());
			query.setParameter(++i, bean.getLAST_CHNG_BY());
			query.setParameter(++i, bean.getBLLR_CATG_ID());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateBillerCatalog|Success");
			log.info(user.getName() + "|" + page + "|updateBillerCatalog|Time|" + timer.getStopTime());
		}catch(IsExistException e){
			log.error(user.getName() + "|" + page + "|updateBillerCatalog|IsExistException:" + e.getMessage());
			throw e;	
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateBillerCatalog|Exception:" + e.getMessage());
			throw e;
		}
	}
}
