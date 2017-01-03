package utility;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

import model.Customer;

public class ManageCustomer {
	private static SessionFactory factory;
	
	public void initializeFactory() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex);
		}
	}
	public List<Customer> listCustomer(String name){
		initializeFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		List<Customer> customers = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("findCustomerByName").setString("name", name.toLowerCase());
			System.out.println(name);
			customers = (List<Customer>)query.list();
			System.out.println(customers);
			for (Customer customer : customers) {
				System.out.println(customer.getCustomerId());
			}
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return customers;
	}
	
	
	
	///////////////////////
	/////////Model/////////
	///////////////////////
	public long addCustomer(Customer customer){
		initializeFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		long customerID = 0;
		try{
			tx = session.beginTransaction();
			customerID = (long) session.save(customer);
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return customerID;
	}
	public List<Customer> listCustomer(){
		initializeFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		List<Customer> customers = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("listAllCustomer");
			customers = (List<Customer>)query.list();
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return customers;
	}
}