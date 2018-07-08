package com.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE3")
public class Employee3 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ID")
	private int id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "DESIGNATION")
	private String designation;
	
	@Column(name = "SALARY")
	private int salary;
	
	@ManyToOne
	@JoinColumn(name = "DPT_ID")
	private Department3 department;
	
	public Employee3() {}

	public Employee3(String name, String designation, int salary, Department3 department) {		
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.department = department;
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department3 getDepartment() {
		return department;
	}

	public void setDepartment(Department3 department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee3 [id=" + id + ", name=" + name + ", designation=" + designation + ", salary=" + salary
				+ ", department=" + department.getId() + "]";
	}	
}
