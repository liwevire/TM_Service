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
	public Customer getCustomer(long loanId) {
		factory =  DbSessionManager.getSessionFactory("core");
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