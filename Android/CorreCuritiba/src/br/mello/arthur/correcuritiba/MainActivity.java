package br.mello.arthur.correcuritiba;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	public static Event[] events;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
        
        if (events != null) {
        	setListAdapter(new EventAdapter(this, R.layout.event_list_item, events));
        } else {
        	Toast.makeText(this, R.string.download_error, Toast.LENGTH_LONG).show();
        }            
    }
}
