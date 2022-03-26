package com.cafeteria2.api.service;

import java.util.List;

import com.cafeteria2.api.entities.Producto;
import com.cafeteria2.api.exceptions.ProductoException;

public interface IProductoService {
	
	public List<Producto> findAll();
	public void save(Producto producto);
	public Producto findById(int id);
	public void update(Producto producto, int id) throws ProductoException;
	public void delete (int id) throws ProductoException;


}
