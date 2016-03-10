package mfs.biller.persistence.bean;

import java.io.Serializable;

public class UserInfoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String ipAddress;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
