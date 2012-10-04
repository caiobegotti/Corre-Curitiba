package br.mello.arthur.correcuritiba;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SplashScreen extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_screen);
		
		try {
    		FetchEvents fetchEventsTask = new FetchEvents(this);
    		fetchEventsTask.execute();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
}
