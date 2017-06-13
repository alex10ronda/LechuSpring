package com.lechuspring.service;

import java.util.List;

import com.lechuspring.entities.Cliente;
import com.lechuspring.entities.Pedido;
import com.lechuspring.entities.ProductoPedido;

public interface PedidoService {

	public void guardarPedido(Cliente cliente, List<ProductoPedido> productos) throws Exception;
	
	public List<Pedido> getAll();
}
