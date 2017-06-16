package com.lechuspring.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lechuspring.dao.ClienteDAO;
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
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	public Double guardarPedido(Cliente cliente, List<ProductoPedido> productos, Double precio)  throws Exception{
		
		List<Producto> listProducto = productoDAO.findAll();
		Producto product = listProducto.get(0);

		Estado estado = new Estado();
		estado.setNombre("En proceso");
		estado.setId(1);

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setImporte(precio);
		pedido.setEstado(estado);
		
		Set<ProductoPedido> lista = new HashSet<ProductoPedido>();
		
		for(ProductoPedido producto : productos){
			
			producto.getPk().setPedido(pedido);
			lista.add(producto);
		}
		
		pedido.setListaProductos(lista);
		
		this.pedidoDAO.save(pedido);
		
		Double importeActual = cliente.getImporteTotal();
		Double nuevoImporte = importeActual + precio;
		cliente.setImporteTotal(nuevoImporte);
		this.clienteDAO.save(cliente);
		
		return nuevoImporte;
		
	}
	
	public List<Pedido> getAll(){
		return this.pedidoDAO.getAllPedido();
		
	}

}
