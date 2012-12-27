package br.mello.arthur.correcuritiba;

import android.annotation.SuppressLint;

@SuppressLint("DefaultLocale")
public class Util {

	static public String formatDistance(int d) {
		if (d % 1000 < 100)
			return String.format("%d km", d / 1000);
		else
			return String.format("%.1f km", (float)d / 1000);
	}

	public static String capitalize(String s) {
		if (s.length() == 0) return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
}
