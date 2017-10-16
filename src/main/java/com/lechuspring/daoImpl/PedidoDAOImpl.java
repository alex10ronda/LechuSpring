package com.lechuspring.daoImpl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lechuspring.dao.PedidoDAO;
import com.lechuspring.entities.Cliente;
import com.lechuspring.entities.Pedido;
import com.lechuspring.entities.ProductoPedido;

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

	
	public List<Pedido> getAllPedidosByUser(Cliente cliente) throws Exception {
		
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Pedido where cliente = :cliente order by Fecha");
		query.setParameter("cliente", cliente);

		//Pedido pedido = (Pedido) query.list().get(0);
	
		query.setMaxResults(10);
		return query.list();
	}
	
	

}
