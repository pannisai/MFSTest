package mfs.config.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement(name = "user")

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlHistoryUser implements java.io.Serializable{

	
	@XmlAttribute(required = true)
	private String name;
	
	@XmlAttribute(required = true)
	private String date;
	
	@XmlAttribute(required = true)
	private String version;
	
	@XmlAttribute(required = true)
	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
