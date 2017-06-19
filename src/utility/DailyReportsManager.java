package utility;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.reports.Daily;

public class DailyReportsManager {
	private static SessionFactory factory;
	public Daily calculateDailyReport(Date date){
		factory =  DbSessionManager.getSessionFactory("reports");
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("calculateDailyReport").setParameter("date", date);
			query.setParameter("recursionIndex", 0);
			Object object =  query.executeUpdate();
			System.out.println(object.toString());
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return getDailyReport(date);
	}
	public Daily getDailyReport(Date date) {
		factory =  DbSessionManager.getSessionFactory("reports");
		Session session = factory.openSession();
		Daily dailyReport = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("getReportByDate").setDate("reportDate", date);
			System.out.println("date processed: " + date.toString());
			dailyReport = (Daily)query.list().get(0);
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return dailyReport;
	}
}