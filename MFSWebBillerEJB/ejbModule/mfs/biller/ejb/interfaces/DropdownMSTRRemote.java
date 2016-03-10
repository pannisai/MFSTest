package mfs.biller.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.DropdownlistBillMSRT;

@Remote
public interface DropdownMSTRRemote {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.DropdownMSTR#mfs.biller.ejb.interfaces.DropdownMSTRRemote";
	 public Collection<DropdownlistBillMSRT> getDropdownlistMSTR() throws Exception;
}
