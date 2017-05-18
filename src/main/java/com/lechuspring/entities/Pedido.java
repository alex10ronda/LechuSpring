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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	private Estado estao;
	
	@Column(name="IMPORTE")
	private Double importe;
	
	/*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "composicion_pedido", joinColumns = {
		@JoinColumn(name="ID_PEDIDO")}, inverseJoinColumns={ @JoinColumn(name ="ID_PRODUCTO")})*/
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="pk.pedido", cascade=CascadeType.ALL)
	private Set<ProductoPedido> listaProductos = new HashSet<ProductoPedido>();

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

	public Estado getEstao() {
		return estao;
	}

	public void setEstao(Estado estao) {
		this.estao = estao;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Set<ProductoPedido> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(Set<ProductoPedido> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	
}
