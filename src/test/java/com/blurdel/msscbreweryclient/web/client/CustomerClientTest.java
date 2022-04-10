package com.blurdel.msscbreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blurdel.msscbreweryclient.web.model.CustomerDto;


@SpringBootTest
class CustomerClientTest {

	@Autowired
	CustomerClient client;
	
	@Test
	void testGetCustomerById() {
		// given
		CustomerDto dto = client.getCustomerById(UUID.randomUUID());
		
		assertNotNull(dto);
	}

	@Test
	void testSaveNewCustomer() {
		// given
		CustomerDto dto = CustomerDto.builder().name("New Customer").build();
		
		URI uri = client.saveNewCustomer(dto);
		
		assertNotNull(uri);
		
		System.out.println(uri.toString());
	}

	@Test
	void testUpdateCustomer() {
		// given
		CustomerDto dto = CustomerDto.builder().name("New Customer").build();
		
		client.updateCustomer(UUID.randomUUID(), dto);
	}

	@Test
	void testDeleteCustomer() {
		client.deleteCustomer(UUID.randomUUID());
	}

}
