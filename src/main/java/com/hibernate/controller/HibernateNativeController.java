package com.hibernate.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hibernate.entity.Address2;
import com.hibernate.entity.Employee2;

@Controller
@RequestMapping("/native")
public class HibernateNativeController {
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
		Query<Employee2> query = session.createNativeQuery("select emp_id, emp_name, emp_salary from Employee2", Employee2.class);	
		List<Employee2> rows = query.list();
		for (Employee2 e : rows) {
			System.err.println(e);
		}
		System.err.println();
		
		Query q2 = session.createNativeQuery("select emp_id, emp_name, emp_salary from Employee2")
						  .addScalar("emp_id", new IntegerType())
						  .addScalar("emp_name", new StringType())
						  .addScalar("emp_salary", new DoubleType());
		List<Object[]> r = q2.list();
		for (Object[] o : r) {
			Employee2 e = new Employee2();
			e.setId(Integer.parseInt(o[0].toString()));
			e.setName(o[1].toString());
			e.setSalary(Double.parseDouble(o[2].toString()));
			System.err.println(e);
		}
		System.err.println();
		
		q2 = session.createNativeQuery("select e.emp_id, e.emp_name, e.emp_salary, a.address_line1, a.city, a.zipcode from Employee2 e, Address2 a where e.emp_id = a.emp_id");
		r = q2.list();
		for (Object[] o : r) {
			Employee2 e = new Employee2();
			e.setId(Integer.parseInt(o[0].toString()));
			e.setName(o[1].toString());
			e.setSalary(Double.parseDouble(o[2].toString()));
			
			Address2 a = new Address2();
			a.setAddressLine1(o[3].toString());
			a.setCity(o[4].toString());
			a.setZipcode(o[5].toString());
			
			e.setAddress(a);
			
			System.err.println(e);
		}
		System.err.println();
		
		q2 = session.createNativeQuery("select {e.*}, {a.*} from Employee2 e join Address2 a on e.emp_id = a.emp_id")
					.addEntity("e", Employee2.class)
					.addJoin("a", "e.address");
		r = q2.list();
		for (Object[] o : r) {
			for (Object a : o) {
				System.err.print(a + "::");
			}
			System.err.println("\n");
		}
		
		for (Object[] o : r) {
			Employee2 e = (Employee2) o[0];
			Address2 a = (Address2) o[1];
			
			System.err.println("Employee Info:: " + e);
			System.err.println("Address Info:: " + a);
		}
		System.err.println();
		
		q2 = session.createNativeQuery("select e.emp_id, e.emp_name, e.emp_salary from Employee2 where emp_id = ?");		
		r = q2.setParameter(0, 1).list();
		q2 = session.createNativeQuery("select e.emp_id, e.emp_name, e.emp_salary from Employee2 where emp_id = :id");
		r = q2.setParameter("id", 2).list();
		System.err.println();
		
		tx.rollback();
		
		return "greetings";
	}
}
