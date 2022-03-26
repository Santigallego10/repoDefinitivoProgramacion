package com.cafeteria2.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.cafeteria2.api.entities.Empleado;

import java.util.List;
import java.util.Optional;
  
public interface IConsultaRepository extends Repository<Empleado, Long> {
  
	@Query(value = "SELECT * FROM Empleado t WHERE " +
            "LOWER(t.nombre) LIKE LOWER(CONCAT('%',:searchTerm, '%'))",
            nativeQuery = true
    )
    List<Empleado> findBySearchTermNative(@Param("searchTerm") String searchTerm);
}