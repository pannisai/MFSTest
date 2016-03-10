package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.BillerFeeBeanLocal;
import mfs.biller.ejb.interfaces.BillerFeeBeanRemote;
import mfs.biller.persistence.bean.BillerFee;
import mfs.biller.persistence.bean.BillerFeeParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.DateTimeUtil;
import mfs.biller.util.Timer;
import mfs.exception.IsExistException;

import org.apache.log4j.Logger;

@Stateless(name = "BillerFeeBean", mappedName = "mfs.biller.ejb.BillerFeeBean")
@CallByReference
public class BillerFeeBean implements BillerFeeBeanLocal, BillerFeeBeanRemote{
	
	private Logger log = Logger.getLogger("EJBBLLRFEE");
	private String page = "BillerFeeBean";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<BillerFee> searchBillerFee(BillerFeeParam PARAM, UserInfoBean user)throws Exception{
		List<BillerFee> list = null;
		//String sqlStr = null;
		
		try{
			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBillerFee|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBillerFee|Param|" + PARAM.toString());
			
			StringBuilder sql = new StringBuilder();		
			sql.append("SELECT BF.*, SRV.BLLR_SRVC_NAME_EN, BFM.FEE_TYPE,"); 			
			sql.append(" DECODE(UPPER(BFM.FEE_TYPE), 'ABSORB',");
			sql.append(" DECODE(BF.FEE_AMOUNT,NULL,0.00,BF.FEE_AMOUNT) + DECODE(BF.FUNDAMO_FEE_AMOUNT,NULL,0.00,BF.FUNDAMO_FEE_AMOUNT),");
			sql.append(" 'SHARING', DECODE(BF.FUNDAMO_FEE_AMOUNT,NULL,0.00,BF.FUNDAMO_FEE_AMOUNT),");
			sql.append(" 0.00) SRVC_FEE");			
			sql.append(" FROM BILLER_FEE BF");   
			sql.append(" LEFT JOIN BILLER_SERVICE SRV"); 
			sql.append(" ON BF.BLLR_SRVC_ID = SRV.BLLR_SRVC_ID");
			sql.append(" LEFT JOIN BILLER_FEE_MAST BFM");
			sql.append(" ON BF.BLLR_FEE_MAST_ID = BFM.BLLR_FEE_MAST_ID");
			sql.append(" WHERE BF.ACT_FLAG = 'A'");

			Vector<String> v = new Vector<String>();
			if (PARAM.getFROM_DTTM() != null && !"".equals(PARAM.getFROM_DTTM()))
				v.add(" TRUNC(BF.EFFT_DATE) >= TO_DATE('"+DateTimeUtil.parseToString(PARAM.getFROM_DTTM(), "yyyy-MM-dd")+ "', 'YYYY-MM-DD')");

			if (PARAM.getTO_DTTM() != null && !"".equals(PARAM.getTO_DTTM()))
				v.add(" TRUNC(BF.EFFT_DATE) <= TO_DATE('"+DateTimeUtil.parseToString(PARAM.getTO_DTTM(), "yyyy-MM-dd")+ "', 'YYYY-MM-DD')");

			if (PARAM.getBLLR_SRVC_ID() != null && !"".equals(PARAM.getBLLR_SRVC_ID()))
				v.add(" BF.BLLR_SRVC_ID = '" + PARAM.getBLLR_SRVC_ID() + "'");
						
			if (!v.isEmpty()) {				
				for (int i = 0; i < v.size(); i++) {
					sql.append(" AND ");
					sql.append(v.get(i));
				}
			}			
			sql.append(" ORDER BY BF.BLLR_SRVC_ID ASC,BF.EFFT_DATE DESC");
			//sqlStr = "SELECT * FROM " + "( " + "SELECT a.*, rownum r__ " + "FROM " + "( " + sql.toString() + ") a " + "WHERE rownum < ((" + PARAM.getPAGE_NO() + " * " + PARAM.getPAGE_SIZE() + ") + 1 ) " + ") " + "WHERE r__ >= (((" + PARAM.getPAGE_NO() + "-1) * " + PARAM.getPAGE_SIZE() + ") + 1) ";			
			log.info(user.getName() + "|" + page + "|searchBillerFee|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql.toString(), BillerFee.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|searchBillerFee|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|searchBillerFee|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|searchBillerFee|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public BillerFee findBillerFee(int BLLR_FEE_ID, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerFee|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_FEE WHERE BLLR_FEE_ID = " + BLLR_FEE_ID;
			log.info(user.getName() + "|" + page + "|findBillerFee|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BillerFee.class);
			List<BillerFee> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerFee|Time:" + timer.getStopTime());
			if ( (list != null) && (list.size() > 0)){
				return (BillerFee)list.get(0);
			}else{
				throw new Exception("Not Found Data");
			}
			
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|findBillerFee|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public BillerFee findBillerFeeExpired (int BLLR_SRVC_ID, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBillerFee|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_FEE WHERE EXPR_DATE IS NULL AND ROWNUM = 1 AND BLLR_SRVC_ID = " + BLLR_SRVC_ID;
			log.info(user.getName() + "|" + page + "|findBillerFee|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BillerFee.class);
			List<BillerFee> list = query.getResultList();
			log.info(user.getName() + "|" + page + "|findBillerFee|Time:" + timer.getStopTime());
			if ( (list != null) && (list.size() > 0)){
				return (BillerFee)list.get(0);
			}else{
				throw new Exception("Not Found Data");
			}
			
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|findBillerFee|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public int insertBillerFee(BillerFee bean, UserInfoBean user)throws IsExistException, Exception{
		try{
			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|insertBillerFee|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|insertBillerFee|Param|" + bean.toString());
			
			Query query = em.createNativeQuery("SELECT SEQ_BILLER_FEE.nextval from DUAL");
			BigDecimal result = (BigDecimal)query.getSingleResult();
			int BLLR_FEE_ID = result.intValue();
			
			log.info(user.getName() + "|" + page + "|insertBillerFee|BLLR_FEE_ID:" + BLLR_FEE_ID);
			
			String sql = "INSERT INTO BILLER_FEE(BLLR_FEE_ID, BLLR_SRVC_ID, BLLR_FEE_MAST_ID, FEE_AMOUNT, FUNDAMO_FEE_AMOUNT, EFFT_DATE, ACT_FLAG, CRTD_BY, CRTD_DTTM, LAST_CHNG_BY, LAST_CHNG_DTTM)"
					   + "VALUES(?, ?, ?, ?, ?, ?, 'A', ?, SYSDATE, ?, SYSDATE)";
			int i = 0;		
			query = em.createNativeQuery(sql);
			query.setParameter(++i, BLLR_FEE_ID);
			query.setParameter(++i, bean.getBLLR_SRVC_ID());
			query.setParameter(++i, bean.getBLLR_FEE_MAST_ID());
			query.setParameter(++i, bean.getFEE_AMOUNT());
			query.setParameter(++i, bean.getFUNDAMO_FEE_AMOUNT());
			query.setParameter(++i, bean.getEFFT_DATE());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, user.getName());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|insertBillerFee|Success");
			log.info(user.getName() + "|" + page + "|insertBillerFee|Time|" + timer.getStopTime());
			return BLLR_FEE_ID;
			
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|insertBillerFee|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public void updateBillerFee(BillerFee bean, UserInfoBean user)throws IsExistException, Exception{
		try{
			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|updateBillerFee|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|updateBillerFee|Param|" + bean.toString());
			
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE BILLER_FEE ")
					.append("SET BLLR_FEE_MAST_ID = ? ")
					.append(", FUNDAMO_FEE_AMOUNT = ? ")
					.append(", FEE_AMOUNT = ? ")
					.append(", ACT_FLAG = ? ")
					.append(", EFFT_DATE = ? ")
					.append(", EXPR_DATE = ? ")
					.append(", LAST_CHNG_BY = ? ")
					.append(", LAST_CHNG_DTTM = SYSDATE ")
					.append("WHERE BLLR_FEE_ID = ? ");
			
			int i = 0;
			Query query = em.createNativeQuery(sb.toString());
			query.setParameter(++i, bean.getBLLR_FEE_MAST_ID());
			query.setParameter(++i, bean.getFUNDAMO_FEE_AMOUNT());
			query.setParameter(++i, bean.getFEE_AMOUNT());
			query.setParameter(++i, bean.getACT_FLAG());
			query.setParameter(++i, bean.getEFFT_DATE());
			query.setParameter(++i, bean.getEXPR_DATE());
			query.setParameter(++i, user.getName());
			query.setParameter(++i, bean.getBLLR_FEE_ID());
			query.executeUpdate();
			
			log.info(user.getName() + "|" + page + "|updateBillerFee|Success");
			log.info(user.getName() + "|" + page + "|updateBillerFee|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|updateBillerFee|Exception:" + e.getMessage());
			throw e;
		}
	}
	
	public boolean isExistBillerFee(BillerFeeParam PARAM, UserInfoBean user)throws Exception{
		try{
			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|isExistBillerFee|Time:" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|isExistBillerFee|Param|" + PARAM.toString());
			
			StringBuilder sql = new StringBuilder();		
			sql.append("SELECT count(*)"); 					
			sql.append(" FROM BILLER_FEE BF");   
			sql.append(" WHERE BF.ACT_FLAG = 'A'");
			Vector<String> v = new Vector<String>();
			// ### ADD ###
			if (PARAM.getFROM_DTTM() != null && !"".equals(PARAM.getFROM_DTTM()))				
				v.add(" NVL(TRUNC(BF.EXPR_DATE),TRUNC(BF.EFFT_DATE)) >= TO_DATE('"+DateTimeUtil.parseToString(PARAM.getFROM_DTTM(), "yyyy-MM-dd")+ "', 'YYYY-MM-DD')");
			// ### UPDATE ###
			if (PARAM.getTO_DTTM() != null && !"".equals(PARAM.getTO_DTTM()))
				v.add(" NVL(TRUNC(BF.EXPR_DATE),TRUNC(BF.EFFT_DATE)) > TO_DATE('"+DateTimeUtil.parseToString(PARAM.getTO_DTTM(), "yyyy-MM-dd")+ "', 'YYYY-MM-DD')");

			if (PARAM.getBLLR_FEE_ID() != null && !"".equals(PARAM.getBLLR_FEE_ID()))
				v.add(" BF.BLLR_FEE_ID <> '" + PARAM.getBLLR_FEE_ID() + "'");

			if (PARAM.getBLLR_SRVC_ID() != null && !"".equals(PARAM.getBLLR_SRVC_ID()))
				v.add(" BF.BLLR_SRVC_ID = '" + PARAM.getBLLR_SRVC_ID() + "'");
						
			if (!v.isEmpty()) {				
				for (int i = 0; i < v.size(); i++) {
					sql.append(" AND ");
					sql.append(v.get(i));
				}
			}
			
			log.info(user.getName() + "|" + page + "|isExistBillerFee|SQL:" + sql);			
			boolean bResult = true;
			Query query = em.createNativeQuery(sql.toString());
			BigDecimal result = (BigDecimal)query.getSingleResult();
			if (result.intValue() > 0){
				bResult = true;
			}else{
				bResult = false;
			}
			log.info(user.getName() + "|" + page + "|isExistBillerFee|Result:" + bResult);
			log.info(user.getName() + "|" + page + "|isExistBillerFee|Time:" + timer.getStopTime());
			return bResult;
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|isExistBillerFee|Exception:" + e.getMessage());
			throw e;
		}
	}
}
