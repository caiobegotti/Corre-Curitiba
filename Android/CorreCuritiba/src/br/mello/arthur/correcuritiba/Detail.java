package br.mello.arthur.correcuritiba;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	@SuppressLint("SimpleDateFormat")
	public Detail(String title, Date detail) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.title = title;
		this.detail = df.format(detail);
	}

	public String getTitle() {
		return title;
	}

	public String getDetail() {
		return detail;
	}
}
