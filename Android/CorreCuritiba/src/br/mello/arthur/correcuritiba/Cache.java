package br.mello.arthur.correcuritiba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import android.content.Context;

public class Cache {
	private static final String FILE = "cache.data";
	private static long TTL = 2 * 24 * 60 * 60 * 1000;		// two days
	File dir;

	public Cache(Context context) {
		dir = context.getCacheDir();
	}

	public void store(String string) {
		File cacheFile = new File(dir, FILE);

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(cacheFile));
			out.write(string);
			out.close();
		} 
		catch (IOException e) { 
			cacheFile.delete();
		}
	}
	
	public String retrieve() {
		File cacheFile = new File(dir, FILE);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
			return reader.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public boolean isValid() {
		File cacheFile = new File(dir, FILE);
		long now = new Date().getTime();
		
		if (!cacheFile.isFile())
			return false;
		
		if (now - cacheFile.lastModified() > TTL)
			return false;
		
		return true;
	}
}
