package com.example.dangerzone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	
	public JSONParser(){
		
	}
	
	public JSONObject getObject(String URL){
		BufferedReader in = null;
		StringBuffer jSon = new StringBuffer();
		
		try {
		    URL webpage = new URL(URL);
		    in = new BufferedReader(new InputStreamReader(webpage.openStream()));
		    String input;

		    while ((input = in.readLine()) != null) {
		         jSon.append(input);
		        }
		    in.close();
		} catch (IOException ex) {
			Log.e("HTTP Get", "Error getting data: " + ex.toString());
		}
		String json = jSon.toString();
		JSONObject jObject = null;
		try {
	        jObject = new JSONObject(json);
	    } catch (JSONException e) {
	        Log.e("JSON Parser", "Error parsing data " + e.toString());
	    }
		return jObject;
	}
	
	public JSONArray getJSON(String URL){
		
		BufferedReader in = null;
		StringBuffer jSon = new StringBuffer();
		
		//String URL = "http://dangerzone.cems.uvm.edu/api/request?latitude=5&longitude=6&radius=5";
		//String URL = "http://www.uvm.edu/~pjglenno/test/test.php";
		try {
		    URL webpage = new URL(URL);
		    in = new BufferedReader(new InputStreamReader(webpage.openStream()));
		    String input;

		    while ((input = in.readLine()) != null) {
		         jSon.append(input);
		        }
		    in.close();
		} catch (IOException ex) {
			Log.e("HTTP Get", "Error getting data: " + ex.toString());
		}
		String json = jSon.toString();
		JSONArray jArray = null;
		try {
	        jArray = new JSONArray(json);
	    } catch (JSONException e) {
	        Log.e("JSON Parser", "Error parsing data " + e.toString());
	    }
		return jArray;
	}
		
}