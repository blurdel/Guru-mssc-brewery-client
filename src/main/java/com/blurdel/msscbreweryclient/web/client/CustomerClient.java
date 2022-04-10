package com.blurdel.msscbreweryclient.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.blurdel.msscbreweryclient.web.model.CustomerDto;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class CustomerClient {

	public final String CUST_PATH_V1 = "/api/v1/customer/";
	private String apihost;

	private final RestTemplate restTemplate;

	
	public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
		super();
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public CustomerDto getCustomerById(UUID custId) {
		return restTemplate.getForObject(apihost + CUST_PATH_V1 + custId, CustomerDto.class);
	}

	public URI saveNewCustomer(CustomerDto custDto) {
		return restTemplate.postForLocation(apihost + CUST_PATH_V1, custDto);
	}
	
	public void updateCustomer(UUID custId, CustomerDto custDto) {
		restTemplate.put(apihost + CUST_PATH_V1 + custId, custDto);
	}
	
	public void deleteCustomer(UUID custId) {
		restTemplate.delete(apihost + CUST_PATH_V1 + custId);
	}
	
	public void setApihost(String apihost) {
		this.apihost = apihost;
	}
	
}
