package com.jeanReb.appSpring.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanReb.appSpring.Exceptions.SubjectOrIdNotFound;

import com.jeanReb.appSpring.entity.Subject;

import com.jeanReb.appSpring.repository.Subjects;

@Service
public class SubjectServiceImpl implements SubjectsService{

	@Autowired
	
	Subjects repositoryS ; 
	
	@Override
	public Iterable<Subject> getAllSubject() {
		repositoryS.findAll();
		return null;
	}

	

	@Override
	public Subject getById(Long id) throws SubjectOrIdNotFound {
		
		return repositoryS.findById(id).orElseThrow(() -> new SubjectOrIdNotFound("La materia no existe"));
		
		
	}



	@Override
	public Subject updateSubject(Subject subject) throws Exception {
		
		Subject toSubject = getById(subject.getId());
		toSubject.setQuota_max(subject.getQuota_max() - 1);
		return repositoryS.save(toSubject);
	}

	protected void mapSubject(Subject from, Subject to) {
		
		to.setQuota_max(from.getQuota_max()); 

		
	}


	@Override
	public int updateSubjectAdmin(Subject subject) throws Exception {
		
        int res=0;
		
		subject = repositoryS.save(subject);
		
		if(!subject.equals(null)) {
			res=1;
			
		}
		return res;
		
		/*
		 * Subject toSubject = getById(subject.getId());
		 * toSubject.setQuota_max(subject.getQuota_max());
		 * toSubject.setName(subject.getName());
		 * toSubject.setTeacher(subject.getTeacher()); return
		 * repositoryS.save(toSubject);
		 */
	}



	@Override
	public void deleteSubject(Long id) throws SubjectOrIdNotFound {
		
		Subject subject = getById(id);
		
		repositoryS.delete(subject);
		
	}
	

}
