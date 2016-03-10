package mfs.biller.ejb.stateless;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javaee.CallByReference;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import mfs.biller.ejb.interfaces.ImportExcelLocal;
import mfs.biller.ejb.interfaces.ImportExcelRemote;
import mfs.biller.persistence.bean.ImportExcelBean;
import mfs.biller.util.DBUtil;
import oracle.jdbc.xa.OracleXid;

import org.apache.log4j.Logger;

@Stateless(name = "ImportExcel", mappedName = "mfs.biller.ejb.ImportExcel")
@CallByReference
public class ImportExcel implements ImportExcelLocal, ImportExcelRemote {
	private Logger log = Logger.getLogger("EJBIMPORTEXCEL");
	private String page = "EJBIMPORTEXCEL";
	@PersistenceContext(unitName = "mfs.db.MFSDAO")
	private EntityManager em;
	private final DBUtil dbUtil = new DBUtil();

	@Override
	public List<ImportExcelBean> getDropDownTableList() {
		List<ImportExcelBean> tableImportList = new ArrayList<ImportExcelBean>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		try {
			con = em.unwrap(Connection.class);
			stmt = con.createStatement();
			sql = "select tb.table_name from user_tables tb	where tb.table_name in(\'DATA_EXT_MAN_COMMISSION_ERP_CD\')";
			rs = stmt.executeQuery(sql);
			ImportExcelBean temp;
			while (rs.next()) {
				temp = new ImportExcelBean();
				temp.setTABLE_NAME(rs.getString("TABLE_NAME"));
				tableImportList.add(temp);
			}
		} catch (SQLException e) {
			log.error("|" + page + "|getDropDownTableImport|Exception:"
					+ e.getMessage());
		} finally {
			dbUtil.closeQuietly(rs);
			dbUtil.closeQuietly(stmt);
			dbUtil.closeQuietly(con);
		}
		return tableImportList;
	}

