package mfs.config.xml.element;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "resp_code")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlResultCode implements java.io.Serializable{
	
	@XmlElement(required = false)
	private List<XmlCode>  code;

	public List<XmlCode> getCode() {
		return code;
	}

	public void setCode(List<XmlCode> code) {
		this.code = code;
	}
	
	
}
