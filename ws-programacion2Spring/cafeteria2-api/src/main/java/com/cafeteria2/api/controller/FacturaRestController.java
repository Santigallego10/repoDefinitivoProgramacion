package com.cafeteria2.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria2.api.entities.Factura;
import com.cafeteria2.api.exceptions.FacturaException;
import com.cafeteria2.api.persistence.CafeteriaUtil;
import com.cafeteria2.api.service.FacturaServiceImpl;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/cafeteria")
public class FacturaRestController {
	
	public static final String RUTA_ARCHIVO_LOG="/Users/santiagogallego/Desktop/UNIVERSIDAD/PROGRAMACION/DT/cursos/ws_programacion/cursos/cursos/cursos-java2/README.md/cursos-java/ws-programacion2Spring/cafeteria2-api/src/main/java/com/cafeteria2/api/resources/Cafeteria2Log.txt";
	
	@Autowired
	private FacturaServiceImpl facturaServiceImpl;
	
	@GetMapping("/facturas")
	public List<Factura> getFacturas(){
		try {
			CafeteriaUtil.guardarRegistroLog("Lista de fscturas mostrada",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return facturaServiceImpl.findAll();
	}
	
	@PostMapping("/facturas")
	@ResponseStatus(HttpStatus.CREATED)
	public void createFactura(@RequestBody @Valid Factura factura){		
		try {
			CafeteriaUtil.guardarRegistroLog("Factura creado:"+factura.getRecibo(),1,"PostMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		facturaServiceImpl.save(factura);
		
	}
	
	@GetMapping("/facturas/{id}")
	public ResponseEntity<?> getFacturas(@PathVariable int id) {
		Map<String, Object> response= new HashMap<>();
		Factura factura=null;
		
		try {
			factura=facturaServiceImpl.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error de conexion en la base de datos");
			response.put("mensaje", e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if (factura==null) {
			response.put("mensaje", "La factura con ID: ".concat(""+id).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			CafeteriaUtil.guardarRegistroLog("Factura consultada:"+factura.getRecibo(),1,"GettMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Factura> (factura, HttpStatus.OK);
	}
	
	@PutMapping("facturas/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateFactura(@RequestBody Factura factura, @PathVariable int id) {
		try {
			CafeteriaUtil.guardarRegistroLog("Factura moodificada:"+factura.getRecibo(),1,"PostMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			facturaServiceImpl.update(factura, id);
		} catch (FacturaException e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("facturas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFactura(@PathVariable int id) {
		try {
			facturaServiceImpl.delete(id);
		} catch (FacturaException e) {
			e.printStackTrace();
		}
		try {
			CafeteriaUtil.guardarRegistroLog("Factura eliminado:"+getFacturas(id),1,"PostMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}
