package br.mello.arthur.correcuritiba;


public class Event {

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
}
