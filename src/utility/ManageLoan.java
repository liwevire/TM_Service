package utility;

import org.hibernate.HibernateException;
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
}