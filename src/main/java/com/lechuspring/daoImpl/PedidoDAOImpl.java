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
		
		//Query query = this.sessionFactory.getCurrentSession().createQuery("Select pedido.Id, pedido.listaProductos  "
			//	+ "from Pedido pedido where pedido.cliente = :cliente");
		
		//Query query = this.sessionFactory.getCurrentSession().createQuery("from Product");
		
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Pedido pedido where pedido.cliente = :cliente");
		//Query query = this.sessionFactory.getCurrentSession().createSQLQuery("SELECT PEDIDO.ID_PEDIDO, COMPOSICION_PEDIDO.CANTIDAD , PRODUCTO.NB_PRODUCTO FROM PEDIDO JOIN COMPOSICION_PEDIDO JOIN PRODUCTO WHERE PEDIDO.ID_PEDIDO = COMPOSICION_PEDIDO.ID_PEDIDO AND COMPOSICION_PEDIDO.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO and PEDIDO.ID_CLIENTE = :cliente");
		query.setParameter("cliente", cliente);

		query.setMaxResults(10);
		return query.list();
	}
	
	

}
