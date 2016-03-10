package mfs.config.xml;

import java.util.HashMap;

import mfs.config.xml.element.XmlCode;
import mfs.config.xml.element.XmlEnum;
import mfs.config.xml.element.XmlEnumerated;
import mfs.config.xml.element.XmlHistoryUser;
import mfs.config.xml.element.XmlProperty;
import mfs.config.xml.element.XmlRestriction;
import mfs.config.xml.util.XmlUtil;

public class XMLConfigManagement {

	private HashMap<String, XmlProperty> propertiesMap;

	private HashMap<String, HashMap> enumeratesMap;

	private HashMap<String, XmlRestriction> validateMap;

	private HashMap<String, XmlCode> respCodeMap;

	private boolean bPrintXml;

	public XMLConfigManagement(String xmlString, boolean bPrintXml) throws Exception {
		this.bPrintXml = bPrintXml;
		LoadConfigManagement(xmlString);
	}

	public XMLConfigManagement(String xmlString) throws Exception {
		this.bPrintXml = false;
		LoadConfigManagement(xmlString);
	}

	public void LoadConfigManagement(String xmlString) throws Exception {
		try {

			ReadXmlConfig mfs = (ReadXmlConfig) XmlUtil.convertXmlToObj(xmlString, ReadXmlConfig.class);

			if (bPrintXml)
				System.out.println("---------------History-------------------------------");

			for (XmlHistoryUser user : mfs.getHistory().getUser()) {
				if (bPrintXml)
					System.out.println("name=" + user.getName() + ":version=" + user.getVersion() + ":desc=" + user.getDesc());
			}

			if (bPrintXml)
				System.out.println("---------------Properties-------------------------------");
			for (XmlProperty prop : mfs.getProperties().getProperty()) {
				if (bPrintXml) {
					System.out.println("name=" + prop.getName() + ":value=" + prop.getValue());
					System.out.println("type=" + prop.getType());
					System.out.println("format=" + prop.getFormat());
				}
				if (null == propertiesMap)
					propertiesMap = new HashMap<String, XmlProperty>();

				this.propertiesMap.put(prop.getName(), prop);
			}

			if (bPrintXml)
				System.out.println("---------------enumerates-------------------------------");

			for (XmlEnumerated enumt : mfs.getEnumerates().getEnumerated()) {
				if (bPrintXml)
					System.out.println("name=" + enumt.getName());
				HashMap<String, String> enummap = new HashMap<String, String>();

				for (XmlEnum enumsub : enumt.getEnumt()) {
					if (bPrintXml)
						System.out.println("type=" + enumsub.getKey() + ":" + "format=" + enumsub.getValue());

					enummap.put(enumsub.getKey(), enumsub.getValue());
				}

				if (null == enumeratesMap)
					enumeratesMap = new HashMap<String, HashMap>();

				this.enumeratesMap.put(enumt.getName(), enummap);
			}

			if (bPrintXml)
				System.out.println("---------------validate-------------------------------");
			for (XmlRestriction rest : mfs.getValidate().getRestriction()) {
				if (bPrintXml) {
					System.out.println("name=" + rest.getName() + ":" + "required=" + rest.isRequired());
					System.out.println("type=" + rest.getType());
					System.out.println("pattern=" + rest.getPattern());
				}

				if (null == validateMap)
					validateMap = new HashMap<String, XmlRestriction>();

				this.validateMap.put(rest.getName(), rest);
			}

			if (bPrintXml)
				System.out.println("---------------Resp Code-------------------------------");
			for (XmlCode code : mfs.getResp_code().getCode()) {
				if (bPrintXml) {
					System.out.println("src_code=" + code.getSrc_code() + ":" + "dest_code=" + code.getDest_code());
					System.out.println("dest_msg=" + code.getDest_message());
					System.out.println("dest_status=" + code.getDest_status());
				}

				if (null == respCodeMap)
					respCodeMap = new HashMap<String, XmlCode>();

				this.respCodeMap.put(code.getSrc_code(), code);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public HashMap<String, XmlProperty> getPropertiesMap() {
		return propertiesMap;
	}

	public void setPropertiesMap(HashMap<String, XmlProperty> propertiesMap) {
		this.propertiesMap = propertiesMap;
	}

	public HashMap<String, HashMap> getEnumeratesMap() {
		return enumeratesMap;
	}

	public void setEnumeratesMap(HashMap<String, HashMap> enumeratesMap) {
		this.enumeratesMap = enumeratesMap;
	}

	public HashMap<String, XmlRestriction> getValidateMap() {
		return validateMap;
	}

	public void setValidateMap(HashMap<String, XmlRestriction> validateMap) {
		this.validateMap = validateMap;
	}

	public HashMap<String, XmlCode> getRespCodeMap() {
		return respCodeMap;
	}

	public void setRespCodeMap(HashMap<String, XmlCode> respCodeMap) {
		this.respCodeMap = respCodeMap;
	}

}
