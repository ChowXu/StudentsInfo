package serviceImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import db.MyHibernateSessionFactory;
import entity.Users;
import service.UsersDAO;

public class UsersDaoImpl implements UsersDAO{
	
	private Session session;

	public boolean userLogin(Users u) {
		
		try{
			session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String Hql = "from Users where username = ? and password = ?";
			Query query = session.createQuery(Hql);
			query.setString(0,u.getUsername());
			query.setString(1,u.getPassword() );
			List list = query.list();
			session.getTransaction().commit();
			if(list.size() > 0){
				return true;
			}
			else 
				return false;
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
			
		}
		
	}
	

}
