package utility;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Item;

public class ItemManager {
	private static SessionFactory factory;
	public Item getItem(long itemId) {
		factory =  DbSessionManager.getSessionFactory("core");
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
		return item;
	}
	public void deleteItem(long loanId) {
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("deleteItemsbyLoanId").setLong("loanId", loanId);
			query.executeUpdate();
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		finally {
			session.close();
		}
	}
}