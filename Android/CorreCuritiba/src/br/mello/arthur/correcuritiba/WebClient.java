package br.mello.arthur.correcuritiba;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient {
	private final String url;
	
	public WebClient(String url) {
		this.url = url;
	}
	
	public String get() {
		try {
			DefaultHttpClient client = new DefaultHttpClient();
	    	HttpGet get = new HttpGet(this.url);
	    	get.setHeader("Accept", "application/json");
	    	get.setHeader("Content-type", "application/json");
	    	
	    	HttpResponse response = client.execute(get);
	    	String json = EntityUtils.toString(response.getEntity());
	    	return json;
        } catch (ClientProtocolException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } 
		
		return null;
	}
}
