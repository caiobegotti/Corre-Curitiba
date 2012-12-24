package br.mello.arthur.correcuritiba;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class DisplayEvent extends ListActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_display);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.display_custom_title);

        Bundle bundle = getIntent().getExtras();
        Event event = bundle.getParcelable("event");

        TextView title = (TextView) findViewById(R.id.displaytitle);
        title.setText(event.getName());

        TextView subTitle = (TextView) findViewById(R.id.displaysubtitle);
        subTitle.setText(event.getDate());
        
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