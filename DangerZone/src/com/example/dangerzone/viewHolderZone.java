package com.example.dangerzone;




import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class viewHolderZone extends ArrayAdapter<Zone> {
	
	  private final List<Zone> list;
	  private final Activity context;

	  public viewHolderZone(Activity context, List<Zone> list) {
	    super(context, R.layout.activity_zone_panel, list);
	    this.list = list;
	    this.context = context;
	    
	    
	    
	    
	  }

	  static class ViewHolder1 {
		 
	    protected TextView uid,category,severity,locale;
	    
	    
	  }

	  @Override
	  public View getView(final int position, View convertView, ViewGroup parent) {
	     View view = null;
	     
	    if (convertView == null) {
	      LayoutInflater inflator = context.getLayoutInflater();
	      view = inflator.inflate(R.layout.activity_zone_panel, null);
	      final ViewHolder1 vHolder0 = new ViewHolder1();
	  
	 
	      vHolder0.uid = (TextView) view.findViewById(R.id.uid);
	      vHolder0.category = (TextView) view.findViewById(R.id.category);
	      //vHolder0.latitude = (TextView) view.findViewById(R.id.latitude);
	      //vHolder0.longitude = (TextView) view.findViewById(R.id.longitude);
	      //vHolder0.range = (TextView) view.findViewById(R.id.range);
	      vHolder0.severity = (TextView) view.findViewById(R.id.severity);
	      //vHolder0.locale = (TextView) view.findViewById(R.id.locale);
	      //vHolder0.tweets = (TextView) view.findViewById(R.id.tweets);
		


	      

	      

	      view.setTag(vHolder0);
	    
	    } else {
	      view = convertView;
	    
	    }

	    ViewHolder1 vholder = (ViewHolder1) view.getTag();
	    

	    vholder.uid.setText("ID: "+list.get(position).getUID());
	    vholder.category.setText("Category: "+list.get(position).getCategory());
	    //vholder.latitude.setText("Latitude: "+list.get(position).getLatitude());
	    //vholder.longitude.setText("Longitude: "+list.get(position).getLongitude());
	    //vholder.range.setText("Range: "+list.get(position).getRange());
	    vholder.severity.setText("Severity: "+list.get(position).getSeverity());
	    //vholder.locale.setText("Locale: "+list.get(position).getLocale());
	    //vholder.tweets.setText("Tweets: "+list.get(position).getTweets());
	    
	    //vholder.toggleButton.setChecked(list.get(position).getState());
	    return view;
	  }

}

