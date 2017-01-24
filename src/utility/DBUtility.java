package utility;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Customer;
import model.Loan;

public class DBUtility {
	private static SessionFactory factory;

	public static void test() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		DBUtility ME = new DBUtility();

		/* Add few customer records in database */
		//long empID1 = ME.addCustomer("Premkumar", "T", "Sethu road", "Peravurani", (long) 614804, "9442576093");
		//long empID2 = ME.addCustomer("Prakash", "V", "Anna nagar", "Chennai", (long) 603001, "9876543210");
		// long empID3 = ME.addCustomer("Pradheep", "M", "Kolathur" , "Mettur",
		// (long)603002, "9876543211");

		/* List down all the customers */
		ME.listCustomers();

		/* Update customer's records */
		//ME.updateCustomer(empID1, 5000);

		/* Delete an customer from the database */
		//ME.deleteCustomer(empID2);

		/* List down new list of the customers */
		ME.listCustomers();
	}

	/* Method to CREATE an customer in the database */
	public long addCustomer(String name, String secondaryName, Date date, String address, String post, String pin, String phone) {
		Session session = factory.openSession();
		Transaction tx = null;
		long customerID = 0;
		try {
			tx = session.beginTransaction();
			Customer customer = new Customer(name, secondaryName, date, address, post, pin, phone);
			customerID = (long) session.save(customer);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return customerID;
	}

	/* Method to READ all the customers */
	public void listCustomers() {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List customers = session.createQuery("FROM Customer").list();
			for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
				Customer customer = (Customer) iterator.next();
				System.out.print("First Name: " + customer.getName());
				System.out.print("Last Name: " + customer.getSecondaryName());
				System.out.println("Address: " + customer.getAddress());
				System.out.println("Post: " + customer.getPost());
				System.out.println("Pin: " + customer.getPin());
				System.out.println("Phone: " + customer.getPhone());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE salary for an customer */
	public void updateCustomer(long CustomerID, long salary) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Customer customer = (Customer) session.get(Customer.class, CustomerID);
			customer.setPhone("9442576093");
			session.update(customer);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an customer from the records */
	public void deleteCustomer(long CustomerID) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Customer customer = (Customer) session.get(Customer.class, CustomerID);
			session.delete(customer);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void temp(Loan loan) {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = factory.openSession();
		Transaction tx =null;
		try {
			tx = session.beginTransaction();
			//session.save(item);
			//session.save(loan.getCustomer());
			session.save(loan);
//			for (Item item : loan.getItems()) {
//				session.save(item);
//			}
			
			//session.save(item);
		} catch (HibernateException he) {
			 if(tx!=null) tx.rollback();
			 he.printStackTrace();
		} finally {
			session.close();
		}
	}
}