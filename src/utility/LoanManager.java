package utility;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.core.Loan;

public class LoanManager {
	private static SessionFactory factory;
	public String addLoan(Loan loan){
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		String loanId = null;
		try {
			tx = session.beginTransaction();
			loanId = (String)session.save(loan);
			tx.commit();
		} catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return loanId;
	}
	public Loan getLoan(String loanId) {
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		Loan loan = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("getLoanById").setString("loanId", loanId);
			loan = (Loan)query.list().get(0);
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return loan;
	}
	public List<Loan> getLoans(Date date) {
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		List<Loan> loans = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("getLoansByDate").setDate("date", date);
			loans = (List<Loan>)query.list();
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return loans;
	}
	public List<Loan> getLoans(Date fromDate, Date toDate) {
		factory =  DbSessionManager.getSessionFactory("core");
		Session session = factory.openSession();
		Transaction tx = null;
		List<Loan> loans = null;
		try{
			session.createCriteria(Loan.class).list();
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("getLoansBetweenDates").setDate("fromDate", fromDate).setDate("toDate", toDate);
			loans = query.list();
//			for (Iterator iterator = loans.iterator(); iterator.hasNext();) {
//				Loan loan = (Loan) iterator.next();
//				System.out.println(loan.getLoanId());
//			}
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return loans;
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
	public String deleteLoan(String loanId) {
		factory =  DbSessionManager.getSessionFactory("core");
		String status = "failure";
		Session session = factory.openSession();
		Transaction tx = null;
		new ItemManager().deleteItem(loanId);
		new TransactionManager().deleteTransaction(loanId);
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("deleteLoanbyLoanId").setString("loanId", loanId);
			query.executeUpdate();
			tx.commit();
			status = "success";
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
			status= "failure";
		}
		return status;
	}
}