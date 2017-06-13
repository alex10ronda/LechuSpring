package com.lechuspring.serviceImpl;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lechuspring.dao.ClienteDAO;
import com.lechuspring.entities.Cliente;
import com.lechuspring.entities.Lugar;
import com.lechuspring.service.ClienteService;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteDAO clienteDAO;
	
	public JSONObject guardarCliente(Cliente cliente) throws Exception {
		
		JSONObject response = new JSONObject();
		
		Cliente clienteFound = clienteDAO.findClienteById(cliente.getId());
		if(clienteFound == null){
			cliente.setImporteTotal(0.0);
			clienteDAO.save(cliente);
			response.put("isOK", true);
			response.put("total", 0.0);
			
		}else{
			response.put("isOK", true);
			response.put("total", clienteFound.getImporteTotal());
		}
		
		return response;
	}

	@Override
	public Cliente getClienteByID(String id) throws Exception {
	
		return this.clienteDAO.findClienteById(id);
		
	}
	
	
	

}
