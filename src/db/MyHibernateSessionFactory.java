package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class MyHibernateSessionFactory {
	
	private static SessionFactory sessionFactory;   //sessionFactory attr

	
	private MyHibernateSessionFactory(){
		
	}
	
	public static SessionFactory getSessionFactory(){
		if(sessionFactory == null){
			
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
			
		}
		
		return sessionFactory;
		
		
	}
}
