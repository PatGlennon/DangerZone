package com.example.dangerzone;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DangerZoneGet {
	
	private static final String ID = "id";
	private static final String CATEGORY = "category";
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	//private static final String RANGE = "range";
	//private static final String SEVERITY = "severity";
	//private static final String LOCALE = "locale";
	
	JSONArray dZone = null;
	private ArrayList<Zone> dZones = new ArrayList<Zone>();
	
	public DangerZoneGet(){
	}
	public ArrayList<Zone> getZones(){

	//Creating JSON Parser instance
			JSONParser jParser = new JSONParser();

			try {
			    // Getting Array of Contacts
			    dZone = jParser.getJSON("http://dangerzone.cems.uvm.edu/api/request?latitude=5&longitude=6&radius=0");
			 
			    // looping through All Contacts
			    for(int i = 0; i < dZone.length(); i++){
			        JSONObject d = dZone.getJSONObject(i);
			 
			        // Storing each json item in variable
			        String id = d.getString(ID);
			        String category = d.getString(CATEGORY);
			        String latitude = d.getString(LATITUDE);
			        String longitude = d.getString(LONGITUDE);
			        //String range = d.getString(RANGE);
			        //String severity = d.getString(SEVERITY);
			        //String locale = d.getString(LOCALE);
			        
			        int uid = Integer.parseInt(id);
			        int lat = (int) (Double.parseDouble(latitude) * 1E6);
			        int lng = (int) (Double.parseDouble(longitude) * 1E6);
			        int cat = Integer.parseInt(category);
			        //int rng = Integer.parseInt(range);
			        //int sev = Integer.parseInt(severity);
			        int rng = 50;
			        int sev = 5;
			        
			        String locale = "";
			        Zone dZone = new Zone(uid,cat,lat,lng,rng,sev,locale);
			        
			        dZones.add(dZone);
			        
			 
			    }
			} catch (JSONException e) {
			    e.printStackTrace();
			}
			return dZones;
			
		}
}
