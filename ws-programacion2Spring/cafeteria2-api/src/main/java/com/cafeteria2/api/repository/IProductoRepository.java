package com.cafeteria2.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.cafeteria2.api.entities.Producto;

public interface IProductoRepository extends CrudRepository<Producto,Integer> {

}
