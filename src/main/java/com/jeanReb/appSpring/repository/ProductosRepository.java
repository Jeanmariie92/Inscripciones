package com.jeanReb.appSpring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jeanReb.appSpring.entity.Productos;


@Repository
public interface ProductosRepository extends CrudRepository<Productos, Long> {

	
}
