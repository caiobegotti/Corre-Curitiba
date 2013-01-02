package br.mello.arthur.correcuritiba;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;


public class FetchEvents extends AsyncTask<String, Object, List<Event>> {
	private final static String JSON_URL = "http://caio.ueberalles.net/corridas/";
	private final Activity activity;
	private boolean useCache;
	private OnPostExecuteListener listener = null;
	
	public interface OnPostExecuteListener {
		public void onPostExecute(Activity activity);
	}

	public FetchEvents(Activity activity, boolean useCache) {
		this.activity = activity;
		this.useCache = useCache;
	}
	
	public void setOnPostExecuteListener(OnPostExecuteListener listener) {
		this.listener = listener;
	}

	@Override
	protected List<Event> doInBackground(String... params) {
		try {
			String jsonEvents = new WebClient(activity, JSON_URL).get(useCache);
			List<Event> events = new EventsParser().listFromJSON(jsonEvents);
			return events;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void onPostExecute(List<Event> results) {
		super.onPostExecute(results);
		
		if (results != null && results.size() > 0) {
			MainActivity.events = new Event[results.size()];
			results.toArray(MainActivity.events);
		}
		
		if (listener != null)
			listener.onPostExecute(activity);
	}
}
