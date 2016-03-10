package mfs.biller.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.BillerCategory;
import mfs.biller.persistence.bean.DropdownlistBillMSRT;
import mfs.biller.persistence.bean.DropdownlistBillservice;

@Remote
public interface BillerCategoryBeenRemote {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.BillerCategoryBeen#mfs.biller.ejb.interfaces.BillerCategoryBeenRemote";
	public Collection<BillerCategory> getBillerCategory() throws Exception;
	public Collection<DropdownlistBillservice> getDropdownlistSERVICE() throws Exception;
	public Collection<DropdownlistBillMSRT> getDropdownlistMSTR() throws Exception;
}
