package com.lechuspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lechuspring.entities.Cliente;
import com.lechuspring.entities.Pedido;
import com.lechuspring.form.TpCartaForm;
import com.lechuspring.service.ClienteService;
import com.lechuspring.service.PedidoService;

@Controller
@RequestMapping(value="/")
public class LoginController {
	
	@Autowired
	private Session session;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/")
	public String onLoad(Model model){
		
		Cliente cliente;
		TpCartaForm cartaForm = new TpCartaForm();
		model.addAttribute("TpCartaForm",cartaForm);
		try {
			//cliente = this.clienteService.getClienteByID("10206796874275381");
			//List<Pedido> pedidos = pedidoService.getAllPedidosByUser(cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping(value="/setTp", method=RequestMethod.POST)
	public String setTpCarta(@ModelAttribute("TpCartaForm") TpCartaForm cartaForm){
		this.session.setTpCarta(cartaForm.getTpCarta());
		return "index";
	}
	

}
