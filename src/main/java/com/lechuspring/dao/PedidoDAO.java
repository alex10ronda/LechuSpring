package com.lechuspring.dao;

import java.util.List;

import com.lechuspring.entities.Pedido;

public interface PedidoDAO {

	public void save(Pedido pedido);
	
	public List<Pedido> getAllPedido();
}
