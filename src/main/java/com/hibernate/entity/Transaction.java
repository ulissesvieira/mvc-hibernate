package com.hibernate.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_generator")
	@SequenceGenerator(name = "transaction_generator", sequenceName="SEQ_TRANSACTION_ID", allocationSize=1)
	@Column(name = "TXN_ID")
	private Integer id;
	
	@Column(name = "TXN_DATE")
	private LocalDate date;
	
	@Column(name = "TXN_TOTAL", columnDefinition = "number(15,3)")
	private Double total;
	
	@OneToOne(mappedBy = "transaction", fetch = FetchType.LAZY)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Customer customer;
	
	public Transaction() {}	

	public Transaction(Integer id, LocalDate date, Double total, Customer customer) {
		super();
		this.id = id;
		this.date = date;
		this.total = total;
		this.customer = customer;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return id + ", " + total + ", " + customer.getName() + ", " + customer.getEmail() + ", "
				+ customer.getAddress();
	}
}
