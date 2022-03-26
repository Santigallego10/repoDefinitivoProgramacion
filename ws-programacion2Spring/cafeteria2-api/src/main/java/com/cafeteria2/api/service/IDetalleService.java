package com.cafeteria2.api.service;

import java.util.List;

import com.cafeteria2.api.entities.DetalleFacturaProducto;
import com.cafeteria2.api.exceptions.DetalleException;

public interface IDetalleService {
	
	public List<DetalleFacturaProducto> findAll();
	public void save(DetalleFacturaProducto detalleFacturaProducto);
	public DetalleFacturaProducto findById(int id);
	public void update(DetalleFacturaProducto detalleFacturaProducto, int id) throws DetalleException;
	public void delete (int id) throws DetalleException;

}
