package com.lechuspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lechuspring.entities.Cliente;
import com.lechuspring.entities.Pedido;
import com.lechuspring.service.ClienteService;
import com.lechuspring.service.PedidoService;

@Controller
public class LoginController {
	
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/")
	public String onLoad(){
		
		Cliente cliente;
		try {
			cliente = this.clienteService.getClienteByID("10206796874275381");
			List<Pedido> pedidos = pedidoService.getAllPedidosByUser(cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}

}
