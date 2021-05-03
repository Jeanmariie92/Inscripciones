package com.jeanReb.appSpring.controller;

import java.util.Arrays;
import java.util.List;




import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.jeanReb.appSpring.Exceptions.CustomeFieldValidationException;


import com.jeanReb.appSpring.entity.Role;

import com.jeanReb.appSpring.entity.User;

import com.jeanReb.appSpring.repository.RoleRepository;
import com.jeanReb.appSpring.repository.Subjects;


import com.jeanReb.appSpring.service.SubjectsService;
import com.jeanReb.appSpring.service.UserService;
import com.jeanReb.appSpring.service.UserServiceImpl;

@Controller
@RequestMapping
public class UserController {

	
	@Autowired
	UserService userService;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	SubjectsService service;
	@Autowired
	RoleRepository roleRepository;

	
	
	@Autowired
	Subjects repositoryS;
	
	
	
	
	
	@GetMapping("/signup")
	public String signup(Model model) {
		Role userRole = roleRepository.findByName("USER");
		List<Role> roles = Arrays.asList(userRole);
		model.addAttribute("userForm", new User());
		model.addAttribute("roles", roles);
		model.addAttribute("signup", true);
		
		return "user-form/user-signup";
	}
	
	
	
	@PostMapping("/signup")
	public String postSignup(@Valid @ModelAttribute("userForm") User user, BindingResult result, ModelMap model) {
		Role userRole = roleRepository.findByName("USER");
		List<Role> roles = Arrays.asList(userRole);
		model.addAttribute("userForm", user);
		model.addAttribute("roles", roles); 
		model.addAttribute("signup", true);
		if (result.hasErrors()) {

			return "user-form/user-signup";
			
		}else {
			
			try {
				userService.createUser(user);
				
				
				
			} catch (CustomeFieldValidationException cfve) {
				result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
				return "user-form/user-signup";
	
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				return "user-form/user-signup";
				
			}
		}
	
		return "index";

	}
	
	
	@GetMapping({"/","/login"})
	public String index(Model model) {
		
		model.addAttribute("roles", roleRepository.findAll());
		return "index";
	}

	



	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("userForm") User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {

			model.addAttribute("userForm", user);
			model.addAttribute("formTab", "active");
			
		}else {
			
			try {
				userService.createUser(user);
				
				model.addAttribute("userForm", new User()); //Blanqueamos los cmapos del form
				model.addAttribute("listTab","active"); //Redirigimos a la pestaña List
			} catch (CustomeFieldValidationException cfve) {
				result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("roles",roleRepository.findAll());
				
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("roles",roleRepository.findAll());
			}
		}
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleRepository.findAll());
		return "user-form/user-view";

	}
	
	
	


		
@GetMapping("/userForm/cancel")
		
public String cancelEditUser(ModelMap model) {
	
	return "redirect:/userForm";
}




}