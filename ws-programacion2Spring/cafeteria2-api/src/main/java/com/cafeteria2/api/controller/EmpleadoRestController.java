package com.cafeteria2.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.cafeteria2.api.entities.Empleado;
import com.cafeteria2.api.entities.Empleado;
import com.cafeteria2.api.exceptions.EmpleadoException;
import com.cafeteria2.api.persistence.CafeteriaUtil;
import com.cafeteria2.api.repository.IDetalleRepository;
import com.cafeteria2.api.exceptions.EmpleadoException;
import com.cafeteria2.api.service.ConsultaServiceImpl;
import com.cafeteria2.api.service.EmpleadoServiceImpl;
import com.cafeteria2.api.service.EmpleadoServiceImpl;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/cafeteria")
public class EmpleadoRestController {
	
	@Autowired
	private EmpleadoServiceImpl empleadoServiceImpl;
	
	@Autowired
	private ConsultaServiceImpl consultaServiceImpl;
	
	public static final String RUTA_ARCHIVO_LOG="/Users/santiagogallego/Desktop/UNIVERSIDAD/PROGRAMACION/DT/cursos/ws_programacion/cursos/cursos/cursos-java2/README.md/cursos-java/ws-programacion2Spring/cafeteria2-api/src/main/java/com/cafeteria2/api/resources/Cafeteria2Log.txt";

	
	@GetMapping("/empleados")
	public List<Empleado> getClients(){
		try {
			CafeteriaUtil.guardarRegistroLog("Lista de empleados mostrada",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return empleadoServiceImpl.findAll();
	}
	
	@PostMapping("/empleados")
	@ResponseStatus(HttpStatus.CREATED)
	public void createEmpleado(@RequestBody Empleado empleado) {
		try {
			CafeteriaUtil.guardarRegistroLog("Empleado creado",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		empleadoServiceImpl.save(empleado);
	}
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> getEmpleados(@PathVariable int id) {
		Map<String, Object> response= new HashMap<>();
		Empleado empleado=null;
		
		try {
			CafeteriaUtil.guardarRegistroLog("Lista de empleados mostrada",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			empleado=empleadoServiceImpl.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error de conexion en la base de datos");
			response.put("mensaje", e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if (empleado==null) {
			response.put("mensaje", "El empleado con ID: ".concat(""+id).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Empleado> (empleado, HttpStatus.OK);
	}
	
	@PutMapping("empleados/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateEmpleado(@RequestBody Empleado empleado, @PathVariable int id) {
		try {
			CafeteriaUtil.guardarRegistroLog("Empleado actualizado",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			empleadoServiceImpl.update(empleado, id);
		} catch (EmpleadoException e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("empleados/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmpleado(@PathVariable int id) {
		try {
			CafeteriaUtil.guardarRegistroLog("Empleado eliminado",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			empleadoServiceImpl.delete(id);
		} catch (EmpleadoException e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/empleados/consulta/{nombre}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Empleado> getConsulta(@PathVariable String nombre) {
		
		return consultaServiceImpl.consultar(nombre);
		
	}

}
