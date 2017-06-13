package com.lechuspring.service;

import org.json.JSONObject;

import com.lechuspring.entities.Cliente;

public interface ClienteService {

	public JSONObject guardarCliente(Cliente cliente) throws Exception;
	
	public Cliente getClienteByID(String id) throws Exception;
}
