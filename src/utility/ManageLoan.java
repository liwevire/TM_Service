package utility;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Loan;

public class ManageLoan {
	private static SessionFactory factory;
	public void initializeFactory() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex);
		}
	}
	public long addLoan(Loan loan){
		initializeFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		long loanId = 0;
		try {
			tx = session.beginTransaction();
			loanId = (long)session.save(loan);
			tx.commit();
		} catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return loanId;
	}
	public Loan getLoan(long loanId) {
		initializeFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Loan loan = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("getLoanById").setLong("loanId", loanId);
			loan = (Loan)query.list().get(0);
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return loan;
	}
	public long updateLoan(Loan loan){
		initializeFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		new ManageItem().deleteItem(loan.getLoanId());
		new ManageTransaction().deleteTransaction(loan.getLoanId());
		long loanId = 0;
		try {
			tx = session.beginTransaction();
			session.update(loan);
			tx.commit();
		} catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return loanId;
	}
}