package utility;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.core.Item;

public class TransactionManager {
	private static SessionFactory factory;
	public void initializeFactory() {
		try {
			factory =  DbSessionManager.getSessionFactory("core");
		} catch (Throwable ex) {
	        throw new ExceptionInInitializerError(ex);
		}
	}
	public void deleteTransaction(String loanId) {
		initializeFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("deleteTransactionsbyLoanId").setString("loanId", loanId);
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