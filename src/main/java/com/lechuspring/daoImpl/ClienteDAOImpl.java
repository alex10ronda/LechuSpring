package com.lechuspring.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lechuspring.dao.ClienteDAO;
import com.lechuspring.entities.Cliente;

@Repository
public class ClienteDAOImpl implements ClienteDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Cliente cliente) {
	
		this.sessionFactory.getCurrentSession().saveOrUpdate(cliente);
	}

}
