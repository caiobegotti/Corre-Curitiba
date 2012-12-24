package br.mello.arthur.correcuritiba;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class DisplayEvent extends ListActivity {
	public final static String NAME_TITLE = "Nome";
	public final static String LOCAL_TITLE = "Local";
	public final static String DATE_TITLE = "Data";
	public final static String DISTANCE_TITLE = "Distância";
	public final static String ENROLLMENT_DATE_TITLE = "Data final das inscrições";
	public final static String ENROLLMENT_URL_TITLE = "Inscrições (Link)";
	public final static String DESCRIPTION_TITLE = "Detalhes";

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
        
        Detail[] details = new Detail[]{
        		new Detail(NAME_TITLE, event.getName()),
        		new Detail(LOCAL_TITLE, event.getLocal()),
        		new Detail(DATE_TITLE, event.getDate()),
        		new Detail(DISTANCE_TITLE, event.getDistance()),
        		new Detail(ENROLLMENT_DATE_TITLE, event.getEnrollmentDate()),
        		new Detail(ENROLLMENT_URL_TITLE, event.getEnrollmentUrl()),
        		new Detail(DESCRIPTION_TITLE, event.getDescription())
        };
        
        setListAdapter(new DetailAdapter(this, R.layout.details_list_item, details));
   }
}