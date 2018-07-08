package com.hibernate.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CART")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_generator")
	@SequenceGenerator(name = "cart_generator", sequenceName="SEQ_CART_ID", allocationSize=1)
	@Column(name = "CART_ID")
	private Integer id;
	
	@Column(name = "TOTAL", columnDefinition = "number(10)")
	private Double total;
	
	@Column(name = "NAME")
	private String name;
	
	@OneToMany(mappedBy = "cart")
	private Set<Items> items;
	
	public Cart() {}

	public Cart(Integer id, Double total, String name, Set<Items> items) {
		super();
		this.id = id;
		this.total = total;
		this.name = name;
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Items> getItems() {
		return items;
	}

	public void setItems(Set<Items> items) {
		this.items = items;
	}	
}
