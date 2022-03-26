package com.cafeteria2.api.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cafeteria2.api.entities.Factura;
import com.cafeteria2.api.exceptions.FacturaException;
import com.cafeteria2.api.repository.IFacturaRepository;

@Service
public class FacturaServiceImpl implements IFacturaService{

	@Autowired
	IFacturaRepository facturaRepository;

	@Override
	@Transactional
	public List<Factura> findAll() {
		return (List<Factura>)facturaRepository.findAll();
	}

	@Override
	public void save(Factura factura) {
		facturaRepository.save(factura);
		
	}

	@Override
	public Factura findById(int id) {
		return facturaRepository.findById(id).orElse(null);
	}

	@Override
	public void update(Factura factura, int id) throws FacturaException {
		if(findById(id) == null)
			throw new FacturaException("El factura no existe");
		
		facturaRepository.save(factura);
	}


	@Override
	public void delete(int id) throws FacturaException{
		Factura factura = null;
		factura = findById(id);
		
		if(factura == null)
			throw new FacturaException("El factura no existe");
		
		facturaRepository.delete(factura);
    }
	
	public FacturaServiceImpl() {
		
	}	
}


