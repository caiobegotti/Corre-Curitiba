package br.mello.arthur.correcuritiba;

public class Util {
	
	static public String formatDistance(int d) {
		if (d % 1000 < 100)
			return String.format("%d km", d / 1000);
		else
			return String.format("%.1f km", (float)d / 1000);
	}
}
