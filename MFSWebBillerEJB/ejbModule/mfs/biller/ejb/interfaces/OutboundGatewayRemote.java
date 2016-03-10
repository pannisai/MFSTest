package mfs.biller.ejb.interfaces;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import mfs.biller.persistence.bean.GWOutbound;
import mfs.biller.persistence.bean.GWOutboundDtail;
import mfs.biller.persistence.bean.OutboundGatewayParam;
import mfs.biller.persistence.bean.OutboundId;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Remote
public interface OutboundGatewayRemote {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.OutboundGateway#mfs.biller.ejb.interfaces.OutboundGatewayRemote";
	public List<GWOutboundDtail> getOutboundGateway(OutboundGatewayParam OutboundGatewayParam , UserInfoBean user) throws Exception ;
	public BigDecimal countRowAll(OutboundGatewayParam Param , UserInfoBean user) throws Exception ;
	public int insertOutboundGateway(GWOutbound bean, UserInfoBean user)throws IsExistException, Exception;
	public GWOutbound searchOutboundGateway(int PARAM, UserInfoBean user)throws Exception;
	public void updateOutboundGateway(GWOutbound bean, UserInfoBean user)throws IsExistException, Exception;
}
