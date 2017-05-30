package com.lechuspring.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cliente")
public class Cliente {

	@Id
	@Column(name="ID_CLIENTE")
	private String id;
	
	@Column(name="NB_CLIENTE")
	private String nombre;
	
	@Column(name="APELLIDO")
	private String apellido;
	
	@Column(name="IMPORTE_TOTAL")
	private Double importeTotal;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_LUGAR")
	private Lugar lugar;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	@JsonIgnore
	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Lugar getLugar() {
		return lugar;
	}

	@JsonIgnore
	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
	
	
}
