package utility;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Loan;

public class LoanManager {
	private static SessionFactory factory;
	public long addLoan(Loan loan){
		factory =  DbSessionManager.getSessionFactory("core");
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
		factory =  DbSessionManager.getSessionFactory("core");
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
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		new ItemManager().deleteItem(loan.getLoanId());
		new TransactionManager().deleteTransaction(loan.getLoanId());
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