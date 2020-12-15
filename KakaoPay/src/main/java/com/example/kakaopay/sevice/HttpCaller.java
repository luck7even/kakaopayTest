package com.example.kakaopay.sevice;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HttpCaller {
	
	HttpHeaders headers = new HttpHeaders();
	
	public HttpCaller() {
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Authorization", "KakaoAK 81f0b9d44a421411884cf68b7acc2070");
	}

	public String getRequest(Map<String, Object> parameters, API api) {
		HttpEntity<String> requestEntity = new HttpEntity<String>(UriComponentsBuilder.fromHttpUrl(api.getApiUri()).uriVariables(parameters).toUriString());

		try {
			ResponseEntity<String> response = new RestTemplate().exchange(UriComponentsBuilder.fromHttpUrl(api.getApiUri()).uriVariables(parameters).toUriString(),HttpMethod.GET,requestEntity, String.class);
			return response.getBody();
		} catch (HttpStatusCodeException exception) {
			return exception.getResponseBodyAsString();
		}
	}
	
	public String postRequestXurlencoded(MultiValueMap<String, String> body, API api) {
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
		log.info("body : " + body);
		try {
			ResponseEntity<String> response = new RestTemplate().postForEntity(api.getApiUri(),	requestEntity, String.class);
			System.out.println(response.getBody());
			log.info("response : " + response.getBody());
			return response.getBody();
		} catch (HttpStatusCodeException exception) {
			log.info("response : " + exception.getResponseBodyAsString());
			return exception.getResponseBodyAsString();
		}
	}
/*
	public String postRequest(Authentication authentication, String body, API uri) {

		HttpEntity<String> requestEntity = new HttpEntity<String>(body, getHeaders(authentication, uri));

		log.info("body : " + body);

		try {
			ResponseEntity<String> response = new RestTemplate().exchange(getRequestUri(authentication, null, uri),
				HttpMethod.POST,
				requestEntity, String.class);

			log.info("response : " + response.getBody());
			log.info("response Status : " + response.getStatusCodeValue());

			return response.getBody();
		} catch (HttpStatusCodeException exception) {
			log.info("response : " + exception.getResponseBodyAsString());
			return exception.getResponseBodyAsString();
		}
	}

	public String postRequestXurlencoded(Authentication authentication, MultiValueMap<String, String> body,
		ApiGroup uri) {

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body,
			getHeaders(authentication, uri));

		log.info("body : " + body);
		try {
			ResponseEntity<String> response = new RestTemplate().postForEntity(getRequestUri(authentication, null, uri),
				requestEntity, String.class);

			log.info("response : " + response.getBody());

			return response.getBody();
		} catch (HttpStatusCodeException exception) {
			log.info("response : " + exception.getResponseBodyAsString());
			return exception.getResponseBodyAsString();
		}
	}
	*/
}
