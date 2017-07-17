package utility;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.core.Customer;
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
	public MiscTransaction getMiscTransaction(long transactionId) {
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		MiscTransaction miscTransaction= null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("getByMiscTransactionId").setLong("transactionId", transactionId);
			miscTransaction = (MiscTransaction)query.list().get(0);
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return miscTransaction;
	}
	public long updateMiscTransaction(MiscTransaction miscTransaction){
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		long transactionId = 0;
		try {
			tx = session.beginTransaction();
			session.update(miscTransaction);
			tx.commit();
		} catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return transactionId;
	}
	public String deleteMiscTransaction(long transactionId) {
		factory =  DbSessionManager.getSessionFactory("core");
		String status = "failure";
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("deleteByTransactionId").setLong("transactionId", transactionId);
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
	public long addMiscTransaction(MiscTransaction miscTransaction){
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		long transactionId = 0;
		try {
			tx = session.beginTransaction();
			transactionId = (long)session.save(miscTransaction);
			tx.commit();
		} catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return transactionId;
	}
}