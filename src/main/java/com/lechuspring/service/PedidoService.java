package com.lechuspring.service;

import java.util.List;

import com.lechuspring.entities.Pedido;

public interface PedidoService {

	public void guardarPedido();
	
	public List<Pedido> getAll();
}
