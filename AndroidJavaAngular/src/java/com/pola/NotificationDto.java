 	package com.pola;

import java.io.Serializable;

public class NotificationDto implements Serializable {


	private String title;
	private String body;
	private String topic;
	
	
	public NotificationDto() {}
	
	
	public NotificationDto( String title, String body, String topic) {
		
		
		this.title = title;
		this.body = body;
		this.topic = topic;
		
	}
	
	
	
	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	@Override
	public String toString() {
		return "NotificationDto [ title=" + title + ", body=" + body + "]";
	}



	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}
	
	
	
}
