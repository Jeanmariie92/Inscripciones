package com.jeanReb.appSpring.service;



import com.jeanReb.appSpring.Exceptions.TeacherOrIdNotFound;

import com.jeanReb.appSpring.entity.Teacher;


public interface TeacherService {

	  public Iterable<Teacher> getAllTeacher();
	
	   public Teacher getById(Long id) throws Exception ;
	   
	   public int createTeacher(Teacher teacher) throws Exception;
	   
	   public void deleteTeacher(Long id) throws TeacherOrIdNotFound;
	   
	   

}
