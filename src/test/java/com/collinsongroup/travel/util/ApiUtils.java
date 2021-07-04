package com.collinsongroup.travel.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiUtils {

	private static Properties props;
	private static final String BASE_URI = "base_Url";

	static {
		props = new Properties();
		try {
			InputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
			props.load(fis);
		} catch (IOException ex) {
			throw new RuntimeException("Exception while loading properties file", ex);
		}

	}

	public static String getBaseUri() {
		return props.getProperty(BASE_URI);
	}

}
