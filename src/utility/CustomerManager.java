package utility;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.core.Customer;

import java.util.List;

public class CustomerManager {
	private static SessionFactory factory;
	
	public List<Customer> listCustomer(String name){
		factory =  DbSessionManager.getSessionFactory("core");
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
	public Customer getCustomerByLoan(String loanId) {
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		Customer customer = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("getByLoanId").setString("loanId", loanId);
			customer = (Customer)query.list().get(0);
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return customer;
	}
	
	
	public long addCustomer(Customer customer){
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		long customerId = 0;
		try {
			tx = session.beginTransaction();
			customerId = (long)session.save(customer);
			tx.commit();
		} catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return customerId;
	}
	public Customer getCustomer(String customerId) {
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		Customer customer= null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("getByCustomerId").setString("customerId", customerId);
			customer = (Customer)query.list().get(0);
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return customer;
	}
	public long updateCustomer(Customer customer){
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		long customerId = 0;
		try {
			tx = session.beginTransaction();
			session.update(customer);
			tx.commit();
		} catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return customerId;
	}
	public String deleteCustomer(String customerId) {
		factory =  DbSessionManager.getSessionFactory("core");
		String status = "failure";
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("deletebyCustomerId").setString("customerId", customerId);
			query.executeUpdate();
			tx.commit();
			status = "success";
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
			System.out.println(he);
			status= "failure";
		}
		return status;
	}
}