	@Override
	public ImportExcelBean validateImportExcel(ImportExcelBean tempBean) {
		XAConnection xaCon = null;
		Connection con = null;
		XAResource xaRes = null;
		Xid xid = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			xaCon = (XAConnection) em.unwrap(Connection.class);
			con = xaCon.getConnection();
			xaRes = xaCon.getXAResource();
			xid = createXid(1, 1);
			xaRes.start(xid, XAResource.TMNOFLAGS);
			stmt = con.createStatement();
			sql = "select atc.column_name,atc.data_type from all_tab_cols atc where atc.table_name = '"
					+ tempBean.getTABLE_NAME() + "'";
			rs = stmt.executeQuery(sql);
			Map<String, String> typeMap = new HashMap<String, String>();
			while (rs.next()) {
				typeMap.put(rs.getString("COLUMN_NAME"),
						rs.getString("DATA_TYPE"));
			}
			List<String> tempField = new ArrayList<String>();
			List<Object> tempValue = new ArrayList<Object>();
			int rowNo = 0;
			Map<String, Object> statusMap;
			sql = "";
			for (Map<String, Object> tempMap : tempBean.getListRowData()) {
				statusMap = tempBean.getListRowStatus().get(rowNo);
				for (Map.Entry<String, Object> temp : tempMap.entrySet()) {
					if (temp.getValue() != null) {
						tempField.add(temp.getKey());
						tempValue.add(temp.getValue());
					}
				}
				sql = "INSERT INTO " + tempBean.getTABLE_NAME() + " (";
				for (int index = 0; index < tempField.size(); index++) {
					if (index == (tempField.size() - 1)) {
						sql += tempField.get(index) + ")";
					} else {
						sql += tempField.get(index) + ",";
					}
				}
				sql += "VALUES (";
				for (int index = 0; index < tempValue.size(); index++) {
					if (index == (tempValue.size() - 1)) {
						sql += getValue(typeMap.get(tempField.get(index)),
								tempValue.get(index)) + ")";
					} else {
						sql += getValue(typeMap.get(tempField.get(index)),
								tempValue.get(index)) + ",";
					}
				}
				statusMap.put("rowNo", rowNo + 1);
				statusMap.put("rowStatus", validateExecute(sql, stmt));
				tempField.clear();
				tempValue.clear();
				sql = "";
				rowNo++;
			}
			xaRes.end(xid, XAResource.TMSUCCESS);
			xaRes.rollback(xid);
		} catch (XAException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			nonXAValidateImportExcel(tempBean);
		} finally {
			dbUtil.closeQuietly(rs);
			dbUtil.closeQuietly(stmt);
			dbUtil.closeQuietly(xaCon);
			dbUtil.closeQuietly(con);
		}
		return tempBean;
	}

	private Xid createXid(int gd, int bd) throws XAException {
		byte[] gid = new byte[1];
		gid[0] = (byte) gd;
		byte[] bid = new byte[1];
		bid[0] = (byte) bd;
		byte[] gtrid = new byte[64];
		byte[] bqual = new byte[64];
		System.arraycopy(gid, 0, gtrid, 0, 1);
		System.arraycopy(bid, 0, bqual, 0, 1);
		Xid xid = new OracleXid(0x1234, gtrid, bqual);
		return xid;
	}

	public ImportExcelBean nonXAValidateImportExcel(ImportExcelBean tempBean) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = em.unwrap(Connection.class);
			con.setAutoCommit(false);
			sql = "select atc.column_name,atc.data_type from all_tab_cols atc where atc.table_name = '"
					+ tempBean.getTABLE_NAME() + "'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			Map<String, String> typeMap = new HashMap<String, String>();
			while (rs.next()) {
				typeMap.put(rs.getString("COLUMN_NAME"),
						rs.getString("DATA_TYPE"));
			}
			List<String> tempField = new ArrayList<String>();
			List<Object> tempValue = new ArrayList<Object>();
			int rowNo = 0;
			Map<String, Object> statusMap;
			sql = "";
			for (Map<String, Object> tempMap : tempBean.getListRowData()) {
				statusMap = tempBean.getListRowStatus().get(rowNo);
				for (Map.Entry<String, Object> temp : tempMap.entrySet()) {
					if (temp.getValue() != null) {
						tempField.add(temp.getKey());
						tempValue.add(temp.getValue());
					}
				}
				sql = "INSERT INTO " + tempBean.getTABLE_NAME() + " (";
				for (int index = 0; index < tempField.size(); index++) {
					if (index == (tempField.size() - 1)) {
						sql += tempField.get(index) + ")";
					} else {
						sql += tempField.get(index) + ",";
					}
				}
				sql += "VALUES (";
				for (int index = 0; index < tempValue.size(); index++) {
					if (index == (tempValue.size() - 1)) {
						sql += getValue(typeMap.get(tempField.get(index)),
								tempValue.get(index)) + ")";
					} else {
						sql += getValue(typeMap.get(tempField.get(index)),
								tempValue.get(index)) + ",";
					}
				}
				statusMap.put("rowNo", rowNo + 1);
				statusMap.put("rowStatus", validateExecute(sql, stmt));
				tempField.clear();
				tempValue.clear();
				sql = "";
				rowNo++;
			}
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeQuietly(rs);
			dbUtil.closeQuietly(stmt);
			dbUtil.closeQuietly(con);
		}
		return tempBean;
	}

	private String validateExecute(String sql, Statement stmt) {
		String status = null;
		try {
			int isSuccess = stmt.executeUpdate(sql);
			if (isSuccess == 1) {
				status = "Success Validate";
			} else {
				status = "Failed Validate : " + isSuccess;
			}
		} catch (SQLException e) {
			status = "Failed Validate : " + e.getMessage();
		}
		return status;
	}

	@Override
	public ImportExcelBean insertImportExcel(ImportExcelBean tempBean) {
		XAConnection xaCon = null;
		Connection con = null;
		XAResource xaRes = null;
		Xid xid = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			xaCon = (XAConnection) em.unwrap(Connection.class);
			con = xaCon.getConnection();
			xaRes = xaCon.getXAResource();
			xid = createXid(1, 1);
			xaRes.start(xid, XAResource.TMNOFLAGS);
			stmt = con.createStatement();
			sql = "select atc.column_name,atc.data_type from all_tab_cols atc where atc.table_name = '"
					+ tempBean.getTABLE_NAME() + "'";
			rs = stmt.executeQuery(sql);
			Map<String, String> typeMap = new HashMap<String, String>();
			while (rs.next()) {
				typeMap.put(rs.getString("COLUMN_NAME"),
						rs.getString("DATA_TYPE"));
			}
			List<String> tempField = new ArrayList<String>();
			List<Object> tempValue = new ArrayList<Object>();
			int rowNo = 0;
			Map<String, Object> statusMap;
			sql = "";
			for (Map<String, Object> tempMap : tempBean.getListRowData()) {
				statusMap = tempBean.getListRowStatus().get(rowNo);
				if ("1".equals(statusMap.get("ActiveStatus"))) {
					for (Map.Entry<String, Object> temp : tempMap.entrySet()) {
						if (temp.getValue() != null) {
							tempField.add(temp.getKey());
							tempValue.add(temp.getValue());
						}
					}
					sql = "INSERT INTO " + tempBean.getTABLE_NAME() + " (";
					for (int index = 0; index < tempField.size(); index++) {
						if (index == (tempField.size() - 1)) {
							sql += tempField.get(index) + ")";
						} else {
							sql += tempField.get(index) + ",";
						}
					}
					sql += "VALUES (";
					for (int index = 0; index < tempValue.size(); index++) {
						if (index == (tempValue.size() - 1)) {
							sql += getValue(typeMap.get(tempField.get(index)),
									tempValue.get(index)) + ")";
						} else {
							sql += getValue(typeMap.get(tempField.get(index)),
									tempValue.get(index)) + ",";
						}
					}
					stmt.executeUpdate(sql);
					tempField.clear();
					tempValue.clear();
					sql = "";
				}
				rowNo++;
			}
			xaRes.end(xid, XAResource.TMSUCCESS);
			xaRes.commit(xid, true);
			tempBean.setSuccess(true);
		} catch (SQLException e) {
			try {
				xaRes.end(xid, XAResource.TMSUCCESS);
				xaRes.rollback(xid);
			} catch (XAException e1) {
				e1.printStackTrace();
			}
			tempBean.setSuccess(false);
		} catch (XAException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			nonXAInsertImportExcel(tempBean);
		} finally {
			dbUtil.closeQuietly(rs);
			dbUtil.closeQuietly(stmt);
			dbUtil.closeQuietly(xaCon);
			dbUtil.closeQuietly(con);
		}
		return tempBean;
	}

	public ImportExcelBean nonXAInsertImportExcel(ImportExcelBean tempBean) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = em.unwrap(Connection.class);
			con.setAutoCommit(false);
			stmt = con.createStatement();
			sql = "select atc.column_name,atc.data_type from all_tab_cols atc where atc.table_name = '"
					+ tempBean.getTABLE_NAME() + "'";
			rs = stmt.executeQuery(sql);
			Map<String, String> typeMap = new HashMap<String, String>();
			while (rs.next()) {
				typeMap.put(rs.getString("COLUMN_NAME"),
						rs.getString("DATA_TYPE"));
			}
			sql = "";
			List<String> tempField = new ArrayList<String>();
			List<Object> tempValue = new ArrayList<Object>();
			int rowNo = 0;
			Map<String, Object> statusMap;
			for (Map<String, Object> tempMap : tempBean.getListRowData()) {
				statusMap = tempBean.getListRowStatus().get(rowNo);
				if ("1".equals(statusMap.get("ActiveStatus"))) {
					for (Map.Entry<String, Object> temp : tempMap.entrySet()) {
						if (temp.getValue() != null) {
							tempField.add(temp.getKey());
							tempValue.add(temp.getValue());
						}
					}
					sql = "INSERT INTO " + tempBean.getTABLE_NAME() + " (";
					for (int index = 0; index < tempField.size(); index++) {
						if (index == (tempField.size() - 1)) {
							sql += tempField.get(index) + ")";
						} else {
							sql += tempField.get(index) + ",";
						}
					}
					sql += "VALUES (";
					for (int index = 0; index < tempValue.size(); index++) {
						if (index == (tempValue.size() - 1)) {
							sql += getValue(typeMap.get(tempField.get(index)),
									tempValue.get(index)) + ")";
						} else {
							sql += getValue(typeMap.get(tempField.get(index)),
									tempValue.get(index)) + ",";
						}
					}
					stmt.executeUpdate(sql);
					tempField.clear();
					tempValue.clear();
					sql = "";
				}
				rowNo++;
			}
			con.commit();
			tempBean.setSuccess(true);
		} catch (SQLException e) {
			log.error("|" + page + "|insertImportTable|Exception:"
					+ e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			tempBean.setSuccess(false);
		} finally {
			dbUtil.closeQuietly(rs);
			dbUtil.closeQuietly(stmt);
			dbUtil.closeQuietly(con);
		}
		return tempBean;
	}

	@SuppressWarnings("unchecked")
	private <T> T getValue(String type, Object obj) {
		// type :: DATE
		// type :: NUMBER
		// type :: VARCHAR2
		// type :: CHAR
		if ("DATE".equals(type)) {
			return (T) new String("TO_DATE(\'" + obj + "\', \'dd-Mon-yy\')");
		} else if ("NUMBER".equals(type)) {
			if (null != obj) {
				return (T) obj;
			} else {
				return null;
			}
		} else if ("VARCHAR2".equals(type)) {
			return (T) new String("\'" + obj + "\'");
		} else if ("CHAR".equals(type)) {
			return (T) new String("\'" + obj + "\'");
		} else {
			return null;
		}
	}
}
