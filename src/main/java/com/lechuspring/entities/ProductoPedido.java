package com.lechuspring.entities;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="composicion_pedido")
@AssociationOverrides({
	@AssociationOverride(name="pk.producto", joinColumns = @JoinColumn(name="ID_PRODUCTO")),
	@AssociationOverride(name="pk.pedido", joinColumns = @JoinColumn(name="ID_PEDIDO"))
})
public class ProductoPedido {

	@EmbeddedId
	private ComposicionPK pk;
	
	@Column(name="CANTIDAD")
	private int cantidad;

	public ComposicionPK getPk() {
		return pk;
	}

	public void setPk(ComposicionPK pk) {
		this.pk = pk;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
