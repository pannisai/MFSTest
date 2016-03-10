package mfs.config.xml.util;

import java.util.ArrayList;
import java.util.List;

import mfs.config.xml.ReadXmlConfig;
import mfs.config.xml.element.XmlCode;
import mfs.config.xml.element.XmlEnum;
import mfs.config.xml.element.XmlEnumerated;
import mfs.config.xml.element.XmlProperty;
import mfs.exception.NotFoundXmlConfigException;

public class XmlValueUtil {

	public static String getPropertyValue(ReadXmlConfig xmlConfig, String name)
			throws NotFoundXmlConfigException {

		if (xmlConfig == null || xmlConfig.getProperties() == null
				|| xmlConfig.getProperties().getProperty() == null)
			throw new NotFoundXmlConfigException("Not found value(key:" + name
					+ ")");

		for (XmlProperty prop : xmlConfig.getProperties().getProperty()) {
			if (prop.getName().equals(name)) {
				return prop.getValue();
			}
		}
		throw new NotFoundXmlConfigException("Not found value(key:" + name
				+ ")");
	}

	public static boolean getPropertyBoolean(ReadXmlConfig xmlConfig,
			String name) throws NotFoundXmlConfigException {

		if (xmlConfig == null || xmlConfig.getProperties() == null
				|| xmlConfig.getProperties().getProperty() == null)
			throw new NotFoundXmlConfigException("Not found value(key:" + name
					+ ")");

		for (XmlProperty prop : xmlConfig.getProperties().getProperty()) {
			if ((prop.getName().equals(name))
					&& (prop.getType().equalsIgnoreCase("Boolean"))) {
				return Boolean.parseBoolean(prop.getValue());
			}
		}
		throw new NotFoundXmlConfigException("Not found value(key:" + name
				+ ")");
	}

	public static String getEnumerateValue(ReadXmlConfig xmlConfig,
			String name, String key) throws NotFoundXmlConfigException {

		if (xmlConfig == null || xmlConfig.getEnumerates() == null
				|| xmlConfig.getEnumerates().getEnumerated() == null)
			throw new NotFoundXmlConfigException("Not found value(name:" + name
					+ ", key:" + key + ")");

		for (XmlEnumerated enumt : xmlConfig.getEnumerates().getEnumerated()) {
			if (enumt.getName().equals(name)) {
				for (XmlEnum enumsub : enumt.getEnumt()) {
					if (enumsub.getKey().equals(key)) {
						return enumsub.getValue();
					}
				}
			}
		}
		throw new NotFoundXmlConfigException("Not found value(name:" + name
				+ ", key:" + key + ")");
	}

	/**
	 * Get Array Of Enumerate String, ignored keys
	 * 
	 * @param xmlConfig
	 * @param name
	 * @return
	 * @throws NotFoundXmlConfigException
	 */
	public static List<String> getEnumerateValues(ReadXmlConfig xmlConfig,
			String name) throws NotFoundXmlConfigException {
		if (xmlConfig == null || xmlConfig.getEnumerates() == null
				|| xmlConfig.getEnumerates().getEnumerated() == null)
			throw new NotFoundXmlConfigException("Not found value(name:" + name
					+ ")");

		for (XmlEnumerated enumt : xmlConfig.getEnumerates().getEnumerated()) {
			if (enumt.getName().equals(name)) {
				final List<String> enumValue = new ArrayList<String>();
				for (XmlEnum enumsub : enumt.getEnumt()) {
					enumValue.add(enumsub.getValue());
				}

				return enumValue;
			}
		}
		throw new NotFoundXmlConfigException("Not found value(name:" + name
				+ ")");
	}

	public static XmlCode getRespCode(ReadXmlConfig xmlConfig, String name)
			throws NotFoundXmlConfigException {

		if (xmlConfig == null || xmlConfig.getResp_code() == null
				|| xmlConfig.getResp_code().getCode() == null)
			throw new NotFoundXmlConfigException("Not found value list (name:"
					+ name + ")");

		for (XmlCode code : xmlConfig.getResp_code().getCode()) {
			if (code.getSrc_code().equals(name)) {
				return code;
			}
		}
		throw new NotFoundXmlConfigException("Not found value list (name:"
				+ name + ")");
	}
}
