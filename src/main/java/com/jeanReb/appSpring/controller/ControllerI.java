package com.jeanReb.appSpring.controller;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.jeanReb.appSpring.entity.Subject;
import com.jeanReb.appSpring.entity.Teacher;

import com.jeanReb.appSpring.repository.Subjects;
import com.jeanReb.appSpring.repository.TeacherRepository;

import com.jeanReb.appSpring.service.SubjectsService;
import com.jeanReb.appSpring.service.TeacherService;

@Controller
public class ControllerI {

	@Autowired
	Subjects repositoryS;
	
	@Autowired
	SubjectsService service;
	
	@Autowired
	TeacherService serviceT;
	
	@Autowired
	TeacherRepository repositoryT;
	
	

	
	@RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            
            return "principalAdmin";
        }
        return "redirect:/principal/";
    }
	
	@GetMapping("/principal")
	public String principal (Model model) {
		
		
		model.addAttribute("materias", repositoryS.findAll());
		
		return "principal";
		
	}
	
	
	@GetMapping("/profesores")
	public String profesores (Model model) {
		
		model.addAttribute("profesor" , new Teacher());
		model.addAttribute("profesores", repositoryT.findAll());
		return "listaP";
		
	}
	
	
	@GetMapping("/nuevoPr")
	public String nuevoProfesor
	(Model model) {

	   model.addAttribute("profesor" , new Teacher());
	   return "nuevoP";

	}
	
	@GetMapping("/nuevaS")
	public String nuevaMateria
	(Model model) {

	   model.addAttribute("subject" , new Subject());
	   model.addAttribute("profesores", repositoryT.findAll());
	   return "materia-form";

	}
	
@PostMapping("/nuevoProfesor")
	public String nuevoProfesor(
 Teacher teacher, Model model) {
		
		
			try {
				serviceT.createTeacher(teacher);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    return "redirect:/profesores";
	     
}
	
@GetMapping("/editarP/{id}")

public String editarProfesor(Model model, @PathVariable (name = "id")Long id) throws Exception{
	
Teacher teacherToEdit = serviceT.getById(id);
model.addAttribute("profesor", teacherToEdit);


return "nuevoP";

}	

@GetMapping("/editar/{id}")

	public String editarMateria(Model model, @PathVariable (name = "id")Long id) throws Exception{
		
	Subject sucjectAdmin = service.getById(id);
	model.addAttribute("subject", sucjectAdmin );
	
	
	return "materia-form";

}

@GetMapping("/inscribirse/{id}")
	
	public String inscribirse(Model model, @PathVariable (name = "id")Long id) throws Exception{
		
	Subject subjectToEdit = service.getById(id);
	
	
	model.addAttribute("subject", subjectToEdit);
		
     return "materia-form";


	}
	
@PostMapping("/save")
public String inscribirseUser(@Valid 
 Subject subject, Model model) throws Exception {

	service.updateSubject(subject);
	model.addAttribute("materias", repositoryS.findAll());
	return "redirect:/principal";
	
}


@GetMapping("/eliminarP/{id}")

public String deleteTeacher
(Model model, @PathVariable (name = "id")Long id) {
	
	
		try {
			serviceT.deleteTeacher(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
   return profesores(model);
}

@GetMapping("/eliminar/{id}")

public String deleteSubject
(Model model, @PathVariable (name = "id")Long id) {
	
	
		try {
			service.deleteSubject(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
   return principal(model);
}

@GetMapping("/cancel")

public String cancelEditUser(ModelMap model) {
	
	return "redirect:/principal";
}

@GetMapping("/nuevoP/cancelt")

public String cancelEditTeacher(ModelMap model) {
	
	model.addAttribute("profesores", repositoryT.findAll());
	
	return "redirect:/listaP";
}

	
}
