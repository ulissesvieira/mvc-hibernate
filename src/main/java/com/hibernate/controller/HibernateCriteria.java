package com.hibernate.controller;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.websocket.server.ServerEndpointConfig.Builder;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hibernate.entity.Department3;
import com.hibernate.entity.Employee2;
import com.hibernate.entity.Employee3;

@Controller
@RequestMapping("/criteria")
public class HibernateCriteria {
	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping("/t1")
	public String t1() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}

		Transaction tx = session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee2> cq = builder.createQuery(Employee2.class);

		Root<Employee2> root = cq.from(Employee2.class);
		cq.select(root);
		Query<Employee2> query = session.createQuery(cq);
		List<Employee2> lst1 = query.list();

		for (Employee2 e : lst1) {
			System.err.println("ID = " + e.getId() + ", Zipcode = " + e.getAddress().getZipcode());
		}

		tx.rollback();

		sessionFactory.close();

		return "greetings";
	}

	@RequestMapping("/t2")
	public String t2() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}

		Transaction tx = session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee3> query = builder.createQuery(Employee3.class);

		Root<Employee3> root = query.from(Employee3.class);
		query.select(root);

		Query<Employee3> q = session.createQuery(query);
		List<Employee3> lst1 = q.getResultList();
		for (Employee3 e : lst1) {
			System.err.println(e);
		}

		CriteriaQuery<Department3> q1 = builder.createQuery(Department3.class);
		Root<Department3> rootDep = q1.from(Department3.class);
		q1.select(rootDep);

		Query<Department3> qd1 = session.createQuery(q1);
		List<Department3> lst2 = qd1.getResultList();
		for (Department3 d : lst2) {
			System.err.println(d);
		}

		CriteriaQuery<String> q2 = builder.createQuery(String.class);
		Root<Employee3> r1 = q2.from(Employee3.class);
		q2.select(r1.get("name"));
		Query q3 = session.createQuery(q2);
		List<String> lst3 = q3.list();
		for (String name : lst3) {
			System.err.println(name);
		}

		CriteriaQuery<Object[]> q4 = builder.createQuery(Object[].class);
		Root<Employee3> r4 = q4.from(Employee3.class);
		q4.multiselect(root.get("name"), root.get("designation"));
		Query<Object[]> qr4 = session.createQuery(q4);
		List<Object[]> rs4 = qr4.list();
		for (Object[] o : rs4) {
			System.err.println("Name: " + o[0]);
			System.err.println("Designation: " + o[1]);
		}

		tx.rollback();
		sessionFactory.close();

		return "greetings";
	}

	@RequestMapping("/t3")
	public String t3() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}

		Transaction tx = session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();

		// count
		CriteriaQuery<Long> cq1 = builder.createQuery(Long.class);
		Root<Employee3> r1 = cq1.from(Employee3.class);
		cq1.select(builder.count(r1));
		Query<Long> q1 = session.createQuery(cq1);
		long count = q1.getSingleResult();
		System.err.println("Count = " + count);

		// max
		CriteriaQuery<Integer> cq2 = builder.createQuery(Integer.class);
		Root<Employee3> r2 = cq2.from(Employee3.class);
		cq2.select(builder.max(r2.get("salary")));
		Query<Integer> q2 = session.createQuery(cq2);
		int maxSalary = q2.getSingleResult();
		System.err.println("Max Salary = " + maxSalary);

		// avg
		CriteriaQuery<Double> cq3 = builder.createQuery(Double.class);
		Root<Employee3> r3 = cq3.from(Employee3.class);
		cq3.select(builder.avg(r3.get("salary")));
		Query<Double> q3 = session.createQuery(cq3);
		double avgSalary = q3.getSingleResult();
		System.err.println("Average Salary = " + avgSalary);

		// count distinct
		CriteriaQuery<Long> cq4 = builder.createQuery(Long.class);
		Root<Employee3> r4 = cq4.from(Employee3.class);
		cq4.select(builder.countDistinct(r4));
		Query<Long> q4 = session.createQuery(cq4);
		long distinct = q4.getSingleResult();
		System.err.println("Distinct count = " + distinct);

		tx.rollback();
		sessionFactory.close();

		return "greetings";
	}

	@RequestMapping("/t4")
	public String t4() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}

		Transaction tx = session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Object[]> cq1 = builder.createQuery(Object[].class);
		Root<Employee3> r1 = cq1.from(Employee3.class);
		Root<Department3> r2 = cq1.from(Department3.class);
		cq1.multiselect(r1, r2);
		cq1.where(builder.equal(r1.get("department"), r2.get("id")));

		Query<Object[]> q1 = session.createQuery(cq1);
		List<Object[]> lst = q1.list();
		for (Object[] o : lst) {
			Employee3 e = (Employee3) o[0];
			Department3 d = (Department3) o[1];

			System.err.println(e);
			System.err.println(d);
		}

		tx.rollback();
		sessionFactory.close();

		return "greetings";
	}

	// group by
	@RequestMapping("/t5")
	public String t5() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}

		Transaction tx = session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Object[]> cq1 = builder.createQuery(Object[].class);
		Root<Employee3> r1 = cq1.from(Employee3.class);
		Root<Department3> r11 = cq1.from(Department3.class);
		cq1.multiselect(builder.count(r1.get("name")), r1.get("salary"), r11.get("name"));
		cq1.where(builder.equal(r1.get("department"), r11.get("id")));
		cq1.groupBy(r1.get("salary"), r11.get("name"));
		cq1.having(builder.greaterThan(r1.get("salary"), 3000));

		Query<Object[]> q1 = session.createQuery(cq1);
		List<Object[]> lst1 = q1.list();

		for (Object[] objects : lst1) {
			long count = (Long) objects[0];
			int salary = (Integer) objects[1];
			Department3 department = (Department3) objects[2];
			System.err.println("Number of Employee = " + count + "\t SALARY=" + salary + "\t DEPT NAME=" + department.getName());
		}

		CriteriaQuery<Employee3> cq2 = builder.createQuery(Employee3.class);
		Root<Employee3> r2 = cq2.from(Employee3.class);
		cq2.select(r2);
		cq2.orderBy(builder.asc(r2.get("salary")));
		Query<Employee3> q2 = session.createQuery(cq2);
		List<Employee3> lst2 = q2.list();
		for (Employee3 employee : lst2) {
			System.out.println("EMP NAME=" + employee.getName() + "\t SALARY=" + employee.getSalary());
		}

		tx.rollback();
		sessionFactory.close();

		return "greetings";
	}
}
