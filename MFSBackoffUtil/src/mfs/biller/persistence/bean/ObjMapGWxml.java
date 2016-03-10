package mfs.biller.persistence.bean;

import java.io.Serializable;

public class ObjMapGWxml implements Serializable{

	private static final long serialVersionUID = 1370037236088807312L;
	private Integer  DATA_MAP_ID;
	private String DATA_MAP_NAME;
	private Integer DATA_MAP_XML_SRC_ID;
	private Integer XML_DATA_ID;
	private String XML_DATA_TYPE;
	private String XML_DATA_SRC;
	private Integer XML_DATA_VRSN;
	private String XML_DATA_DESC;
	private String XML_DATA_STTS;
	
	public  String toString(){
		return "DATA_MAP_ID:" + DATA_MAP_ID 
				+ "|DATA_MAP_NAME:" + DATA_MAP_NAME 
				+ "|DATA_MAP_XML_SRC_ID:" + DATA_MAP_XML_SRC_ID 
				+ "|XML_DATA_ID:" + XML_DATA_ID 
				+ "|XML_DATA_TYPE:" + XML_DATA_TYPE 
				+ "|XML_DATA_SRC:" + XML_DATA_SRC 
				+ "|XML_DATA_VRSN:" + XML_DATA_VRSN 
				+ "|XML_DATA_DESC:" + XML_DATA_DESC 
				+ "|XML_DATA_STTS:" + XML_DATA_STTS;
	}

	public Integer getDATA_MAP_ID() {
		return DATA_MAP_ID;
	}

	public void setDATA_MAP_ID(Integer dATA_MAP_ID) {
		DATA_MAP_ID = dATA_MAP_ID;
	}

	public String getDATA_MAP_NAME() {
		return DATA_MAP_NAME;
	}

	public void setDATA_MAP_NAME(String dATA_MAP_NAME) {
		DATA_MAP_NAME = dATA_MAP_NAME;
	}

	public Integer getDATA_MAP_XML_SRC_ID() {
		return DATA_MAP_XML_SRC_ID;
	}

	public void setDATA_MAP_XML_SRC_ID(Integer dATA_MAP_XML_SRC_ID) {
		DATA_MAP_XML_SRC_ID = dATA_MAP_XML_SRC_ID;
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
