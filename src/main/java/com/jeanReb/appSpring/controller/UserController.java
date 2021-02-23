package com.jeanReb.appSpring.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jeanReb.appSpring.Exceptions.CustomeFieldValidationException;
import com.jeanReb.appSpring.Exceptions.UsernameOrIdNotFound;
import com.jeanReb.appSpring.dto.ChangePasswordForm;
import com.jeanReb.appSpring.entity.Role;
import com.jeanReb.appSpring.entity.User;
import com.jeanReb.appSpring.repository.ProductosRepository;
import com.jeanReb.appSpring.repository.RoleRepository;
import com.jeanReb.appSpring.service.ProductosService;
import com.jeanReb.appSpring.service.ProductosServiceImpl;
import com.jeanReb.appSpring.service.UserService;
import com.jeanReb.appSpring.service.UserServiceImpl;

@Controller
public class UserController {

	
	@Autowired
	UserService userService;
	// No usamos el Servicio debido a que con esta entidad solo realizaremos
	// consultas, asi evitamos tener una capa en el medio del proceso
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ProductosService productosService;
	
	
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
	
		return index();

	}
	
	
	@GetMapping({"/","/login"})
	public String index() {

		return "index";
	}

	@GetMapping("/userForm")
	public String userForm(Model model) {

		model.addAttribute("userForm", new User());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("listTab", "active");
	
		return "user-form/user-view";

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
	
	@GetMapping("/editUser/{id}")
	
	public String getEditUserForm(Model model, @PathVariable (name = "id")Long id) throws Exception{
		
		User userToEdit = userService.getUserById(id);
		
		model.addAttribute("userForm", userToEdit);
		
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("formTab", "active");
		model.addAttribute("editMode", "true");
		model.addAttribute("passwordForm", new ChangePasswordForm(userToEdit.getId()));
		return "user-form/user-view";
		
	}
	

@PostMapping("/editUser")
	
	public String postEditUserForm(@Valid @ModelAttribute("userForm") User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {

			model.addAttribute("userForm", user);
			model.addAttribute("formTab", "active");
			model.addAttribute("editMode", "true");
			model.addAttribute("passwordForm", new ChangePasswordForm(user.getId()));
			
		}else {
			
			try {
				userService.updateUser(user);
				model.addAttribute("userForm", new User()); //Blanqueamos los cmapos del form
				model.addAttribute("listTab","active"); //Redirigimos a la pestaña List
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab", "active");
				model.addAttribute("editMode", "true");
				model.addAttribute("passwordForm", new ChangePasswordForm(user.getId()));
				
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


@GetMapping("/deleteUser/{id}")

public String deleteUser(Model model, @PathVariable (name = "id")Long id) {
	
	try {
		userService.deleteUser(id);
		
	} catch (UsernameOrIdNotFound uoin) {
		model.addAttribute("listErrorMessage", uoin.getMessage());
	}
	
   return userForm(model);
}




@PostMapping("/editUser/changePassword")
public ResponseEntity postEditUseChangePassword(@Valid @RequestBody ChangePasswordForm form, Errors errors) {
	try {
		if( errors.hasErrors()) {
			String result = errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(""));

			throw new Exception(result);
		}
		userService.changePassword(form);
	} catch (Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	return ResponseEntity.ok("Success");
}

}