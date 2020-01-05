package com.bawei.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Goods implements Serializable{

	private Integer id;
	private String name;
	private BigDecimal price;
	private Double  sell;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Double getSell() {
		return sell;
	}
	public void setSell(Double sell) {
		this.sell = sell;
	}
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Goods(Integer id, String name, BigDecimal price, Double sell) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.sell = sell;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", sell=" + sell + "]";
	}
     
}
