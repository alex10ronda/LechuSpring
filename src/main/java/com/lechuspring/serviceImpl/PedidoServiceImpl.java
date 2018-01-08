package com.lechuspring.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lechuspring.dao.ClienteDAO;
import com.lechuspring.dao.PedidoDAO;
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
	private ClienteDAO clienteDAO;
	

	public Double guardarPedido(Cliente cliente, List<ProductoPedido> productos, Double precio, Date fecha)  throws Exception{

		//Se crea un nuevo estado que es proceso... Esto es temporal hasta que se gestionen los estados
		Estado estado = new Estado();
		estado.setNombre("En proceso");
		estado.setId(1);

		//Se crea un nuevo objeto pedido
		Pedido pedido = new Pedido();
		
		//Se setea el cliente, el importe, la fecha que se reciben como parámetros
		pedido.setCliente(cliente);
		pedido.setImporte(precio);
		pedido.setEstado(estado);
		pedido.setFecha(fecha);
		
		
		//Se recorre la lista de productoPedidos seteando el Pedido al que pertenecen
		for(ProductoPedido producto : productos){
			producto.getPk().setPedido(pedido);
		}
		
		//Se setea esa lista de productos
		pedido.setListaProductos(productos);
		
		//Se persiste el objeto Pedido
		this.pedidoDAO.save(pedido);
		
		//Se calcula el nuevo importe y se setea en el cliente para actualizar esa tabla
		Double importeActual = cliente.getImporteTotal();
		Double nuevoImporte = importeActual + precio;
		cliente.setImporteTotal(nuevoImporte);
		this.clienteDAO.save(cliente);
		
		//Se devuelve el nuevo importe para enviarlo en la respuesta
		return nuevoImporte;
		
	}
	
	
	
	public List<Pedido> getAll(){
		return this.pedidoDAO.getAllPedido();
		
	}

	
	/*
	 * Método que devuelve un JSON con todos los últimos pedidos de un cliente.
	 * 
	 * El método formatea la lista recibida del DAO para enviar en la respuesta únicamente los datos que necesitamos
	 * 
	 * */
	public JSONArray getAllPedidosByUser(String idCliente) throws Exception {
		
		//Obtenemos el objeto cliente para realizar la búsqueda
		Cliente cliente = this.clienteDAO.findClienteById(idCliente);
		
		//Obtenemos los últimos pedidos del usuario
		List<Pedido> pedidos =  this.pedidoDAO.getLastPedidosByUser(cliente);
		
		JSONArray listaPedidos = new JSONArray();
		
		//Se crea el objeto json Pedido
		JSONObject pedido = null;

		//Se recorren todos los objetos pedidos
		for(Pedido p : pedidos){
			
			pedido = new JSONObject();
			
			//Se setea la fecha y el importe
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
			pedido.put("fecha", df.format(p.getFecha()));
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
