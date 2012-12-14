package com.example.dangerzone;

public class DangerType{
	private String type;
	private String tweet;
	
	
	  public DangerType(String type, String tweet){
		  this.type = type;
		  this.tweet = tweet;
		  
	  }
	  public void setTweet(String tweet) {
		  this.tweet = tweet;
	  }

	  public String getTweet() {
		  return tweet;
	  }
	  public void setAuthor(String author) {
		  //this.author = author;
	  }
	  public void setType(String type){
		  this.type = type;
	  }
	  public String getType(){
		  return type;
	  }
	  //public String getAuthor() {
		  //return author;
	  //}



}
