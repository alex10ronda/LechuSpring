package com.lechuspring.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lechuspring.dao.ProductoDAO;
import com.lechuspring.entities.Producto;
import com.lechuspring.service.ProductoService;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDAO productoDAO;
	
	public List<Producto> getAllProducts() {
		return productoDAO.findAll();
	}

	public List<Producto> getAllFood() {
		return productoDAO.findAllComida();
	}

	public List<Producto> getAllDrinks() {
		return productoDAO.findAllBebida();
	}

}
