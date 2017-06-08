package com.lechuspring.dao;

import com.lechuspring.entities.Cliente;

public interface ClienteDAO {
	
	public Cliente findClienteById(String id) throws Exception; 
	public void save(Cliente cliente) throws Exception;
}
