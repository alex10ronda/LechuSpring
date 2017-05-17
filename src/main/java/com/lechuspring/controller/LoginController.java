package com.lechuspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lechuspring.service.ClienteService;
import com.lechuspring.service.EstadoService;

@Controller
public class LoginController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EstadoService estadoService;
	
	@RequestMapping(value="/")
	public String onLoad(){
		
	
	
		return "index";
	}

}
