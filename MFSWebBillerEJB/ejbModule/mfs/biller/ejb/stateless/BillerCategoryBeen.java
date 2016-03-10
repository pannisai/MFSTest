package mfs.biller.ejb.stateless;

import java.util.Collection;
import java.util.List;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.BillerCategoryBeenLocal;
import mfs.biller.ejb.interfaces.BillerCategoryBeenRemote;
import mfs.biller.persistence.bean.BillerCategory;
import mfs.biller.persistence.bean.DropdownlistBillMSRT;
import mfs.biller.persistence.bean.DropdownlistBillservice;
import mfs.biller.util.Timer;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
//import mfs.biller.util.ConfigLoader;

/**
 * Session Bean implementation class BillerCategoryBeen
 */
@Stateless(name = "BillerCategoryBeen", mappedName = "mfs.biller.ejb.BillerCategoryBeen")
@CallByReference
public class BillerCategoryBeen implements BillerCategoryBeenRemote, BillerCategoryBeenLocal {
	private Logger log = Logger.getLogger("EJBBACHMASTRAN");
    /**
     * Default constructor. 
     */
 
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	Timer timer = new Timer("-");
	@SuppressWarnings("unchecked")
	public Collection<BillerCategory> getBillerCategory() throws Exception {
		List listBillerCategory = null;
		try{

			String sql = "SELECT BLLR_CATG_ID,BLLR_CATG_NAME,BLLR_CATG_DESC,ACT_FLAG,CRTD_BY,CRTD_DTTM,LAST_CHNG_BY,LAST_CHNG_DTTM " + "FROM BILLER_CATEGORY ";
			
			Query query = em.createNativeQuery(sql, BillerCategory.class);
			
			listBillerCategory = (List) query.getResultList();
			
		}
		catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return listBillerCategory;
		
	}
	 public Collection<BillerCategory> getDropdownlistCATG() throws Exception {
			List listBillerCategory = null;
			try{

				String sql = "SELECT BLLR_CATG_ID,BLLR_CATG_NAME,BLLR_CATG_DESC,ACT_FLAG,CRTD_BY,CRTD_DTTM,LAST_CHNG_BY,LAST_CHNG_DTTM " + "FROM BILLER_CATEGORY order by BLLR_CATG_ID ";
				
				Query query = em.createNativeQuery(sql, BillerCategory.class);
				listBillerCategory = (List) query.getResultList();

			}
			catch (Exception e) {
				log.error(ExceptionUtils.getStackTrace(e));
			}
			return listBillerCategory;
			
		}

		public Collection<DropdownlistBillMSRT> getDropdownlistMSTR() throws Exception {
			List listDropdownlistBillMSRT = null;
			try{

				String sql = "SELECT BLLR_MSTR_ID ,BLLR_CATG_ID ,BLLR_CODE " + "FROM BILLER_MASTER  order by BLLR_MSTR_ID";
				
				Query query = em.createNativeQuery(sql, DropdownlistBillMSRT.class);
				listDropdownlistBillMSRT = (List) query.getResultList();
				

			}
			catch (Exception e) {
				log.error(ExceptionUtils.getStackTrace(e));
			}
			return listDropdownlistBillMSRT;
			
		}
		public Collection<DropdownlistBillservice> getDropdownlistSERVICE() throws Exception {
			List listBillerCategory = null;
			try{

				String sql = "SELECT BLLR_SRVC_ID,BLLR_SRVC_DESC ,BLLR_MSTR_ID  " + "FROM BILLER_SERVICE  order by BLLR_SRVC_ID ";
				
				Query query = em.createNativeQuery(sql, DropdownlistBillservice.class);
				listBillerCategory = (List) query.getResultList();
				

			}
			catch (Exception e) {
				log.error(ExceptionUtils.getStackTrace(e));
			}
			return listBillerCategory;
			
		}


}
