package utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.core.MiscTransaction;

public class MiscTransactionManager {
	private static SessionFactory factory;
	public List<MiscTransaction> getMiscTransaction(Date date){
		factory=  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		List<MiscTransaction> miscTransactions = null;
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("getMiscTransactionByDate").setDate("date", date);
			miscTransactions = (List<MiscTransaction>)query.list();
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return miscTransactions;
	}
}