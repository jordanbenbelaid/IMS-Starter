package com.qa.ims.persistence.domain;

import java.util.ArrayList;

public class Order {

	private Long id;
	private Customer customer;
	private ArrayList<Integer> quantity = new ArrayList<Integer>();
	private ArrayList<Item> orderItems = new ArrayList<Item>();
	
	public Order() {}
	
	public Order(Long id, Customer customer) {
		this.setId(id);
		this.setCustomer(customer);
	}
	
	public Order(Long id, Customer customer, ArrayList<Item> orderItems, ArrayList<Integer> quantity) {
		this.setId(id);
		this.setCustomer(customer);
		this.setOrderItems(orderItems);
		this.setQuantity(quantity);
	}
	
	public Order(Long id, Customer customer, ArrayList<Item>orderItems) {
		this.setId(id);
		this.setCustomer(customer);
		this.setOrderItems(orderItems);
	}
	
	@Override
	public String toString() {
		return "id:" + id + " first name: " + customer.getFirstName() + " surname: " + customer.getSurname() 
		 + " items:" + orderItems ;
//		 + " quantity " + quantity;
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

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(ArrayList<Integer> quantity) {
		this.quantity = quantity;
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
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
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
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	
	
}
