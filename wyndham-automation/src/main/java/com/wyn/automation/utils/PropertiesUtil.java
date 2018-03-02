package com.wyn.automation.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author mandalapu.rajasekhar
 *
 */
public class PropertiesUtil {

	public static Logger Log = Logger.getLogger(PropertiesUtil.class.getName());

	/**
	 * 
	 * This method reads startup.properties file and return properties stream.
	 * 
	 */
	public static String getPropertyValue(String propertyName) {

		String value = StringUtils.EMPTY;
		String filePath = AutomationUtils.getRelativePath() + "\\properties\\startup.properties";
		Properties properties = new Properties();
		try (FileReader reader = new FileReader(filePath)) {
			properties.load(reader);
			value = properties.getProperty(propertyName);
			if (value == null) {
				Log.error("Unable to read property value : " + propertyName);
			}
		} catch (IOException e) {
			Log.error("Unable to read statup.properties" + e.getMessage());
		}

		return value;
	}

}
