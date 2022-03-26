package com.cafeteria2.api.service;

import java.util.List;

import com.cafeteria2.api.entities.Factura;
import com.cafeteria2.api.exceptions.FacturaException;

public interface IFacturaService {
	
	public List<Factura> findAll();
	public void save(Factura factura);
	public Factura findById(int id);
	public void update(Factura factura, int id) throws FacturaException;
	public void delete (int id) throws FacturaException;

}
