package mfs.config.xml.util;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class XmlUtil {
	
	public static Object readXmltoObj(FileReader filereader,Class<?> classname) throws JAXBException{
		// create JAXB context and instantiate Unmarshaller
		JAXBContext jc = JAXBContext.newInstance(classname);

		Unmarshaller um = jc.createUnmarshaller();
		
		return um.unmarshal(filereader);
		
	}
	
	
	public static Object readXmltoObj(String filename ,Class<?> classname,boolean absolutepath) throws JAXBException, FileNotFoundException{
		// create JAXB context and instantiate Unmarshaller
		JAXBContext jc = JAXBContext.newInstance(classname);
		Unmarshaller um = jc.createUnmarshaller();
		
		if(absolutepath)
			return um.unmarshal(new FileReader(filename));
		else
			return um.unmarshal(new FileReader(
				System.getProperty("user.home")+"/"+filename));
		
	}
	
	public static Object readXmlAsStreamtoObj(InputStreamReader fileinputStream ,Class<?> classname,boolean absolutepath) throws JAXBException, FileNotFoundException{
		// create JAXB context and instantiate Unmarshaller
		JAXBContext jc = JAXBContext.newInstance(classname);
		Unmarshaller um = jc.createUnmarshaller();
	
		return um.unmarshal(fileinputStream);
		
	}
	

	
	public static Object readXmltoObj(String filename ,Class<?> classname) throws JAXBException, FileNotFoundException{
		return readXmltoObj(filename,classname,false);	
	}
	

	
	
	public static void printObjToXml(Object obj,Class<?> classname) throws JAXBException{
		// create JAXB context and instantiate marshaller
		JAXBContext jc = JAXBContext.newInstance(classname);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(obj, System.out);

	}
	
	public static Object convertXmlToObj(String xmls,Class<?> classname) throws JAXBException{
		JAXBContext jc = JAXBContext.newInstance(classname);
		Unmarshaller um = jc.createUnmarshaller();
		//convert String into InputStream
    	InputStream is =null;
    	try{
    		is = new ByteArrayInputStream(xmls.getBytes());
    		return um.unmarshal(is);
    	}finally{
    		Close(is);
    	}
	}
	

	
	public static String convertObjToXml(Object obj,Class<?> classname) throws JAXBException{
		 OutputStream out=null;
		 try{
			// create JAXB context and instantiate marshaller
			JAXBContext jc = JAXBContext.newInstance(classname);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			out  = new OutputStreamToString();
			m.marshal(obj, out);
			return out.toString();
		}finally{
			Close(out);
		}
	}
	
	public static void writeObjToXml(String file,Object obj,Class<?> classname) throws JAXBException, IOException{
		// create JAXB context and instantiate marshaller
		JAXBContext jc = JAXBContext.newInstance(classname);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//		m.marshal(obj, System.out);
		
		Writer w = null;
		try {
			w = new FileWriter(System.getProperty("user.home")+"/"+file);
			m.marshal(obj, w);
		} finally {
			Close(w);
		}
		
	}
	
	public static void Close(Closeable io) {
		try {
			if(io !=null)
				io.close();
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
}
