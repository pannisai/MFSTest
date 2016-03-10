package mfs.config.xml.element;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "enumerates")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlEnumerates implements java.io.Serializable{
	
	@XmlElement(required = false)
	private  List<XmlEnumerated>  enumerated ;

	public List<XmlEnumerated> getEnumerated() {
		return enumerated;
	}

	public void setEnumerated(List<XmlEnumerated> enumerated) {
		this.enumerated = enumerated;
	}
	
	
}
