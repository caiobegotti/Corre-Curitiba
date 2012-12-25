package br.mello.arthur.correcuritiba;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter<Event> {
	private Context context;
	private int textViewResourceId;
	private Event[] objects;
	private String lastDate;

	public EventAdapter(Context context, int textViewResourceId, Event[] objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.objects = objects;
		this.lastDate = null;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View item = inflater.inflate(textViewResourceId, parent, false);
		
		Event event = objects[position];
		
		TextView name = (TextView) item.findViewById(R.id.name);
		name.setText(event.getName());
		
		TextView distance = (TextView) item.findViewById(R.id.distance);
		int dist = event.getDistance();
		distance.setText(String.format("%d m", dist));
		
		try {
			int color;
			if (dist > 0 && dist <= 5000) {
				color = 0xff7d9ec0; 
			} else if (dist <= 10000) {
				color = 0xffe3cf57;
			} else if (dist <= 22000) {
				color = 0xffff9912;
			} else if (dist <= 43000) {
				color = 0xffff7256;
			} else if (dist <= 100000) {
				color = 0xff8e388e;
			} else {
				color = 0xffc0c0c0;
			}

			View tag = item.findViewById(R.id.tag);
			tag.setBackgroundColor(color);
		} catch(Exception e) {

		}

		TextView date = (TextView) item.findViewById(R.id.date);
	
		String newDate = event.getDate();
		
		if (newDate.equals(lastDate))
			date.setVisibility(View.GONE);
		else
			date.setText(newDate);
		
		lastDate = newDate;
		
		return item;
	}
}
