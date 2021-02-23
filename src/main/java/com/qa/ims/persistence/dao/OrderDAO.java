package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("orderid");
//		String fName = resultSet.getString("fName");
//		String lName = resultSet.getString("lName");
		Customer customer = new Customer("Sam", "abdullah");
		ArrayList<Integer> quantity = new ArrayList<Integer>();
		ArrayList<Item> items = new ArrayList<Item>();

		return new Order(id, customer, items, quantity);

	}

	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"select orders.id as orderid, customers.id as custid, customers.first_name, orderline.itemid, orderline.quantity from orders "
								+ "inner join customers on customers.id = orders.custid "
								+ "inner join orderline on orderline.orderid = orders.id");) {

			System.out.println("TEST1");
			List<Order> orders = new ArrayList<>();

			while (resultSet.next()) {
				
				Long custid = resultSet.getLong("custid");
				ArrayList<Item> items = new ArrayList<>();
				ArrayList<Integer> quantities = new ArrayList<>();
				Statement statement2 = connection.createStatement();
				ResultSet resultSet2 = statement2.executeQuery("select * from orderline inner join orders on orderline.orderid = orders.id");
				
				while (resultSet2.next()) {
					System.out.println("TEST2");
					items.add(new Item(resultSet.getLong("itemId"), resultSet.getString("name"), resultSet.getDouble("price")));
					System.out.println("TEST5");
//					quantities.add(resultSet.getInt("quantity"));
//					System.out.println(resultSet2);
				}
				orders.add(groupOrder(resultSet, items, quantities));
			}
			return orders;

		} catch (SQLException e) {
			System.out.println("TEST3");
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	// method for grouping items in an order
	public Order groupOrder(ResultSet resultSet, ArrayList<Item> items, ArrayList<Integer> quantities)
			throws SQLException {
		Long id = resultSet.getLong("orderid");
		Customer customer = new Customer("Sam", "abdullah");
		System.out.println("TEST4");

		return new Order(id, customer, items, quantities);
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"select orders.id as orderid, customers.id as custid, customers.first_name, orderline.itemid, orderline.quantity from orders"
								+ "inner join customers on customers.id = orders.custid"
								+ "inner join orderline on orderline.orderid = orders.id "
								+ "ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/*
	 * Creates a Order in the database
	 * 
	 * @param order - takes in a order object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(custid, orderlineid) VALUES (?, ?)");) {
//			statement.setLong(1, order.getCustId());
//			statement.setLong(2, order.getOrderLineId());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("select orders.id, customers.id as custid, items.itemName, "
								+ "orderline.quantity, sum(quantity*\r\n" + "itemprice) as 'price for items'\r\n"
								+ "from orders\r\n" + "inner join customers, items, orderline; WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a order in the database
	 * 
	 * @param order - takes in a order object, the id field will be used to update
	 *              that order in the database
	 * @return
	 */
	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET custid = ?, orderlineid = ? WHERE id = ?");) {
//			statement.setLong(1, order.getCustId());
//			statement.setLong(2, order.getOrderLineId());
			statement.setLong(3, order.getId());
			statement.executeUpdate();
			return read(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a order in the database
	 * 
	 * @param id - id of the order
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM orders, orderline WHERE orders.id & orderline.id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}
