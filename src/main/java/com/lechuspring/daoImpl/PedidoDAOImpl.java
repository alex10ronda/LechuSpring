package com.lechuspring.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lechuspring.dao.PedidoDAO;
import com.lechuspring.entities.Cliente;
import com.lechuspring.entities.Pedido;

@Repository
public class PedidoDAOImpl implements PedidoDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public void save(Pedido pedido) {
		
		this.sessionFactory.getCurrentSession().save(pedido);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> getAllPedido(){
		return this.sessionFactory.getCurrentSession().createCriteria(Pedido.class).list();
	}

	
	@SuppressWarnings("unchecked")
	public List<Pedido> getLastPedidosByUser(Cliente cliente) throws Exception {
		
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Pedido pedido where pedido.cliente = :cliente order by(pedido.fecha) desc");
		query.setParameter("cliente", cliente);

		query.setMaxResults(10);
		return query.list();
	}
	
	

}
