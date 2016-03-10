package mfs.biller.util;

import java.io.FileInputStream;
import java.util.Properties;






public class ConfigLoader {
//	private PropertyFileConfig conf = null;
	Properties conf = new Properties();	
	
    public ConfigLoader() {
		String pathconfig = System.getProperty("user.home")+ "/conf/ejb/backofficewebConfig.properties";
		//String pathconfig = "C:/config/backofficewebConfig.properties";


		try{
//			conf = new PropertyFileConfig(System.getProperty("user.home") + pathconfig );
			conf.load(new FileInputStream(pathconfig));
    		
		}catch(Exception e){
			System.err.println("|conn't load config file :" +pathconfig+"|"+e);
		}
    	
    }

//	static{
//		String pathconfig = "/conf/biller/backofficewebConfig.properties";
//
//		try{
//			conf = new PropertyFileConfig(System.getProperty("user.home") + pathconfig );
//			
//		}catch(Exception e){
//			System.err.println("|conn't load config file :" +pathconfig+"|"+e);
//		}
//	}
	
	public String getConfig(String config_name){
//		return conf.getConfig(config_name);
		return conf.getProperty(config_name);
		
	}
}
