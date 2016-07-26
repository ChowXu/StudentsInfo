package serviceImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Students;
import service.StudentsDAO;

public class StudentsDAOImpl implements StudentsDAO {

	private SessionFactory sessionFactory;
	private Session session;

	public StudentsDAOImpl() {

		sessionFactory = MyHibernateSessionFactory.getSessionFactory();

	}

	public List<Students> queryAllStudents() {
		Transaction ts = null;
		String Hql = "";
		List<Students> list = null;

		try {
			session = sessionFactory.getCurrentSession();
			ts = session.beginTransaction();
			Hql = "from Students";
			Query query = session.createQuery(Hql);
			list = query.list();

			ts.commit();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}

	}

	public Students queryStudentsBySid(int sid) {
		Transaction ts = null;
		try{
			session = sessionFactory.getCurrentSession();
			ts = session.beginTransaction();
			Students s = (Students) session.get(Students.class, sid);
			ts.commit();
			return s;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	public boolean addStudents(Students students) {

		Transaction ts = null;
		try {
			session = sessionFactory.getCurrentSession();
			ts = session.beginTransaction();
			students.setSid((int) (Math.random()* 10000 %999) );
			session.save(students);
			ts.commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		finally {
			if (ts != null)
				ts = null;
		}
	}

	public boolean updateStudents(Students s) {
		Transaction ts = null;
		try {
			session = sessionFactory.getCurrentSession();
			ts = session.beginTransaction();
			session.update(s);
			ts.commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteStudents(int sid) {

		Transaction ts = null;

		try {
			session = sessionFactory.getCurrentSession();
			ts = session.beginTransaction();
			Students s = (Students) session.get(Students.class, sid);
			session.delete(s);

			ts.commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ts != null)
				ts = null;

		}

	}

}
