package mfs.biller.persistence.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Lob;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Table(name="GW_XML_DATA_SRC")
@Cache(type=CacheType.NONE)
public class GWXmlDataSrcBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="XML_DATA_ID", nullable=false)
	private Integer XML_DATA_ID;
	
	@Column(name="XML_DATA_TYPE", nullable=false)
	private String XML_DATA_TYPE;
	
	@Lob
	@Column(name="XML_DATA_SRC", nullable=true)
    private String XML_DATA_SRC;
	
	@Column(name="XML_DATA_VRSN", nullable=true)
    private Integer XML_DATA_VRSN;
	
	@Column(name="XML_DATA_DESC", nullable=true)
    private String XML_DATA_DESC;
	
	@Column(name="XML_DATA_STTS", nullable=true)
    private String XML_DATA_STTS;
	
	
	public String toString() {
		return "XML_DATA_ID:" + XML_DATA_ID 
				+ "|XML_DATA_TYPE:" + XML_DATA_TYPE 
				+ "|XML_DATA_SRC:" + XML_DATA_SRC 
				+ "|XML_DATA_VRSN:" + XML_DATA_VRSN 
				+ "|XML_DATA_DESC:" + XML_DATA_DESC 
				+ "|XML_DATA_STTS:" + XML_DATA_STTS ;
				}
	

	public Integer getXML_DATA_ID() {
		return XML_DATA_ID;
	}

	public void setXML_DATA_ID(Integer xML_DATA_ID) {
		XML_DATA_ID = xML_DATA_ID;
	}

	public String getXML_DATA_TYPE() {
		return XML_DATA_TYPE;
	}

	public void setXML_DATA_TYPE(String xML_DATA_TYPE) {
		XML_DATA_TYPE = xML_DATA_TYPE;
	}

	public String getXML_DATA_SRC() {
		return XML_DATA_SRC;
	}

	public void setXML_DATA_SRC(String xML_DATA_SRC) {
		XML_DATA_SRC = xML_DATA_SRC;
	}

	public Integer getXML_DATA_VRSN() {
		return XML_DATA_VRSN;
	}

	public void setXML_DATA_VRSN(Integer xML_DATA_VRSN) {
		XML_DATA_VRSN = xML_DATA_VRSN;
	}

	public String getXML_DATA_DESC() {
		return XML_DATA_DESC;
	}

	public void setXML_DATA_DESC(String xML_DATA_DESC) {
		XML_DATA_DESC = xML_DATA_DESC;
	}

	public String getXML_DATA_STTS() {
		return XML_DATA_STTS;
	}

	public void setXML_DATA_STTS(String xML_DATA_STTS) {
		XML_DATA_STTS = xML_DATA_STTS;
	}
	
	
}
