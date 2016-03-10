package mfs.biller.ejb.interfaces;

import java.math.BigDecimal;
import java.util.Collection;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.BatchMastFile;
import mfs.biller.persistence.bean.BatchMastFileParam;

@Remote
public interface BatchMastFileBeanRemote {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.BatchMastFileBean#mfs.biller.ejb.interfaces.BatchMastFileBeanRemote";

	public Collection<BatchMastFile> getBatchMastFileAll(BatchMastFileParam PARAM) throws Exception;

	public BatchMastFile findBatchMastFile(String BTCH_MAST_FILE_ID)
			throws Exception;

	public BigDecimal countRowAll(BatchMastFileParam PARAM) throws Exception;

	public byte[] getBatchMastFile(String BTCH_MAST_FILE_ID) throws Exception;

}
