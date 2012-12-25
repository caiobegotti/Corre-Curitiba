package br.mello.arthur.correcuritiba;

import android.app.ListActivity;
import android.os.Bundle;

public class DisplayEvent extends ListActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Bundle bundle = getIntent().getExtras();
        Event event = bundle.getParcelable("event");

        setTitle(event.getName());
        
        Detail[] details = new Detail[] {
        	new Detail(getString(R.string.name_title), event.getName()),
        	new Detail(getString(R.string.local_title), event.getLocal()),
        	new Detail(getString(R.string.date_title), event.getDate()),
        	new Detail(getString(R.string.distance_title), event.getDistance()),
        	new Detail(getString(R.string.enrollment_date_title), event.getEnrollmentDate()),
        	new Detail(getString(R.string.enrollment_url_title), event.getEnrollmentUrl()),
        	new Detail(getString(R.string.description_title), event.getDescription())
        };
        
        setListAdapter(new DetailAdapter(this, R.layout.details_list_item, details));
   }
}