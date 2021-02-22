package com.qa.ims.persistence.domain;

public class Order {

		private Long id;
		private Long custId;
		private Customer customer;
		private Item item;
		private Long orderLineId;
		
		public Order (Long id, Long orderLineId, Integer quantity) {
			this.setId(id);
			this.setCustId(custId);
//			this.setQuantity(quantity);
		}

		public Order(Long custId, Long orderLineId) {
			this.setCustId(custId);
			this.setOrderLineId(orderLineId);

		}
		
		public Order(Long id, Long custId, Long orderLineId) {
			this.setId(id);
			this.setCustId(custId);
			this.setOrderLineId(orderLineId);
		}
		
		public Order(Long custId, Long orderLineId, Customer customer, Item item) {
			this.setCustId(custId);
			this.setOrderLineId(orderLineId);
			this.setCustomer(customer);
			this.setItem(item);
		}

		public Order(Long id, Long custId, Long orderLineId, Customer customer, Item item) {
			this.setId(id);
			this.setCustId(custId);
			this.setOrderLineId(orderLineId);
			this.setCustomer(customer);
			this.setItem(item);
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getCustId() {
			return custId;
		}

		public void setCustId(Long custId) {
			this.custId = custId;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}

		public Long getOrderLineId() {
			return orderLineId;
		}

		public void setOrderLineId(Long orderLineId) {
			this.orderLineId = orderLineId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((custId == null) ? 0 : custId.hashCode());
			result = prime * result + ((customer == null) ? 0 : customer.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((item == null) ? 0 : item.hashCode());
			result = prime * result + ((orderLineId == null) ? 0 : orderLineId.hashCode());
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
			if (custId == null) {
				if (other.custId != null)
					return false;
			} else if (!custId.equals(other.custId))
				return false;
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
			if (item == null) {
				if (other.item != null)
					return false;
			} else if (!item.equals(other.item))
				return false;
			if (orderLineId == null) {
				if (other.orderLineId != null)
					return false;
			} else if (!orderLineId.equals(other.orderLineId))
				return false;
			return true;
		}
		
		
}
