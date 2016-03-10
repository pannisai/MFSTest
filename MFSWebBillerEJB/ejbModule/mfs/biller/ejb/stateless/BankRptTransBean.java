package mfs.biller.ejb.stateless;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.BankRptTransBeanLocal;
import mfs.biller.ejb.interfaces.BankRptTransBeanRemote;
import mfs.biller.persistence.bean.BankFdmCode;
import mfs.biller.persistence.bean.BankReportTransDetail;
import mfs.biller.persistence.bean.BankReportTransParam;
import mfs.biller.persistence.bean.BillerChannel;
import mfs.biller.persistence.bean.BillerPymtCode;
import mfs.biller.persistence.bean.GWMasterTrans;
import mfs.biller.persistence.bean.GWMasterTransParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.util.DateTimeUtil;
import mfs.biller.util.JpaResultUtil;
import mfs.biller.util.Timer;
import mfs.biller.util.ValidateUtil;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

@Stateless(name = "BankRptTransBean", mappedName = "mfs.biller.ejb.BankRptTransBean")
@CallByReference
public class BankRptTransBean implements BankRptTransBeanRemote, BankRptTransBeanLocal {
	
	private Logger log = Logger.getLogger("EJBBANKRPTTRNS");
	private String page = "BankRptTransBean";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	
	public Collection<GWMasterTrans> getMasterTransAll(GWMasterTransParam Param)throws Exception {

		List listMastTrnsPrtn = null;

		
		try {

			Timer timer = new Timer("-");

		log.info("RptTransBean|getMasterTransAll|GWMasterTransParam:"
				+ Param.toString());log.info("RptTransBean|getMasterTransAll|Time:"
						+ timer.getStartTime());
		log.info("RptTransBean|getMasterTransAll|GWMasterTransParam:"
				+ Param.toString());

		if (Param.getTABLE_NAME() == null || Param.getTABLE_NAME() == "") {
			Param.setTABLE_NAME("GW_MAST_TRNS_01");
		}

		
			String sql = "SELECT * " + 
					"FROM " + Param.getTABLE_NAME() + " ";

			log.info("RptTransBean|getMasterTransAll|SQL:" + sql);

			Vector<String> v = new Vector<String>();

			if (Param.getTRNS_ID() != null && !"".equals(Param.getTRNS_ID()))
				v.add("TRNS_ID='" + Param.getTRNS_ID() + "'");

			if (Param.getTRNS_SRCE_CHNL_CODE() != null
					&& !"".equals(Param.getTRNS_SRCE_CHNL_CODE()))
				v.add("TRNS_SRCE_CHNL_CODE='" + Param.getTRNS_SRCE_CHNL_CODE()
						+ "'");

			if (Param.getTRNS_DEST_CODE() != null
					&& !"".equals(Param.getTRNS_DEST_CODE()))
				v.add("TRNS_DEST_CODE='" + Param.getTRNS_DEST_CODE() + "'");

			if (Param.getFROM_DTTM() != null
					&& !"".equals(Param.getFROM_DTTM()))
				v.add("CRTD_DTTM >=TO_DATE('" + Param.getFROM_DTTM()
						+ "', 'YYYY-MM-DD HH24:MI:SS')");

			if (Param.getTO_DTTM() != null && !"".equals(Param.getTO_DTTM()))
				v.add("CRTD_DTTM <=TO_DATE('" + Param.getTO_DTTM()
						+ "', 'YYYY-MM-DD HH24:MI:SS')");

			if (Param.getTRNS_STTS_CODE() != null
					&& !"".equals(Param.getTRNS_STTS_CODE()))
				v.add("TRNS_STTS_CODE='" + Param.getTRNS_STTS_CODE() + "'");

			log.info("RptTransBean|getMasterTransAll|param:" + v);

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

			if (Param.getPAGE_NO() == null) {
				Param.setPAGE_NO(1);
			}

			if (Param.getPAGE_SIZE() == null) {
				Param.setPAGE_SIZE(20);
			}

			sql = "SELECT * FROM " + "( " + "SELECT a.*, rownum r__ " + "FROM "
					+ "( " + sql + ") a " + "WHERE rownum < (("
					+ Param.getPAGE_NO() + " * " + Param.getPAGE_SIZE()
					+ ") + 1 ) " + ") " + "WHERE r__ >= ((("
					+ Param.getPAGE_NO() + "-1) * " + Param.getPAGE_SIZE()
					+ ") + 1) ";

			log.info("RptTransBean|getMasterTransAll|sql:" + sql);

			Query query = em.createNativeQuery(sql, GWMasterTrans.class);

			listMastTrnsPrtn = (List) query.getResultList();
			log.info("RptTransBean|getMasterTransAll|GWMasterTransParam:"
					+ Param.toString());log.info("RptTransBean|getMasterTransAll|Time:"
							+ timer.getStopTime());

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}

		return listMastTrnsPrtn;
	}

