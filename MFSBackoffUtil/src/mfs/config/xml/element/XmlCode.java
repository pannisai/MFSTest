package mfs.config.xml.element;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "code")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlCode implements java.io.Serializable{

	
	@XmlElement(required = false)
	private  String src_code;
	
	@XmlElement(required = false)
	private  String dest_code;
	
	
	@XmlElement(required = false)
	private  String dest_message;
	
	
	@XmlElement(required = false)
	private  String dest_status;


	public String getSrc_code() {
		return src_code;
	}


	public void setSrc_code(String src_code) {
		this.src_code = src_code;
	}


	public String getDest_code() {
		return dest_code;
	}


	public void setDest_code(String dest_code) {
		this.dest_code = dest_code;
	}


	public String getDest_message() {
		return dest_message;
	}


	public void setDest_message(String dest_message) {
		this.dest_message = dest_message;
	}


	public String getDest_status() {
		return dest_status;
	}


	public void setDest_status(String dest_status) {
		this.dest_status = dest_status;
	}
	
	
}
