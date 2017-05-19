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

}
