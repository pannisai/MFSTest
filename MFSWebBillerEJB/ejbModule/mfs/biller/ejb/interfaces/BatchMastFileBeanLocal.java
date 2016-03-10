package mfs.biller.ejb.interfaces;

import java.math.BigDecimal;
import java.util.Collection;

import javax.ejb.Local;

import mfs.biller.persistence.bean.BatchMastFile;
import mfs.biller.persistence.bean.BatchMastFileParam;

@Local
public interface BatchMastFileBeanLocal {
	public Collection<BatchMastFile> getBatchMastFileAll(BatchMastFileParam PARAM) throws Exception;

	public BatchMastFile findBatchMastFile(String BTCH_MAST_FILE_ID)
			throws Exception;

	public BigDecimal countRowAll(BatchMastFileParam PARAM) throws Exception;
	
	public byte[] getBatchMastFile(String BTCH_MAST_FILE_ID) throws Exception;

}
