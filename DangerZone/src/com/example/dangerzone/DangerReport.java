package com.example.dangerzone;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class DangerReport extends Activity {
	private int lat, lng;
	private Context mContext;
	private String type;
	int dType, dLevel;
    List<Address> addresses = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger_report);
        
        mContext = this;
        
        Bundle extras = getIntent().getExtras();
        
        lat = extras.getInt("lat");
        lng = extras.getInt("lng");
        
        double dlat = (double) lat / 1E6;
        double dlng = (double) lng / 1E6;
        
        TextView t = (TextView) findViewById(R.id.textView2);
        
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        try{
        	addresses = geocoder.getFromLocation(dlat,dlng,1);
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        if (!addresses.isEmpty()){
        	t.setText("");
	        t.append(addresses.get(0).getLocality()+", ");
	        t.append(addresses.get(0).getAdminArea());
        }
       
        Spinner type_spinner = (Spinner) findViewById(R.id.danger_type_spinner);
        ArrayAdapter<CharSequence> type_adapter = ArrayAdapter.createFromResource(this,R.array.danger_types, android.R.layout.simple_spinner_item);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spinner.setAdapter(type_adapter);
        
        Spinner level_spinner = (Spinner) findViewById(R.id.danger_level_spinner);
        ArrayAdapter<CharSequence> level_adapter = ArrayAdapter.createFromResource(this,R.array.danger_levels, android.R.layout.simple_spinner_item);
        level_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level_spinner.setAdapter(level_adapter);
        
        Button addZone = (Button) findViewById(R.id.btnAdd);
        
        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                type = parent.getItemAtPosition(pos).toString();
                if (type.equals("Unclassified"))
                	dType = 0;
                else if (type.equals("Weather"))
                	dType = 1;
                else if (type.equals("Violence"))
                	dType = 2;
                else if (type.equals("Accident"))
                	dType = 3;
        	}
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
		});
        
        level_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                dLevel = Integer.parseInt(parent.getItemAtPosition(pos).toString());
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
		});
        
        addZone.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//int index = DangerMap.dZones.size();
				DangerZoneReport dzr = new DangerZoneReport();
				int id = dzr.postZone(lng, lat, dType);
				//int id = 123;
				Toast.makeText(mContext, id+"", Toast.LENGTH_LONG).show();
				
				Zone dZone = new Zone(id, dType, lat, lng, 50, dLevel, addresses.get(0).getLocality());
				DangerMap.dZones.add(dZone);
				GeoPoint point = new GeoPoint(lat, lng);
		        OverlayItem item = new OverlayItem(point, "DANGER!", "");
		        
		        DangerMap.dOverlay.addOverlay(item);
		        DangerMap.mapOverlays.add(DangerMap.dOverlay);
		        
		        finish();
		        
		        //Intent intent = new Intent(mContext, DangerZone.class);
				//mContext.startActivity(intent);
		        /*
		        Zone dZone = new Zone(0,5,lat,lng,50,5,addresses.get(0).getLocale().toString());
		        byte[] zone = Serializer.serializeObject(dZone);
		        Intent intent = new Intent(mContext, DangerZone.class);
		        
		        intent.putExtra("zone", zone);
				mContext.startActivity(intent);
		        */
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_danger_report, menu);
        return true;
    }
}
