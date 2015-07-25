package com.inlog.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
public class Order extends BaseDataObject {
	@Id
	private String id;
	private List<String> itemList;
	private List<Item> itemDataList;
	private String orderSatus;

	public String getOrderSatus() {
		return orderSatus;
	}

	public void setOrderSatus(String orderSatus) {
		this.orderSatus = orderSatus;
	}

	public List<Item> getItemDataList() {
		return itemDataList;
	}

	public void setItemDataList(List<Item> itemDataList) {
		this.itemDataList = itemDataList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getItemList() {
		return itemList;
	}

	public void setItemList(List<String> itemList) {
		this.itemList = itemList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", itemList=" + itemList + "]";
	}

}
