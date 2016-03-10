package mfs.config.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import mfs.config.xml.element.XmlEnumerates;
import mfs.config.xml.element.XmlHistory;
import mfs.config.xml.element.XmlProperties;
import mfs.config.xml.element.XmlResultCode;
import mfs.config.xml.element.XmlValidate;

@XmlRootElement(name = "mfs")
@XmlType(name = "", propOrder = { "name", "history", "debug_mode",
		"properties", "enumerates", "validate", "resp_code" })
@XmlAccessorType(XmlAccessType.FIELD)
public class ReadXmlConfig implements java.io.Serializable {

	@XmlAttribute(required = true)
	private String name;

	@XmlElement(required = false)
	private XmlHistory history;

	@XmlElement(required = false)
	private boolean debug_mode = false;

	@XmlElement(required = false)
	private XmlProperties properties;

	@XmlElement(required = false)
	private XmlEnumerates enumerates;

	@XmlElement(required = false)
	private XmlValidate validate;

	@XmlElement(required = false)
	private XmlResultCode resp_code;

	public boolean isDebug_mode() {
		return debug_mode;
	}

	public void setDebug_mode(boolean debug_mode) {
		this.debug_mode = debug_mode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public XmlHistory getHistory() {
		return history;
	}

	public void setHistory(XmlHistory history) {
		this.history = history;
	}

	public XmlProperties getProperties() {
		return properties;
	}

	public void setProperties(XmlProperties properties) {
		this.properties = properties;
	}

	public XmlEnumerates getEnumerates() {
		return enumerates;
	}

	public void setEnumerates(XmlEnumerates enumerates) {
		this.enumerates = enumerates;
	}

	public XmlValidate getValidate() {
		return validate;
	}

	public void setValidate(XmlValidate validate) {
		this.validate = validate;
	}

	public XmlResultCode getResp_code() {
		return resp_code;
	}

	public void setResp_code(XmlResultCode resp_code) {
		this.resp_code = resp_code;
	}

}
