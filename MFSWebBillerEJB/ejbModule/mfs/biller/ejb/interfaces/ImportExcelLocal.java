package mfs.biller.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.ImportExcelBean;

@Local
public interface ImportExcelLocal {

	public List<ImportExcelBean> getDropDownTableList();

	public ImportExcelBean validateImportExcel(ImportExcelBean tempBean);

	public ImportExcelBean insertImportExcel(ImportExcelBean tempBean);
}
