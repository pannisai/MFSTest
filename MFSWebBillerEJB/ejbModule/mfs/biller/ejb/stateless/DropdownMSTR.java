package mfs.biller.ejb.stateless;

import java.util.Collection;
import java.util.List;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.DropdownMSTRLocal;
import mfs.biller.ejb.interfaces.DropdownMSTRRemote;
import mfs.biller.persistence.bean.DropdownlistBillMSRT;
import mfs.biller.util.Timer;

/**
 * Session Bean implementation class DropdownMSTR
 */
@Stateless(name = "DropdownMSTR", mappedName = "mfs.biller.ejb.DropdownMSTR")
@CallByReference
public class DropdownMSTR implements DropdownMSTRRemote, DropdownMSTRLocal {
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
    /**
     * Default constructor. 
     */
	Timer timer = new Timer("-");
    public DropdownMSTR() {
        
    }
    public Collection<DropdownlistBillMSRT> getDropdownlistMSTR() throws Exception {
		List listDropdownlistBillMSRT = null;
		try{

			String sql = "SELECT BLLR_MSTR_ID ,BLLR_CATG_ID ,BLLR_CODE " + "FROM BILLER_MASTER ";
			Query query = em.createNativeQuery(sql, DropdownlistBillMSRT.class);
			listDropdownlistBillMSRT = (List) query.getResultList();

		}
		catch (Exception e) {
			
		}
		return listDropdownlistBillMSRT;
		
	}

}
