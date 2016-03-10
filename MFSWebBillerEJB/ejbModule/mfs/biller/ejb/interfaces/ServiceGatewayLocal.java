package mfs.biller.ejb.interfaces;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import mfs.biller.persistence.bean.GWService;
import mfs.biller.persistence.bean.ServiceGatewayResult;
import mfs.biller.persistence.bean.ServiceGatewayResultParam;
import mfs.biller.persistence.bean.UserInfoBean;
import mfs.exception.IsExistException;

@Local
public interface ServiceGatewayLocal {
	public static final String JNDI_WEBLOGIC = "mfs.biller.ejb.ServiceGateway#mfs.biller.ejb.interfaces.ServiceGatewayRemote";
	public List<ServiceGatewayResult> getServiceGatewayResult(ServiceGatewayResultParam Param , UserInfoBean user) throws Exception;
	public BigDecimal countRowAll(ServiceGatewayResultParam Param , UserInfoBean user)
			throws Exception;
	public int insertServiceGateway(GWService bean, UserInfoBean user)throws IsExistException, Exception;
	public GWService searchServiceGateway(int PARAM, UserInfoBean user)throws Exception;
	public void updateServiceGateway(GWService bean, UserInfoBean user)throws IsExistException, Exception;


}
