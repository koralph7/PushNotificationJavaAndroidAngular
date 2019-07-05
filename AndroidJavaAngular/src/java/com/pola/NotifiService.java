package com.pola;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotifiService {

	
	private static final String FIREBASE_SERVER_KEY = "AAAA0nXVxB4:APA91bFjrWG_yYMRZvQY61R21T_l6MutuZWlkC3i80gFYD9xsOYO6o2ZJdcFHJ9gCJgaCdpTDaNR3uES_N8I_dH79g9LhHeTfswuxumE5YUXEyn78ZNXBqHnSh0SwPcLgkw4IB2Jnr23";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String>entity) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new RequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
	    interceptors.add(new RequestInterceptor("Content-Type", "application/json"));
	    restTemplate.setInterceptors(interceptors);
	 
	    String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
	 
	    return CompletableFuture.completedFuture(firebaseResponse);
	}
}
