package com.cafeteria2.api.entities;

import java.sql.Date;
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
@Table(name = "Factura")
public class Factura {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idFactura")
	private int idFactura;
	@Column(name="empleado_string")
	private String empleado_string;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="recibo")
	private String recibo;
    @JsonIgnoreProperties(value={"facturas",  "hibernateLazyInitializer", "handler"}, allowSetters=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cliente_idCliente")
	private Cliente cliente;
    @JsonIgnoreProperties(value={"facturas",  "hibernateLazyInitializer", "handler"}, allowSetters=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="empleado_idEmpleado")
	private Empleado empleado;
    @JsonIgnoreProperties(value={"factura", "hybernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToMany(fetch=FetchType.LAZY, mappedBy="factura", cascade=CascadeType.ALL)
	private List<DetalleFacturaProducto> detalles;
	
	public Factura() {
		
	}
	
	
	
	
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public String getEmpleado_string() {
		return empleado_string;
	}
	public void setEmpleado_string(String empleado_string) {
		this.empleado_string = empleado_string;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getRecibo() {
		return recibo;
	}
	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	

	public List<DetalleFacturaProducto> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFacturaProducto> detalles) {
		this.detalles = detalles;
	}
	
	

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", empleado_string=" + empleado_string + ", fecha=" + fecha
				+ ", recibo=" + recibo + ", cliente=" + cliente + ", empleado=" + empleado + "]";
	}
	
	
	
	

}
