package com.example.dangerzone;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class viewHolder extends ArrayAdapter<Tweet> {
	
	  private final List<Tweet> list;
	  private final Activity context;

	  public viewHolder(Activity context, List<Tweet> list) {
	    super(context, R.layout.activity_panel, list);
	    this.list = list;
	    this.context = context;
	    
	    
	    
	    
	  }

	  static class ViewHolder {
	    protected TextView author,tweet;
	    
	    
	  }

	  @Override
	  public View getView(final int position, View convertView, ViewGroup parent) {
	     View view = null;
	     
	    if (convertView == null) {
	      LayoutInflater inflator = context.getLayoutInflater();
	      view = inflator.inflate(R.layout.activity_panel, null);
	      final ViewHolder vHolder0 = new ViewHolder();
	      vHolder0.tweet = (TextView) view.findViewById(R.id.tweet);
	      vHolder0.author = (TextView) view.findViewById(R.id.author);




	      

	      

	      view.setTag(vHolder0);
	    //  vHolder0.toggleButton.setTag(list.get(position));
	    } else {
	      view = convertView;
	     // ((ViewHolder) view.getTag()).toggleButton.setTag(list.get(position));
	    }
	    ViewHolder vholder = (ViewHolder) view.getTag();
	    //holder.elapsedTime = (list.get(position).getTime());
	    vholder.author.setText(list.get(position).getAuthor());
	    vholder.tweet.setText(list.get(position).getTweet());
	    //vholder.toggleButton.setChecked(list.get(position).getState());
	    return view;
	  }

}
