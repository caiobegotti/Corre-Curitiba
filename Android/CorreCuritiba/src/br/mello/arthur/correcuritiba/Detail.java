package br.mello.arthur.correcuritiba;


public class Detail {
	protected String title;
	protected String detail;
	protected boolean isHeader = false;

	public Detail(String title) {
		this.title = title;
	}

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

	public void adapt() {

	}
}
