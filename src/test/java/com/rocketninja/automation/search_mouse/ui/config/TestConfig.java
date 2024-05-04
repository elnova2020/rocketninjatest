package com.rocketninja.automation.search_mouse.ui.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestConfig {

	public static Properties props;
	
	public static void loadTestConfigProps() {
		
		File file = new File(new TestConfig().getClass().getClassLoader().getResource("test-config.properties").getFile());
		try ( InputStream input = new FileInputStream(file.getPath())) {

            props = new Properties();
            props.load(input);

            System.out.println("Chrome driver home: " + props.getProperty("chrome.driver.home"));
            System.out.println("Chrome headless: " + props.getProperty("chrome.headless"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
	}
	
}
