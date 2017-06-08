package com.lechuspring.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lechuspring.dao.PedidoDAO;
import com.lechuspring.entities.Pedido;

@Repository
public class PedidoDAOImpl implements PedidoDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public void save(Pedido pedido) {
		
		this.sessionFactory.getCurrentSession().save(pedido);
		
	}
	
	public List<Pedido> getAllPedido(){
		return this.sessionFactory.getCurrentSession().createCriteria(Pedido.class).list();
	}
	
	

}
