package com.qa.ims.persistence.domain;

import java.text.NumberFormat;

public class Item {

		private Long id;
		private String name;
		private Double price;
		
		public Item(String name, Double price) {
			this.setName(name);
			this.setPrice(price);
		}
		
		public Item(Long id, String name, Double price) {
			this.setId(id);
			this.setName(name);
			this.setPrice(price);
		}
		
		public Item(Long id) {
			this.setId(id);
		}

		public Item() {
			
		}
		
		@Override
		public String toString() {
			return "id: " + id + " Item Name: " + name + " Price: £" + getPrice();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((price == null) ? 0 : price.hashCode());
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
			Item other = (Item) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (price == null) {
				if (other.price != null)
					return false;
			} else if (!price.equals(other.price))
				return false;
			return true;
		}
		
		
}