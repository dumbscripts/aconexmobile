package com.aconexmobile.frame;

import java.io.IOException;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.log4testng.Logger;

public class Utils {

	static final Logger logger = Logger.getLogger(Utils.class);
	
	private static Random ran;
	static final String url = "https://apidev.aconex.com/field/mobile/www/index.html";
	
	
	public static int getRandom(){
		ran = new Random();
		int x = ran.nextInt(9999);
		return x;
	} 
	
	public static void verifyAppServerIsUp() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() == 200) {
        	logger.info("App server is up, continuing execution!");
        } else {
            throw new RuntimeException("!!! App server down, aborting execution !!! ");        
        }
    }
	
}
