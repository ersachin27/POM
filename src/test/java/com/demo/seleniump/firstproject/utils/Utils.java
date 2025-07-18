package com.demo.seleniump.firstproject.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
	
 private static Properties prop;

	    static {
	        try {
	            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
	            prop = new Properties();
	            prop.load(fis);
	        } catch (IOException e) {
	            throw new RuntimeException("Could not load config.properties", e);
	        }
	    }

	    public static String getProperty(String key) {
	        return prop.getProperty(key);
	    }
	}





