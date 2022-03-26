package co.edu.cue.cafeteria.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_Cliente;
	private String nombre;
	private String numeroContacto;
	
	public Cliente() {
		
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
		return "Cliente [idCliente=" + id_Cliente + ", nombre=" + nombre + ", numeroContacto=" + numeroContacto + "]";
	}
	
	


}
