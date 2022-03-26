package com.cafeteria2.api.service;

import java.util.List;

import com.cafeteria2.api.entities.Empleado;

public interface IConsultaService {
	
	public List<Empleado> consultar(String nombre);

}
