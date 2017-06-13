package com.lechuspring.service;

import java.util.List;

import com.lechuspring.entities.Producto;

public interface ProductoService {
	
	public List<Producto> getAllProducts();
	
	public List<Producto> getAllFood();
	
	public List<Producto> getAllDrinks();
	
	public Producto getProductoByID(int id) throws Exception;
}
