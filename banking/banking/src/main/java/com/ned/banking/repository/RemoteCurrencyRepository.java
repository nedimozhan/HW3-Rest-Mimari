package com.ned.banking.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;

@Component
@Qualifier("RemoteCurrencyRepository")
@Scope("prototype") 
public class RemoteCurrencyRepository implements IRemoteCurrencyRepository{
	
	RestTemplate client;
	org.springframework.http.HttpHeaders headers;
	HttpEntity<?> requestEntity;
	ObjectMapper objectMapper;
	
	@Override
	public float currencyUSD_TL() {
		
		client = new RestTemplate();
		headers = new org.springframework.http.HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("authorization", "apikey 7MZD4WQL0wL8GlGixqrZOy:4frUSNRHQZ4Y10PgbkCH5g");		
		requestEntity = new HttpEntity<>(headers);
		objectMapper=new ObjectMapper();
		
		String urlString = "https://api.collectapi.com/economy/singleCurrency?int=1&tag=USD";
		ResponseEntity<String> response = client.exchange(urlString, HttpMethod.GET,requestEntity,String.class);
		String r = response.getBody();
		
		
		float curr = 0;
		try {
			JsonNode node = objectMapper.readTree(r);
			//JsonArray array = node.get("result");
			//String result = node.get("result").get(0); //.get(0).get("selling").toString();
			JsonNode node2 = node.get("result").get(0);
			//System.out.println(node2.toString());
			//System.out.println(node2.get("buying"));
			curr = Float.parseFloat(node2.get("buying").toString()); 
			System.out.println(curr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return curr;
	}

	@Override
	public float currencyGOLD_TL() {
		
		client = new RestTemplate();
		headers = new org.springframework.http.HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("authorization", "apikey 7MZD4WQL0wL8GlGixqrZOy:4frUSNRHQZ4Y10PgbkCH5g");		
		requestEntity = new HttpEntity<>(headers);
		objectMapper=new ObjectMapper();
		String urlString = "https://api.collectapi.com/economy/goldPrice";
		ResponseEntity<String> response = client.exchange(urlString, HttpMethod.GET,requestEntity,String.class);
		String r = response.getBody();
		
		float curr = 0;
		try {
			JsonNode node = objectMapper.readTree(r);
			System.out.println("X");
			System.out.println(node.get("result").get(1).toString());
			String value = node.get("result").get(1).get("buying").toString();
			curr = Float.parseFloat(value); 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return curr;
	}
}
