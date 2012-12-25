package br.mello.arthur.correcuritiba;

import java.util.Date;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;


public class DisplayEvent extends SherlockListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		Bundle bundle = getIntent().getExtras();
		Event event = bundle.getParcelable("event");

		setTitle(event.getName());

		Detail[] details = new Detail[] {
				new Detail(getString(R.string.name_title), event.getName()),
				new Detail(getString(R.string.local_title), event.getLocal()),
				new Detail(getString(R.string.date_title), new Date(event.getDate())),
				new Detail(getString(R.string.distance_title), event.getDistance()),
				new Detail(getString(R.string.enrollment_date_title), new Date(event.getEnrollmentDate())),
				new Detail(getString(R.string.enrollment_url_title), event.getEnrollmentUrl()),
				new Detail(getString(R.string.description_title), event.getDescription())
		};

		setListAdapter(new DetailAdapter(this, R.layout.details_list_item, details));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	            Intent intent = new Intent(this, MainActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}