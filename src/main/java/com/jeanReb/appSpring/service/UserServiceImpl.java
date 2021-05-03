package com.jeanReb.appSpring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jeanReb.appSpring.Exceptions.CustomeFieldValidationException;
import com.jeanReb.appSpring.Exceptions.UsernameOrIdNotFound;

import com.jeanReb.appSpring.entity.User;
import com.jeanReb.appSpring.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Iterable<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	
	public User createUser(User formUser) throws Exception {
		
		if(checkUsernameAvailable(formUser)
				&& checkPasswordValid(formUser)) {
			String encodePassword = bCryptPasswordEncoder.encode(formUser.getPassword());
			formUser.setPassword(encodePassword);
			formUser = repository.save(formUser);
			
			
		}
		return formUser;
	}
	
	private boolean checkUsernameAvailable(User user) throws Exception {
		
		Optional<User> userFound = repository.findByUsername(user.getUsername());
		if(userFound.isPresent()) {
			
			throw new CustomeFieldValidationException("Username already exists","username");
		}
		return true;
		
	}
	


	private boolean checkPasswordValid(User user) throws Exception {
		if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			throw new CustomeFieldValidationException("Confirm Password es obligatorio","confirmPassword");
		}
		
		if ( !user.getPassword().equals(user.getConfirmPassword())) {
			throw new CustomeFieldValidationException("Password y Confirm Password no son iguales","password");
		}
		return true;
	}
	

	@Override
	public User getUserById(Long id) throws UsernameOrIdNotFound {
		
		return repository.findById(id).orElseThrow(() ->
		new UsernameOrIdNotFound("El usuario no existe"));
	
	}


	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public User updateUser(User fromUser) throws Exception {
		
		User toUser  = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return  repository.save(toUser);
		
		
	}
	
	protected void mapUser(User from, User to) {
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
		
		
	}


	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deleteUser(Long id) throws UsernameOrIdNotFound {
		
			User user = getUserById(id);
			repository.delete(user);
		
	}


	public boolean isLoggedUserADMIN(){
		 return loggedUserHasRole("ROLE_ADMIN");
		}

		public boolean loggedUserHasRole(String role) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails loggedUser = null;
			Object roles = null; 
			if (principal instanceof UserDetails) {
				loggedUser = (UserDetails) principal;
			
				roles = loggedUser.getAuthorities().stream()
						.filter(x -> role.equals(x.getAuthority() ))      
						.findFirst().orElse(null); //loggedUser = null;
			}
			return roles != null ?true :false;
		}
		

	

	public User getLoggedUser() throws Exception {
		//Obtener el usuario logeado, el objeto con el que se realiza la autenticacion
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails loggedUser = null;

		//Verificar que ese objeto traido de sesion es el usuario
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		}
		
		User myUser = repository
				.findByUsername(loggedUser.getUsername()).orElseThrow(() -> new Exception(""));
		
		return myUser;
	}
	
	
	
}