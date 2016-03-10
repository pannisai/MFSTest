package mfs.config.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "property")

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlProperty implements java.io.Serializable{

	
	@XmlAttribute(required = true)
	private String name;
	
	
	@XmlAttribute(required = true)
	private String value;
	
	
	@XmlElement(required = false)
	private String type;
	
	@XmlElement(required = false)
	private String format;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	
}
