package mfs.config.xml.element;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "validate")

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlValidate implements java.io.Serializable{

	
	@XmlElement(required = false)
	private List<XmlRestriction> restriction ;

	public List<XmlRestriction> getRestriction() {
		return restriction;
	}

	public void setRestriction(List<XmlRestriction> restriction) {
		this.restriction = restriction;
	}
	
	
}
