package com.lechuspring.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonFactory;
import com.lechuspring.entities.Cliente;
import com.lechuspring.service.ClienteService;

@Controller

@RequestMapping(value="/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addClient(@RequestBody Cliente cliente){
		
		JSONObject response = new JSONObject();
		
		try {
			response = clienteService.guardarCliente(cliente);
		} catch (Exception e) {
			try {
				response.put("isOK", false);
			} catch (JSONException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return response.toString();
	}
}
