package br.mello.arthur.correcuritiba;

import android.annotation.SuppressLint;
import android.content.Context;

public class Util {

	@SuppressLint("DefaultLocale")
	static public String formatDistance(Context context, int d) {
		if (d <= 0)
			return context.getString(R.string.unspecified_distance);
		if (d % 1000 < 100)
			return String.format("%d km", d / 1000);
		else
			return String.format("%.1f km", (float)d / 1000);
	}

	@SuppressLint("DefaultLocale")
	public static String capitalize(String s) {
		if (s.length() == 0) return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
