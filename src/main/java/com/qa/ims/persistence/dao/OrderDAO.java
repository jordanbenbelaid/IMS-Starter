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
		Long id = resultSet.getLong("id");
		Long custId = resultSet.getLong("custid");
		String fName = resultSet.getString("first_name");
		String lName = resultSet.getString("surname");
		Customer customer = new Customer(custId, fName, lName);
		ArrayList<Item> items = new ArrayList<Item>();

		return new Order(id, customer, items);

	}

//	public Order modelFromResultSetCreateOrder(ResultSet resultSet) throws SQLException {
//		Long id = resultSet.getLong("id");
//		Long custId = resultSet.getLong("custid");
//		return new Order(id, custId);
//	}

	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select orders.id as orderid, "
						+ "customers.id as custid, customers.first_name, customers.surname from orders "
						+ "inner join customers on customers.id = orders.custid");) {

			List<Order> orders = new ArrayList<>();

			while (resultSet.next()) {

				ArrayList<Item> items = new ArrayList<>();
				ArrayList<Integer> quantities = new ArrayList<Integer>();
				Statement statement2 = connection.createStatement();
				ResultSet resultSet2 = statement2
						.executeQuery("select * from orderline " + "inner join orders on orderline.orderid = orders.id "
								+ "inner join items on items.id = orderline.itemid " + "where orderline.orderid = "
								+ resultSet.getLong("orderid"));

				while (resultSet2.next()) {

					items.add(new Item(resultSet2.getLong("itemId"), resultSet2.getString("itemname"),
							resultSet2.getDouble("itemprice")));

					quantities.add(resultSet2.getInt("quantity"));
				}
				orders.add(groupOrder(resultSet, items, quantities));
			}
			return orders;

		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	// method for grouping items in an order
	public Order groupOrder(ResultSet resultSet, ArrayList<Item> items, ArrayList<Integer> quantities)
			throws SQLException {
		Long id = resultSet.getLong("orderid");

		String custFName = resultSet.getString("first_name");
		String surname = resultSet.getString("surname");
		Customer customer = new Customer(custFName, surname);

		return new Order(id, customer, items, quantities);
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders "
								+ "inner join customers on customers.id = orders.custid " 
								+ "ORDER BY orders.id DESC LIMIT 1");) {
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
				PreparedStatement statement = connection.prepareStatement("INSERT INTO orders(custid) VALUES (?)");) {

			statement.setLong(1, order.getCustomer().getId()); // check
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Order createOrderForCustomer(Customer customer) {
		return new Order(customer);
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("select * from orders "
						+ "inner join customers on customers.id = orders.custid");) {
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
						.prepareStatement("INSERT INTO orderline (orderid, itemid, quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, order.getId());
			statement.setLong(2, order.getItemId());
			statement.setLong(3, order.getQuantity());
			statement.executeUpdate();
			return read(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order deleteItemFromOrder(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("delete from orderline where orderid = ? and itemid = ?");) {
			statement.setLong(1, order.getId());
			statement.setLong(2, order.getItemId());
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
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders where orders.id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}
