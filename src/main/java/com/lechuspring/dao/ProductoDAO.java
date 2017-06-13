package com.lechuspring.dao;

import java.util.List;

import com.lechuspring.entities.Producto;

public interface ProductoDAO {

	public List<Producto> findAll();
	
	public List<Producto> findAllComida();
	
	public List<Producto> findAllBebida();
	
	public Producto findProductoById(int id) throws Exception;
}
