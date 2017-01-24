package utility;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

import model.Customer;
import model.Loan;

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
			Query query = session.getNamedQuery("findCustomerByName").setString("name", name.toLowerCase()+'%');
			customers = (List<Customer>)query.list();
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return customers;
	}
	public Customer getCustomer(long loanId) {
		initializeFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Customer customer = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("getCustomerByLoanId").setLong("loanId", loanId);
			customer = (Customer)query.list().get(0);
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return customer;
	}
}