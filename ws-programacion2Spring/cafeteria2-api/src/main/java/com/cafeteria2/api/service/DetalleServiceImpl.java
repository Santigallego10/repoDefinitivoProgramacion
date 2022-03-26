package com.cafeteria2.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria2.api.entities.DetalleFacturaProducto;
import com.cafeteria2.api.exceptions.DetalleException;
import com.cafeteria2.api.repository.IDetalleRepository;

@Service
public class DetalleServiceImpl implements IDetalleService{
	
	@Autowired
	IDetalleRepository detalleRepository;

	@Override
	@Transactional
	public List<DetalleFacturaProducto> findAll() {
		return (List<DetalleFacturaProducto>)detalleRepository.findAll();
	}

	@Override
	public void save(DetalleFacturaProducto detalle) {
		detalleRepository.save(detalle);
		
	}

	@Override
	public DetalleFacturaProducto findById(int id) {
		return detalleRepository.findById(id).orElse(null);
	}

	@Override
	public void update(DetalleFacturaProducto detalle, int id) throws DetalleException {
		if(findById(id) == null)
			throw new DetalleException("El detalle no existe");
		
		detalleRepository.save(detalle);
	}


	@Override
	public void delete(int id) throws DetalleException{
		DetalleFacturaProducto detalle = null;
		detalle = findById(id);
		
		if(detalle == null)
			throw new DetalleException("El detalle no existe");
		
		detalleRepository.delete(detalle);
    }
}
