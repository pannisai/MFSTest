package mfs.biller.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.Query;
import javax.sql.XAConnection;

import org.apache.log4j.Logger;

public class DBUtil {
	private Logger log = Logger.getLogger(DBUtil.class);

	public void setParams(Query query, List<Object> params) {
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i+1, params.get(i));
		}
	}
	
	public void closeQuietly(ResultSet resultSet) {
	    if (resultSet != null) {
	    	try {
				resultSet.close();
			} catch (SQLException e) {
			}
	    }
	}
	
	public void closeQuietly(Statement stmt) {
	    if (stmt != null) {
	    	try {
	    		stmt.close();
			} catch (SQLException e) {
			}
	    }
	}
	
	public void closeQuietly(Connection con) {
	    if (con != null) {
	    	try {
	    		con.close();
			} catch (SQLException e) {
			}
	    }
	}
	
	public void closeQuietly(XAConnection con) {
	    if (con != null) {
	    	try {
	    		con.close();
			} catch (SQLException e) {
			}
	    }
	}
}
