package com.lechuspring.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lechuspring.dao.ClienteDAO;
import com.lechuspring.dao.PedidoDAO;
import com.lechuspring.dao.ProductoDAO;
import com.lechuspring.entities.Cliente;
import com.lechuspring.entities.Estado;
import com.lechuspring.entities.Pedido;
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
	
	public Double guardarPedido(Cliente cliente, List<ProductoPedido> productos, Double precio, Date fecha)  throws Exception{

		Estado estado = new Estado();
		estado.setNombre("En proceso");
		estado.setId(1);

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setImporte(precio);
		pedido.setEstado(estado);
		pedido.setFecha(fecha);
		
		List<ProductoPedido> lista = new ArrayList<ProductoPedido>();
		
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

	
	
	@Override
	public JSONArray getAllPedidosByUser(String idCliente) throws Exception {
		
		Cliente cliente = this.clienteDAO.findClienteById(idCliente);
		List<Pedido> pedidos =  this.pedidoDAO.getAllPedidosByUser(cliente);
		
		JSONArray listaPedidos = new JSONArray();
		
		//Se crea el objeto json Pedido
		JSONObject pedido = null;
		
		for(Pedido p : pedidos){
			
			pedido = new JSONObject();
			
			//Se setea la fecha y el importe
			pedido.put("fecha", p.getFecha());
			pedido.put("importe", p.getImporte());
			
			//Se recorre la lista de productos del pedido y se van añadiendo al array detalle pedido
			JSONArray detallePedido = new JSONArray();
			for(int i=0; i<p.getListaProductos().size(); i++){
				JSONObject producto = new JSONObject();
				producto.put("producto", p.getListaProductos().get(i).getPk().getProducto().getNombre());
				producto.put("cantidad", p.getListaProductos().get(i).getCantidad());
				producto.put("precio", p.getListaProductos().get(i).getPk().getProducto().getPrecio());
				detallePedido.put(producto);
			}
			
			//Se añade esta lista de detalle al objeto pedido
			pedido.put("detalle", detallePedido);
			
			//Se añade a la lista el json pedido creado
			listaPedidos.put(pedido);
		}
		
		
		
		return listaPedidos;
		
	}

}
