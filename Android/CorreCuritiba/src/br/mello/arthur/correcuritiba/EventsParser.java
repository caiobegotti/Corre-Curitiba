package br.mello.arthur.correcuritiba;

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
	
	public List<Event> listFromJSON(String json) throws JSONException {
		String name = null;
		String description = null;
		String date = null;
		String distance = null;
		String local = null;
		String url = null;
		String enrollmentUrl = null;
		String enrollmentDate = null;

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
				date = eventJson.getString(DATE_KEY);				
			}

			if(eventJson.has(DISTANCE_KEY)){
				distance = eventJson.getString(DISTANCE_KEY);				
			}

			if(eventJson.has(LOCAL_KEY)){
				local = eventJson.getString(LOCAL_KEY);				
			}

			url = key;

			if(eventJson.has(ENROLLMENT_URL_KEY)){
				enrollmentUrl = eventJson.getString(ENROLLMENT_URL_KEY);				
			}

			if(eventJson.has(ENROLLMENT_DATE_KEY)){
				enrollmentDate = eventJson.getString(ENROLLMENT_DATE_KEY);				
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
