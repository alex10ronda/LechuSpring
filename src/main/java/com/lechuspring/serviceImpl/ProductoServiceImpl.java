package com.lechuspring.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lechuspring.controller.Session;
import com.lechuspring.dao.ProductoDAO;
import com.lechuspring.entities.Producto;
import com.lechuspring.service.ProductoService;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDAO productoDAO;
	
	@Autowired
	private Session session;
	
	public List<Producto> getAllProducts() {
		return productoDAO.findAll();
	}

	public List<Producto> getAllFood() {
		return productoDAO.findAllComida();
	}

	public List<Producto> getAllDrinks() {
		return productoDAO.findAllBebida();
	}

	
	public Producto getProductoByID(int id) throws Exception {
		
		return this.productoDAO.findProductoById(id);
	}

	
	public List<Producto> getAllFoodByType() throws Exception {
		
		return this.productoDAO.findAllComidaByType(this.session.getTpCarta());
	}
	
	

}
