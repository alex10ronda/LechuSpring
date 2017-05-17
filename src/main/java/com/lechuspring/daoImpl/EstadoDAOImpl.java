package com.lechuspring.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.lechuspring.dao.EstadoDAO;
import com.lechuspring.entities.Estado;

@Repository
public class EstadoDAOImpl implements EstadoDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public void save() {
		
		Estado estado = new Estado();
		estado.setNombre("En proceso");
		
		this.sessionFactory.getCurrentSession().save(estado);
	}

	public List<Estado> findAll() {
		
		return this.sessionFactory.getCurrentSession().createCriteria(Estado.class).list();
		
	}
	
	

}
