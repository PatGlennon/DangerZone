package com.example.dangerzone;



import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.maps.GeoPoint;

public class DangerZones extends Activity{
	Button addButton;
	ArrayList<Zone> panelArray1;
	private Context mContext;
	public static boolean updated = false;
	
	public void onCreate(Bundle savedInstanceState) {
        
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_layout);
        mContext = this;
        
        
        ListView myListView = (ListView) findViewById(R.id.myListView1);
        
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, final int position, long id){
        		
        		final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        		  dialog.setTitle("DANGER!")
        		  	.setPositiveButton("Map", new DialogInterface.OnClickListener() {
        				
        				public void onClick(DialogInterface dialog, int which) {
        					int lat = DangerMap.dZones.get(position).getLatitude();
        					int lng = DangerMap.dZones.get(position).getLongitude();
        					GeoPoint g = new GeoPoint(lat, lng);
        					DangerMap.mapView.getController().animateTo(g);
        					DangerZone.tabHost.setCurrentTab(0);
        				}
        			})
        			.setNeutralButton("Tweets", new DialogInterface.OnClickListener() {
        				
        				public void onClick(DialogInterface dialog, int which) {
        					Intent intent = new Intent(mContext, DangerTweets.class);
        					mContext.startActivity(intent);
        				}
        			});
        		  dialog.show();
        		  
        	}
		});
        

 	   ////////UPDATES ALL THE TIME AHHHHHHHH
	   	
	  	  // array list of activities
	  	  panelArray1 = new ArrayList<Zone>();
	
	  	  // array adapter 
	  	  final ArrayAdapter<Zone> arrayAdapter1;
	  	  //link to view holder
	  	  arrayAdapter1 = new viewHolderZone(this, panelArray1);
	
	  	  	// set array adapter
	  	  	myListView.setAdapter(arrayAdapter1);
	  	  	if (updated == false){
	  	  for (int i = 0; i < DangerMap.dZones.size(); i++){
	        
				panelArray1.add(DangerMap.dZones.get(i));
				updated = true;
	  	  	}
	  	  	}
	  	  arrayAdapter1.notifyDataSetChanged();	
	  	  	
	        //add action upon adding activity
	       
    }
	
	/*public void POST(View v){
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		
		//EditText message = (EditText) findViewById(R.id.editText1);
		
		//String messageStr = message.getText().toString();
		String URL = "http://www.cems.uvm.edu/~01cmorse/dz/DangerZone"; //+ messageStr;
		
		try {
		    URL webpage = new URL(URL);
		    in = new BufferedReader(new InputStreamReader(webpage.openStream()));
		    String input;

		    while ((input = in.readLine()) != null) {
		         sb.append(input);
		        }
		    in.close();
		} catch (IOException ex) {
			Toast.makeText(getApplicationContext(), "ERRORZ", Toast.LENGTH_LONG).show();
		}
		
		//TextView txtView = (TextView) findViewById(R.id.textView1);
		//txtView.setText(sb.toString());
	}*/
}

