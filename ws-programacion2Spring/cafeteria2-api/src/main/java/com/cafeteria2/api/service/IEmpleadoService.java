package com.cafeteria2.api.service;

import java.util.List;

import com.cafeteria2.api.entities.Empleado;
import com.cafeteria2.api.exceptions.EmpleadoException;

public interface IEmpleadoService {
	
	public List<Empleado> findAll();
	public void save(Empleado empleado);
	public Empleado findById(int id);
	public void update(Empleado empleado, int id) throws EmpleadoException;
	public void delete (int id) throws EmpleadoException;

}
