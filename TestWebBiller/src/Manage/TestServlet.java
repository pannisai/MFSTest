package Manage;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mfs.biller.ejb.interfaces.BankChannelRemote;
import mfs.biller.persistence.bean.BankChannelBean;
import mfs.biller.persistence.bean.UserInfoBean;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import common.EJBInitialContext;


/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(TestServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hoho");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String log4jConfPath = "D:/DTAC/Workspace/Biller/TestWebBiller/WebContent/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		UserInfoBean mook = new UserInfoBean();
		mook.setName("mook");
		mook.setIpAddress("ip najazzz");
		
		BankChannelRemote bankRmt;
		try {
			bankRmt = (BankChannelRemote) EJBInitialContext.lookup("java:global/MFSWebBillerEAR/MFSWebBillerEJB/BankChannel!mfs.biller.ejb.interfaces.BankChannelRemote");
			BankChannelBean x = bankRmt.findBankChannel("0099", mook);
			logger.debug(x.getBANK_CHNL_FULL_NAME_EN());
		} catch (NamingException e2) {
			e2.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("TEST LOG");
		logger.debug("Dok tongggg");
		response.sendRedirect("./index.jsp");
		
	}

}
