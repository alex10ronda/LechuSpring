package com.lechuspring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lechuspring.entities.Cliente;
import com.lechuspring.entities.ComposicionPK;
import com.lechuspring.entities.Pedido;
import com.lechuspring.entities.Producto;
import com.lechuspring.entities.ProductoPedido;
import com.lechuspring.service.ClienteService;
import com.lechuspring.service.PedidoService;
import com.lechuspring.service.ProductoService;

@Controller

@RequestMapping(value="/pedido")
public class PedidoController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private PedidoService pedidoService;
	

	@RequestMapping(value="/new", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String newPedido(@RequestBody String pedidoStr){
		
		JSONObject response = null;
		try {
			response = new JSONObject();
			JSONObject pedidoJSON = new JSONObject(pedidoStr);
			Cliente cliente = clienteService.getClienteByID(pedidoJSON.getString("userId"));
			JSONArray arrayProductos = pedidoJSON.getJSONArray("pedido");
			ProductoPedido productoPedido = new ProductoPedido();
			ComposicionPK compPK = new ComposicionPK();
			List<ProductoPedido> listaProductos = new ArrayList<>();
			Double precio = 0.0;
			
			for(int i=0; i< arrayProductos.length(); i++){
				JSONObject productoJSON = arrayProductos.getJSONObject(i);
				Producto producto = productoService.getProductoByID(productoJSON.getInt("id"));
				compPK.setProducto(producto);
				
				productoPedido.setCantidad(productoJSON.getInt("cant"));
				productoPedido.setPk(compPK);
				
				precio = precio + (productoPedido.getPk().getProducto().getPrecio() * productoPedido.getCantidad());
				listaProductos.add(productoPedido);
				
				productoPedido = new ProductoPedido();
				compPK = new ComposicionPK();
			}
			
			Double importe = pedidoService.guardarPedido(cliente, listaProductos, precio, new Date());
			
			response.put("isOK", true);
			response.put("importe", importe);
			
			
		} catch (Exception e) {
			
			try {
				response.put("isOK", false);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return response.toString();
	}
	
	
	@RequestMapping(value="/getUltimos/{id}")
	public @ResponseBody String  obtenerUltimosPedidos(@PathVariable(value="id") String idCliente){
		
		try {
			
			JSONArray pedidos = this.pedidoService.getAllPedidosByUser(idCliente);
			return pedidos.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
}
