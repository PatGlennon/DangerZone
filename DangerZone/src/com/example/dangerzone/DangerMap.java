package com.example.dangerzone;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class DangerMap extends MapActivity {
	public static List<Overlay> mapOverlays;
	public static ArrayList<Zone> dZones = new ArrayList<Zone>();
	public static DangerItemizedOverlay dOverlay;
	public static DangerMapView mapView;
	private GeoPoint longpressLocation;
	private Context mContext;
	private Handler handler = new Handler();
	private int lat, lng;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        
        //if (isNetworkConnected()){
        //Check for network connectivity
	        DangerZoneGet dGet = new DangerZoneGet();
	      //  if (dGet != null){
	        //Make sure no errors in parsing data.
	        	ArrayList<Zone> serverZones = dGet.getZones();
	        	dZones.addAll(serverZones);
	        //}
        //}
        
        mContext = this;

        mapView = (DangerMapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        // Adding overlays to the map
        mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.pin);
        dOverlay = new DangerItemizedOverlay(drawable, this);
        
        for (int i = 0; i < dZones.size(); i++){
        	GeoPoint p = new GeoPoint(dZones.get(i).getLatitude(), dZones.get(i).getLongitude());
        	OverlayItem o = new OverlayItem(p,"DangerZone","");
        	dOverlay.addOverlay(o);
        }
        mapOverlays.add(dOverlay);
        
        //GeoPoint point = new GeoPoint(19240000,-99120000);
        //OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
        
        //dOverlay.addOverlay(overlayitem);
        //mapOverlays.add(dOverlay);
        
        
        Button center = (Button) findViewById(R.id.center);
        Button refresh = (Button) findViewById(R.id.refresh);
        
        center.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				mapView.getController().animateTo(getCurrentLoc());
				
			}
		});
        
        refresh.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
		        DangerZoneGet dGet = new DangerZoneGet();
				dZones.clear();
			    ArrayList<Zone> serverZones = dGet.getZones();
			    dZones.addAll(serverZones);
			    
			    mapOverlays.clear();
		        Drawable drawable = mContext.getResources().getDrawable(R.drawable.pin);
		        dOverlay = new DangerItemizedOverlay(drawable, mContext);
		        
		        for (int i = 0; i < dZones.size(); i++){
		        	GeoPoint p = new GeoPoint(dZones.get(i).getLatitude(), dZones.get(i).getLongitude());
		        	OverlayItem o = new OverlayItem(p,"DangerZone","");
		        	dOverlay.addOverlay(o);
		        }
		        mapOverlays.add(dOverlay);
		        DangerZones.updated = false;
		        mapView.getController().animateTo(getCurrentLoc());
				
			}
		});
       
        
      
        
        
        mapView.setOnLongpressListener(new DangerMapView.OnLongpressListener() {
			public void onLongpress(MapView view, final GeoPoint lpr) {

				handler.post(new Runnable(){
					public void run(){
						longpressLocation = lpr;
						createAlert("Add Danger?");
					}
				});
			}
		});    
        
    }
	
	private GeoPoint getCurrentLoc(){
		LocationManager mlocManager=null;
        LocationListener mlocListener;
        mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, mlocListener);
        
        Criteria criteria = new Criteria();
        String provider = mlocManager.getBestProvider(criteria, false);
        Location loc = mlocManager.getLastKnownLocation(provider);
        //et_field_name.append("LOC: " + provider);

       if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
    	   int lat = (int) (loc.getLatitude()*1E6);
    	   int lng = (int) (loc.getLongitude()*1E6);
    	   GeoPoint g = new GeoPoint(lat, lng);
    	   return g;
       }
       else{
    	   GeoPoint p = new GeoPoint(0,0);
    	   return p;
       }
	}
	
	private void createAlert(String s){
	/**************************************
	 * creates an alert dialog to add a dangerzone to the map
	 * if yes sends the user to the DangerReport page
	 */
		lat = longpressLocation.getLatitudeE6();
		lng = longpressLocation.getLongitudeE6();
		
		double dlat = (double) lat / 1E6;
        double dlng = (double) lng / 1E6;
        List<Address> addresses = null;
        
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        try{
        	addresses = geocoder.getFromLocation(dlat,dlng,1);
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        if (!addresses.isEmpty()){
			AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
			  dialog.setTitle(s).setCancelable(true)
			  	.setPositiveButton("Add", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						
	        			Intent intent = new Intent(mContext, DangerReport.class);
	        			intent.putExtra("lat", lat);
	        			intent.putExtra("lng", lng);
	        			
						mContext.startActivity(intent);
					}	
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}
				});
			  dialog.show();
		}else {
			Toast.makeText(mContext, "Address could not be resolved.", Toast.LENGTH_LONG).show();
		}
    }
	
	private boolean isNetworkConnected() {
		  ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		  NetworkInfo n = cm.getActiveNetworkInfo();
		  if (n == null) {
		   return false;
		  } else
		   return true;
		 }
	
	
	
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}

}
