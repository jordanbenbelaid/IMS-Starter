package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}
	
	@Test
	public void itemDefaultConstructorTest() {
		Item item = new Item();
		
		assertNotNull(item);
	}
	
	@Test
	public void itemGetIdTest() {
		Long id = 1L;
		Item item = new Item(id);
		
		assertEquals(id, item.getId());
	}
	
	@Test
	public void itemGetNameTest() {
		String name = "John";
		//0.0 fills constructor argument as 0, as it is not being tested here
		Item item = new Item(name,0.0);
		
		assertEquals(name, item.getName());
	}
	
	@Test
	public void itemGetPriceTest() {
		Double price = 2.5;
		Item item = new Item(" ", price);
		
		assertEquals(price, item.getPrice());
	}
	
	@Test
	public void itemAllArgsConstructorTest() {
		Item item = new Item(1L, "John", 2.5);
		
		assertNotNull(item.getId());
		assertNotNull(item.getName());
		assertNotNull(item.getPrice());
		
		assertEquals(item.getId(), (Long)1L);
		assertEquals(item.getName(), "John");
		assertEquals(item.getPrice(), (Double)2.5);
	}
	
}
