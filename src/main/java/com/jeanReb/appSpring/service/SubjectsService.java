package com.jeanReb.appSpring.service;




import org.springframework.stereotype.Service;

import com.jeanReb.appSpring.Exceptions.SubjectOrIdNotFound;

import com.jeanReb.appSpring.entity.Subject;

@Service
public interface SubjectsService {

	
   public Iterable<Subject> getAllSubject();
	
   public Subject getById(Long id) throws Exception ;
   
   public Subject updateSubject(Subject subject) throws Exception;
   
   public int updateSubjectAdmin(Subject subject) throws Exception;
   
   public void deleteSubject(Long id) throws SubjectOrIdNotFound;
}
