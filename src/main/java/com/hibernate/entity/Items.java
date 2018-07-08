package com.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMS")
public class Items {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "items_generator")
	@SequenceGenerator(name = "items_generator", sequenceName="SEQ_ITEMS_ID", allocationSize=1)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "ITEM_ID")
	private String itemId;
	
	@Column(name = "ITEM_TOTAL", columnDefinition = "number(10)")
	private Double itemTotal;
	
	@Column(name = "QUANTITY")
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "CART_ID", nullable = false)
	private Cart cart;
	
	public Items() {}

	public Items(String itemId, Double itemTotal, Integer quantity, Cart cart) {		
		this.itemId = itemId;
		this.itemTotal = itemTotal;
		this.quantity = quantity;
		this.cart = cart;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Double getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(Double itemTotal) {
		this.itemTotal = itemTotal;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}	
}
