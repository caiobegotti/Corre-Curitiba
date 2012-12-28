package br.mello.arthur.correcuritiba;

import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;


public class MainActivity extends SherlockFragmentActivity {
	public static Event[] events;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		if (events != null) {
			EventsFragment fragment = (EventsFragment)getSupportFragmentManager().findFragmentById(R.id.events_fragment);
			if (fragment != null && fragment.isInLayout()) {
				fragment.setEvents(events);
			}
		} else {
			Toast.makeText(this, R.string.download_error, Toast.LENGTH_LONG).show();
		}            
	}

	// Menu
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.base_menu, menu);
		return true;
	}
}
