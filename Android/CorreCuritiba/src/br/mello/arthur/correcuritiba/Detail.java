package br.mello.arthur.correcuritiba;

public class Detail {
	private String title;
	private String detail;

	public Detail(String title, String detail) {
		this.title = title;
		this.detail = detail;
	}
	
	public Detail(String title, int detail) {
		this.title = title;
		this.detail = Integer.toString(detail);
	}

	public String getTitle() {
		return title;
	}

	public String getDetail() {
		return detail;
	}
}
