package com.hibernate.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CART2")
public class Cart2 {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart2_generator")
	@SequenceGenerator(name = "cart2_generator", sequenceName="SEQ_CART2_ID", allocationSize=1)
	@Column(name = "CART_ID")
	private Integer id;
	
	@Column(name = "CART_TOTAL", nullable = false, columnDefinition = "number(10,3)")
	private Double total;
	
	@ManyToMany(targetEntity = Item2.class, cascade = {CascadeType.ALL})
	@JoinTable(name = "CAR_ITEM_2",
			joinColumns = {@JoinColumn(name = "CART_ID")},
			inverseJoinColumns = {@JoinColumn(name = "ITEM_ID")})
	private Set<Item2> items;
	
	public Cart2() {}

	public Cart2(Double total, Set<Item2> items) {		
		this.total = total;
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

	public Set<Item2> getItems() {
		return items;
	}

	public void setItems(Set<Item2> items) {
		this.items = items;
	}	
}
