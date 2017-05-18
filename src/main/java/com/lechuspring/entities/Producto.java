package com.lechuspring.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {

	@Id
	@Column(name="ID_PRODUCTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name="NB_PRODUCTO")
	private String nombre;
	
	@Column(name="PRECIO")
	private Double precio;
	
	@Column(name="DESCRIPCION")
	private String desc;
	
	@Column(name="IMAGEN", columnDefinition = "blob")
	private String imagen;
	
	@Column(name="TP_CARTA")
	private int tpCarta;
	
	@Column(name="TP_PRODUCTO")
	private String tpProducto;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="pk.producto", cascade=CascadeType.ALL)
	private Set<ProductoPedido> listaProductos = new HashSet<ProductoPedido>();

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getTpCarta() {
		return tpCarta;
	}

	public void setTpCarta(int tpCarta) {
		this.tpCarta = tpCarta;
	}

	public String getTpProducto() {
		return tpProducto;
	}

	public void setTpProducto(String tpProducto) {
		this.tpProducto = tpProducto;
	}

	public Set<ProductoPedido> getListProductosPed() {
		return listaProductos;
	}

	public void setListProductosPed(Set<ProductoPedido> listProductosPed) {
		this.listaProductos = listProductosPed;
	}
	
	
}
