package com.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM2")
public class Item2 {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item2_generator")
	@SequenceGenerator(name = "item2_generator", sequenceName="SEQ_ITEM2_ID", allocationSize=1)
	@Column(name = "ITEM_ID")
	private Integer id;
	
	@Column(name = "ITEM_PRICE", columnDefinition = "number(10,3)", nullable = false)
	private Double price;
	
	@Column(name = "ITEM_DESC")
	private String description;	
	
	public Item2() {}

	public Item2(Double price, String description) {		
		this.price = price;
		this.description = description;		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
