package com.jeanReb.appSpring.service;

import com.jeanReb.appSpring.Exceptions.ProductOrIdNotFound;
import com.jeanReb.appSpring.Exceptions.UsernameOrIdNotFound;
import com.jeanReb.appSpring.entity.Productos;
import com.jeanReb.appSpring.entity.User;

public interface ProductosService {
	
	public Iterable<Productos> getAllProducts();
	
	public Productos getProductosById(Long id) throws ProductOrIdNotFound;

	public Productos createProducto(Productos producto) throws Exception;
	
	public Productos updateProducto(Productos producto) throws Exception;
	
	public void deleteProduct(Long id) throws ProductOrIdNotFound;
}
