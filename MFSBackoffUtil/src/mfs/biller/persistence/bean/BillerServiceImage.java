package mfs.biller.persistence.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Table(name = "BILLER_SERVICE_IMAGE")
@Cache(type = CacheType.NONE)
public class BillerServiceImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BLLR_SRVC_ID", nullable = false)
	private Integer bllrSrvcId;

	@Column(name = "BLLR_SRVC_CODE", nullable = true)
	private String bllrSrvcCode;

	@Column(name = "BLLR_SRVC_WEB_ICON", nullable = true)
	@Lob
	private byte[] bllrSrvcWebIcon;

	@Column(name = "BLLR_SRVC_WEB_INST", nullable = true)
	@Lob
	private byte[] bllrSrvcWebInst;

	@Column(name = "BLLR_SRVC_MOB_ICON", nullable = true)
	@Lob
	private byte[] bllrSrvcMobIcon;

	@Column(name = "CRTD_BY", nullable = true)
	private String createBy;

	@Column(name = "CRTD_DTTM", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "LAST_CHNG_BY", nullable = true)
	private String lastChangeBy;

	@Column(name = "LAST_CHNG_DTTM", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastChangeDate;

	public String toString() {
		return "bllrSrvcId:" + bllrSrvcId + "|bllrSrvcCode:" + bllrSrvcCode + 
				 "|CRTD_BY:" + createBy + "|CRTD_DTTM:" + createDate + 
				 "|LAST_CHNG_BY:" + lastChangeBy + "|LAST_CHNG_DTTM:" + lastChangeDate;
	}

	public Integer getBllrSrvcId() {
		return bllrSrvcId;
	}

	public void setBllrSrvcId(Integer bllrSrvcId) {
		this.bllrSrvcId = bllrSrvcId;
	}

	public String getBllrSrvcCode() {
		return bllrSrvcCode;
	}

	public void setBllrSrvcCode(String bllrSrvcCode) {
		this.bllrSrvcCode = bllrSrvcCode;
	}

	public byte[] getBllrSrvcWebIcon() {
		return bllrSrvcWebIcon;
	}

	public void setBllrSrvcWebIcon(byte[] bllrSrvcWebIcon) {
		this.bllrSrvcWebIcon = bllrSrvcWebIcon;
	}

	public byte[] getBllrSrvcWebInst() {
		return bllrSrvcWebInst;
	}

	public void setBllrSrvcWebInst(byte[] bllrSrvcWebInst) {
		this.bllrSrvcWebInst = bllrSrvcWebInst;
	}

	public byte[] getBllrSrvcMobIcon() {
		return bllrSrvcMobIcon;
	}

	public void setBllrSrvcMobIcon(byte[] bllrSrvcMobIcon) {
		this.bllrSrvcMobIcon = bllrSrvcMobIcon;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastChangeBy() {
		return lastChangeBy;
	}

	public void setLastChangeBy(String lastChangeBy) {
		this.lastChangeBy = lastChangeBy;
	}

	public Date getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}
}
