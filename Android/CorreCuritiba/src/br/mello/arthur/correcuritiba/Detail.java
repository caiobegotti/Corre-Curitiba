package br.mello.arthur.correcuritiba;

import java.util.Date;

import android.content.Context;
import android.text.format.DateFormat;

public class Detail {
	private String title;
	private String detail;
	private int iconRes = -1;
	private Event event = null;

	public Detail(String title, String detail) {
		this.title = title;
		this.detail = detail;
	}

	public Detail(String title, int detail) {
		this.title = title;
		this.detail = Integer.toString(detail);
	}

	public Detail(String title, Date detail, Event event, Context context) {
		java.text.DateFormat df = android.text.format.DateFormat.getDateFormat(context);
		this.title = title;
		this.detail = df.format(detail) + " (" + Util.capitalize(DateFormat.format("E", detail).toString()) + ")";
		this.iconRes = R.drawable.calendar;
		this.event = event;
	}

	public String getTitle() {
		return title;
	}

	public String getDetail() {
		return detail;
	}
	
	public int getIconRes() {
		return iconRes;
	}
	
	public Event getEvent() {
		return event;
	}
}
