package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.bcm.process.model.BillerBlindRefView;
import mfs.biller.ejb.interfaces.RptTransBeanLocal;
import mfs.biller.ejb.interfaces.RptTransBeanRemote;
import mfs.biller.persistence.bean.BillerChannel;
import mfs.biller.persistence.bean.BillerFdmCode;
import mfs.biller.persistence.bean.BillerPymtCode;
import mfs.biller.persistence.bean.GWMasterTrans;
import mfs.biller.persistence.bean.GWMasterTransParam;
import mfs.biller.persistence.bean.ReportTransDetail;
import mfs.biller.persistence.bean.ReportTransParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.AppUtil;
import mfs.biller.util.DBUtil;
import mfs.biller.util.DateTimeUtil;
import mfs.biller.util.JpaResultUtil;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;
import mfs.constant.PersonalConstant;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@Stateless(name = "RptTransBean", mappedName = "mfs.biller.ejb.RptTransBean")
@CallByReference
public class RptTransBean implements RptTransBeanRemote, RptTransBeanLocal {

	private Logger log = Logger.getLogger("EJBRPTTRNS");
	private String page = "RptTransBean";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	
	DBUtil dbUtil=new DBUtil();
	
	@SuppressWarnings("unchecked")
	public Collection<GWMasterTrans> getMasterTransAll(GWMasterTransParam Param) throws Exception {
		List<GWMasterTrans> listMastTrnsPrtn = null;
		String sqlStr = null;
		
		try {
			Timer timer = new Timer("-");
			log.info("RptTransBean|getMasterTransAll|GWMasterTransParam:" + Param.toString());
			log.info("RptTransBean|getMasterTransAll|Time:" + timer.getStartTime());

			StringBuilder sb = new StringBuilder();			
			sb.append("SELECT * FROM VIEW_BILLER_TRANSACTION ");

			Vector<String> v = new Vector<String>();
			if (!AppUtil.isEmpty(Param.getTRNS_ID())) {
				v.add("TRNS_ID = '"+Param.getTRNS_ID()+"'");
			}
			if (!AppUtil.isEmpty(Param.getTRNS_SRVC_CODE())) {
				if(Param.getTRNS_SRVC_CODE().equals("0005")){
					if (!AppUtil.isEmpty(Param.getNETWORK_SERVICE())) {
						if(Param.getNETWORK_SERVICE().equals("1")){ //TOT
							v.add("TRNS_SRVC_CODE = '"+Param.getTRNS_SRVC_CODE()+"'");
							v.add("SUBSTR(REAL_TRNS_REF1,2,1) NOT IN ('2','4')");
						}else if(Param.getNETWORK_SERVICE().equals("2")){//TRUE
							v.add("TRNS_SRVC_CODE = '"+Param.getTRNS_SRVC_CODE()+"'");
							v.add("SUBSTR(REAL_TRNS_REF1,2,1) IN ('2','4')");
						}else{ //3.TT&T
							v.add("TRNS_SRVC_CODE = '0052'");
						}
					}
				}else{
					v.add("TRNS_SRVC_CODE = '"+Param.getTRNS_SRVC_CODE()+"'");
				}
			}
			if (!AppUtil.isEmpty(Param.getTRNS_SRCE_CHNL_CODE())) {
				v.add("TRNS_SRCE_CHNL_CODE = '"+Param.getTRNS_SRCE_CHNL_CODE()+"'");
			}
			if (!AppUtil.isEmpty(Param.getTRNS_DEST_CODE())) {
				v.add("TRNS_DEST_CODE = '"+Param.getTRNS_DEST_CODE()+"'");
			}		
			if (Param.getFROM_DTTM() != null) {
				v.add("TRNS_DTTM >= TO_DATE('"+DateTimeUtil.parseToString(Param.getFROM_DTTM(), "yyyy-MM-dd HH:mm:ss")+"', 'YYYY-MM-DD HH24:MI:SS')");
			}
			if (Param.getTO_DTTM() != null) {
				v.add("TRNS_DTTM <= TO_DATE('"+DateTimeUtil.parseToString(Param.getTO_DTTM(), "yyyy-MM-dd HH:mm:ss")+"', 'YYYY-MM-DD HH24:MI:SS')");
			}
			if (!AppUtil.isEmpty(Param.getBRANCH())) {
				v.add("SSO_LOCN_CODE_39 = '"+Param.getBRANCH()+"'");
			}				
			if (!AppUtil.isEmpty(Param.getCHANNEL_SERVICE())) {			
				if("0".equals(Param.getCHANNEL_SERVICE())){
					v.add("TRNS_ID NOT LIKE 'PSBCC%'");
				}else if("1".equals(Param.getCHANNEL_SERVICE())){
					v.add("TRNS_ID LIKE 'PSBCC%'");
				}
			}
			
			if (!v.isEmpty()) {
				sb.append(" WHERE 1=1 ");
				for (int i = 0; i < v.size(); i++) {
					//if (i != 0) {
						sb.append(" AND ");
					//}
					sb.append(v.get(i));
				}
			}
			
			if (AppUtil.isEmpty(Param.getTRNS_SRVC_CODE())) {
				if (!AppUtil.isEmpty(Param.getListparam()) && Param.getListparam().size() != 0) {
					List<String> service1 = Param.getListparam();
					sb.append("AND TRNS_SRVC_CODE in (" + "");
					for (int i = 0; i < service1.size(); i++) {
						if (i != 0) {
							sb.append(" , ");
						}
						sb.append(service1.get(i));
					}
					sb.append(")");
				}
			}
			
			// order by 2 = TRNS_DTTM
			sb.append(" ORDER BY 2 desc");

			if(!AppUtil.isEmpty(Param.getEXPORT()) && "Y".equals(Param.getEXPORT())){
				sqlStr = "SELECT a.*,rownum r__ FROM( " + sb.toString()
						+ ")a ";		
			}else{
				if (Param.getPAGE_NO() == null) {
					Param.setPAGE_NO(1);
				}
				if (Param.getPAGE_SIZE() == null) {
					Param.setPAGE_SIZE(20);
				}
				sqlStr = "SELECT * FROM " + "( " + "SELECT a.*, rownum r__ " + "FROM " + "( " + sb.toString() + ") a " + "WHERE rownum < ((" + Param.getPAGE_NO() + " * " + Param.getPAGE_SIZE() + ") + 1 ) " + ") " + "WHERE r__ >= (((" + Param.getPAGE_NO() + "-1) * " + Param.getPAGE_SIZE() + ") + 1) ";
			}			
			
			log.info("RptTransBean|getMasterTransAll|sql:" + sqlStr);
			Query query = em.createNativeQuery(sqlStr,GWMasterTrans.class);
			listMastTrnsPrtn =  query.getResultList();
			log.info("Results Size::"+listMastTrnsPrtn.size());
			log.info("RptTransBean|getMasterTransAll|GWMasterTransParam:" + Param.toString());
			log.info("RptTransBean|getMasterTransAll|Time:" + timer.getStopTime());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}

		return listMastTrnsPrtn;
	}
	
//	@SuppressWarnings("unchecked")
//	public Collection<GWMasterTrans> getMasterTransAll(GWMasterTransParam Param) throws Exception {
//		List<GWMasterTrans> listMastTrnsPrtn = null;
//		String sqlStr = null;
//		
//		try {
//			Timer timer = new Timer("-");
//			log.info("RptTransBean|getMasterTransAll|GWMasterTransParam:" + Param.toString());
//			log.info("RptTransBean|getMasterTransAll|Time:" + timer.getStartTime());
//
//			List<Object> params = new ArrayList<Object>();
//			StringBuilder sql = new StringBuilder();
//	
//			String selectCause = " SELECT TRN.*, SRV.BLLR_SRVC_NAME_EN, RCPT.CHNL_CODE , RCPT.RCPT_CRTD_GRUP_CODE, RCPT.RCPT_TOTL_AMNT,'N' as REV_STATUS";
//			getSqlTranCode(sql, Param, params,selectCause, "CON");
//			sql.append("  UNION ALL");
//			selectCause = " SELECT TRN.*, SRV.BLLR_SRVC_NAME_EN, RCPT.CHNL_CODE , RCPT.RCPT_CRTD_GRUP_CODE, RCPT.RCPT_TOTL_AMNT,decode(TRNS_FUNC_CODE,'REV','Y','N') as REV_STATUS";
//			getSqlTranCode(sql, Param, params,selectCause, "REV");
//			
//			selectCause = " SELECT TRN.*,  SRV.BLLR_SRVC_NAME_EN,  TRN.TRNS_SRCE_CHNL_CODE AS CHNL_CODE , 'CUS' AS RCPT_CRTD_GRUP_CODE , TRN.TRNS_TOTL_AMNT AS RCPT_TOTL_AMNT,'N' as REV_STATUS";
//			if (AppUtil.isEmpty(Param.getCHANNEL_SERVICE())) {
//				sql.append("  UNION ALL");
//				addMobileCreditList(sql, Param,params,selectCause);
//			}else{
//				if("1".equals(Param.getCHANNEL_SERVICE())){
//					sql.setLength(0);
//					params = new ArrayList<Object>();
//					addMobileCreditList(sql, Param,params,selectCause);
//				}
//			}
//			
//			// order by 2 = TRNS_DTTM
//			sql.append(" ORDER BY 2 desc");
//
//			if(!AppUtil.isEmpty(Param.getEXPORT()) && "Y".equals(Param.getEXPORT())){
//				sqlStr = "SELECT a.*,rownum r__ FROM( " + sql.toString()
//						+ ")a ";		
//			}else{
//				if (Param.getPAGE_NO() == null) {
//					Param.setPAGE_NO(1);
//				}
//				if (Param.getPAGE_SIZE() == null) {
//					Param.setPAGE_SIZE(20);
//				}
//				sqlStr = "SELECT * FROM " + "( " + "SELECT a.*, rownum r__ " + "FROM " + "( " + sql.toString() + ") a " + "WHERE rownum < ((" + Param.getPAGE_NO() + " * " + Param.getPAGE_SIZE() + ") + 1 ) " + ") " + "WHERE r__ >= (((" + Param.getPAGE_NO() + "-1) * " + Param.getPAGE_SIZE() + ") + 1) ";
//			}			
//			
//			log.info("RptTransBean|getMasterTransAll|sql:" + sqlStr);
//			Query query = em.createNativeQuery(sqlStr,GWMasterTrans.class);
//			dbUtil.setParams(query, params);
//			
//			listMastTrnsPrtn =  query.getResultList();
//			log.info("Results Size::"+listMastTrnsPrtn.size());
//			log.info("RptTransBean|getMasterTransAll|GWMasterTransParam:" + Param.toString());
//			log.info("RptTransBean|getMasterTransAll|Time:" + timer.getStopTime());
//		} catch (Exception e) {
//			log.error(ExceptionUtils.getStackTrace(e));
//		}
//
//		return listMastTrnsPrtn;
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BigDecimal countRowAll(GWMasterTransParam Param) throws Exception {
		BigDecimal item = null;
		try {
			Timer timer = new Timer("-");
			log.info("RptTransBean|countRowAll|Time:" + timer.getStartTime());
			log.info("RptTransBean|countRowAll|GWMasterTransParam:" + Param.toString());

			StringBuilder sb = new StringBuilder();			
			sb.append("SELECT COUNT(TRNS_ID) AS count_row FROM VIEW_BILLER_TRANSACTION ");

			Vector<String> v = new Vector<String>();
			if (!AppUtil.isEmpty(Param.getTRNS_ID())) {
				v.add("TRNS_ID = '"+Param.getTRNS_ID()+"'");
			}
			if (!AppUtil.isEmpty(Param.getTRNS_SRVC_CODE())) {
				if(Param.getTRNS_SRVC_CODE().equals("0005")){
					if (!AppUtil.isEmpty(Param.getNETWORK_SERVICE())) {
						if(Param.getNETWORK_SERVICE().equals("1")){ //TOT
							v.add("TRNS_SRVC_CODE = '"+Param.getTRNS_SRVC_CODE()+"'");
							v.add("SUBSTR(REAL_TRNS_REF1,2,1) NOT IN ('2','4')");
						}else if(Param.getNETWORK_SERVICE().equals("2")){//TRUE
							v.add("TRNS_SRVC_CODE = '"+Param.getTRNS_SRVC_CODE()+"'");
							v.add("SUBSTR(REAL_TRNS_REF1,2,1) IN ('2','4')");
						}else{ //3.TT&T
							v.add("TRNS_SRVC_CODE = '0052'");
						}
					}
				}else{
					v.add("TRNS_SRVC_CODE = '"+Param.getTRNS_SRVC_CODE()+"'");
				}
			}
			if (!AppUtil.isEmpty(Param.getTRNS_SRCE_CHNL_CODE())) {
				v.add("TRNS_SRCE_CHNL_CODE = '"+Param.getTRNS_SRCE_CHNL_CODE()+"'");
			}
			if (!AppUtil.isEmpty(Param.getTRNS_DEST_CODE())) {
				v.add("TRNS_DEST_CODE = '"+Param.getTRNS_DEST_CODE()+"'");
			}		
			if (Param.getFROM_DTTM() != null) {
				v.add("TRNS_DTTM >= TO_DATE('"+DateTimeUtil.parseToString(Param.getFROM_DTTM(), "yyyy-MM-dd HH:mm:ss")+"', 'YYYY-MM-DD HH24:MI:SS')");
			}
			if (Param.getTO_DTTM() != null) {
				v.add("TRNS_DTTM <= TO_DATE('"+DateTimeUtil.parseToString(Param.getTO_DTTM(), "yyyy-MM-dd HH:mm:ss")+"', 'YYYY-MM-DD HH24:MI:SS')");
			}
			if (!AppUtil.isEmpty(Param.getBRANCH())) {
				v.add("SSO_LOCN_CODE_39 = '"+Param.getBRANCH()+"'");
			}					
			if (!AppUtil.isEmpty(Param.getCHANNEL_SERVICE())) {			
				if("0".equals(Param.getCHANNEL_SERVICE())){
					v.add("TRNS_ID NOT LIKE 'PSBCC%'");
				}else if("1".equals(Param.getCHANNEL_SERVICE())){
					v.add("TRNS_ID LIKE 'PSBCC%'");
				}
			}

			if (!v.isEmpty()) {
				sb.append(" WHERE 1=1 ");
				for (int i = 0; i < v.size(); i++) {
					sb.append(" AND ");
					sb.append(v.get(i));
				}
			}
			
			if (AppUtil.isEmpty(Param.getTRNS_SRVC_CODE())) {
				if (!AppUtil.isEmpty(Param.getListparam()) && Param.getListparam().size() != 0) {
					List<String> service1 = Param.getListparam();
					sb.append("AND TRNS_SRVC_CODE in (" + "");
					for (int i = 0; i < service1.size(); i++) {
						if (i != 0) {
							sb.append(" , ");
						}
						sb.append(service1.get(i));
					}
					sb.append(")");
				}
			}
					
			log.info("RptTransBean|countRowAll|sql:" + sb.toString());		
			Query query = em.createNativeQuery(sb.toString());			
			List list = query.getResultList();
			item = getCountList(list);
			log.info("RptTransBean|countRowAll|numRow:" + item);
			log.info("RptTransBean|countRowAll|Time:" + timer.getStopTime());
			
			return item;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("RptTransBean|countRowAll|Exception:" + e);
		}
		return item;
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public BigDecimal countRowAll(GWMasterTransParam Param) throws Exception {
//		BigDecimal item = null;
//		try {
//			Timer timer = new Timer("-");
//			log.info("RptTransBean|countRowAll|Time:" + timer.getStartTime());
//			log.info("RptTransBean|countRowAll|GWMasterTransParam:" + Param.toString());
//
//			List<Object> params = new ArrayList<Object>();
//			StringBuilder sql = new StringBuilder();
//			String selectCause = " SELECT COUNT(TRNS_ID) AS count_row";
//			getSqlTranCode(sql, Param, params,selectCause, "CON");
//			sql.append("  UNION ALL");
//			getSqlTranCode(sql, Param, params,selectCause, "REV");
//			
//			log.info("RptTransBean|countRowAll|sql:" + sql.toString());
//			
//			Query query = em.createNativeQuery(sql.toString());
//			dbUtil.setParams(query, params);			
//			List list = query.getResultList();
//			item = getCountList(list);
//			log.info("RptTransBean|countRowAll|numRow:" + item);
//			log.info("RptTransBean|countRowAll|Time:" + timer.getStopTime());
//			
//			sql.setLength(0);
//			params = new ArrayList<Object>();
//			getSqlTranMobileCredit(sql, Param, params, selectCause, "CON");
//			sql.append(" AND SUBSTR(TRN.TRNS_ID,0,5) = 'PSBCC'");
//			if (AppUtil.isEmpty(Param.getCHANNEL_SERVICE())) {
//				item = item.add(addMobileCreditCount(sql,params));
//			}else{
//				if("1".equals(Param.getCHANNEL_SERVICE())){
//					item = addMobileCreditCount(sql,params);
//				}
//			}
//			
//			return item;
//
//		} catch (Exception e) {
//			log.error(ExceptionUtils.getStackTrace(e));
//			log.info("RptTransBean|countRowAll|Exception:" + e);
//		}
//		return item;
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BigDecimal getTotalAmount(GWMasterTransParam Param) throws Exception {
		BigDecimal item = null;
		try {
			Timer timer = new Timer("-");
			log.info("RptTransBean|getTotalAmount|Time:" + timer.getStartTime());
			log.info("RptTransBean|getTotalAmount|GWMasterTransParam:" + Param.toString());

			StringBuilder sb = new StringBuilder();			
			sb.append("SELECT SUM(TRNS_PAID_AMNT) AS total_amount FROM VIEW_BILLER_TRANSACTION ");

			Vector<String> v = new Vector<String>();
			if (!AppUtil.isEmpty(Param.getTRNS_ID())) {
				v.add("TRNS_ID = '"+Param.getTRNS_ID()+"'");
			}
			if (!AppUtil.isEmpty(Param.getTRNS_SRVC_CODE())) {
				if(Param.getTRNS_SRVC_CODE().equals("0005")){
					if (!AppUtil.isEmpty(Param.getNETWORK_SERVICE())) {
						if(Param.getNETWORK_SERVICE().equals("1")){ //TOT
							v.add("TRNS_SRVC_CODE = '"+Param.getTRNS_SRVC_CODE()+"'");
							v.add("SUBSTR(REAL_TRNS_REF1,2,1) NOT IN ('2','4')");
						}else if(Param.getNETWORK_SERVICE().equals("2")){//TRUE
							v.add("TRNS_SRVC_CODE = '"+Param.getTRNS_SRVC_CODE()+"'");
							v.add("SUBSTR(REAL_TRNS_REF1,2,1) IN ('2','4')");
						}else{ //3.TT&T
							v.add("TRNS_SRVC_CODE = '0052'");
						}
					}
				}else{
					v.add("TRNS_SRVC_CODE = '"+Param.getTRNS_SRVC_CODE()+"'");
				}
			}
			if (!AppUtil.isEmpty(Param.getTRNS_SRCE_CHNL_CODE())) {
				v.add("TRNS_SRCE_CHNL_CODE = '"+Param.getTRNS_SRCE_CHNL_CODE()+"'");
			}
			if (!AppUtil.isEmpty(Param.getTRNS_DEST_CODE())) {
				v.add("TRNS_DEST_CODE = '"+Param.getTRNS_DEST_CODE()+"'");
			}		
			if (Param.getFROM_DTTM() != null) {
				v.add("TRNS_DTTM >= TO_DATE('"+DateTimeUtil.parseToString(Param.getFROM_DTTM(), "yyyy-MM-dd HH:mm:ss")+"', 'YYYY-MM-DD HH24:MI:SS')");
			}
			if (Param.getTO_DTTM() != null) {
				v.add("TRNS_DTTM <= TO_DATE('"+DateTimeUtil.parseToString(Param.getTO_DTTM(), "yyyy-MM-dd HH:mm:ss")+"', 'YYYY-MM-DD HH24:MI:SS')");
			}
			if (!AppUtil.isEmpty(Param.getBRANCH())) {
				v.add("SSO_LOCN_CODE_39 = '"+Param.getBRANCH()+"'");
			}				
			if (!AppUtil.isEmpty(Param.getCHANNEL_SERVICE())) {			
				if("0".equals(Param.getCHANNEL_SERVICE())){
					v.add("TRNS_ID NOT LIKE 'PSBCC%'");
				}else if("1".equals(Param.getCHANNEL_SERVICE())){
					v.add("TRNS_ID LIKE 'PSBCC%'");
				}
			}

			if (!v.isEmpty()) {
				sb.append(" WHERE 1=1 ");
				for (int i = 0; i < v.size(); i++) {
					sb.append(" AND ");
					sb.append(v.get(i));
				}
			}
			
			if (AppUtil.isEmpty(Param.getTRNS_SRVC_CODE())) {
				if (!AppUtil.isEmpty(Param.getListparam()) && Param.getListparam().size() != 0) {
					List<String> service1 = Param.getListparam();
					sb.append("AND TRNS_SRVC_CODE in (" + "");
					for (int i = 0; i < service1.size(); i++) {
						if (i != 0) {
							sb.append(" , ");
						}
						sb.append(service1.get(i));
					}
					sb.append(")");
				}
			}
			
			log.info("RptTransBean|getTotalAmount|sql:" + sb.toString());		
			Query query = em.createNativeQuery(sb.toString());			
			List list = query.getResultList();
			item = getCountList(list);
			log.info("RptTransBean|getTotalAmount|TotalAmount:" + item);
			log.info("RptTransBean|getTotalAmount|Time:" + timer.getStopTime());		
			
			return item;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("RptTransBean|getTotalAmount|Exception:" + e);
		}
		return item;
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public BigDecimal getTotalAmount(GWMasterTransParam Param) throws Exception {
//		BigDecimal item = null;
//		try {
//			Timer timer = new Timer("-");
//			log.info("RptTransBean|getTotalAmount|Time:" + timer.getStartTime());
//			log.info("RptTransBean|getTotalAmount|GWMasterTransParam:" + Param.toString());
//
//			List<Object> params = new ArrayList<Object>();
//			StringBuilder sql = new StringBuilder();
//			String selectSumCon = " SELECT SUM(TRNS_PAID_AMNT) AS total_amount";
//			getSqlTranCode(sql, Param, params,selectSumCon, "CON");
//			sql.append("  UNION ALL");
//			String selectSumRev = " SELECT SUM(decode(TRNS_FUNC_CODE,'REV',TRNS_PAID_AMNT*(-1),TRNS_PAID_AMNT)) AS total_amount";
//			getSqlTranCode(sql, Param, params,selectSumRev, "REV");
//			
//			log.info("RptTransBean|getTotalAmount|sql:" + sql.toString());
//			
//			Query query = em.createNativeQuery(sql.toString());
//			dbUtil.setParams(query, params);			
//			List list = query.getResultList();
//			item = getCountList(list);
//			log.info("RptTransBean|getTotalAmount|TotalAmount:" + item);
//			log.info("RptTransBean|getTotalAmount|Time:" + timer.getStopTime());
//			
//			sql.setLength(0);
//			params = new ArrayList<Object>();
//			selectSumCon = " SELECT SUM(TRNS_TOTL_AMNT) AS total_amount";
//			getSqlTranMobileCredit(sql, Param, params, selectSumCon, "CON");
//			sql.append(" AND SUBSTR(TRN.TRNS_ID,0,5) = 'PSBCC'");
//			if (AppUtil.isEmpty(Param.getCHANNEL_SERVICE())) {
//				item = item.add(addMobileTotalAmount(sql,params));
//			}else{
//				if("1".equals(Param.getCHANNEL_SERVICE())){
//					item = addMobileTotalAmount(sql,params);
//				}
//			}
//			
//			return item;
//
//		} catch (Exception e) {
//			log.error(ExceptionUtils.getStackTrace(e));
//			log.info("RptTransBean|getTotalAmount|Exception:" + e);
//		}
//		return item;
//	}

	@SuppressWarnings("rawtypes")
	public GWMasterTrans findMasterTrans(String TRNS_ID, Date TRNS_DTTM, String TRNS_DEST_CODE, String TABLE_NAME) throws Exception {

		Timer timer = new Timer("-");
		log.info("RptTransBean|findMasterTrans|Time:" + timer.getStartTime());
		log.info("RptTransBean|findMasterTrans|TRNS_ID:" + TRNS_ID);

		if (TABLE_NAME == null || TABLE_NAME == "") {
			TABLE_NAME = "GW_MAST_TRNS_01";
		}

		String sql = "SELECT * " + "FROM " + TABLE_NAME + " ";

		Vector<String> v = new Vector<String>();

		if (TRNS_ID != null && !"".equals(TRNS_ID))
			v.add("TRNS_ID='" + TRNS_ID + "'");

		if (TRNS_DTTM != null && !"".equals(TRNS_DTTM))
			v.add("TRNS_DTTM =TO_DATE('" + TRNS_DTTM + "', 'YYYY-MM-DD HH24:MI:SS')");

		if (TRNS_DEST_CODE != null && !"".equals(TRNS_DEST_CODE))
			v.add("TRNS_DEST_CODE='" + TRNS_DEST_CODE + "'");

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
		sql += " order by TRNS_DTTM desc";

		log.info("RptTransBean|findMasterTrans|SQL:" + sql);

		Query query = em.createNativeQuery(sql, GWMasterTrans.class);

		List listRow = query.getResultList();

		GWMasterTrans gwMasterTrans = (GWMasterTrans) listRow.get(0);

		log.info("BatchMastFileBean|findMasterTrans|GWMasterTrans:" + gwMasterTrans.getTRNS_ID() + "|" + gwMasterTrans.getTRNS_DTTM() + "|" + gwMasterTrans.getTRNS_DEST_CODE() + "|" + gwMasterTrans.getTRNS_SRCE_CHNL_CODE() + "|" + gwMasterTrans.getTRNS_PAID_AMNT() + "|" + gwMasterTrans.getTRNS_FUNC_CODE() + "|" + gwMasterTrans.getTRNS_STTS_CODE() + "|" + gwMasterTrans.getTRNS_PYMT_DTTM() + "|" + gwMasterTrans.getTRNS_RCPT_NUMB());
		log.info("RptTransBean|findMasterTrans|Time:" + timer.getStopTime());

		return gwMasterTrans;
	}

	@SuppressWarnings("unchecked")
	public List<BillerChannel> getBillerChannelAll(UserInfoBean user) throws Exception {
		List<BillerChannel> list = null;
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|getBillerChannelAll|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_CHANNEL ORDER BY BLLR_CHNL_ID";

			log.info(user.getName() + "|" + page + "|getBillerChannelAll|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerChannel.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|getBillerChannelAll|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|getBillerChannelAll|Time:" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|getBillerChannelAll|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<BillerFdmCode> getBillerFdmCodeAll(UserInfoBean user) throws Exception {
		List<BillerFdmCode> list = null;
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|getBillerFdmCodeAll|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_FDM_CODE WHERE ACT_FLAG = 'A' ORDER BY BLLR_MSGE_CODE";

			log.info(user.getName() + "|" + page + "|getBillerFdmCodeAll|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerFdmCode.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|getBillerFdmCodeAll|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|getBillerFdmCodeAll|Time:" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|getBillerFdmCodeAll|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<BillerPymtCode> getBillerPymtCodeAll(UserInfoBean user) throws Exception {
		List<BillerPymtCode> list = null;
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|getBillerPymtCodeAll|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_PYMT_CODE WHERE ACL_FLAG = 'A'  ORDER BY BLLR_MSGE_CODE";

			log.info(user.getName() + "|" + page + "|getBillerPymtCodeAll|SQL:" + sql);

			Query query = em.createNativeQuery(sql, BillerPymtCode.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|getBillerPymtCodeAll|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|getBillerPymtCodeAll|Time:" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|getBillerPymtCodeAll|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ReportTransDetail> searchReportTrans(ReportTransParam PARAM, UserInfoBean user) throws Exception {
		List<ReportTransDetail> listDetail = new ArrayList<ReportTransDetail>();
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchReportTrans|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchReportTrans|Param|" + PARAM.toString());

			String sql = "SELECT MT.TRNS_ID, TO_CHAR(MT.TRNS_DTTM, 'YYYY-MM-DD HH24:MI:SS') AS TRNS_DTTM, MT.TRNS_SRVC_CODE, MT.TRNS_DEST_CODE, MT.TRNS_SRCE_CHNL_CODE, MT.TRNS_REF1,MT.TRNS_REF2,MT.TRNS_REF3,MT.TRNS_REF4,MT.TRNS_REF5,MT.TRNS_REF6, " + "MT.TRNS_PAID_AMNT, MT.TRNS_FEE_AMNT, MT.TRNS_VAT_RATE, MT.TRNS_CUST_ADDR_2, MT.TRNS_FUNC_CODE, TO_CHAR(MT.TRNS_PYMT_DTTM, 'YYYY-MM-DD HH24:MI:SS') AS TRNS_PYMT_DTTM, " + "FC.BLLR_MSGE_CODE_MSGE_EN AS TRNS_STTS_CODE_DESC, " + "PC.BLLR_MSGE_CODE_MSGE_EN AS TRNS_PYMT_STTS_CODE_DESC , BLLR_SRVC_DESC " + "FROM GW_MAST_TRNS_01 MT " + "LEFT JOIN BILLER_FDM_CODE FC ON MT.TRNS_STTS_CODE = FC.BLLR_MSGE_CODE " + "LEFT JOIN BILLER_PYMT_CODE PC ON MT.TRNS_PYMT_STTS_CODE = PC.BLLR_MSGE_CODE " + "LEFT JOIN BILLER_SERVICE  SE  ON  MT.TRNS_SRVC_CODE = SE.BLLR_SRVC_CODE ";
			sql += genReportTransCriteria(PARAM);
			sql += " ORDER BY TRNS_DTTM  DESC ";

			if (PARAM.getPAGE_NO() == null) {
				PARAM.setPAGE_NO(1);
			}
			if (PARAM.getPAGE_SIZE() == null) {
				PARAM.setPAGE_SIZE(20);
			}
			sql = "SELECT * FROM " + "( " + "SELECT a.*, rownum r__ " + "FROM " + "( " + sql + ") a " + "WHERE rownum < ((" + PARAM.getPAGE_NO() + " * " + PARAM.getPAGE_SIZE() + ") + 1 ) " + ") " + "WHERE r__ >= (((" + PARAM.getPAGE_NO() + "-1) * " + PARAM.getPAGE_SIZE() + ") + 1) ";

			log.info(user.getName() + "|" + page + "|searchReportTrans|SQL:" + sql);

			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			ReportTransDetail bean = null;
			for (Object[] item : list) {
				int x = 0;
				bean = new ReportTransDetail();
				bean.setTRNS_ID(JpaResultUtil.getString(item, x++));
				bean.setTRNS_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setTRNS_SRVC_CODE(JpaResultUtil.getString(item, x++));
				bean.setTRNS_DEST_CODE(JpaResultUtil.getString(item, x++));
				bean.setTRNS_SRCE_CHNL_CODE(JpaResultUtil.getString(item, x++));
				bean.setTRNS_REF1(JpaResultUtil.getString(item, x++));
				bean.setTRNS_REF2(JpaResultUtil.getString(item, x++));
				bean.setTRNS_REF3(JpaResultUtil.getString(item, x++));
				bean.setTRNS_REF4(JpaResultUtil.getString(item, x++));
				bean.setTRNS_REF5(JpaResultUtil.getString(item, x++));
				bean.setTRNS_REF6(JpaResultUtil.getString(item, x++));
				bean.setTRNS_PAID_AMNT(JpaResultUtil.getBigDecimal(item, x++));
				bean.setTRNS_FEE_AMNT(JpaResultUtil.getBigDecimal(item, x++));
				bean.setTRNS_VAT_RATE(JpaResultUtil.getBigDecimal(item, x++));
				bean.setTRNS_CUST_ADDR_2(JpaResultUtil.getString(item, x++));
				bean.setTRNS_FUNC_CODE(JpaResultUtil.getString(item, x++));
				bean.setTRNS_PYMT_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setTRNS_STTS_CODE_DESC(JpaResultUtil.getString(item, x++));
				bean.setTRNS_PYMT_STTS_CODE_DESC(JpaResultUtil.getString(item, x++));
				bean.setBLLR_SRVC_DESC(JpaResultUtil.getString(item, x++));

				listDetail.add(bean);
			}
			log.info(user.getName() + "|" + page + "|searchReportTrans|List.Size:" + listDetail.size());
			log.info(user.getName() + "|" + page + "|searchReportTrans|Time|" + timer.getStopTime());
		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|searchReportTrans|Exception:" + e.getMessage());
			throw e;
		}
		return listDetail;
	}

	public int countRowReportTrans(ReportTransParam PARAM, UserInfoBean user) throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|countRowReportTrans|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|countRowReportTrans|Param|" + PARAM.toString());

			String sql = "SELECT count(*) FROM GW_MAST_TRNS_01 ";
			sql += genReportTransCriteria(PARAM);

			log.info(user.getName() + "|" + page + "|countRowReportTrans|SQL:" + sql);

			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal) query.getSingleResult();
			int row = result.intValue();
			log.info(user.getName() + "|" + page + "|countRowReportTrans|Total Row:" + row);
			log.info(user.getName() + "|" + page + "|countRowReportTrans|Time|" + timer.getStopTime());
			return row;

		} catch (Exception e) {
			log.error(user.getName() + "|" + page + "|countRowReportTrans|Exception:" + e.getMessage());
			throw e;
		}
	}

	private String genReportTransCriteria(ReportTransParam PARAM) {
		Vector<String> v = new Vector<String>();
		StringBuffer sb = new StringBuffer();
		if ((PARAM.getFROM_TRNS_DTTM() != null) && (PARAM.getTO_TRNS_DTTM() != null)) {
			v.add("TRNS_DTTM BETWEEN to_date('" + DateTimeUtil.parseToString(PARAM.getFROM_TRNS_DTTM(), "yyyy-MM-dd HH:mm:ss") + "', 'YYYY-MM-DD HH24:MI:SS') AND to_date('" + DateTimeUtil.parseToString(PARAM.getTO_TRNS_DTTM(), "yyyy-MM-dd HH:mm:ss") + "', 'YYYY-MM-DD HH24:MI:SS')");
		}
		if (!ValidateUtil.isEmpty(PARAM.getTRNS_SRVC_CODE()) && !PARAM.getTRNS_SRVC_CODE().equals("ALL")) {
			v.add("TRNS_SRVC_CODE = '" + PARAM.getTRNS_SRVC_CODE() + "'");
		}
		if (!ValidateUtil.isEmpty(PARAM.getTRNS_SRCE_CHNL_CODE()) && !PARAM.getTRNS_SRCE_CHNL_CODE().equals("ALL")) {
			v.add("TRNS_SRCE_CHNL_CODE = '" + PARAM.getTRNS_SRCE_CHNL_CODE() + "'");
		}
		if (!ValidateUtil.isEmpty(PARAM.getTRNS_ID())) {
			v.add("TRNS_ID = '" + PARAM.getTRNS_ID() + "'");
		}
		if (!ValidateUtil.isEmpty(PARAM.getTRNS_FUNC_CODE()) && !PARAM.getTRNS_FUNC_CODE().equals("ALL")) {
			v.add("TRNS_FUNC_CODE = '" + PARAM.getTRNS_FUNC_CODE() + "'");
		}
		if (!ValidateUtil.isEmpty(PARAM.getTRNS_STTS_CODE()) && !PARAM.getTRNS_STTS_CODE().equals("ALL")) {
			v.add("TRNS_STTS_CODE = '" + PARAM.getTRNS_STTS_CODE() + "'");
		}
		if (!ValidateUtil.isEmpty(PARAM.getTRNS_PYMT_STTS_CODE()) && !PARAM.getTRNS_PYMT_STTS_CODE().equals("ALL")) {
			v.add("TRNS_PYMT_STTS_CODE = '" + PARAM.getTRNS_PYMT_STTS_CODE() + "'");
		}

		if (!v.isEmpty()) {
			sb.append(" WHERE (");
			for (int i = 0; i < v.size(); i++) {
				if (i != 0) {
					sb.append(" AND ");
				}
				sb.append(v.get(i));
			}
			sb.append(")");
		}

		return sb.toString();
	}

	public BillerBlindRefView getBillerBlindRefView(int bllrSrvcId, String refType) throws Exception {
		// TODO Auto-generated method stub
		EntityManagerFactory factory;
		System.out.println("Start getBillerBlindRefView");
		/* Test */
		System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		System.setProperty("javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
		factory = Persistence.createEntityManagerFactory("mfs.db.MFSDAO");
		EntityManager em = factory.createEntityManager();

//		System.out.println("EM:" + em);
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT * FROM VIEW_BILLER_BLIND_REF");
	    sql.append(" WHERE BLLR_SRVC_ID=? AND REF_TYPE=?");
		Query query = em.createNativeQuery(sql.toString(),BillerBlindRefView.class);
		query.setParameter(1,bllrSrvcId);
		query.setParameter(2,refType);
		Object result=query.getSingleResult();
		
		if(result==null)
		{
			return null;
		}
		BillerBlindRefView billerBlindRefView=(BillerBlindRefView)query.getSingleResult();
		return billerBlindRefView;
	}
	
	private void addMobileCreditList(StringBuilder sql,
			GWMasterTransParam Param, List<Object> params, String selectCause){
		getSqlTranMobileCredit(sql, Param, params, selectCause, "CON");
		sql.append(" AND SUBSTR(TRN.TRNS_ID,0,5) = 'PSBCC'");
	}
	
	private BigDecimal addMobileCreditCount(StringBuilder sql,List<Object> params) {
		BigDecimal item = null;
		try {
			Timer timer = new Timer("-");
			log.info("RptTransBean|addMobileCreditCount|Time:"
					+ timer.getStartTime());
			log.info("RptTransBean|addMobileCreditCount|sql:" + sql.toString());

			Query query = em.createNativeQuery(sql.toString());
			dbUtil.setParams(query, params);
			List list = query.getResultList();
			item = getCountList(list);
			log.info("RptTransBean|addMobileCreditCount|numRow:" + item);
			log.info("RptTransBean|addMobileCreditCount|Time:"
					+ timer.getStopTime());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("RptTransBean|addMobileCreditCount|Exception:" + e);
		}
		return item;
	}
	
	private BigDecimal addMobileTotalAmount(StringBuilder sql,List<Object> params) {
		BigDecimal count = null;
		try {
			Timer timer = new Timer("-");
			log.info("RptTransBean|addMobileTotalAmount|Time:"
					+ timer.getStartTime());
			log.info("RptTransBean|addMobileTotalAmount|sql:" + sql.toString());

			Query query = em.createNativeQuery(sql.toString());
			dbUtil.setParams(query, params);
			List list = query.getResultList();	
			count = getCountList(list);			
			log.info("RptTransBean|addMobileTotalAmount|numRow:" + count);
			log.info("RptTransBean|addMobileTotalAmount|Time:"
					+ timer.getStopTime());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("RptTransBean|addMobileTotalAmount|Exception:" + e);
		}
		return count;
	}
	
	private void getSqlTranCode(StringBuilder sql,GWMasterTransParam Param,List<Object> params,String selectCause,String tranCode){
		sql.append(selectCause); 
		sql.append(" FROM BILLER_SERVICE SRV");
		sql.append(" LEFT JOIN GW_MAST_TRNS_01 TRN"); 
		sql.append(" ON TRN.TRNS_SRVC_CODE = SRV.BLLR_SRVC_CODE"); 
		sql.append(" LEFT JOIN (");
		sql.append(" SELECT R1.*");		
		if (!AppUtil.isEmpty(Param.getBRANCH())) {
			sql.append(" , S.SSO_LOCN_CODE_39");
		}
		sql.append(" FROM RCPT_MAST R1");
		sql.append(" INNER JOIN (");
		sql.append(" SELECT MAX (RCPT_ID) RCPT_ID, FDM_TRNS_ID");
		sql.append(" FROM RCPT_MAST");
		sql.append(" WHERE 1=1");
		sql.append(" AND DOCM_TYPE IN ('R2A','R2','R3','R4','R739','R739A','R740','R740A') GROUP BY FDM_TRNS_ID");
		sql.append(" ) R2");
		sql.append(" ON R2.RCPT_ID = R1.RCPT_ID");
		sql.append(" AND R2.FDM_TRNS_ID = R1.FDM_TRNS_ID");
		if (!AppUtil.isEmpty(Param.getBRANCH())) {
			sql.append(" INNER JOIN BACK_OFFC_RTR_SHOP S");
			sql.append(" ON r1.RTR_CODE = s.RTR_CODE");
		}
		sql.append(" ) RCPT");
		sql.append(" ON RCPT.FDM_TRNS_ID = TRN.TRNS_ID");
		sql.append(" LEFT JOIN RCPT_MTHD MTHD");
		sql.append(" ON MTHD.RCPT_ID = RCPT.RCPT_ID");			
		sql.append(" INNER JOIN VIEW_BILLER_CUS_INFO VW_CUS");
		sql.append(" ON TRN.TRNS_ID = VW_CUS.FDM_TRNS_ID");				    
		sql.append(" WHERE 1=1");
		if("REV".equals(tranCode)){
			sql.append(" AND (RCPT.RCPT_STTS = 'C' AND NOT EXISTS (SELECT 'X' FROM RCPT_MAST X WHERE RCPT.RCPT_ID = X.RCPT_REFN_ID))");
		}
		sql.append(" AND RCPT.DOCM_TYPE IN ('R2A','R2','R3','R4','R739','R739A','R740','R740A')");
		if("CON".equals(tranCode)){
			sql.append(" AND TRN.TRNS_FUNC_CODE = '"+tranCode+"'");
			sql.append(" AND TRN.TRNS_STTS_CODE = '00'");
			sql.append(" AND RCPT.RCPT_STTS = 'N'");
		}
		if (!AppUtil.isEmpty(Param.getTRNS_ID())) {
			sql.append(" AND TRN.TRNS_ID = ?");
			params.add(Param.getTRNS_ID());
		}
		if (!AppUtil.isEmpty(Param.getTRNS_SRVC_CODE())) {
			sql.append(" AND TRN.TRNS_SRVC_CODE = ?");
			params.add(Param.getTRNS_SRVC_CODE());
		}
		if (!AppUtil.isEmpty(Param.getTRNS_SRCE_CHNL_CODE())) {
			sql.append(" AND TRN.TRNS_SRCE_CHNL_CODE = ?");
			params.add(Param.getTRNS_SRCE_CHNL_CODE());
		}
		if (!AppUtil.isEmpty(Param.getTRNS_DEST_CODE())) {
			sql.append(" AND TRN.TRNS_DEST_CODE = ?");
			params.add(Param.getTRNS_DEST_CODE());
		}		
		if (Param.getFROM_DTTM() != null) {
			sql.append(" AND TRN.CRTD_DTTM >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')");
			params.add(DateTimeUtil.parseToString(Param.getFROM_DTTM(), "yyyy-MM-dd HH:mm:ss"));
		}
		if (Param.getTO_DTTM() != null) {
			sql.append(" AND TRN.CRTD_DTTM <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')");
			params.add(DateTimeUtil.parseToString(Param.getTO_DTTM(), "yyyy-MM-dd HH:mm:ss"));
		}
		if (!AppUtil.isEmpty(Param.getBRANCH())) {
			sql.append(" AND RCPT.SSO_LOCN_CODE_39 = ?");
			params.add(Param.getBRANCH());
		}
		if (AppUtil.isEmpty(Param.getTRNS_SRVC_CODE())) {
			if (!AppUtil.isEmpty(Param.getListparam()) && Param.getListparam().size() != 0) {
				List<String> service1 = Param.getListparam();
				sql.append(" AND  TRN.TRNS_SRVC_CODE in (" + "");
				for (int i = 0; i < service1.size(); i++) {
					if (i != 0) {
						sql.append(" , ");
					}
					sql.append("?");
					params.add(service1.get(i) );
				}
				sql.append(")");
			}
		}
	}
	
	private void getSqlTranMobileCredit(StringBuilder sql,GWMasterTransParam Param,List<Object> params,String selectCause,String tranCode){
		sql.append(selectCause);
		sql.append(" FROM BILLER_SERVICE SRV");
		sql.append(" LEFT JOIN GW_MAST_TRNS_01 TRN");
		sql.append(" ON TRN.TRNS_SRVC_CODE = SRV.BLLR_SRVC_CODE");
		sql.append(" INNER JOIN VIEW_BILLER_CUS_INFO VW_CUS");
		sql.append(" ON TRN.TRNS_ID = VW_CUS.FDM_TRNS_ID");
		sql.append(" WHERE 1=1");
		sql.append(" AND TRN.TRNS_FUNC_CODE = '"+tranCode+"'");
		sql.append(" AND TRN.TRNS_STTS_CODE = '00'");

		if (!AppUtil.isEmpty(Param.getTRNS_ID())) {
			sql.append(" AND TRN.TRNS_ID = ?");
			params.add(Param.getTRNS_ID());
		}
		if (!AppUtil.isEmpty(Param.getTRNS_SRVC_CODE())) {
			sql.append(" AND TRN.TRNS_SRVC_CODE = ?");
			params.add(Param.getTRNS_SRVC_CODE());
		}
		if (!AppUtil.isEmpty(Param.getTRNS_SRCE_CHNL_CODE())) {
			sql.append(" AND TRN.TRNS_SRCE_CHNL_CODE = ?");
			params.add(Param.getTRNS_SRCE_CHNL_CODE());
		}
		if (!AppUtil.isEmpty(Param.getTRNS_DEST_CODE())) {
			sql.append(" AND TRN.TRNS_DEST_CODE = ?");
			params.add(Param.getTRNS_DEST_CODE());
		}
		if (Param.getFROM_DTTM() != null) {
			sql.append(" AND TRN.TRNS_DTTM >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')");
			params.add(DateTimeUtil.parseToString(Param.getFROM_DTTM(),
					"yyyy-MM-dd HH:mm:ss"));
		}
		if (Param.getTO_DTTM() != null) {
			sql.append(" AND TRN.TRNS_DTTM <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')");
			params.add(DateTimeUtil.parseToString(Param.getTO_DTTM(),
					"yyyy-MM-dd HH:mm:ss"));
		}

		if (AppUtil.isEmpty(Param.getTRNS_SRVC_CODE())) {
			if (!AppUtil.isEmpty(Param.getListparam())
					&& Param.getListparam().size() != 0) {
				List<String> service1 = Param.getListparam();
				sql.append(" AND  TRN.TRNS_SRVC_CODE in (" + "");
				for (int i = 0; i < service1.size(); i++) {
					if (i != 0) {
						sql.append(" , ");
					}
					sql.append("?");
					params.add(service1.get(i));
				}
				sql.append(")");
			}
		}
	}
	
	private BigDecimal getCountList(List list) {
		BigDecimal item = new BigDecimal(0);
		for (Object count : list) {
			if(null != count){
				item = item.add((BigDecimal) count);
			}
		}
		return item;
	}
	
	public static void main(String[] args) {
//		PropertyConfigurator.configure("log4j.properties");
		PropertyConfigurator.configure(PersonalConstant.logConfigPath+"log4j.properties");
		
		RptTransBean rtb = new RptTransBean();
		try {
			System.out.println(rtb.getBillerBlindRefView(12, "add.ref1").getRefBlindFomt());
//			GWMasterTransParam param=new GWMasterTransParam();
//			param.setTRNS_SRCE_CHNL_CODE("sd_web");
//			param.setFROM_DTTM(new Date());
//			param.setTO_DTTM(new Date());
//			Collection<GWMasterTrans> gwmasterTrns= rtb.getMasterTransAll(param);
//			System.out.println("Size:"+gwmasterTrns.size());
		} catch (Exception e) {
		
		}
	}
	
}
