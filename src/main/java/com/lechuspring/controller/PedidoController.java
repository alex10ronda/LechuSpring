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
	

	/**
	 * Método al que se llama para crear un nuevo pedido.
	 * 
	 * La url es /pedido/new.
	 * Usa el método POST
	 * Recibe un JSON (consume JSON --> RequestBody (parámetro que recibe)) 
	 *
	 * 
	 * @param pedidoStr: Cadena con formato JSON con la información del pedido a guardar
	 * @return: Cadena con formato JSON con la respuesta
	 */
	@RequestMapping(value="/new", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String newPedido(@RequestBody String pedidoStr){
		
		JSONObject response = null;
		try {
			response = new JSONObject();
			
			//Se pasa de String a objeto JSON
			JSONObject pedidoJSON = new JSONObject(pedidoStr);
			
			//Se obtiene el cliente a partir del id que recibimos
			Cliente cliente = clienteService.getClienteByID(pedidoJSON.getString("userId"));
			
			//Obtenemos el array de productos de la peticion
			JSONArray arrayProductos = pedidoJSON.getJSONArray("pedido");
			
			ProductoPedido productoPedido = new ProductoPedido();
			ComposicionPK compPK = new ComposicionPK();
			List<ProductoPedido> listaProductos = new ArrayList<>();
			Double precio = 0.0;
			
			//Recorremos ese JSON de productos
			for(int i=0; i< arrayProductos.length(); i++){
				
				//Se crea un objetoJSON para cada producto
				JSONObject productoJSON = arrayProductos.getJSONObject(i);
				
				//Solo recibimos el id por lo que buscamos a que objeto Producto pertenece
				Producto producto = productoService.getProductoByID(productoJSON.getInt("id"));
				//Se setea ese producto al objeto Composicion
				compPK.setProducto(producto);
				
				//Al objeto ProductoPedido, se setea esa composicion y cantidad
				productoPedido.setCantidad(productoJSON.getInt("cant"));
				productoPedido.setPk(compPK);
				
				//Se actualiza el precio total del producto
				precio = precio + (productoPedido.getPk().getProducto().getPrecio() * productoPedido.getCantidad());
				//Se añade ese Producto a la listaProductos
				listaProductos.add(productoPedido);
				
				//Se limpian los objetos
				productoPedido = new ProductoPedido();
				compPK = new ComposicionPK();
			}
			
			//Una vez que se ha formateado todos los productos que forman el pedido, se guarda el pedido y se nos devuelve el
			//nuevo importe que se introduce en la respuesta
			Double importe = pedidoService.guardarPedido(cliente, listaProductos, precio, new Date());
			
			response.put("isOK", true);
			response.put("importe", importe);	
			
		} catch (Exception e) {
			
			try {
				//En caso de error, se envia un false
				response.put("isOK", false);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return response.toString();
	}
	
	
	/**
	 * Método al que se llama para obtener los últimos pedidos de un cliente
	 * 
	 * la url es /pedido/getUltimos/id del cliente. Se recibe el id del cliente en la url(PathVariable)
	 * produce JSON... hay que especificarlo porque se envía un arrayJSON construido
	 * 
	 * @param idCliente
	 * @return
	 */
	@RequestMapping(value="/getUltimos/{id}", produces="application/json;charset=UTF-8")
	public @ResponseBody String obtenerUltimosPedidos(@PathVariable(value="id") String idCliente){
		
		try {
			
			JSONArray pedidos = this.pedidoService.getAllPedidosByUser(idCliente);
			return pedidos.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
