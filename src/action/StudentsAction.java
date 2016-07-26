package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import entity.Students;
import service.StudentsDAO;
import serviceImpl.StudentsDAOImpl;

public class StudentsAction extends SuperAction {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private StudentsDAO sDAO = new StudentsDAOImpl();

	// query all students
	public String query() {

		List<Students> list = sDAO.queryAllStudents();

		if (list != null && list.size() > 0) {
			session.setAttribute("students_list", list);
		} else {
			session.setAttribute("students_list", list);
		}

		return "query_success";
	}

	// delete a student
	public String delete() {

		int id = Integer.valueOf(request.getParameter("sid"));

		sDAO.deleteStudents(id);

		return "delete_success";
	}

	// add s student;
	public String add() {
		Students student = new Students();
		student.setSname(request.getParameter("sname"));
		student.setAddress(request.getParameter("address"));
		student.setGender(request.getParameter("gender"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			student.setBirthday(sdf.parse(request.getParameter("birthday")));
		} catch (ParseException e) {

			e.printStackTrace();
			return "add_failure";
		}
		
		sDAO.addStudents(student);
		return "add_success";
	}
	//modify page 
	public String modify(){
		int id = Integer.valueOf(request.getParameter("sid"));
		Students student = sDAO.queryStudentsBySid(id);
		session.setAttribute("modify_students", student);
		
		return "modify";
		
	}
	//save modified student
	public String save(){
		Students student = new Students();
		student.setSid(Integer.valueOf(request.getParameter("sid")));
		student.setSname(request.getParameter("sname"));
		student.setAddress(request.getParameter("address"));
		student.setGender(request.getParameter("gender"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			student.setBirthday(sdf.parse(request.getParameter("birthday")));
		} catch (ParseException e) {

			e.printStackTrace();
			return "save_failure";
		}
		
		sDAO.updateStudents(student);
		return "save_success";
	}

}
