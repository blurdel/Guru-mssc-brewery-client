package com.blurdel.msscbreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blurdel.msscbreweryclient.web.model.BeerDto;


@SpringBootTest
class BreweryClientTest {

	@Autowired
	BreweryClient client;
	
	@Test
	void testGetBeer() {
		BeerDto dto = client.getBeerById(UUID.randomUUID());
		
		assertNotNull(dto);
	}

	@Test
	void testSaveNewBeer() {
		// given
		BeerDto dto = BeerDto.builder().beerName("New Beer").beerStyle("IPA").build();
		
		URI uri = client.saveNewBeer(dto);
		
		assertNotNull(uri);
		
		System.out.println(uri.toString());
	}
	
	@Test
	void testUpdateBeer() {
		// given
		BeerDto dto = BeerDto.builder().beerName("New Beer").beerStyle("IPA").build();
		
		client.updateBeer(UUID.randomUUID(), dto);		
	}
	
	@Test
	void deleteBeer() {
		client.deleteBeer(UUID.randomUUID());
	}
	
}
