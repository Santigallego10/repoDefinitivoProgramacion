package com.cafeteria2.api.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Cliente")
public class Cliente {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCliente")
	private int idCliente;
	@NotEmpty
	@Column(name="nombre")
	private String nombre;
	@NotEmpty
	@Size(min = 10, max = 20)
	@Column(name="numeroContacto")
	private String numeroContacto;
    @JsonIgnoreProperties(value={"cliente", "hybernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToMany(fetch=FetchType.LAZY, mappedBy="cliente", cascade=CascadeType.ALL)
	private List<Factura> facturas;
	
	public Cliente() {
		
	}

	

	public int getIdCliente() {
		return idCliente;
	}



	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}



	public List<Factura> getFacturas() {
		return facturas;
	}



	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	
	



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", numeroContacto=" + numeroContacto + "]";
	}
	
	


}

