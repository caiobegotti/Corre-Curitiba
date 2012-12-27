package br.mello.arthur.correcuritiba;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;


public class DetailFragment extends SherlockListFragment {
	private ShareActionProvider shareActionProvider;
	private Event currentEvent = null;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.detail_fragment, container, false);
		return view;
	}

	public void displayEvent(Event event) {
		Context context = getActivity().getApplicationContext();
		Detail[] details = new Detail[] {
				new Detail(getString(R.string.name_title), event.getName()),
				new Detail(getString(R.string.local_title), event.getLocal()),
				new Detail(getString(R.string.date_title), new Date(event.getDate()), event, context),
				new Detail(getString(R.string.distance_title), Util.formatDistance(context, event.getDistance())),
				new Detail(getString(R.string.enrollment_date_title), new Date(event.getEnrollmentDate()), event, context),
				new Detail(getString(R.string.enrollment_url_title), event.getEnrollmentUrl()),
				new Detail(getString(R.string.description_title), event.getDescription())
		};

		setListAdapter(new DetailAdapter(getActivity(), R.layout.detail_list_item, details));
		currentEvent = event;
		
		setShareIntent();
	}


	// Menu

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.detail_menu, menu);

		MenuItem item = menu.findItem(R.id.menu_share);		
		shareActionProvider = (ShareActionProvider)item.getActionProvider();
		setShareIntent();
	}
	
	
	// Sharing
	
	private void setShareIntent() {
	    if (shareActionProvider != null && currentEvent != null) {
	    	Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						getString(R.string.app_name) + ": " + currentEvent.getName());
			shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, currentEvent.getDescription());	    	
	    	
	        shareActionProvider.setShareIntent(shareIntent);
	    }
	}
}
