package com.pola;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

public class RequestInterceptor implements ClientHttpRequestInterceptor {

	private final String hName;
	private final String hValue;
	
	
	
	public RequestInterceptor(String hName, String hValue) {
		
		this.hName = hName;
		this.hValue = hValue;
	}



	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpRequest wrap = new HttpRequestWrapper(request);
		wrap.getHeaders().set(hName, hValue);
		return execution.execute(wrap, body);
	}

}
