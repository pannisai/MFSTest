package mfs.config.xml.element;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "enumerated")
@XmlAccessorType(XmlAccessType.FIELD)
		
public class XmlEnumerated implements java.io.Serializable{

	
	@XmlAttribute(required = true)
	private String name;
	
	@XmlElement(required = false)
	private  List<XmlEnum> enumt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<XmlEnum> getEnumt() {
		return enumt;
	}

	public void setEnumt(List<XmlEnum> enumt) {
		this.enumt = enumt;
	}
	
	
}
