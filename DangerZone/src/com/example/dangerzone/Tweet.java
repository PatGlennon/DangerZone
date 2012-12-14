package com.example.dangerzone;

public class Tweet{
	private String author;
	private String tweet;
	
	
	  public Tweet(String author, String tweet){
		  this.author = author;
		  this.tweet = tweet;
		  
	  }
	  public void setTweet(String tweet) {
		  this.tweet = tweet;
	  }

	  public String getTweet() {
		  return tweet;
	  }
	  public void setAuthor(String author) {
		  this.author = author;
	  }
	  public String getAuthor() {
		  return author;
	  }



}
