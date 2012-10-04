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

	public EventAdapter(Context context, int textViewResourceId, Event[] objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.objects = objects;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View item = inflater.inflate(textViewResourceId, parent, false);
		
		Event event = objects[position];
		
		TextView name = (TextView) item.findViewById(R.id.name);
		name.setText(event.getName());
		
		TextView local = (TextView) item.findViewById(R.id.local);
		local.setText(event.getLocal());
		
		TextView date = (TextView) item.findViewById(R.id.date);
		date.setText(event.getDate());
		
		return item;
	}
}
