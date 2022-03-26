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

import com.cafeteria2.api.entities.DetalleFacturaProducto;
import com.cafeteria2.api.exceptions.DetalleException;
import com.cafeteria2.api.persistence.CafeteriaUtil;
import com.cafeteria2.api.service.DetalleServiceImpl;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/cafeteria")
public class DetalleRestController {
	
	public static final String RUTA_ARCHIVO_LOG="/Users/santiagogallego/Desktop/UNIVERSIDAD/PROGRAMACION/DT/cursos/ws_programacion/cursos/cursos/cursos-java2/README.md/cursos-java/ws-programacion2Spring/cafeteria2-api/src/main/java/com/cafeteria2/api/resources/Cafeteria2Log.txt";
	
	@Autowired
	private DetalleServiceImpl detalleServiceImpl;
	
	@GetMapping("/detalles")
	public List<DetalleFacturaProducto> getDetalles(){
		try {
			CafeteriaUtil.guardarRegistroLog("Lista de detalles mostrada",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return detalleServiceImpl.findAll();
	}
	
	@PostMapping("/detalles")
	@ResponseStatus(HttpStatus.CREATED)
	public void createDetalle(@RequestBody @Valid DetalleFacturaProducto detalle){		
		try {
			CafeteriaUtil.guardarRegistroLog("Detalle creado:"+detalle.getIdDetalleFacturaProducto(),1,"PostMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		detalleServiceImpl.save(detalle);
		
	}
	
	@GetMapping("/detalles/{id}")
	public ResponseEntity<?> getDetalles(@PathVariable int id) {
		Map<String, Object> response= new HashMap<>();
		DetalleFacturaProducto detalle=null;
		
		try {
			detalle=detalleServiceImpl.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error de conexion en la base de datos");
			response.put("mensaje", e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if (detalle==null) {
			response.put("mensaje", "El detalle con ID: ".concat(""+id).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			CafeteriaUtil.guardarRegistroLog("Detalle consultado:"+detalle.getIdDetalleFacturaProducto(),1,"GettMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<DetalleFacturaProducto> (detalle, HttpStatus.OK);
	}
	
	@PutMapping("detalles/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateDetalle(@RequestBody DetalleFacturaProducto detalle, @PathVariable int id) {
		try {
			CafeteriaUtil.guardarRegistroLog("Detalle moodificado:"+detalle.getIdDetalleFacturaProducto(),1,"PostMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			detalleServiceImpl.update(detalle, id);
		} catch (DetalleException e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("detalles/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDetalle(@PathVariable int id) {
		try {
			detalleServiceImpl.delete(id);
		} catch (DetalleException e) {
			e.printStackTrace();
		}
		try {
			CafeteriaUtil.guardarRegistroLog("Detalle eliminado:"+getDetalles(id),1,"PostMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}
