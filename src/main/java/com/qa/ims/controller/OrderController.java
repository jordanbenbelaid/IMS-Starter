package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order>{

	
	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		CustomerDAO customerDAO = new CustomerDAO();
		
		LOGGER.info("Please enter an existing customer ID");
		Long custId = utils.getLong();
		Customer customer = customerDAO.read(custId);
		
		Order customerOrder = orderDAO.createOrderForCustomer(customer);
		Order order = orderDAO.create(customerOrder);
		
		LOGGER.info("Order created");
		return order;		
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		
		LOGGER.info("Please type 'add' or 'delete' to update an item in an order");
		String answer = utils.getString();
		if(answer.equalsIgnoreCase("add")) {
//			add item to order

			LOGGER.info("Please enter the ID of the order you want to add an item to");
			Long id = utils.getLong();
			LOGGER.info("Please enter the ID of the item you want to add");
			Long itemId = utils.getLong();
//			Item item = itemDAO.read(itemId);
			LOGGER.info("Please enter the quantity of the item you want to add");
			Long quantity = utils.getLong();
			
			Order order = orderDAO.update(new Order(id, itemId, quantity));
			LOGGER.info("Order created");
			return order;
			
		}else if (answer.equalsIgnoreCase("delete")){
//			delete item to order
			
			LOGGER.info("Please enter the ID of the order you want to delete an item from");
			Long id = utils.getLong();
			LOGGER.info("Please enter the ID of the item you want to delete from the order");
			Long itemId = utils.getLong();
//			Item item = itemDAO.read(itemId);
			
			Order order = orderDAO.deleteItemFromOrder(new Order(id, itemId));
			LOGGER.info("Order created");
			return order;
		}
		LOGGER.info("Invalid option");
		return null;
		
	}

	/**
	 * Deletes an existing order by the id of the order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}
