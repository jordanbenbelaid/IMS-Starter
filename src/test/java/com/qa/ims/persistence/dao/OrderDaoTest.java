package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDaoTest {

	private OrderDAO DAO;

	@Before
	public void setup() {
		DAO = new OrderDAO();
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		// given
		Order order = new Order(2L, new Customer(1L, "jordan", "harrison"), new ArrayList<Item>());
		// when
		Order result = DAO.create(order);
		// then
		Order expected = new Order(2L, new Customer(1L, "jordan", "harrison"), new ArrayList<Item>());

		assertEquals(expected, result);
	}
	
	@Test
	public void testCreateException() {
		assertNull(DAO.create(null));
	}

	@Test
	public void testCreateOrderForCustomer() {
		// given
		Customer customer = new Customer(1L, "jordan", "harrison");

		// when
		Order result = DAO.createOrderForCustomer(customer);

		// then
		Order expected = new Order(null, new Customer(1L, "jordan", "harrison"), new ArrayList<Item>());
		assertEquals(expected, result);

	}

	@Test
	public void testReadLatest() {
		// given

		// when
		Order result = DAO.readLatest();

		// then
		Order expected = new Order(1L, new Customer(1L, "jordan", "harrison"), new ArrayList<Item>());

		assertEquals(expected, result);
	}
	
	@Test
	public void testReadLatestException() {
		DAO.delete(1L);
		assertNull(DAO.readLatest());
	}

	@Test
	public void testReadAll() {
		// given
		ArrayList<Item> fakeOrderItems = new ArrayList<Item>();
		ArrayList<Integer> fakeQuantities = new ArrayList<Integer>();

		fakeOrderItems.add(new Item(1L, "crisps", 3.5));
		fakeQuantities.add(1);

		// when
		List<Order> result = DAO.readAll();

		// then
		List<Order> expected = new ArrayList<Order>();
		expected.add(new Order(1L, new Customer("jordan", "harrison"), fakeOrderItems, fakeQuantities));

		assertEquals(expected, result);

	}

	@Test
	public void testRead() {
		// given

		// when
		Order result = DAO.read(1L);

		// then
		Order expected = new Order(1L, new Customer(1L, "jordan", "harrison"), new ArrayList<Item>());

		assertEquals(expected, result);
	}
	
	@Test
	public void testReadException() {
		DAO.delete(1L);
		assertNull(DAO.read(1L));
	}

	@Test
	public void testUpdateAddOrder() {
		// given
		ArrayList<Item> OrderItems = new ArrayList<Item>();

		Order order = new Order(1L, 1L, 1L);
		// when
		Order result = DAO.update(order);
		// then
		Order expected = new Order(1L, new Customer(1L, "jordan", "harrison"), OrderItems);

		assertEquals(expected, result);
	}
	
	@Test
	public void testUpdateException() {
		assertNull(null, DAO.update(null));
	}

	@Test
	public void testDeleteItemFromOrderTest() {
		// given
		ArrayList<Item> OrderItems = new ArrayList<Item>();

		Order order = new Order(1L, 1L, 1L);
		// when
		Order result = DAO.deleteItemFromOrder(order);
		// then
		Order expected = new Order(1L, new Customer(1L, "jordan", "harrison"), OrderItems);

		assertEquals(expected, result);
	}
	
	@Test
	public void testDeleteItemFromOrderException() {
		assertNull(null, DAO.deleteItemFromOrder(null));
	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
	
}
