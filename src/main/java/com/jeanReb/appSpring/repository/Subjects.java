package com.jeanReb.appSpring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jeanReb.appSpring.entity.Role;
import com.jeanReb.appSpring.entity.Subject;



@Repository
public interface Subjects extends CrudRepository<Subject, Long>{ 
	


}
