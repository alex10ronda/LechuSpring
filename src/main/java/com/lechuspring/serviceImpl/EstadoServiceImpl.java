package com.lechuspring.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lechuspring.dao.EstadoDAO;
import com.lechuspring.entities.Estado;
import com.lechuspring.service.EstadoService;

@Service
@Transactional
public class EstadoServiceImpl implements EstadoService{

	@Autowired
	private EstadoDAO estadoDAO;
	
	public void saveEstado() {
		
		estadoDAO.save();
		
	}

	public List<Estado> getAllEstados() {
		return estadoDAO.findAll();
	}

}
