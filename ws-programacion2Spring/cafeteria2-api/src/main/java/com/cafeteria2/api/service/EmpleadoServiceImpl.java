package com.cafeteria2.api.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria2.api.entities.Empleado;
import com.cafeteria2.api.exceptions.EmpleadoException;
import com.cafeteria2.api.repository.IEmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

	@Autowired
	IEmpleadoRepository empleadoRepository;

	@Override
	@Transactional
	public List<Empleado> findAll() {
		return (List<Empleado>)empleadoRepository.findAll();
	}

	@Override
	public void save(Empleado empleado) {
		empleadoRepository.save(empleado);
		
	}

	@Override
	public Empleado findById(int id) {
		return empleadoRepository.findById(id).orElse(null);
	}

	@Override
	public void update(Empleado empleado, int id) throws EmpleadoException {
		if(findById(id) == null)
			throw new EmpleadoException("El empleado no existe");
		
		empleadoRepository.save(empleado);
	}


	@Override
	public void delete(int id) throws EmpleadoException{
		Empleado empleado = null;
		empleado = findById(id);
		
		if(empleado == null)
			throw new EmpleadoException("El empleado no existe");
		
		empleadoRepository.delete(empleado);
    }
	
	public EmpleadoServiceImpl() {
		
	}
}
