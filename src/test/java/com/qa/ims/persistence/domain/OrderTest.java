package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}
	
	@Test
	public void orderDefaultConstructorTest() {
		Order order = new Order();
		
		assertNotNull(order);
	}
	
	@Test
	public void toStringTest() {
		Order order = new Order();
		Customer customer = new Customer(null, null);
		
		order.setCustomer(customer);
		
		String result = order.toString();
		
		String expected = "Order id: " + null + " first name: " + null + " surname: " + 
				null + " items:" + new ArrayList<Item>() + " quantity " + new ArrayList<Integer>() + " total price: £" + "0.00";
	
		assertEquals(result, expected);
	}
	
	@Test 
	public void costOfOrderTest() {
		//given
		//made up order list to check functionality
		ArrayList<Item> fakeOrderItems = new ArrayList<Item>();
		ArrayList<Integer> fakeQuantities = new ArrayList<Integer>();
		
		fakeOrderItems.add(new Item("Crisps", 2.0));
		fakeOrderItems.add(new Item("Drink", 1.0));
		
		fakeQuantities.add(3);
		fakeQuantities.add(5);

		Order order = new Order(null, null, fakeOrderItems, fakeQuantities);
		
		//when
		String result = order.costOfOrder();
		
		//then
		String expected = "11.00";
		
		assertEquals(expected, result);
	}
	
	@Test
	public void orderGetIdTest() {
		Long id = 1L;
		Order order = new Order(1L, 0L);

		assertEquals(id, order.getId());
	}
	
	@Test
	public void orderGetItemIdTest() {
		Long itemId = 1L;
		Order order = new Order(0L, 1L);
		
		assertEquals(itemId, order.getItemId());
	}
	
	@Test
	public void orderGetQuantityTest() {
		Long quant = 3L;
		Order order = new Order(0L, 0L, 3L);
		
		assertEquals(quant, order.getQuantity());
	}
	
	@Test
	public void orderGetCustomerTest() {
		Customer customer = new Customer();
		Order order = new Order(customer);
		
		assertEquals(customer, order.getCustomer());
	}
	
	@Test
	public void orderGetQuantitiesListTest() {
		ArrayList<Integer> quantities = new ArrayList<Integer>();
		Order order = new Order(0L, null, null, quantities);
		
		assertEquals(quantities, order.getQuantities());
	}
	
	@Test
	public void orderGetOrderItemsListTest() {
		ArrayList<Item> orderItems = new ArrayList<Item>();
		Order order = new Order(0L, null, orderItems);
		
		assertEquals(orderItems, order.getOrderItems());
	}
	
	@Test
	public void orderArgsTest() {
		Customer customer = new Customer();
		Order order = new Order(1L, customer);
		
		assertNotNull(order.getId());
		assertNotNull(order.getCustomer());
		
		assertEquals((Long)1L, order.getId());
		assertEquals(customer, order.getCustomer());

	}
}
