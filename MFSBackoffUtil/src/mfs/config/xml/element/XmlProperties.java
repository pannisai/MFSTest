package mfs.config.xml.element;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "properties")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlProperties implements java.io.Serializable{
	
	@XmlElement(required = false)
	private  List<XmlProperty>  property ;

	public List<XmlProperty> getProperty() {
		return property;
	}

	public void setProperty(List<XmlProperty> property) {
		this.property = property;
	}
	
	
}
