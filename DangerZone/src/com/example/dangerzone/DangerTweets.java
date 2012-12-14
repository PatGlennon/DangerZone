package com.example.dangerzone;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DangerTweets extends Activity{
	Button addButton;
	Button deleteButton;
	ArrayList<Tweet> panelArray;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_layout);
        
        ListView myListView = (ListView) findViewById(R.id.myListView);

 	   
	   	
	  	  // array list of activities
	  	  panelArray = new ArrayList<Tweet>();
	
	  	  // array adapter 
	  	  final ArrayAdapter<Tweet> arrayAdapter;
	  	  //link to view holder
	  	  arrayAdapter = new viewHolder(this, panelArray);
	
	  	  	// set array adapter
	  	  	myListView.setAdapter(arrayAdapter);
	  	  addButton = (Button) findViewById(R.id.addButton);
	        //add action upon adding activity
	        addButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v){ 
					//panelArray.add(new Tweet("@tweeter","tweet"));
	  				  //arrayAdapter.notifyDataSetChanged();
					finish();
				}
			});
        
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
