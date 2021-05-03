package com.jeanReb.appSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanReb.appSpring.Exceptions.TeacherOrIdNotFound;

import com.jeanReb.appSpring.entity.Teacher;

import com.jeanReb.appSpring.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherRepository repository;
	
	@Override
	public Iterable<Teacher> getAllTeacher() {
		
		repository.findAll();
		return null;
	}

	@Override
	
	public Teacher getById(Long id) throws  TeacherOrIdNotFound {
		
		return repository.findById(id).orElseThrow(() ->
		new TeacherOrIdNotFound("no existe"));
	
	}


	@Override
	public int createTeacher(Teacher teacher) throws Exception {
		
		int res=0;
		
		teacher = repository.save(teacher);
		
		if(!teacher.equals(null)) {
			res=1;
			
		}
		return res;
		
	}

	@Override
	public void deleteTeacher(Long id) throws TeacherOrIdNotFound {
	
		Teacher teacher = getById(id);
		System.out.println(teacher);
		repository.delete(teacher);
		
	}
	


}
