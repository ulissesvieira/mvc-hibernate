package com.hibernate.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.entity.Cart;
import com.hibernate.entity.Cart2;
import com.hibernate.entity.Customer;
import com.hibernate.entity.Employee;
import com.hibernate.entity.Employee2;
import com.hibernate.entity.Item2;
import com.hibernate.entity.Items;
import com.hibernate.entity.Transaction;

@Controller
@RequestMapping("/hibernate")
public class HibernateController {
	@Autowired
	private SessionFactory sessionFactory;
	
	@RequestMapping("/t1")
	public String t1(ModelAndView model) {
		Employee e = new Employee();
		e.setName("Kenshiro");
		e.setRole("64th successor of the Hokuto Shinken");
		e.setInsertTime(LocalDate.of(1983, Month.SEPTEMBER, 13));
		
		Session session = null;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
		    session = sessionFactory.openSession();
		}
		
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		
		System.out.println("Employee ID = " + e.getId());
		sessionFactory.close();
		
		model.addObject("employee", e);
		return "hibernate/t1";
	}
	
	@RequestMapping("/t2")
	public String t2(Model model) {		
		Session session = null;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
		    session = sessionFactory.openSession();
		}		
		
		Transaction trans = new Transaction();
		trans.setDate(LocalDate.now());
		trans.setTotal(75.6);
		
		Customer cus = new Customer();
		cus.setName("Customer");
		cus.setEmail("email@email.com");
		cus.setAddress("address");
		
		trans.setCustomer(cus);
		cus.setTransaction(trans);
		
		org.hibernate.Transaction tx = session.beginTransaction();
		session.save(trans);
		//session.save(cus);		
		tx.commit();
		
		System.out.println(trans.toString());
		System.out.println(cus.toString());
		
		sessionFactory.close();		
	
		model.addAttribute("transaction", trans);
		model.addAttribute("customer", cus);
		return "hibernate/t2";
	}
	
	@RequestMapping("/t3")
	public String t3(Model model) {
		Cart cart = new Cart();
		cart.setName("My Cart");
		
		Items item1 = new Items("I10", 10.0, 1, cart);
		Items item2 = new Items("I20", 20.0, 2, cart);
		Set<Items> set = new HashSet<Items>();
		set.add(item1);
		set.add(item2);
		
		cart.setItems(set);
		cart.setTotal(10.0 * 1 + 20.0 * 2);
		
		Session session = null;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
		    session = sessionFactory.openSession();
		}	
		
		org.hibernate.Transaction tx = session.beginTransaction();
		session.save(cart);
		session.save(item1);
		session.save(item2);
		tx.commit();
		
		sessionFactory.close();		
		
		model.addAttribute("cart", cart);
		return "hibernate/t3";
	}
	
	@RequestMapping("/l3")
	public String l3(Model model) {
		Session session = null;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
		    session = sessionFactory.openSession();
		}	
		
		Cart cart = session.find(Cart.class, 1);
		
		sessionFactory.close();		
		
		model.addAttribute("cart", cart);
		return "hibernate/t3";
	}
	
	@RequestMapping("/t4")
	public String t4(Model model) {
		Item2 item1 = new Item2(300D, "samsung");
		Item2 item2 = new Item2(200D, "nokia");
		
		Cart2 cart = new Cart2();
		cart.setTotal(500D);
		Set<Item2> items = new HashSet<>();
		items.add(item1);
		items.add(item2);
		cart.setItems(items);
		
		Session session = null;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
		    session = sessionFactory.openSession();
		}	
		
		org.hibernate.Transaction tx = session.beginTransaction();
		session.save(cart);
		tx.commit();
		
		sessionFactory.close();		
		
		model.addAttribute("cart", cart);
		return "hibernate/t4";
	}
	
	@RequestMapping("/l4")
	public String l4(Model model) {
		Session session = null;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
		    session = sessionFactory.openSession();
		}	
		
		Cart2 cart = session.find(Cart2.class, 1);
		Hibernate.initialize(cart.getItems());
		
		sessionFactory.close();		
		
		model.addAttribute("cart", cart);
		return "hibernate/t4";
	}
	
	@RequestMapping("/t5")
	private String t5() {
		Session session = null;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
		    session = sessionFactory.openSession();
		}	
		
		Query<Employee2> query = session.createQuery("from Employee2", Employee2.class);
		List<Employee2> list = query.list();
		for (Employee2 e : list) {
			System.err.println(e);
			System.err.println(e.getAddress());
		}
		System.err.println();
		
		query = session.createQuery("from Employee2 where id = :id", Employee2.class);
		query.setParameter("id", 3, IntegerType.INSTANCE);
		Employee2 e2 = (Employee2) query.uniqueResult();
		System.err.println(e2);
		System.err.println();
		
		query = session.createQuery("from Employee2", Employee2.class);
		query.setFirstResult(0);
		query.setFetchSize(2);
		List<Employee2> lst2 = query.list();
		for (Employee2 e : lst2) {
			System.err.println(e);
			System.err.println(e.getAddress());
		}
		System.err.println();
		
		org.hibernate.Transaction tx = session.beginTransaction();
		
		Query query2 = session.createQuery("update Employee2 set name = :name where id = :id");
		query2.setParameter("name", "Pankaj Kumar", StringType.INSTANCE);
		query2.setParameter("id", 1);
		int result = query2.executeUpdate();
		System.err.println("Employee update status = " + result);
		System.err.println();
		
		query2 = session.createQuery("delete from Address2 where id = :id");
		query2.setParameter("id", 4);
		result = query2.executeUpdate();
		System.err.println("Employee delete status = " + result);
		System.err.println();
		
		tx.rollback();
		
		query2 = session.createQuery("select sum(salary) from Employee2");
		Double sumSalary = (Double) query2.uniqueResult();
		System.err.println("Sum of all salaries = " + sumSalary);
		System.err.println();
		
		query2 = session.createQuery("select e.name, a.city from Employee2 e join e.address a");
		List<Object[]> lst3 = query2.list();
		for (Object[] arr: lst3) {
			System.err.println(Arrays.toString(arr));
		}
		System.err.println();
		
		query2 = session.createQuery("select e.name, sum(e.salary), count(e) from Employee2 e " + 
				" where e.name like '%i%' group by e.name");
		lst3 = query2.list();
		for (Object[] arr: lst3) {
			System.err.println(Arrays.toString(arr));
		}
		System.err.println();
		
		query = session.createQuery("from Employee2 e order by e.id desc", Employee2.class);
		lst2 = query.list();
		for (Employee2 e : lst2) {
			System.err.println(e);
		}
		System.err.println();
		
		sessionFactory.close();	
		
		return "hibernate/t1";
	}
}
