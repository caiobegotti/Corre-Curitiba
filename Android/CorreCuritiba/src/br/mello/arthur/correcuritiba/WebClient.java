package br.mello.arthur.correcuritiba;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;


public class WebClient {
	private final String url;
	private Cache cache;

	public WebClient(Context context, String url) {
		this.url = url;
		this.cache = new Cache(context);
	}

	public String get(boolean useCache) {
		
		if (useCache && cache.isValid()) {
			return cache.retrieve();
		}
		
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(this.url);
			get.setHeader("Accept", "application/json");
			get.setHeader("Content-type", "application/json");

			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String json = EntityUtils.toString(entity);
			cache.store(json);
			return json;
		} catch (Exception e) {
			return cache.retrieve();
		} 		
	}
}
