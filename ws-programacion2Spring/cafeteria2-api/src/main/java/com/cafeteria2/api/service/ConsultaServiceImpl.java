package com.cafeteria2.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria2.api.repository.IConsultaRepository;

import com.cafeteria2.api.entities.Empleado;

@Service
public class ConsultaServiceImpl implements IConsultaService {
	
	@Autowired
	private IConsultaRepository consultaRepository;

	@Override
	public List<Empleado> consultar(String nombre) {
		return consultaRepository.findBySearchTermNative(nombre);
	}

}
