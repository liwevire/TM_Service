package utility;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Item;

public class ManageItem {
	private static SessionFactory factory;
	public void initializeFactory() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex);
		}
	}
	public Item getItem(long itemId) {
		initializeFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Item item = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("getItemById").setLong("itemId", itemId);
			System.out.println(itemId);
			item = (Item)query.list().get(0);
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		System.out.println(item.getLoan().getCustomer().getName());
		System.out.println("test");
		return item;
	}
}