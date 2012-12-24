package br.mello.arthur.correcuritiba;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;


public class Event implements Parcelable {

	private String name;
	private String description;
	private String date;
	private String distance;
	private String local;
	private String url;
	private String enrollmentUrl;
	private String enrollmentDate;
	
	public Event(String name, String description, String date, String distance, String local, String url, String enrollmentUrl, String enrollmentDate) {
		this.name = name;
		this.description = description;
		this.date = date;
		this.distance = distance;
		this.local = local;
		this.url = url;
		this.enrollmentUrl = enrollmentUrl;
		this.enrollmentDate = enrollmentDate;
	}
	
	public Event(Parcel in) {
		name = in.readString();
		description = in.readString();
		date = in.readString();
		distance = in.readString();
		local = in.readString();
		url = in.readString();
		enrollmentUrl = in.readString();
		enrollmentDate = in.readString();		
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDate() {
		return date;
	}

	public String getDistance() {
		return distance;
	}

	public String getLocal() {
		return local;
	}

	public String getUrl() {
		return url;
	}

	public String getEnrollmentUrl() {
		return enrollmentUrl;
	}

	public String getEnrollmentDate() {
		return enrollmentDate;
	}
	
	// Parcelable stuff

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(name);
		out.writeString(description);
		out.writeString(date);
		out.writeString(distance);
		out.writeString(local);
		out.writeString(url);
		out.writeString(enrollmentUrl);
		out.writeString(enrollmentDate);
	}
	
	public static final Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
		public Event createFromParcel(Parcel in) {
			return new Event(in);
		}

		public Event[] newArray(int size) {
			return new Event[size];
		}
	};
}
