package br.mello.arthur.correcuritiba;

import android.os.Parcel;
import android.os.Parcelable;


public class Event implements Parcelable, Comparable<Event> {

	private String name;
	private String description;
	private long date;
	private int distance;
	private String local;
	private String url;
	private String enrollmentUrl;
	private long enrollmentDate;
	private String map;

	public Event(String name, String description, long date, int distance, String local, String url,
			String enrollmentUrl, long enrollmentDate, String map) {
		this.name = name;
		this.description = description;
		this.date = date;
		this.distance = distance;
		this.local = local;
		this.url = url;
		this.enrollmentUrl = enrollmentUrl;
		this.enrollmentDate = enrollmentDate;
		this.map = map;
	}

	public Event(Parcel in) {
		name = in.readString();
		description = in.readString();
		date = in.readLong();
		distance = in.readInt();
		local = in.readString();
		url = in.readString();
		enrollmentUrl = in.readString();
		enrollmentDate = in.readLong();
		map = in.readString();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public long getDate() {
		return date;
	}

	public int getDistance() {
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

	public long getEnrollmentDate() {
		return enrollmentDate;
	}
	
	public String getMap() {
		return map;
	}

	// Parcelable methods

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(name);
		out.writeString(description);
		out.writeLong(date);
		out.writeInt(distance);
		out.writeString(local);
		out.writeString(url);
		out.writeString(enrollmentUrl);
		out.writeLong(enrollmentDate);
		out.writeString(map);
	}

	public static final Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
		public Event createFromParcel(Parcel in) {
			return new Event(in);
		}

		public Event[] newArray(int size) {
			return new Event[size];
		}
	};

	// Comparable methods

	@Override
	public int compareTo(Event event) {
		return (int)Math.signum(event.date - this.date);
	}
}
