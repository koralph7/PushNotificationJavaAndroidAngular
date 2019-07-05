package com.pola;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.codehaus.jackson.JsonGenerationException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

@CrossOrigin("*")
@RestController
public class ApplicationController {

	
	private final String TOPIC = "updates";
	
	@Autowired
	NotifiService notifiService;
	
//	 @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
//	  public ResponseEntity<String> send() throws JSONException {
//	 
//	    JSONObject body = new JSONObject();
//	    body.put("to", "/topics/" + TOPIC);
////	    body.put("priority", "high");
//	 
//	    JSONObject notification = new JSONObject();
//	    notification.put("title", "JSA Notification");
//	    notification.put("body", "Happy Message!");
//	    
////	    JSONObject data = new JSONObject();
////	    data.put("Key-1", "JSA Data 1");
////	    data.put("Key-2", "JSA Data 2");
//	 
//	    body.put("notification", notification);
//	    body.put("data", data);
	
	
	@RequestMapping(value = "/send", method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	public ResponseEntity<String> sendNotif(@RequestBody NotificationDto notification) throws JSONException, JsonGenerationException {


		   
    
		
		JSONObject body = new JSONObject();
	    body.put("to", "/topics/" + notification.getTopic());

	 
	    JSONObject notificationn = new JSONObject();
	    notificationn.put("title", notification.getTitle());
	    notificationn.put("body", notification.getBody());
	    

	 
	    body.put("notification", notification);

        	
        HttpEntity<String> request = new HttpEntity<>(body.toString());
        
        System.out.println(request);
        
        CompletableFuture<String> pushNotification = notifiService.send(request);
        CompletableFuture.allOf(pushNotification).join();
        
        try {
            String firebaseResponse = pushNotification.get();

            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
}
		
        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
        
	}
	
//	 @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
//	  public ResponseEntity<String> send() throws JSONException {
//	 
//	    JSONObject body = new JSONObject();
//	    body.put("to", "/topics/" + TOPIC);
//	    body.put("priority", "high");
//	 
//	    JSONObject notification = new JSONObject();
//	    notification.put("title", "JSA Notification");
//	    notification.put("body", "Happy Message!");
//	    
//	    JSONObject data = new JSONObject();
//	    data.put("Key-1", "JSA Data 1");
//	    data.put("Key-2", "JSA Data 2");
//	 
//	    body.put("notification", notification);
//	    body.put("data", data);
//	 
//	/**
//	    {
//	       "notification": {
//	          "title": "JSA Notification",
//	          "body": "Happy Message!"
//	       },
//	       "data": {
//	          "Key-1": "JSA Data 1",
//	          "Key-2": "JSA Data 2"
//	       },
//	       "to": "/topics/JavaSampleApproach",
//	       "priority": "high"
//	    }
//	*/
//	 
//	    HttpEntity<String> request = new HttpEntity<>(body.toString());
//	 
//	    CompletableFuture<String> pushNotification = notifiService.send(request);
//	    CompletableFuture.allOf(pushNotification).join();
//	 
//	    try {
//	      String firebaseResponse = pushNotification.get();
//	      
//	      return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
//	    } catch (InterruptedException e) {
//	      e.printStackTrace();
//	    } catch (ExecutionException e) {
//	      e.printStackTrace();
//	    }
//	 
//	    return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
//	  }
//	
//	
//	
//	
	
	
	
	
	
}
