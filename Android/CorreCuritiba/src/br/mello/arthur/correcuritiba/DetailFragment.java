package br.mello.arthur.correcuritiba;

import java.util.ArrayList;
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
		ArrayList<Detail> detailList = new ArrayList<Detail>();
		
		detailList.add(new Detail(getString(R.string.name_title), event.getName()));
		detailList.add(new Detail(getString(R.string.local_title), event.getLocal()));
		detailList.add(new DateDetail(context, getString(R.string.date_title), new Date(event.getDate()), event));
		
		int distance = event.getDistance();
		if (distance > 0)
			detailList.add(new Detail(getString(R.string.distance_title), Util.formatDistance(context, distance)));
		
		detailList.add(new DateDetail(context, getString(R.string.enrollment_date_title), new Date(event.getEnrollmentDate()), event));
		
		String map = event.getMap();
		if (map != null)
			detailList.add(new Detail(getString(R.string.map_title), map));
		
		detailList.add(new Detail(getString(R.string.enrollment_url_title), event.getEnrollmentUrl()));
		detailList.add(new Detail(getString(R.string.description_title), event.getDescription()));

		setListAdapter(new DetailAdapter(getActivity(), R.layout.detail_list_item, detailList.toArray(new Detail[detailList.size()])));
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
			shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						getString(R.string.app_name) + ": " + currentEvent.getName());
			shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, currentEvent.getDescription());	    	
	    	
	        shareActionProvider.setShareIntent(shareIntent);
	    }
	}
}
