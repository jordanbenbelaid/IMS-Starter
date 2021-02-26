package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

		@Mock
		private Utils utils;
		
		@Mock
		private OrderDAO orderDao;
		
		@Mock
		private CustomerDAO custDao;
		
		@InjectMocks
		private OrderController controller;
		
		@Test
		public void testCreate() {
			Customer customer = new Customer(1L, "sam", "smith");
		//given
		//mocks getting cust id without reading sql db
		Mockito.when(utils.getLong()).thenReturn(1L);
		//returning fake customer without sql call
//		Mockito.when(custDao.read(1L)).thenReturn(customer);
		//mock fake order with fake customer made
		Mockito.when(orderDao.createOrderForCustomer(Mockito.any())).thenReturn(new Order(customer));
		//create order for customer method, creates fake order for fake customer
		Mockito.when(orderDao.create(Mockito.any())).thenReturn(new Order(1L, customer, new ArrayList<Item>()));
		
		//when
		Order result = controller.create();
		
		//then
		Order expected = new Order(1L, customer, new ArrayList<Item>());
		
		assertEquals(expected, result);
		}
		
		@Test
		public void testReadAll() {
			//given
			//list of fake orders
			List<Order> orders = new ArrayList<>();
			orders.add(new Order(1L, 1L));

			Mockito.when(orderDao.readAll()).thenReturn(orders);

			//when
			//readall result based on fake data generated
			List<Order> result = controller.readAll();
			
			//then
			//what we expect it to return
			List<Order> expected = new ArrayList<Order>();
			expected.add(new Order(1L, 1L));
			
			assertEquals(expected, result);

		}
		
		@Test
		public void testAddItemToOrder(){
		//given
		//mocking input of add if statement
		Mockito.when(utils.getString()).thenReturn("add");
		//mocking the long for id, itemid and quantity with fake details
		Mockito.when(utils.getLong()).thenReturn(1L);
		//mocking fake order, adding fake item to fake order
		Mockito.when(orderDao.update(Mockito.any())).thenReturn(new Order(1L, null, new ArrayList<Item>()));
		
		//when
		Order result = controller.update();
		
		//then
		Order expected = new Order(1L, null, new ArrayList<Item>());
		
		assertEquals(expected, result);
		}
		
		@Test
		public void testDeleteItemToOrder(){
		//given
		//mocking input of delete statement
		Mockito.when(utils.getString()).thenReturn("delete");
		//mocking the long for id, itemid and quantity with fake details
		Mockito.when(utils.getLong()).thenReturn(1L);
		//mocking fake order, deleting fake item from fake order
		Mockito.when(orderDao.deleteItemFromOrder(Mockito.any())).thenReturn(new Order(1L, null, new ArrayList<Item>()));
		
		//when
		Order result = controller.update();
		
		//then
		Order expected = new Order(1L, null, new ArrayList<Item>());
		
		assertEquals(expected, result);
		}
		
		@Test
		public void testInvalidOption() {
			//given
		Mockito.when(utils.getString()).thenReturn("");	
		
		//when
		Order result = controller.update();
		
		//then
		assertNull(result);
		}
		
		@Test
		public void testDelete() {
		//given
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(orderDao.delete(1L)).thenReturn(1);	
		
		//when
		int result = controller.delete();
		
		//then
		//assert false because using 0, indicates nothing happened
		assertEquals(1, result);	
		}
}
