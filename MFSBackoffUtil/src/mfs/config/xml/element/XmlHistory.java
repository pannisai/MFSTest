package mfs.config.xml.element;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement(name = "history")

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlHistory implements java.io.Serializable{
	
	@XmlElement(required = false)
	private List<XmlHistoryUser> user ;

	public List<XmlHistoryUser> getUser() {
		return user;
	}

	public void setUser(List<XmlHistoryUser> user) {
		this.user = user;
	}


	

	
}
