package com.lechuspring.dao;

import java.util.List;

import com.lechuspring.entities.Estado;

public interface EstadoDAO {

	public void save();
	public List<Estado> findAll ();
}
