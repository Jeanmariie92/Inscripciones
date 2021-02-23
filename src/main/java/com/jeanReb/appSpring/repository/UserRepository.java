package com.jeanReb.appSpring.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.jeanReb.appSpring.entity.User;



public interface UserRepository extends CrudRepository<User, Long>{

	
	public Optional<User> findByUsername(String username);
		
	
}
