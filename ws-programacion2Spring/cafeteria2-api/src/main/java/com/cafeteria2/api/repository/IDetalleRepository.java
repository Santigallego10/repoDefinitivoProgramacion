package com.cafeteria2.api.repository;
import org.springframework.data.repository.CrudRepository;

import com.cafeteria2.api.entities.Cliente;
import com.cafeteria2.api.entities.DetalleFacturaProducto;



public interface IDetalleRepository extends CrudRepository<DetalleFacturaProducto,Integer>{

}  
