package br.mello.arthur.correcuritiba;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;


public class DetailFragment extends SherlockFragment {
	private ShareActionProvider shareActionProvider;
	private Event currentEvent = null;
	private int position;

	static DetailFragment newInstance(int position) {
		DetailFragment fragment = new DetailFragment();

		// Supply position as an argument
		Bundle args = new Bundle();
		args.putInt("position", position);
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.detail_fragment, container, false);
		position = getArguments() != null ? getArguments().getInt("position") : 0;
		return view;
	}

	// makes the fragment visible to the user
	@Override
	public void onStart() {
		super.onStart();
		displayEvent();
	}

	public void displayEvent() {
		displayEvent(position);
	}

	public void displayEvent(int position) {
		View rootView = getView();
		Event event = MainActivity.events[position];
		Context context = getActivity().getApplicationContext();

		// Fill header

		TextView title = (TextView)rootView.findViewById(R.id.title);
		title.setText(event.getName());

		TextView subtitle = (TextView)rootView.findViewById(R.id.subtitle);
		subtitle.setText(event.getLocal());


		// Fill detail list

		ArrayList<Detail> detailList = new ArrayList<Detail>();
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

		ListView list = (ListView)rootView.findViewById(R.id.list);
		list.setAdapter(new DetailAdapter(getActivity(), R.layout.detail_list_item, detailList.toArray(new Detail[detailList.size()])));
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

	public void setShareIntent() {
		if (shareActionProvider != null && currentEvent != null) {
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, currentEvent.getName());
			shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, currentEvent.getUrl());	    	

			shareActionProvider.setShareIntent(shareIntent);
		}
	}
}
