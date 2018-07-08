package com.hibernate.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENT3")
public class Department3 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DPT_ID")
	private int id;
	
	@Column(name = "NAME", nullable = false,  unique = true)
	private String name;
	
	@OneToMany(mappedBy = "department")	
	private List<Employee3> employee;
	
	public Department3() {}

	public Department3(String name, List<Employee3> employee) {		
		this.name = name;
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee3> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee3> employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Department3 [id=" + id + ", name=" + name + "]";
	}	
}
