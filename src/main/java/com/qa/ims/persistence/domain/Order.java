package com.qa.ims.persistence.domain;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Order {

	private Long id;
	private Long itemId;
	private Long quantity;
	private Customer customer;
	private ArrayList<Integer> quantities = new ArrayList<Integer>();
	private ArrayList<Item> orderItems = new ArrayList<Item>();
	
	public Order() {}
	
	public Order(Long Id, Long itemId, Long quantity) {
		this.setId(Id);
		this.setItemId(itemId);
		this.setQuantity(quantity);
	}
	public Order(Long Id, Long itemId) {
		this.setId(Id);
		this.setItemId(itemId);
	}
	
	public Order(Customer customer) {
		this.setCustomer(customer);
	}
	
	public Order(Long id, Customer customer) {
		this.setId(id);
		this.setCustomer(customer);
	}
	
	public Order(Long id, Customer customer, ArrayList<Item> orderItems, ArrayList<Integer> quantities) {
		this.setId(id);
		this.setCustomer(customer);
		this.setOrderItems(orderItems);
		this.setQuantities(quantities);
	}
	
	public Order(Long id, Customer customer, ArrayList<Item>orderItems) {
		this.setId(id);
		this.setCustomer(customer);
		this.setOrderItems(orderItems);
	}
	
	@Override
	public String toString() {
		return "Order id: " + id + " first name: " + customer.getFirstName() + " surname: " + customer.getSurname() 
		 + " items:" + orderItems + " quantity " + quantities + " total price: £" + costOfOrder();
	}

		//calculating cost of order, each item price gets multiplied by corresponding quantity
	private String costOfOrder() {
		Double total = 0.0;
		for(int i = 0; i<orderItems.size(); i++) {
			total += orderItems.get(i).getPrice()*quantities.get(i);
		}
		NumberFormat formatter = new DecimalFormat("#0.00");     
		return formatter.format(total);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<Integer> getQuantities() {
		return quantities;
	}

	public void setQuantities(ArrayList<Integer> quantities) {
		this.quantities = quantities;
	}

	public ArrayList<Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(ArrayList<Item> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + ((quantities == null) ? 0 : quantities.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (quantities == null) {
			if (other.quantities != null)
				return false;
		} else if (!quantities.equals(other.quantities))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	
}
