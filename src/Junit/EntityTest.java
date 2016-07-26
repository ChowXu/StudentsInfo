package Junit;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.BeforeClass;
import org.junit.Test;

import service.StudentsDAO;
import serviceImpl.StudentsDAOImpl;
import serviceImpl.UsersDaoImpl;
import db.MyHibernateSessionFactory;
import entity.Students;
import entity.Users;

public class EntityTest {

	private static SessionFactory sessionFactory;
	private StudentsDAO sDao;

	@BeforeClass
	public static void beforeClass() {
		sessionFactory = MyHibernateSessionFactory.getSessionFactory();
		StudentsDAO sDao = new StudentsDAOImpl();
	}

	@Test
	public void test() {

		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();
		Users user = new Users();
		user.setPassword("23");
		user.setUsername("Tom");
		session.save(user);

		session.getTransaction().commit();

		assertEquals(true, new UsersDaoImpl().userLogin(user));

	}

	@Test
	public void TestStudent() {
		Session session = sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();

		Students student1 = new Students(0001, "张三", "male", new Date(), "武当山");
		Students student2 = new Students(0002, "郭靖", "male", new Date(), "桃花岛");
		Students student3 = new Students(0003, "黄蓉", "female", new Date(),
				"桃花岛");

		session.save(student1);
		session.save(student2);
		session.save(student3);
		
		ts.commit();

		sessionFactory.close();

	}
	
	@Test
	public void QueryAllStudent(){
		StudentsDAO sDAO = new StudentsDAOImpl();
		List<Students> list = sDAO.queryAllStudents();
		for(Students s : list){
			System.out.println(s.toString()+ "\n");
		}
		
		
		
	}
	@Test
	public void updateStudentTest(){
		StudentsDAO sDao = new StudentsDAOImpl();
		Students student3 = new Students(0003, "黄蓉", "female", new Date(),
				"江苏");
		
		sDao.updateStudents(student3);
		
		Students student4 = new Students(0004, "张无忌", "male", new Date(),
				"南京");
		
		sDao.addStudents(student4);
		
		
		QueryAllStudent();
	}
	
	@Test
	public void getStudentById(){
		Session session = null;
		Transaction ts = null;
		try{
			session = sessionFactory.getCurrentSession();
			ts = session.beginTransaction();
			Students s = (Students) session.get(Students.class, 3);
			ts.commit();
			System.out.println(s.toString());
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		
	}

}
