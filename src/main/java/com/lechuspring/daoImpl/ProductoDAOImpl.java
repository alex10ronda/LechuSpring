package com.lechuspring.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lechuspring.dao.ProductoDAO;
import com.lechuspring.entities.Producto;

@Repository
public class ProductoDAOImpl implements ProductoDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Producto> findAll() {
		return this.sessionFactory.getCurrentSession().createCriteria(Producto.class).list();
	}

	public List<Producto> findAllComida() {
		String hql = "FROM Producto p WHERE p.tpProducto = 'Comida'";
		return this.sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	public List<Producto> findAllBebida() {
		String hql = "FROM Producto p WHERE p.tpProducto = 'Bebida'";
		return this.sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	
	public Producto findProductoById(int id) throws Exception {
		return (Producto) this.sessionFactory.getCurrentSession().get(Producto.class, id);
		
	}


	public List<Producto> findAllComidaByType(int type) throws Exception {
		
		String hql = "FROM Producto p WHERE p.tpProducto = 'Comida' AND tpCarta >= :type ";
		List<Producto> result =  this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("type", type).list();
		
		return result ;
	}

}
