package mfs.config.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "restriction")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRestriction implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@XmlAttribute(required = true)
	private String objName;

	@XmlAttribute(required = true)
	private String fieldName;

	@XmlAttribute(required = true)
	private boolean required;

	@XmlElement(required = false)
	private String type;

	@XmlElement(required = false)
	private String pattern;

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getName() {
		return objName + "." + fieldName;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
