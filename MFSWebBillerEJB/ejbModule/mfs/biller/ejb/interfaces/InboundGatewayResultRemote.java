package mfs.biller.ejb.interfaces;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.GWInboundPK;
import mfs.biller.persistence.bean.OBJGW_INBOUND;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.biller.persistence.bean.getInboundGatewayResult;
import mfs.biller.persistence.bean.getInboundGatewayResultParam;
import mfs.exception.IsExistException;

@Remote

public interface InboundGatewayResultRemote {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.InboundGatewayResult#mfs.biller.ejb.interfaces.InboundGatewayResultRemote";
	public ArrayList<getInboundGatewayResult> getInboundGatewayResult(getInboundGatewayResultParam Param , UserInfoBean user) throws Exception;
	public BigDecimal countRowAll(getInboundGatewayResultParam Param , UserInfoBean user) throws Exception ;
	public OBJGW_INBOUND getInboundGatewayResultED(getInboundGatewayResultParam Param , UserInfoBean user) throws Exception;
	public GWInboundPK insertGW_INBOUND(OBJGW_INBOUND bean, UserInfoBean user)throws IsExistException, Exception;
	public void updateGW_INBOUND(OBJGW_INBOUND bean, UserInfoBean user)throws IsExistException, Exception;

}


