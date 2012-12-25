package br.mello.arthur.correcuritiba;

import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;


public class MainActivity extends SherlockListActivity {
	public static Event[] events;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (events != null) {
        	Arrays.sort(events);
        	setListAdapter(new EventAdapter(this, R.layout.event_list_item, events));
        } else {
        	Toast.makeText(this, R.string.download_error, Toast.LENGTH_LONG).show();
        }            
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Intent intent = new Intent(this, DisplayEvent.class);
    	intent.putExtra("event", ((Event)l.getItemAtPosition(position)));
    	startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }
    	  
}
