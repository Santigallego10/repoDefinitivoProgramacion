package com.cafeteria2.api.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProducto;
	private String nombre;
	private int cantidadExistencia;
	private double precioCompra;
	private double precioVenta;
	private double margenGanancia;
	@JsonIgnoreProperties(value={"producto", "hybernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToMany(fetch=FetchType.LAZY, mappedBy="producto", cascade=CascadeType.ALL)
	private List<DetalleFacturaProducto> detalles;
	
	public Producto() {
	}
	
	

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidadExistencia() {
		return cantidadExistencia;
	}

	public void setCantidadExistencia(int cantidadExistencia) {
		this.cantidadExistencia = cantidadExistencia;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public double getMargenGanancia() {
		return margenGanancia;
	}

	public void setMargenGanancia(double margenGanancia) {
		this.margenGanancia = margenGanancia;
	}
	
	

	public List<DetalleFacturaProducto> getDetalles() {
		return detalles;
	}


	public void setDetalles(List<DetalleFacturaProducto> detalles) {
		this.detalles = detalles;
	}


	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", apellido="
				+ ", cantidadExistencia=" + cantidadExistencia + ", precioCompra=" + precioCompra + ", precioVenta="
				+ precioVenta + ", margenGanancia=" + margenGanancia + "]";
	}
	
	

}
