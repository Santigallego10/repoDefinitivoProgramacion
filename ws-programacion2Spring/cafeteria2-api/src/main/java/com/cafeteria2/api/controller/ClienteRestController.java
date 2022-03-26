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

import com.cafeteria2.api.entities.Cliente;
import com.cafeteria2.api.exceptions.ClienteException;
import com.cafeteria2.api.persistence.CafeteriaUtil;
import com.cafeteria2.api.service.ClienteServiceImpl;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/cafeteria")
public class ClienteRestController {
	
	public static final String RUTA_ARCHIVO_LOG="/Users/santiagogallego/Desktop/UNIVERSIDAD/PROGRAMACION/DT/cursos/ws_programacion/cursos/cursos/cursos-java2/README.md/cursos-java/ws-programacion2Spring/cafeteria2-api/src/main/java/com/cafeteria2/api/resources/Cafeteria2Log.txt";
	
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@GetMapping("/clientes")
	public List<Cliente> getClients(){
		try {
			CafeteriaUtil.guardarRegistroLog("Lista de empleados mostrada",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return clienteServiceImpl.findAll();
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public void createCliente(@RequestBody @Valid Cliente cliente){		
		try {
			CafeteriaUtil.guardarRegistroLog("Cliente creado:"+cliente.getNombre(),1,"PostMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		clienteServiceImpl.save(cliente);
		
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> getClientes(@PathVariable int id) {
		Map<String, Object> response= new HashMap<>();
		Cliente cliente=null;
		
		try {
			cliente=clienteServiceImpl.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error de conexion en la base de datos");
			response.put("mensaje", e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if (cliente==null) {
			response.put("mensaje", "El cliente con ID: ".concat(""+id).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			CafeteriaUtil.guardarRegistroLog("Cliente consultado:"+cliente.getNombre(),1,"GettMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Cliente> (cliente, HttpStatus.OK);
	}
	
	@PutMapping("clientes/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateCliente(@RequestBody Cliente cliente, @PathVariable int id) {
		try {
			CafeteriaUtil.guardarRegistroLog("Cliente moodificado:"+cliente.getNombre(),1,"PostMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			clienteServiceImpl.update(cliente, id);
		} catch (ClienteException e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCliente(@PathVariable int id) {
		try {
			clienteServiceImpl.delete(id);
		} catch (ClienteException e) {
			e.printStackTrace();
		}
		try {
			CafeteriaUtil.guardarRegistroLog("Cliente eliminado:"+getClientes(id),1,"PostMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}
