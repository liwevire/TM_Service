package utility;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.DailyReport;

public class DailyReportsManager {
	private static SessionFactory factory;
	public DailyReport calculateDailyReport(Date date){
		factory =  DbSessionManager.getSessionFactory("reports");
		Session session = factory.openSession();
		Transaction tx = null;
		DailyReport dailyReport = null;
		try{
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("calculateDailyReport").setParameter("date", date);
			dailyReport = (DailyReport)query.list().get(0);
			tx.commit();
		}catch (HibernateException he) {
			if(tx!=null) tx.rollback();
			he.printStackTrace();
		}
		return dailyReport;
	}
}
