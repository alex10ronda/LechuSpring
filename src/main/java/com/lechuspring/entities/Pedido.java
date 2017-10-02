package com.lechuspring.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="pedido")
public class Pedido {


	@Id
	@Column(name="ID_PEDIDO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@OneToOne
	@JoinColumn(name="ID_CLIENTE")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name="ST_PEDIDO")
	private Estado estado;
	
	@Column(name="IMPORTE")
	private Double importe;
	
	@Column(name="FECHA")
	private Date fecha;
	
	
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy="pk.pedido", cascade=CascadeType.ALL)
	private List<ProductoPedido> listaProductos = new ArrayList<ProductoPedido>();

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	@JsonIgnore
	public List<ProductoPedido> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<ProductoPedido> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	
}
