package br.mello.arthur.correcuritiba;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DetailAdapter extends ArrayAdapter<Detail> {
	private Context context;
	private int textViewResourceId;
	private Detail[] objects;
	
	public DetailAdapter(Context context, int textViewResourceId, Detail[] objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.objects = objects;
		
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View item = inflater.inflate(textViewResourceId, parent, false);
		
		Detail detail = objects[position];
		
		TextView title = (TextView) item.findViewById(R.id.detailtitle);
		title.setText(detail.getTitle());

		TextView detailText = (TextView) item.findViewById(R.id.detail);
		detailText.setText(detail.getDetail());

		return item;
	}	
}
