package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@IdClass(value = BatchMastFilePK.class)
@Table(name = "BATCH_MAST_FILE")
@Cache(type = CacheType.NONE)
public class BatchMastFile implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer  BTCH_MAST_FILE_ID;
	
	@Column(name = "BTCH_DEST_CODE", nullable = false)
	private String BTCH_DEST_CODE;

	@Column(name = "BTCH_SEND_FILE_DTTM", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date BTCH_SEND_FILE_DTTM;
	
	@Column(name = "BTCH_MAST_FILE_NAME", nullable = false)
	private String BTCH_MAST_FILE_NAME; 
	
	@Column(name = "BTCH_DETL_TRNS_TOTL", nullable = false)
	private Integer BTCH_DETL_TRNS_TOTL;

	@Column(name = "BTCH_MAST_FILE_STTS", nullable = false)
	private String BTCH_MAST_FILE_STTS; 
	
//	@Column(name = "BTCH_MAST_FILE_DATA", nullable = false)
//	private File  BTCH_MAST_FILE_DATA; 

	public Integer  getBTCH_MAST_FILE_ID() {
		return this.BTCH_MAST_FILE_ID;
	}

	public void setBTCH_MAST_FILE_ID(Integer BTCH_MAST_FILE_ID) {
		this.BTCH_MAST_FILE_ID = BTCH_MAST_FILE_ID;
	}

	public String getBTCH_DEST_CODE() {
		return this.BTCH_DEST_CODE;
	}

	public void setBTCH_DEST_CODE(String BTCH_DEST_CODE) {
		this.BTCH_DEST_CODE = BTCH_DEST_CODE;
	}
	
	public Date getBTCH_SEND_FILE_DTTM() {
		return BTCH_SEND_FILE_DTTM;
	}

	public void setBTCH_SEND_FILE_DTTM(Date bTCH_SEND_FILE_DTTM) {
		BTCH_SEND_FILE_DTTM = bTCH_SEND_FILE_DTTM;
	}

	public String getBTCH_MAST_FILE_NAME() {
		return BTCH_MAST_FILE_NAME;
	}

	public void setBTCH_MAST_FILE_NAME(String bTCH_MAST_FILE_NAME) {
		BTCH_MAST_FILE_NAME = bTCH_MAST_FILE_NAME;
	}

	public Integer getBTCH_DETL_TRNS_TOTL() {
		return BTCH_DETL_TRNS_TOTL;
	}

	public void setBTCH_DETL_TRNS_TOTL(Integer bTCH_DETL_TRNS_TOTL) {
		BTCH_DETL_TRNS_TOTL = bTCH_DETL_TRNS_TOTL;
	}

	public String getBTCH_MAST_FILE_STTS() {
		return BTCH_MAST_FILE_STTS;
	}

	public void setBTCH_MAST_FILE_STTS(String bTCH_MAST_FILE_STTS) {
		BTCH_MAST_FILE_STTS = bTCH_MAST_FILE_STTS;
	}

//	public String getBTCH_MAST_FILE_DATA() {
//		return BTCH_MAST_FILE_DATA;
//	}
//
//	public void setBTCH_MAST_FILE_DATA(String bTCH_MAST_FILE_DATA) {
//		BTCH_MAST_FILE_DATA = bTCH_MAST_FILE_DATA;
//	}
}
