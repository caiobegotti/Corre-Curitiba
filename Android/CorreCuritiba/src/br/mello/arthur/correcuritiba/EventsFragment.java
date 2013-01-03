package br.mello.arthur.correcuritiba;

import java.util.Arrays;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;


public class EventsFragment extends SherlockListFragment {

	private MenuItem refreshItem;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.events_fragment, container, false);    	
	}

	public void setEvents(Event[] events) {
		Arrays.sort(events);
		setListAdapter(new EventAdapter(getActivity(), R.layout.event_list_item, events));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		DetailFragment fragment = (DetailFragment)getFragmentManager().findFragmentById(R.id.detail_fragment);
		if (fragment != null && fragment.isInLayout()) {
			v.setActivated(true);
			fragment.displayEvent(position);
		} else {			
			Intent intent = new Intent(getActivity(), DetailActivity.class);
			intent.putExtra("position", position);
			startActivity(intent);
		}	
	}

	private void refresh() {
		// Attach a rotating ImageView to the refresh item
		LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ImageView image = (ImageView) inflater.inflate(R.layout.refresh_icon, null);

		Animation rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotation);
		rotation.setRepeatCount(Animation.INFINITE);
		image.startAnimation(rotation);

		refreshItem.setActionView(image);

		try {
			FetchEvents fetchEventsTask = new FetchEvents(getActivity(), false);
			fetchEventsTask.setOnPostExecuteListener(new FetchEvents.OnPostExecuteListener() {
				@Override
				public void onPostExecute(Activity activity) {
					refreshItem.getActionView().clearAnimation();
					refreshItem.setActionView(null);
				}	    			
			});
			fetchEventsTask.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Menu

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.menu_refresh) {
			refreshItem = item;
			refresh();
			try {
				FetchEvents fetchEventsTask = new FetchEvents(getActivity(), false);
				fetchEventsTask.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.events_menu, menu);
	}

}
