package com.lechuspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lechuspring.service.ClienteService;
import com.lechuspring.service.EstadoService;
import com.lechuspring.service.PedidoService;

@Controller
public class LoginController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/")
	public String onLoad(){
		
		pedidoService.guardarPedido();
	
		return "index";
	}

}
