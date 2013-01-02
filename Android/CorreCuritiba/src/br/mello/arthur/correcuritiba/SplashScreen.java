package br.mello.arthur.correcuritiba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class SplashScreen extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_screen);
		
		try {
    		FetchEvents fetchEventsTask = new FetchEvents(this, true);
    		fetchEventsTask.setOnPostExecuteListener(new FetchEvents.OnPostExecuteListener() {
				@Override
				public void onPostExecute(Activity activity) {
					activity.startActivity(new Intent(activity, MainActivity.class));
					activity.finish();
					
				}    			
    		});
    		fetchEventsTask.execute();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
}
