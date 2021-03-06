package com.lechuspring.service;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;

import com.lechuspring.entities.Cliente;
import com.lechuspring.entities.Pedido;
import com.lechuspring.entities.ProductoPedido;

public interface PedidoService {

	public Double guardarPedido(Cliente cliente, List<ProductoPedido> productos, Double precio, Date fecha) throws Exception;
	
	public List<Pedido> getAll();
	
	public JSONArray getAllPedidosByUser(String idCliente) throws Exception;
}
