package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
	
	@Test
	public void customerDefaultConstructor() {
		Customer customer = new Customer();
		
		assertNotNull(customer);
	}
	
	@Test
	public void customerGetId() {
		Long id = 1L;
		Customer customer = new Customer(id);
		
		assertEquals(id, customer.getId());
	}

	@Test
	public void customerGetName() {
		String fName = "jordan";
		String lName = "harrison";
		Customer customer = new Customer(fName, lName);
		
		assertEquals(fName, customer.getFirstName());
		assertEquals(lName, customer.getSurname());
	}
	
	@Test
	public void customerAllArgs() {
		String fName = "jordan";
		String lName = "harrison";
		Customer customer = new Customer(1L, fName, lName);
		
		assertNotNull(customer.getId());
		assertNotNull(customer.getFirstName());
		assertNotNull(customer.getSurname());
		
		assertEquals((Long)1L, customer.getId());
		assertEquals(fName, customer.getFirstName());
		assertEquals(lName, customer.getSurname());
	}
}
