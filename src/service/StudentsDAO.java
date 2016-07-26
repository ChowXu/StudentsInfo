package service;

import java.util.List;

import entity.Students;

public interface StudentsDAO {
	
	//query all students
	public List<Students> queryAllStudents();
	
	
	//query a student
	public Students queryStudentsBySid(int sid);
	
	//add students
	public boolean addStudents(Students students);
	
	//modify students
	public boolean updateStudents(Students s);
	
	//delete students
	public boolean deleteStudents(int sid);
	
	

}
