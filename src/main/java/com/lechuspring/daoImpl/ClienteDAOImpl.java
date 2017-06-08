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
	
	public void save(Cliente cliente) throws Exception {
		
		try{
			this.sessionFactory.getCurrentSession().saveOrUpdate(cliente);
		} catch(Exception e){
			throw e;
		}
		
	}

	
	public Cliente findClienteById(String id) throws Exception {
		
		return (Cliente) this.sessionFactory.getCurrentSession().get(Cliente.class, id);
		
	}

}
