package com.cafeteria2.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria2.api.entities.Producto;
import com.cafeteria2.api.exceptions.ProductoException;
import com.cafeteria2.api.repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	IProductoRepository productoRepository;

	@Override
	@Transactional
	public List<Producto> findAll() {
		return (List<Producto>)productoRepository.findAll();
	}

	@Override
	public void save(Producto producto) {
		productoRepository.save(producto);
		
	}

	@Override
	public Producto findById(int id) {
		return productoRepository.findById(id).orElse(null);
	}

	@Override
	public void update(Producto producto, int id) throws ProductoException {
		if(findById(id) == null)
			throw new ProductoException("El producto no existe");
		
		productoRepository.save(producto);
	}


	@Override
	public void delete(int id) throws ProductoException{
		Producto producto = null;
		producto = findById(id);
		
		if(producto == null)
			throw new ProductoException("El producto no existe");
		
		productoRepository.delete(producto);
    }
	
	public ProductoServiceImpl() {
		
	}
}
