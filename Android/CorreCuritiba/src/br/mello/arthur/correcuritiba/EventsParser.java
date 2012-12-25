package br.mello.arthur.correcuritiba;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


public class EventsParser {
	public final static String NAME_KEY = "Nome:";
	public final static String DESCRIPTION_KEY = "Descrição:";
	public final static String DATE_KEY = "Data:";
	public final static String DISTANCE_KEY = "Distância:";
	public final static String LOCAL_KEY = "Local:";
	public final static String ENROLLMENT_URL_KEY = "Inscrições (Link):";
	public final static String ENROLLMENT_DATE_KEY = "Data final das inscrições:";
	
	@SuppressLint("SimpleDateFormat")
	public List<Event> listFromJSON(String json) throws JSONException {
		String name = null;
		String description = null;
		long date = 0;
		int distance = 0;
		String local = null;
		String url = null;
		String enrollmentUrl = null;
		long enrollmentDate = 0;

		ArrayList<Event> events = new ArrayList<Event>();
		
		JSONObject objectJson = new JSONObject(json);
		Iterator<?> id = objectJson.keys();

		while(id.hasNext()){
			String key = id.next().toString();
			JSONObject eventJson = objectJson.getJSONObject(key);
			
			if(eventJson.has(NAME_KEY)){
				name = eventJson.getString(NAME_KEY);				
			}

			if(eventJson.has(DESCRIPTION_KEY)){
				description = eventJson.getString(DESCRIPTION_KEY);				
			}

			if(eventJson.has(DATE_KEY)){
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					date = df.parse(eventJson.getString(DATE_KEY)).getTime();
				} catch (Exception e) {
					date = 0;
				}				
			}

			if(eventJson.has(DISTANCE_KEY)){
				distance = Integer.parseInt(eventJson.getString(DISTANCE_KEY).split(" ", 2)[0]);				
			}

			if(eventJson.has(LOCAL_KEY)){
				local = eventJson.getString(LOCAL_KEY);				
			}

			url = key;

			if(eventJson.has(ENROLLMENT_URL_KEY)){
				enrollmentUrl = eventJson.getString(ENROLLMENT_URL_KEY);				
			}

			if(eventJson.has(ENROLLMENT_DATE_KEY)){
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					enrollmentDate = df.parse(eventJson.getString(ENROLLMENT_DATE_KEY)).getTime();
				} catch (Exception e) {
					enrollmentDate = 0;
				}
			}

			Event event = new Event(name,
									description,
									date,
									distance,
									local,
									url,
									enrollmentUrl,
									enrollmentDate);

			events.add(event);
		}
		
		return events;
	}

}
