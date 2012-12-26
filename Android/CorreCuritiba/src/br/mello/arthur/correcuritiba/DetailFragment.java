package br.mello.arthur.correcuritiba;

import java.util.Date;

import com.actionbarsherlock.app.SherlockListFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DetailFragment extends SherlockListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.detail_fragment, container, false);
		return view;
	}

	public void displayEvent(Event event) {
		Detail[] details = new Detail[] {
				new Detail(getString(R.string.name_title), event.getName()),
				new Detail(getString(R.string.local_title), event.getLocal()),
				new Detail(getString(R.string.date_title), new Date(event.getDate())),
				new Detail(getString(R.string.distance_title), Util.formatDistance(event.getDistance())),
				new Detail(getString(R.string.enrollment_date_title), new Date(event.getEnrollmentDate())),
				new Detail(getString(R.string.enrollment_url_title), event.getEnrollmentUrl()),
				new Detail(getString(R.string.description_title), event.getDescription())
		};

		setListAdapter(new DetailAdapter(getActivity(), R.layout.detail_list_item, details));
	}
}
