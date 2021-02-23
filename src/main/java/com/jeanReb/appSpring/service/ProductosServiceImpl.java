package com.jeanReb.appSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.jeanReb.appSpring.Exceptions.ProductOrIdNotFound;
import com.jeanReb.appSpring.Exceptions.UsernameOrIdNotFound;
import com.jeanReb.appSpring.entity.Productos;
import com.jeanReb.appSpring.entity.User;
import com.jeanReb.appSpring.repository.ProductosRepository;

@Service
public class ProductosServiceImpl implements ProductosService{

	@Autowired
	ProductosRepository repository;
	
	@Override
	public Iterable<Productos> getAllProducts() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	@Override
	public Productos getProductosById(Long id) throws ProductOrIdNotFound  {
		
		return repository.findById(id).orElseThrow(() -> new ProductOrIdNotFound("El producto no existe"));
		
	}
	


	@Override
	public Productos createProducto(Productos producto) throws Exception {
		
		producto = repository.save(producto);
		return producto;
	}
	
	@Override
	public Productos updateProducto(Productos producto) throws Exception {
		
		
		Productos toProduct = getProductosById(producto.getIdproductos());
		mapProduct(producto, toProduct);
		return repository.save(toProduct);
	
	}

	protected void mapProduct(Productos from, Productos to) {
		
		to.setNombre(from.getNombre());
		to.setPrecio(from.getPrecio());
		
		
		
	}

	@Override
	public void deleteProduct(Long id) throws ProductOrIdNotFound {
		
		Productos productD = getProductosById(id);
		repository.delete(productD);
		
	}

	
	
}
