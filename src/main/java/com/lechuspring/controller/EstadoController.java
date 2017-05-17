package com.lechuspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lechuspring.dao.EstadoDAO;
import com.lechuspring.entities.Estado;
import com.lechuspring.service.EstadoService;

@Controller

@RequestMapping(value="/estado")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;

	@RequestMapping(value="/get")
	public @ResponseBody List<Estado> getEstados(){
		return estadoService.getAllEstados();
		
	}
}
