package com.chirkov.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class DataSupplier {
	
	//private static final File propFile = new File("src/test/resources/temp/test.properties");
	public static Properties props;
	private static String baseUrl;
	
	private static void setUrl(String URL){
		baseUrl = URL;
	}
	public static String getURL(){
		return baseUrl;
	}
	
	public static void setUpConfig(String env){	
		
		File propsFile = new File("src/test/resources/"+env+"_test.properties");
		FileInputStream fileInput = null;
	
		try {
			fileInput = new FileInputStream(propsFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		props = new Properties();
		//load properties file
		try {
			props.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setUrl(props.getProperty("baseURL"));
	}
	
}
