package mfs.biller.ejb.stateless;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mfs.biller.ejb.interfaces.BatchMastFileBeanLocal;
import mfs.biller.ejb.interfaces.BatchMastFileBeanRemote;
import mfs.biller.persistence.bean.BatchMastFile;
import mfs.biller.persistence.bean.BatchMastFileParam;
import mfs.biller.util.DateTimeUtil;
import mfs.biller.util.JpaResultUtil;
import mfs.biller.util.Timer;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

/**
 * Session Bean implementation class BatchMastFileBean
 */

@Stateless(name = "BatchMastFileBean", mappedName = "mfs.biller.ejb.BatchMastFileBean")
@CallByReference
public class BatchMastFileBean implements BatchMastFileBeanRemote,
		BatchMastFileBeanLocal {
	private Logger log = Logger.getLogger("EJBBACHMASTRAN");
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public Collection<BatchMastFile> getBatchMastFileAll(
			BatchMastFileParam PARAM) throws Exception {

		try {

			Timer timer = new Timer("-");
			log.info("BatchMastFileBean|getBatchMastFileAll|Time:"
					+ timer.getStartTime());
			log.info("BatchMastFileBean|getBatchMastFileAll|BatchMastFileParam:"
					+ PARAM.toString());

			String sql = "SELECT * " + " FROM BATCH_MAST_FILE ";

			Vector<String> v = new Vector<String>();

			if (PARAM.getFROM_DTTM() != null && !"".equals(PARAM.getFROM_DTTM()))
				v.add(" TRUNC(BTCH_SEND_FILE_DTTM) >= TO_DATE('"+DateTimeUtil.parseToString(PARAM.getFROM_DTTM(), "yyyy-MM-dd")+ "', 'YYYY-MM-DD')");

			if (PARAM.getTO_DTTM() != null && !"".equals(PARAM.getTO_DTTM()))
				v.add(" TRUNC(BTCH_SEND_FILE_DTTM) <= TO_DATE('"+DateTimeUtil.parseToString(PARAM.getTO_DTTM(), "yyyy-MM-dd")+ "', 'YYYY-MM-DD')");

			if (PARAM.getBTCH_DEST_CODE() != null && !"".equals(PARAM.getBTCH_DEST_CODE()))
				v.add(" BTCH_DEST_CODE = '" + PARAM.getBTCH_DEST_CODE() + "'");

			if (PARAM.getBTCH_MAST_FILE_STTS() != null && !"".equals(PARAM.getBTCH_MAST_FILE_STTS()))
				v.add(" BTCH_MAST_FILE_STTS = '" + PARAM.getBTCH_MAST_FILE_STTS() + "'");			
			
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
			
			if(!v.isEmpty()){
				sb.append(" AND BTCH_MAST_FILE_STTS = 'S'");
			}else{
				sb.append(" WHERE BTCH_MAST_FILE_STTS = 'S'");
			}
			if (PARAM.getBTCH_DEST_CODE() == null || "".equals(PARAM.getBTCH_DEST_CODE())){
				if(PARAM.getListparam().size()!=0){
					List<String>  service1 =PARAM.getListparam();
					sb.append(" AND  BTCH_DEST_CODE in ("+"");
					for (int i = 0; i < service1.size(); i++) {
						if (i != 0) {
							sb.append(" , ");
						}
						sb.append("'"+service1.get(i)+"'");
					}
					sb.append(")");
				}
			}
			
			//SSO39
			Vector<String> x = new Vector<String>();
			if(PARAM.getEnableAuthorize() != null && "Y".equalsIgnoreCase(PARAM.getEnableAuthorize())){
				x.add("1 != 1");				
				if(PARAM.getTextFilePattern() != null){
					x.add(" REGEXP_LIKE (BTCH_MAST_FILE_NAME, '" + PARAM.getTextFilePattern() + "')");
				}
				if(PARAM.getSummaryReportFilePattern() != null){
					x.add(" REGEXP_LIKE (BTCH_MAST_FILE_NAME, '" + PARAM.getSummaryReportFilePattern() + "')");
				}
				if(PARAM.getDetailReportFilePattern() != null){
					x.add(" REGEXP_LIKE (BTCH_MAST_FILE_NAME, '" + PARAM.getDetailReportFilePattern() + "')");
				}				
			}			
			if (!x.isEmpty()) {
				sb.append(" AND (");
				for (int i = 0; i < x.size(); i++) {
					if (i != 0) {
						sb.append(" OR ");
					}
					sb.append(x.get(i));
				}
				sb.append(")");						
			}
			if(PARAM.getIgnoreFilePattern() != null){
				sb.append(" AND NOT REGEXP_LIKE (BTCH_MAST_FILE_NAME, '" + PARAM.getIgnoreFilePattern() + "')");
			}
			
			sql = sql + sb.toString();
			sql += " ORDER BY BTCH_SEND_FILE_DTTM desc, BTCH_MAST_FILE_NAME asc";
			//sql += " ORDER BY TRUNC(BTCH_SEND_FILE_DTTM , 'DD') desc, BTCH_MAST_FILE_NAME asc";

			if (PARAM.getPAGE_NO() == null) {
				PARAM.setPAGE_NO(1);
			}

			if (PARAM.getPAGE_SIZE() == null) {
				PARAM.setPAGE_SIZE(20);
			}

			sql = "SELECT * FROM " + "( " + "SELECT a.*, rownum r__ " + " FROM "
					+ "( " + sql + ") a " + " WHERE rownum < (("
					+ PARAM.getPAGE_NO() + " * " + PARAM.getPAGE_SIZE()
					+ ") + 1 ) " + ") " + " WHERE r__ >= ((("
					+ PARAM.getPAGE_NO() + "-1) * " + PARAM.getPAGE_SIZE()
					+ ") + 1) ";

			log.info("BatchMastFileBean|getBatchMastFileAll|sql:" + sql);

			Collection<BatchMastFile> colreturn = new Vector();

			Query query = em.createNativeQuery(sql, BatchMastFile.class);

			List list = (List) query.getResultList();
			log.info("BatchMastFileBean|getBatchMastFileAll|list.size:"
					+ list.size());

			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				BatchMastFile BatchMastFile = (BatchMastFile) o;

				// log.info("BatchMastFileBean|getBatchMastFileAll|BatchMastFile:"+BatchMastFile.getBTCH_MAST_FILE_ID()+
				// "|"+BatchMastFile.getBTCH_MAST_FILE_NAME()+"|"+BatchMastFile.getBTCH_SEND_FILE_DTTM());

				log.info("BatchMastFileBean|getBatchMastFileAll|BatchMastFile:"
						+ BatchMastFile.getBTCH_MAST_FILE_ID() + "|"
						+ BatchMastFile.getBTCH_MAST_FILE_NAME() + "|"
						+ BatchMastFile.getBTCH_SEND_FILE_DTTM());

				colreturn.add(BatchMastFile);
			}
			log.info("BatchMastFileBean|getBatchMastFileAll|Time:"
					+ timer.getStopTime());
			return colreturn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("BatchMastFileBean|getBatchMastFileAll|Exception:" + e);
		}

		return new Vector();
	}

	public BatchMastFile findBatchMastFile(String BTCH_MAST_FILE_ID)
			throws Exception {
		try {

			Timer timer = new Timer("-");
			log.info("BatchMastFileBean|findBatchMastFile|Time:" + timer.getStartTime());
			String sql = "SELECT * " + "FROM BATCH_MAST_FILE ";

			Vector<String> v = new Vector<String>();

			if (BTCH_MAST_FILE_ID != null && !"".equals(BTCH_MAST_FILE_ID))
				v.add("BTCH_MAST_FILE_ID='" + BTCH_MAST_FILE_ID + "'");

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
			sql += " ORDER BY BTCH_SEND_FILE_DTTM desc";

			log.info("BatchMastFileBean|findBatchMastFile|sql:" + sql);

			Query query = em.createNativeQuery(sql, BatchMastFile.class);

			List<BatchMastFile> listRow = query.getResultList();

			BatchMastFile mastTrnsPrtn = (BatchMastFile) listRow.get(0);
			log.info("BatchMastFileBean|findBatchMastFile|BatchMastFile:"
					+ mastTrnsPrtn.getBTCH_MAST_FILE_ID() + "|"
					+ mastTrnsPrtn.getBTCH_MAST_FILE_NAME() + "|"
					+ mastTrnsPrtn.getBTCH_SEND_FILE_DTTM());
			log.info("BatchMastFileBean|findBatchMastFile|Time:" + timer.getStopTime());
			return mastTrnsPrtn;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("BatchMastFileBean|findBatchMastFile|Exception:" + e);
		}

		return new BatchMastFile();
	}

	public BigDecimal countRowAll(BatchMastFileParam PARAM) throws Exception {
		try {
			
			Timer timer = new Timer("-");
			log.info("BatchMastFileBean|countRowAll|Time:" +timer.getStartTime());
			String sql = "SELECT COUNT(BTCH_MAST_FILE_ID)AS count_row "
					+ " FROM BATCH_MAST_FILE ";

			Vector<String> v = new Vector<String>();				
			if (PARAM.getFROM_DTTM() != null && !"".equals(PARAM.getFROM_DTTM()))
				v.add(" TRUNC(BTCH_SEND_FILE_DTTM) >= TO_DATE('"+DateTimeUtil.parseToString(PARAM.getFROM_DTTM(), "yyyy-MM-dd")+ "', 'YYYY-MM-DD')");

			if (PARAM.getTO_DTTM() != null && !"".equals(PARAM.getTO_DTTM()))
				v.add(" TRUNC(BTCH_SEND_FILE_DTTM) <= TO_DATE('"+DateTimeUtil.parseToString(PARAM.getTO_DTTM(), "yyyy-MM-dd")+ "', 'YYYY-MM-DD')");

			if (PARAM.getBTCH_DEST_CODE() != null && !"".equals(PARAM.getBTCH_DEST_CODE()))
				v.add(" BTCH_DEST_CODE = '" + PARAM.getBTCH_DEST_CODE() + "'");

			if (PARAM.getBTCH_MAST_FILE_STTS() != null && !"".equals(PARAM.getBTCH_MAST_FILE_STTS()))
				v.add(" BTCH_MAST_FILE_STTS = '" + PARAM.getBTCH_MAST_FILE_STTS() + "'");
			
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
			if(!v.isEmpty()){
				sb.append(" AND BTCH_MAST_FILE_STTS = 'S'");
				}else{
					sb.append(" WHERE BTCH_MAST_FILE_STTS = 'S'");
				}
			if (PARAM.getBTCH_DEST_CODE() == null || "".equals(PARAM.getBTCH_DEST_CODE())){
				if(PARAM.getListparam().size()!=0){
					List<String>  service1 =PARAM.getListparam();
					sb.append(" AND  BTCH_DEST_CODE in ("+"");
					for (int i = 0; i < service1.size(); i++) {
						if (i != 0) {
							sb.append(" , ");
						}
						sb.append("'"+service1.get(i)+"'");
					}
					sb.append(")");
				}
			}
			
			//SSO39
			Vector<String> x = new Vector<String>();
			if(PARAM.getEnableAuthorize() != null && "Y".equalsIgnoreCase(PARAM.getEnableAuthorize())){
				x.add("1 != 1");
				if(PARAM.getTextFilePattern() != null){
					x.add(" REGEXP_LIKE (BTCH_MAST_FILE_NAME, '" + PARAM.getTextFilePattern() + "')");
				}
				if(PARAM.getSummaryReportFilePattern() != null){
					x.add(" REGEXP_LIKE (BTCH_MAST_FILE_NAME, '" + PARAM.getSummaryReportFilePattern() + "')");
				}
				if(PARAM.getDetailReportFilePattern() != null){
					x.add(" REGEXP_LIKE (BTCH_MAST_FILE_NAME, '" + PARAM.getDetailReportFilePattern() + "')");
				}
			}			
			if (!x.isEmpty()) {
				sb.append(" AND (");
				for (int i = 0; i < x.size(); i++) {
					if (i != 0) {
						sb.append(" OR ");
					}
					sb.append(x.get(i));
				}
				sb.append(")");				
			}
			if(PARAM.getIgnoreFilePattern() != null){
				sb.append(" AND NOT REGEXP_LIKE (BTCH_MAST_FILE_NAME, '" + PARAM.getIgnoreFilePattern() + "')");
			}
			
			sql = sql + sb.toString();
			log.info("BatchMastFileBean|countRowAll|sql:" + sql);

			Query query = em.createNativeQuery(sql);

			List list = query.getResultList();
			BigDecimal numRow = (BigDecimal) list.get(0);
//			BigDecimal numRow = Integer.parseInt("" + item.get(0));
			log.info("BatchMastFileBean|countRowAll|Time:" +timer.getStopTime());
			return numRow;

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("BatchMastFileBean|countRowAll|Exception:" + e);
		}

		return new BigDecimal(0);
	}

	public byte[] getBatchMastFile1(String BTCH_MAST_FILE_ID) throws Exception {
		try {
			String BTCH_MAST_FILE_NAME = null;
			Timer timer = new Timer("-");
			log.info("BatchMastFileBean|findBatchMastFile|Time:" + timer.getStartTime());
			String sql = "SELECT BTCH_MAST_FILE_NAME "
					+ "FROM BATCH_MAST_FILE ";

			Vector<String> v = new Vector<String>();

			if (BTCH_MAST_FILE_ID != null && !"".equals(BTCH_MAST_FILE_ID))
				v.add(" BTCH_MAST_FILE_ID = '" + BTCH_MAST_FILE_ID + "'");

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
			sql += " ORDER BY BTCH_SEND_FILE_DTTM desc";

			log.info("BatchMastFileBean|findBatchMastFile|sql:" + sql);

		Query query = em.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			for (Object[] item : list){
				int x = 0;
		 BTCH_MAST_FILE_NAME =(JpaResultUtil.getString(item, x++));
				
		}
			
		/*	List<String> listRow = query.getResultList();
			log.info("BatchMastFileBean|getBatchMastFile|listRow.size:"
					+ listRow.size());

			byte[] bytes =(byte[]) listRow.get(0).getBytes();*/
			
			/*List list = query.getResultList();
			log.info(list.toString());
			log.info(list.get(0).toString());
			
			log.info(list.get(0));
			byte[] b=(byte[])list.get(0);
			log.info(b);
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(baos);
			oos.writeObject(b);
			byte[] bs=baos.toByteArray();
			log.info("BatchMastFileBean|getBatchMastFile|listRow.size:"
					+ bs.length);

			log.info("BatchMastFileBean|getBatchMastFile|Text [Byte Format] : "
					+ bs);
			log.info("BatchMastFileBean|findBatchMastFile|Time:" + timer.getStopTime());
//			int tempInt = (int) blob.length();  
//			tempByte = blob.getBytes(1, tempInt);

			
			return bs;*/

		
			
		////////////////////////////////////////////////////////////////////////////////
			File file = new File(System.getProperty("user.home")+"/conf/billerweb/billerWebLog4j.properties");
			//File file = new File(System.getProperty("user.home")+"/conf/billerweb/"+BTCH_MAST_FILE_NAME+"");
			byte[] bytes = null ;
			try {
				 bytes = loadFile(file);
			} catch (IOException e1) {
				log.error(ExceptionUtils.getStackTrace(e1));
			}
			
			return bytes;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("BatchMastFileBean|getBatchMastFile|Exception:" + e);
		}

		return "".getBytes();
	}

	// public File getBatchMastFile(String BTCH_MAST_FILE_ID) throws Exception {
	// try {
	//
	// String sql = "SELECT BTCH_MAST_FILE_DATA "
	// + "FROM BATCH_MAST_FILE ";
	//
	// Vector<String> v = new Vector<String>();
	//
	// if (BTCH_MAST_FILE_ID != null && !"".equals(BTCH_MAST_FILE_ID))
	// v.add("BTCH_MAST_FILE_ID='" + BTCH_MAST_FILE_ID + "'");
	//
	// StringBuffer sb = new StringBuffer();
	//
	// if (!v.isEmpty()) {
	// sb.append(" WHERE ");
	// for (int i = 0; i < v.size(); i++) {
	// if (i != 0) {
	// sb.append(" AND ");
	// }
	//
	// sb.append(v.get(i));
	// }
	// }
	//
	// sql = sql + sb.toString();
	// sql += " ORDER BY BTCH_SEND_FILE_DTTM desc";
	//
	// log.info("BatchMastFileBean|findBatchMastFile|sql:" + sql);
	//
	// Query query = em.createNativeQuery(sql);
	//
	// List<String> listRow = query.getResultList();
	// log.info("BatchMastFileBean|findBatchMastFile|listRow:" + listRow);
	//
	// log.info("BatchMastFileBean|findBatchMastFile|listRow.size():"
	// + listRow.size());
	//
	// log.info("BatchMastFileBean|findBatchMastFile|listRow.get(0):"
	// + listRow.get(0));
	//
	// byte[] bytes = listRow.get(0).getBytes("UTF-8");
	//
	//
	// log.info("Text : " + listRow.get(0));
	// log.info("Text [Byte Format] : " + bytes);
	//
	// File batchMastFile = writeStringToFile(BTCH_MAST_FILE_ID,
	// listRow.get(0));
	//
	// // File batchMastFile = new File()) listRow.get(0);
	// log.info("BatchMastFileBean|findBatchMastFile|BatchMastFile:"
	// + batchMastFile);
	//
	// return batchMastFile;
	//
	// } catch (Exception e) {

	// log.info("BatchMastFileBean|findBatchMastFile|Exception:" + e);
	// }
	//
	// return new File("");
	// }

	public File writeStringToFile(String fileName, String data) {
		FileWriter fileWriter = null;

		try {
			String content = data;
			File newTextFile = new File("BTCH_MAST_FILE_" + fileName + ".txt");
			fileWriter = new FileWriter(newTextFile);
			fileWriter.write(content);
			fileWriter.close();

			return newTextFile;

		} catch (IOException ex) {
			log.info("BatchMastFileBean|WriteStringToFile|IOException:" + ex);

		} finally {
			try {
				fileWriter.close();

			} catch (IOException ex) {
				log.info("BatchMastFileBean|WriteStringToFile|finally IOException:"
						+ ex);
			}
		}

		return new File("");
	}

	/*public static void main(String[] argh) {
		File file = new File(System.getProperty("user.home")+"/conf/billerweb/billerWebLog4j.properties");
		//File file = new File("C:\\log\\");

		//for(String fileNames : file.list());
		try {
			byte[] bytes = loadFile(file);
			

		} catch (IOException e1) {
	
		}
		byte[] fileData = new byte[(int) file.length()];

		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			

			
		}

		try {
			
			in.read(fileData);
	
		} catch (IOException e) {
		


		}
		try {
			in.close();
		} catch (IOException e) {
			
		}


	}*/
	
	private static byte[] loadFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);
 
	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    byte[] bytes = new byte[(int)length];
	    
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }
 
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }
 
	    is.close();
	    return bytes;
	}
	public byte[] getBatchMastFile(String BTCH_MAST_FILE_ID) throws Exception {
		try {
			String BTCH_MAST_FILE_NAME = null;
			Timer timer = new Timer("-");
			log.info("BatchMastFileBean|findBatchMastFile|Time:" + timer.getStartTime());
			String sql = "SELECT BTCH_MAST_FILE "
					+ "FROM BATCH_MAST_FILE ";

			Vector<String> v = new Vector<String>();

			if (BTCH_MAST_FILE_ID != null && !"".equals(BTCH_MAST_FILE_ID))
				v.add(" BTCH_MAST_FILE_ID = '" + BTCH_MAST_FILE_ID + "'");

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
			sql += " ORDER BY BTCH_SEND_FILE_DTTM desc";

			log.info("BatchMastFileBean|findBatchMastFile|sql:" + sql);

		Query query = em.createNativeQuery(sql);
//			List<Object> list = query.getResultList();
//			for (Object item : list){
//				int x = 0;
//			 BTCH_MAST_FILE_NAME =(JpaResultUtil.getString(item, x++));
//				
//			}
			
		/*	List<String> listRow = query.getResultList();
			log.info("BatchMastFileBean|getBatchMastFile|listRow.size:"
					+ listRow.size());

			byte[] bytes =(byte[]) listRow.get(0).getBytes();*/
			
			List list = query.getResultList();

			byte[] b=(byte[])( list.get(0));
			/*log.info(b);
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(baos);
			oos.writeObject(b);
			byte[] bs=baos.toByteArray();*/
			log.info("BatchMastFileBean|getBatchMastFile|listRow.size:"
					+ b.length);

			log.info("BatchMastFileBean|getBatchMastFile|Text [Byte Format] : "
					+ b);
			log.info("BatchMastFileBean|findBatchMastFile|Time:" + timer.getStopTime());
//			int tempInt = (int) blob.length();  
//			tempByte = blob.getBytes(1, tempInt);

			
			return b;

		
			
		////////////////////////////////////////////////////////////////////////////////
			//File file = new File(System.getProperty("user.home")+"/conf/billerweb/billerWebLog4j.properties");
		/*	File file = new File(System.getProperty("user.home")+"/conf/billerweb/"+BTCH_MAST_FILE_NAME+"");
			byte[] bytes = null ;
			try {
				 bytes = loadFile(file);
			} catch (IOException e1) {
				
			
			}
			
			return bytes;*/
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			log.info("BatchMastFileBean|getBatchMastFile|Exception:" + e);
		}

		return "".getBytes();
	}
	
}
