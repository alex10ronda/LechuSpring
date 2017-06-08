package com.lechuspring.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lechuspring.dao.PedidoDAO;
import com.lechuspring.dao.ProductoDAO;
import com.lechuspring.entities.Cliente;
import com.lechuspring.entities.ComposicionPK;
import com.lechuspring.entities.Estado;
import com.lechuspring.entities.Pedido;
import com.lechuspring.entities.Producto;
import com.lechuspring.entities.ProductoPedido;
import com.lechuspring.service.PedidoService;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService{

	@Autowired
	private PedidoDAO pedidoDAO;
	
	@Autowired 
	private ProductoDAO productoDAO;
	
	public void guardarPedido() {
		
		List<Producto> listProducto = productoDAO.findAll();
		Producto product = listProducto.get(0);
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Alejandro");
		cliente.setId("A1");

		Estado estado = new Estado();
		estado.setNombre("En proceso");
		estado.setId(1);

		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setImporte(0.0);
		pedido.setEstao(estado);
		
		ProductoPedido productoPedido1 = new ProductoPedido();
		ProductoPedido productoPedido2 = new ProductoPedido();
		ComposicionPK composicionPK1 = new ComposicionPK();
		ComposicionPK composicionPK2 = new ComposicionPK();
		
		Set<ProductoPedido> lista = new HashSet<ProductoPedido>();
		
		composicionPK1.setPedido(pedido);
		composicionPK1.setProducto(listProducto.get(0));
		productoPedido1.setPk(composicionPK1);
		productoPedido1.setCantidad(1);
		
		
		composicionPK2.setPedido(pedido);
		composicionPK2.setProducto(listProducto.get(1));
		productoPedido2.setPk(composicionPK2);
		productoPedido2.setCantidad(2);
	
		lista.add(productoPedido1);
		lista.add(productoPedido2);
		
		pedido.setListaProductos(lista);
		
		this.pedidoDAO.save(pedido);
		
	}
	
	public List<Pedido> getAll(){
		return this.pedidoDAO.getAllPedido();
		
	}

}
