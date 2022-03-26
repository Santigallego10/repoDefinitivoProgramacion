package com.cafeteria2.api.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="detalleFacturaProducto")
public class DetalleFacturaProducto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idDetalleFacturaProducto")
	private int idDetalleFacturaProducto;
	@JsonIgnoreProperties(value={"detalles",  "hibernateLazyInitializer", "handler"}, allowSetters=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="producto_idProducto")
	private Producto producto;
	@JsonIgnoreProperties(value={"facturas",  "hibernateLazyInitializer", "handler"}, allowSetters=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Factura_idFactura")
	private Factura factura;
	
	public DetalleFacturaProducto() {
		
	}
	
	
	
	
	public Factura getFactura() {
		return factura;
	}



	public void setFactura(Factura factura) {
		this.factura = factura;
	}


	

	public int getIdDetalleFacturaProducto() {
		return idDetalleFacturaProducto;
	}
	public void setIdDetalleFacturaProducto(int idDetalleFacturaProducto) {
		this.idDetalleFacturaProducto = idDetalleFacturaProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
	
	
	
}
