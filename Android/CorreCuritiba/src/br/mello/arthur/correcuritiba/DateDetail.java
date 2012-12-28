package br.mello.arthur.correcuritiba;

import java.util.Date;

import android.content.Context;
import android.text.format.DateFormat;


public class DateDetail extends Detail {
	protected int iconRes = -1;
	protected Event event = null;

	public DateDetail(Context context, String title, Date detail, Event event) {
		super(title);

		java.text.DateFormat df = android.text.format.DateFormat.getDateFormat(context);
		String text = df.format(detail) + " (" + Util.capitalize(DateFormat.format("E", detail).toString()) + ")";

		this.detail = text;		
		this.iconRes = R.drawable.calendar;
		this.event = event;
	}

	public int getIconRes() {
		return iconRes;
	}

	public Event getEvent() {
		return event;
	}
}

