package com.lechuspring.serviceImpl;

import javax.transaction.Transactional;

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
	
	public void guardarCliente() {
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Alejandro");
		cliente.setApellido("Ruiz");
		cliente.setImporteTotal(0.0);
		cliente.setId("A1");
		
		Lugar lugar = new Lugar();
		lugar.setNombre("Agua-Aire");
		
		cliente.setLugar(lugar);
		
		clienteDAO.save(cliente);
		
	}

}
