package com.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "EMPLOYEE2")
public class Employee2 {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee2_generator")
	@SequenceGenerator(name = "employee2_generator", sequenceName="SEQ_EMPLOYEE2_ID", allocationSize=1)
	@Column(name = "EMP_ID")
	private Integer id;

	@Column(name = "EMP_NAME")
	private String name;
	
	@Column(name = "EMP_SALARY", columnDefinition = "number(10,3)")
	private Double salary;
	
	@OneToOne(mappedBy = "employee")
	@Cascade(value = CascadeType.ALL)
	private Address2 address;
	
	public Employee2() {}

	public Employee2(String name, Double salary) {		
		this.name = name;
		this.salary = salary;
	}

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

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Address2 getAddress() {
		return address;
	}

	public void setAddress(Address2 address) {
		this.address = address;
	}	
	
	@Override
	public String toString() {
		return "Employee2 [id=" + id + ", name=" + name + ", salary=" + salary + ", address=" + address + "]";
	}
}
