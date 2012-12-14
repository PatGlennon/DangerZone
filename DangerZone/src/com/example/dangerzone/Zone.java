package com.example.dangerzone;

import java.io.Serializable;
import java.util.ArrayList;


public class Zone implements Serializable {
	int latitude, longitude,uid;
	String locale;
	//ArrayList<Tweet> tweets;
	int range,severity,category;
	
	public Zone(){
		
	}
	
	
	  public Zone(int uid,int category,int latitude,int longitude,int range,int severity,String locale){
		  
		  this.uid =uid;
		  this.category = category;
		  this.longitude = longitude;
		  this.latitude =  latitude;
		  this.range = range;
		  this.severity =severity;
		  this.locale =locale;
		  //this.tweets = tweets;		  
		  
	  }
	  //uid
	  public void setUID(int uid) {
		  this.uid = uid;
	  }

	  public int getUID() {
		  return uid;
	  }
		//category
	  public void setCategory(int category) {
		  this.category = category;
	  }

	  public int getCategory() {
		  return category;
	  }
	  //range
	  public void setRange(int range) {
		  this.range = range;
	  }

	  public int getRange() {
		  return range;
	  }
	  //severity
	  public void setSeverity(int severity) {
		  this.severity = severity;
	  }

	  public int getSeverity() {
		  return severity;
	  }
	  //longitude
	  public void setLongitude(int longitude) {
		  this.longitude = longitude;
	  }

	  public int getLongitude() {
		  return longitude;
	  }
	  //latitude
	  public void setLatitude(int latitude) {
		  this.latitude = latitude;
	  }

	  public int getLatitude() {
		  return latitude;
	  }
	  //locale
	  public void setLocale(String locale) {
		  this.locale = locale;
	  }

	  public String getLocale() {
		  return locale;
	  }
	  /*
	  //tweets
	  public void setLocale(ArrayList<Tweet> tweets) {
		  this.tweets = tweets;
	  }

	  public ArrayList<Tweet> getTweets() {
		  return tweets;
	  }*/

}

