package utility;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.core.MiscTransaction;

public class MiscTransactionManager {
	private static SessionFactory factory;
	public MiscTransaction getMiscTransaction(Date date){
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		MiscTransaction miscTransaction = null;
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("getMiscTransactionByDate").setDate("date", date);
			miscTransaction = (MiscTransaction)query.list().get(0);
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return miscTransaction;
	}
}