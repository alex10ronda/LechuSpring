package com.lechuspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lechuspring.entities.Producto;
import com.lechuspring.service.ProductoService;

@Controller
@RequestMapping(value="/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@RequestMapping(value="/findAll")
	public @ResponseBody List<Producto> getAllProducts(){
		return productoService.getAllProducts();
	}
	
	@RequestMapping(value="/findAllFood")
	public @ResponseBody List<Producto> getAllFood(){
		return productoService.getAllFood();
	}
	
	@RequestMapping(value="/findAllDrink")
	public @ResponseBody List<Producto> getAllDrink(){
		return productoService.getAllDrinks();
	}

}
