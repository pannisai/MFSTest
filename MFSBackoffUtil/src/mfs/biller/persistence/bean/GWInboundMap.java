package mfs.biller.persistence.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@IdClass(value = GWInboundMapPK.class)
@Table(name = "GW_INBOUND_MAP")
@Cache(type = CacheType.NONE)
public class GWInboundMap  implements Serializable {
    
	private static final long serialVersionUID = 1L;
	@Id
	private Integer  DATA_MAP_ID;
	
	@Column(name = "DATA_MAP_NAME", nullable = false)
	private String DATA_MAP_NAME;
	
	@Column(name = "DATA_MAP_XML_SRC_ID", nullable = false)
	private Integer DATA_MAP_XML_SRC_ID;

	public Integer getDATA_MAP_ID() {
		return DATA_MAP_ID;
	}

	public void setDATA_MAP_ID(Integer dATA_MAP_ID) {
		DATA_MAP_ID = dATA_MAP_ID;
	}

	public String getDATA_MAP_NAME() {
		return DATA_MAP_NAME;
	}

	public void setDATA_MAP_NAME(String dATA_MAP_NAME) {
		DATA_MAP_NAME = dATA_MAP_NAME;
	}

	public Integer getDATA_MAP_XML_SRC_ID() {
		return DATA_MAP_XML_SRC_ID;
	}

	public void setDATA_MAP_XML_SRC_ID(Integer dATA_MAP_XML_SRC_ID) {
		DATA_MAP_XML_SRC_ID = dATA_MAP_XML_SRC_ID;
	}

	public String toString() {
		return DATA_MAP_ID+"|"+DATA_MAP_NAME+"|"+DATA_MAP_XML_SRC_ID;
	}

}
