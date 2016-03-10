package common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class InitialListener implements ServletContextListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {

		try{
//			PropertyConfigurator.configure("D:/Dtaclog/billerInqWebLog4j.properties");	
		
			PropertyConfigurator.configureAndWatch(AppConstant.USER_HOME+org.zkoss.lang.Library.getProperty("app.log.location"),10000);	
			Labels.register(new AppMessageLocator(AppConstant.USER_HOME + org.zkoss.lang.Library.getProperty("app.message.location")));

		}catch(Exception ex){
		
		}
			
	}

}
