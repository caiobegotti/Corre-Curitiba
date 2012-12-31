package br.mello.arthur.correcuritiba;


public class Event implements Comparable<Event> {

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

	// Comparable methods

	@Override
	public int compareTo(Event event) {
		return (int)Math.signum(event.date - this.date);
	}
}
