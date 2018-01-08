package com.lechuspring.dao;

import java.util.List;

import com.lechuspring.entities.Cliente;
import com.lechuspring.entities.Pedido;

public interface PedidoDAO {

	public void save(Pedido pedido);
	
	public List<Pedido> getAllPedido();
	
	public List<Pedido> getLastPedidosByUser(Cliente cliente) throws Exception;
}
