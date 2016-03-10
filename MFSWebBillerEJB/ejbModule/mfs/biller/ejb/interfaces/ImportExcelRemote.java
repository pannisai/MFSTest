package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.ImportExcelBean;

@Remote
public interface ImportExcelRemote {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.ImportExcel#mfs.biller.ejb.interfaces.ImportExcelRemote";

	public List<ImportExcelBean> getDropDownTableList();

	public ImportExcelBean validateImportExcel(ImportExcelBean tempBean);

	public ImportExcelBean insertImportExcel(ImportExcelBean tempBean);
}
