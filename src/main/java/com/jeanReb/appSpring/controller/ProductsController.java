package com.jeanReb.appSpring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jeanReb.appSpring.Exceptions.CustomeFieldValidationException;
import com.jeanReb.appSpring.Exceptions.ProductOrIdNotFound;
import com.jeanReb.appSpring.Exceptions.UsernameOrIdNotFound;
import com.jeanReb.appSpring.dto.ChangePasswordForm;
import com.jeanReb.appSpring.entity.Productos;
import com.jeanReb.appSpring.entity.User;
import com.jeanReb.appSpring.service.ProductosService;

@Controller
public class ProductsController {

	
	@Autowired
	ProductosService productosService;	
	
	@GetMapping("/productos")
	public String productos(Model model) {
	      
		 model.addAttribute("productForm", new Productos());
		model.addAttribute("productosList", productosService.getAllProducts());
		model.addAttribute("listTabP", "active");
		model.addAttribute("editModeP", "false");
		return "user-form/productos-view";
		
	}
	
	@PostMapping("/productForm")
	public String createProduct(@Valid @ModelAttribute("productForm") Productos producto, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {

			model.addAttribute("productForm", producto);
			model.addAttribute("formPTab", "active");
			
		}else {
			
			try {
				productosService.createProducto(producto);
				model.addAttribute("productForm", new Productos()); //Blanqueamos los cmapos del form
				model.addAttribute("listTabP","active"); //Redirigimos a la pestaña List
			} catch (CustomeFieldValidationException cfve) {
				result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
				model.addAttribute("productForm", producto);
				model.addAttribute("formPTab","active");
				model.addAttribute("productosList", productosService.getAllProducts());
				
				
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("productForm", producto);
				model.addAttribute("formPTab","active");
				model.addAttribute("productosList", productosService.getAllProducts());
				
			}
		}
		model.addAttribute("productosList", productosService.getAllProducts());
		return "user-form/productos-view";
	}
	
	
	@PostMapping("/editP")
	
	public String editProduct(@Valid @ModelAttribute("productForm") Productos producto, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {

			model.addAttribute("productForm", producto);
			model.addAttribute("formPTab", "active");
			model.addAttribute("editModeP", "true");
			
			
			
		}else {
			
			try {
				productosService.updateProducto(producto);
				model.addAttribute("productForm", new Productos()); //Blanqueamos los cmapos del form
				model.addAttribute("listTabP","active"); //Redirigimos a la pestaña List
		
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("productForm", producto);
				model.addAttribute("formPTab","active");
				model.addAttribute("editModeP", "true");
				model.addAttribute("productosList", productosService.getAllProducts());
				
			}
		}
		model.addAttribute("productosList", productosService.getAllProducts());
		return "user-form/productos-view";
	}
	
	
	@GetMapping("/editP/{id}")
	
	public String getEditP(Model model, @PathVariable (name = "id")Long id) throws Exception{
		
		
	Productos productToEdit = productosService.getProductosById(id);
		
	    model.addAttribute("productForm", productToEdit);
		model.addAttribute("formPTab", "active");
		model.addAttribute("editModeP", "true");
		return "user-form/productos-view";
		
	}
	
	@GetMapping("/productForm/cancel")
	
	public String cancelEditUser(ModelMap model) {
		
		return "redirect:/productos";
	}
	
	@GetMapping("/deleteP/{id}")
	
	public String deleteProduct(Model model, @PathVariable (name = "id")Long id) {
		
		
    try {
		productosService.deleteProduct(id);
	} catch (ProductOrIdNotFound e) {
		model.addAttribute("listErrorMessage", e.getMessage());
	}
			
		return "user-form/productos-view";
	
	}
	
	
	
}
