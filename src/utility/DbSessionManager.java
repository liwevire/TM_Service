package utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbSessionManager {
	private static final SessionFactory coreFactory = buildSessionFactory("core");
	private static final SessionFactory reportsFactory = buildSessionFactory("reports");
	
    private static SessionFactory buildSessionFactory(String db)
    {
        try
        {
        	SessionFactory sessionFactory = null;
        	if(db.equalsIgnoreCase("core"))
        		sessionFactory = new Configuration().configure("/core.cfg.xml").buildSessionFactory();
        	else
        		sessionFactory = new Configuration().configure("/reports.cfg.xml").buildSessionFactory();
            return sessionFactory;        
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory(String db) {
        if(db.equalsIgnoreCase("core"))
        	return coreFactory;
        else
        	return reportsFactory;
    }
    public static void shutdown(String db) {
        	getSessionFactory(db).close();
        
    }
}
