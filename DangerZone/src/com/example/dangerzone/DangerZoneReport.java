package com.example.dangerzone;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

public class DangerZoneReport {
	private static final String ID = "id";
	
	JSONObject dZone = null;
	
	public DangerZoneReport(){
	}
	public int postZone(int lng, int lat, int cat){

	//Creating JSON Parser instance
			JSONParser jParser = new JSONParser();
			int uid = 0;
			String data = "longitude=" + (lng/1E6) + "&latitude=" + (lat/1E6) + "&category=" + cat + "&timestamp=" + System.currentTimeMillis() / 1000L;

			try {
			    // Getting Array of Contacts
			    JSONObject d = jParser.getObject("http://dangerzone.cems.uvm.edu/api/submit?"+data);
			 
			    // Storing each json item in variable
			    JSONObject req = d.getJSONObject("response");
			    String id = req.getString(ID);
			    
			        
			    uid = Integer.parseInt(id);

			} catch (JSONException e) {
			    e.printStackTrace();
			}
			return uid;
			
		}
}
