package com.lechuspring.service;

import java.util.List;

import com.lechuspring.entities.Cliente;
import com.lechuspring.entities.Pedido;
import com.lechuspring.entities.ProductoPedido;

public interface PedidoService {

	public Double guardarPedido(Cliente cliente, List<ProductoPedido> productos, Double precio) throws Exception;
	
	public List<Pedido> getAll();
	
	public List<Pedido> getAllPedidosByUser(Cliente cliente) throws Exception;
}
