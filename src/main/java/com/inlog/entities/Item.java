package com.inlog.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "item")
public class Item extends BaseDataObject{
	@Id
	private String id;
	private String categoryName;
	private Double width;
	private Double height;
	private Integer quantity;
	private Double price;
	private String color;
	private String brand;
	private String shape;
	private String fabric;
	private String itemtype;
	private String itemName;
	private String quanityunit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getFabric() {
		return fabric;
	}
	public void setFabric(String fabric) {
		this.fabric = fabric;
	}
	public String getItemtype() {
		return itemtype;
	}
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getQuanityunit() {
		return quanityunit;
	}
	public void setQuanityunit(String quanityunit) {
		this.quanityunit = quanityunit;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + id + ", categoryName=" + categoryName
				+ ", width=" + width + ", height=" + height + ", quantity="
				+ quantity + ", price=" + price + ", color=" + color
				+ ", brand=" + brand + ", shape=" + shape + ", fabric="
				+ fabric + ", itemtype=" + itemtype + ", itemName=" + itemName
				+ ", quanityunit=" + quanityunit + ", getCreateUserId()="
				+ getCreateUserId() + ", getUpdateUserId()="
				+ getUpdateUserId() + ", getVersion()=" + getVersion()
				+ ", getCreatedAt()=" + getCreatedAt() + ", getLastModified()="
				+ getLastModified() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
