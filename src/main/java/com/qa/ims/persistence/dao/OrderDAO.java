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
		Integer quantity = resultSet.getInt("quantity");
		Double price = resultSet.getDouble("price");
		String fName = resultSet.getString("fName");
		String lName = resultSet.getString("lName");
		return new Order(id, quantity, price, fName, lName);
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
				ResultSet resultSet = statement
						.executeQuery("select orders.id, customers.id as custid, items.itemName, "
								+ "orderline.quantity, sum(quantity*\r\n" + "itemprice) as 'price for items'\r\n"
								+ "from orders\r\n" + "inner join customers, items, orderline;");) {

			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				Order order = new Order();
				List<Item> items = new ArrayList<>();
				while (resultSet.next()) {
					orders.add(modelFromResultSet(resultSet));
				}
				items.add(order);
			}
			return orders;

		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("select orders.id, customers.id as custid, items.itemName, "
								+ "orderline.quantity, sum(quantity*\r\n" + "itemprice) as 'price for items'\r\n"
								+ "from orders\r\n"
								+ "inner join customers, items, orderline ORDER BY id DESC LIMIT 1");) {
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
			statement.setLong(1, order.getCustId());
			statement.setLong(2, order.getOrderLineId());
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