	public BankReportTransDetail findBankMasterTrans(String TRNS_ID, UserInfoBean user)throws Exception {

		BankReportTransDetail bean = new BankReportTransDetail();
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|findBankMasterTrans|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|findBankMasterTrans|TRNS_ID|" + TRNS_ID);

			String sql = "SELECT MT.TRNS_ID, TO_CHAR(MT.TRNS_DTTM, 'YYYY-MM-DD HH24:MI:SS') AS TRNS_DTTM, MT.TRNS_BANK_CODE, BM.BANK_NAME,"
						+ " SE.BANK_SRVC_NAME ,MT.TRNS_TNSF_AMNT ,MT.TRNS_ACCT_TO , MT.TRNS_ACCT_FROM ,BFC.BANK_MSGE_CODE_MSGE_EN ,"
						+ "MT.TRNS_EFCT_DTTM ,MT.TRNS_RVSL_STTS_CODE  ,MT.BANK_SRVC_ID ,MT.TRNS_BANK_REF ,MT.TRNS_BANK_STTS_CODE ,MT.TRNS_BANK_STTS_MSG,MT.TRNS_RVSL_DTTM,MT.LAST_CHNG_DTTM,MT.TRNS_TNSF_DTTM,MT.TRNS_ACCT_HOLD_NAME,MT.TRNS_BANK_BRNC_CODE  "
						+ "FROM GW_MAST_TRNS_02 MT "
						+ "LEFT JOIN Bank_master BM ON MT.TRNS_BANK_CODE = BM.BANK_CODE "
						+ "LEFT JOIN BANK_SERVICE  SE  ON  MT.BANK_SRVC_ID  = SE.BANK_SRVC_ID  "
						+ "LEFT JOIN Bank_fdm_code  BFC ON  MT.TRNS_STTS_CODE  = BFC.BANK_MSGE_CODE "
						+ " WHERE  MT.TRNS_ID = '"+TRNS_ID+"'";
			sql += " ORDER BY TRNS_DTTM  DESC ";			
			

			log.info(user.getName() + "|" + page + "|findBankMasterTrans|SQL:" + sql);

			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			for (Object[] item : list){
				int x = 0;
				bean = new BankReportTransDetail();
				bean.setTRNS_ID(JpaResultUtil.getString(item, x++));
				bean.setTRNS_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setTRNS_BANK_CODE(JpaResultUtil.getString(item, x++));
				bean.setBANK_NAME(JpaResultUtil.getString(item, x++));
				bean.setBANK_SRVC_NAME(JpaResultUtil.getString(item, x++));
				bean.setTRNS_TNSF_AMNT(JpaResultUtil.getBigDecimal(item, x++));
				bean.setTRNS_ACCT_TO(JpaResultUtil.getString(item, x++));
				bean.setTRNS_ACCT_FROM(JpaResultUtil.getString(item, x++));
				bean.setBANK_MSGE_CODE_MSGE_EN(JpaResultUtil.getString(item, x++));
				bean.setTRNS_EFCT_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setTRNS_RVSL_STTS_CODE(JpaResultUtil.getString(item, x++));
				bean.setBANK_SRVC_ID(JpaResultUtil.getInteger(item, x++));
				bean.setTRNS_BANK_REF(JpaResultUtil.getString(item, x++));
				bean.setTRNS_BANK_STTS_CODE(JpaResultUtil.getString(item, x++));
				bean.setTRNS_BANK_STTS_MSG(JpaResultUtil.getString(item, x++));
				bean.setTRNS_RVSL_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setLAST_CHNG_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setTRNS_TNSF_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setTRNS_ACCT_HOLD_NAME(JpaResultUtil.getString(item, x++));
				bean.setTRNS_BANK_BRNC_CODE(JpaResultUtil.getString(item, x++));
			}
			//log.info(user.getName() + "|" + page + "|searchReportTrans|List.Size:" + listDetail.size());
			log.info(user.getName() + "|" + page + "|findBankMasterTrans|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|findBankMasterTrans|Exception:" + e.getMessage());
			throw e;
		}
		return bean;
	}

	public Integer countRowAll(GWMasterTransParam Param) throws Exception {

		try {

			Timer timer = new Timer("-");
			
			log.info("RptTransBean|countRowAll|Time:"
					+ timer.getStartTime());
			log.info("RptTransBean|countRowAll|GWMasterTransParam:"
					+ Param.toString());

			if (Param.getTABLE_NAME() == null || Param.getTABLE_NAME() == "") {
				Param.setTABLE_NAME("GW_MAST_TRNS_01");
			}

			Vector<String> v = new Vector<String>();

			String sql = "SELECT COUNT(TRNS_ID) AS count_row" + "FROM "
					+ Param.getTABLE_NAME() + " ";

			log.info("RptTransBean|RptTransBean|SQL:" + sql);

			if (Param.getTRNS_ID() != null && !"".equals(Param.getTRNS_ID()))
				v.add("TRNS_ID='" + Param.getTRNS_ID() + "'");

			if (Param.getTRNS_SRCE_CHNL_CODE() != null
					&& !"".equals(Param.getTRNS_SRCE_CHNL_CODE()))
				v.add("TRNS_SRCE_CHNL_CODE='" + Param.getTRNS_SRCE_CHNL_CODE()
						+ "'");

			if (Param.getTRNS_DEST_CODE() != null
					&& !"".equals(Param.getTRNS_DEST_CODE()))
				v.add("TRNS_DEST_CODE='" + Param.getTRNS_DEST_CODE() + "'");

			if (Param.getFROM_DTTM() != null
					&& !"".equals(Param.getFROM_DTTM()))
				v.add("CRTD_DTTM >=TO_DATE('" + Param.getFROM_DTTM()
						+ "', 'YYYY-MM-DD HH24:MI:SS')");

			if (Param.getTO_DTTM() != null && !"".equals(Param.getTO_DTTM()))
				v.add("CRTD_DTTM <=TO_DATE('" + Param.getTO_DTTM()
						+ "', 'YYYY-MM-DD HH24:MI:SS')");

			if (Param.getTRNS_STTS_CODE() != null
					&& !"".equals(Param.getTRNS_STTS_CODE()))
				v.add("TRNS_STTS_CODE='" + Param.getTRNS_STTS_CODE() + "'");

			StringBuffer sb = new StringBuffer();

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

			sql = sql + sb.toString();

			log.info("RptTransBean|countRowAll|sql:" + sql);

			Query query = em.createNativeQuery(sql);

			List list = query.getResultList();
			List item = (List) list.get(0);
			Integer numRow = Integer.parseInt("" + item.get(0));

			log.info("RptTransBean|countRowAll|numRow:" + numRow);
			log.info("RptTransBean|countRowAll|Time:"
					+ timer.getStopTime());
			return numRow;

		} catch (Exception e) {
		
			log.info("RptTransBean|countRowAll|Exception:" + e);
		}

		return new Integer(0);
	}
	
	public List<BillerChannel> getBillerChannelAll(UserInfoBean user)throws Exception{
		List<BillerChannel> list = null;
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|getBillerChannelAll|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_CHANNEL ORDER BY BLLR_CHNL_ID";

			log.info(user.getName() + "|" + page + "|getBillerChannelAll|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BillerChannel.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|getBillerChannelAll|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|getBillerChannelAll|Time:" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|getBillerChannelAll|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}
	
	public List<BankFdmCode> getBankFdmCodeAll(UserInfoBean user)throws Exception{
		List<BankFdmCode> list = null;
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|getBillerFdmCodeAll|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BANK_FDM_CODE WHERE ACT_FLAG = 'A' ORDER BY BANK_MSGE_CODE";

			log.info(user.getName() + "|" + page + "|getBillerFdmCodeAll|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BankFdmCode.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|getBillerFdmCodeAll|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|getBillerFdmCodeAll|Time:" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|getBillerFdmCodeAll|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}
	
	public List<BillerPymtCode> getBillerPymtCodeAll(UserInfoBean user)throws Exception{
		List<BillerPymtCode> list = null;
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|getBillerPymtCodeAll|Time:" + timer.getStartTime());
			String sql = "SELECT * FROM BILLER_PYMT_CODE WHERE ACL_FLAG = 'A'  ORDER BY BLLR_MSGE_CODE";

			log.info(user.getName() + "|" + page + "|getBillerPymtCodeAll|SQL:" + sql);
			
			Query query = em.createNativeQuery(sql, BillerPymtCode.class);
			list = query.getResultList();
			log.info(user.getName() + "|" + page + "|getBillerPymtCodeAll|List.Size:" + list.size());
			log.info(user.getName() + "|" + page + "|getBillerPymtCodeAll|Time:" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|getBillerPymtCodeAll|Exception:" + e.getMessage());
			throw e;
		}
		return list;
	}

	public List<BankReportTransDetail> searchBankReportTrans(BankReportTransParam PARAM, UserInfoBean user)throws Exception{
		List<BankReportTransDetail> listDetail = new ArrayList<BankReportTransDetail>();
		try {

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|searchBankReportTrans|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|searchBankReportTrans|Param|" + PARAM.toString());

			String sql = "SELECT MT.TRNS_ID, TO_CHAR(MT.TRNS_DTTM, 'YYYY-MM-DD HH24:MI:SS') AS TRNS_DTTM, MT.TRNS_BANK_CODE, BM.BANK_NAME, "
						+ "SE.BANK_SRVC_NAME ,MT.TRNS_TNSF_AMNT ,MT.TRNS_ACCT_TO , MT.TRNS_ACCT_FROM ,BFC.BANK_MSGE_CODE_MSGE_EN ,"
						+ "MT.TRNS_EFCT_DTTM ,MT.TRNS_RVSL_STTS_CODE  ,MT.BANK_SRVC_ID ,MT.TRNS_BANK_REF ,MT.TRNS_BANK_STTS_CODE ,MT.TRNS_BANK_STTS_MSG,MT.TRNS_RVSL_DTTM,MT.LAST_CHNG_DTTM,MT.TRNS_TNSF_DTTM,MT.TRNS_ACCT_HOLD_NAME,MT.TRNS_BANK_BRNC_CODE "
						+ "FROM GW_MAST_TRNS_02 MT "
						+ "LEFT JOIN Bank_master BM ON MT.TRNS_BANK_CODE = BM.BANK_CODE "
						+ "LEFT JOIN BANK_SERVICE  SE  ON  MT.BANK_SRVC_ID  = SE.BANK_SRVC_ID  "
						+ "LEFT JOIN Bank_fdm_code  BFC ON  MT.TRNS_STTS_CODE  = BFC.BANK_MSGE_CODE  ";
					   sql += genBankReportTransCriteria(PARAM);
			sql += " ORDER BY TRNS_DTTM  DESC ";
			
			if (PARAM.getPAGE_NO() == null){
				PARAM.setPAGE_NO(1);
			}
			if (PARAM.getPAGE_SIZE() == null){
				PARAM.setPAGE_SIZE(20);
			}
			sql = "SELECT * FROM " + "( " + "SELECT a.*, rownum r__ " + "FROM "
					+ "( " + sql + ") a " + "WHERE rownum < (("
					+ PARAM.getPAGE_NO() + " * " + PARAM.getPAGE_SIZE()
					+ ") + 1 ) " + ") " + "WHERE r__ >= ((("
					+ PARAM.getPAGE_NO() + "-1) * " + PARAM.getPAGE_SIZE()
					+ ") + 1) ";

			log.info(user.getName() + "|" + page + "|searchBankReportTrans|SQL:" + sql);

			Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			BankReportTransDetail bean = null;
			for (Object[] item : list){
				int x = 0;
				bean = new BankReportTransDetail();
				bean.setTRNS_ID(JpaResultUtil.getString(item, x++));
				bean.setTRNS_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setTRNS_BANK_CODE(JpaResultUtil.getString(item, x++));
				bean.setBANK_NAME(JpaResultUtil.getString(item, x++));
				bean.setBANK_SRVC_NAME(JpaResultUtil.getString(item, x++));
				bean.setTRNS_TNSF_AMNT(JpaResultUtil.getBigDecimal(item, x++));
				bean.setTRNS_ACCT_TO(JpaResultUtil.getString(item, x++));
				bean.setTRNS_ACCT_FROM(JpaResultUtil.getString(item, x++));
				bean.setBANK_MSGE_CODE_MSGE_EN(JpaResultUtil.getString(item, x++));
				bean.setTRNS_EFCT_DTTM(JpaResultUtil.getDate(item, x++, "yyyy-MM-dd HH:mm:ss"));
				bean.setTRNS_RVSL_STTS_CODE(JpaResultUtil.getString(item, x++));
				bean.setBANK_SRVC_ID(JpaResultUtil.getInteger(item, x++));
				listDetail.add(bean);
			}
			log.info(user.getName() + "|" + page + "|searchBankReportTrans|List.Size:" + listDetail.size());
			log.info(user.getName() + "|" + page + "|searchBankReportTrans|Time|" + timer.getStopTime());
		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|searchBankReportTrans|Exception:" + e.getMessage());
			throw e;
		}
		return listDetail;
	}

	
	public int countRowBankReportTrans(BankReportTransParam PARAM, UserInfoBean user)throws Exception{
		try{

			Timer timer = new Timer("-");
			log.info(user.getName() + "|" + page + "|countRowBankReportTrans|Time|" + timer.getStartTime());
			log.info(user.getName() + "|" + page + "|countRowBankReportTrans|Param|" + PARAM.toString());
			
			String sql = "SELECT count(*) FROM GW_MAST_TRNS_02 MT";
			sql += genBankReportTransCriteria(PARAM);
			
			log.info(user.getName() + "|" + page + "|countRowBankReportTrans|SQL:" + sql);

			Query query = em.createNativeQuery(sql);
			BigDecimal result = (BigDecimal)query.getSingleResult();
			int row = result.intValue();
			log.info(user.getName() + "|" + page + "|countRowBankReportTrans|Total Row:" + row);
			log.info(user.getName() + "|" + page + "|countRowBankReportTrans|Time|" + timer.getStopTime());
			return row;

		}catch(Exception e){
			log.error(user.getName() + "|" + page + "|countRowBankReportTrans|Exception:" + e.getMessage());
			throw e;
		}
	}

	private String genBankReportTransCriteria(BankReportTransParam PARAM){
		Vector<String> v = new Vector<String>();
		StringBuffer sb = new StringBuffer();
		if ( (PARAM.getFROM_TRNS_DTTM()!=null) && (PARAM.getTO_TRNS_DTTM()!=null) ){
			v.add(" MT.TRNS_DTTM BETWEEN to_date('" + DateTimeUtil.parseToString(PARAM.getFROM_TRNS_DTTM(), "yyyy-MM-dd HH:mm:ss") + "', 'YYYY-MM-DD HH24:MI:SS') AND to_date('" + DateTimeUtil.parseToString(PARAM.getTO_TRNS_DTTM(), "yyyy-MM-dd HH:mm:ss") + "', 'YYYY-MM-DD HH24:MI:SS')");
		}
		if (!ValidateUtil.isEmpty(PARAM.getBANK_SRVC_ID()) && !PARAM.getBANK_SRVC_ID().equals("ALL")){
			v.add(" MT.BANK_SRVC_ID = '" + PARAM.getBANK_SRVC_ID() + "'");
		}		
		if (!ValidateUtil.isEmpty(PARAM.getTRNS_BANK_CODE()) && !PARAM.getTRNS_BANK_CODE().equals("ALL")){
			v.add(" MT.TRNS_BANK_CODE = '" + PARAM.getTRNS_BANK_CODE() + "'");
		}
		if (!ValidateUtil.isEmpty(PARAM.getTRNS_ID()) && !PARAM.getTRNS_ID().equals("ALL")){
			v.add(" MT.TRNS_ID = '" + PARAM.getTRNS_ID() + "'");
		}
		if (!ValidateUtil.isEmpty(PARAM.getTRNS_STTS_FLAG()) && !PARAM.getTRNS_STTS_FLAG().equals("ALL")){
			v.add(" MT.TRNS_STTS_FLAG = '" + PARAM.getTRNS_STTS_FLAG() + "'");
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
		return sb.toString();
	}

}
