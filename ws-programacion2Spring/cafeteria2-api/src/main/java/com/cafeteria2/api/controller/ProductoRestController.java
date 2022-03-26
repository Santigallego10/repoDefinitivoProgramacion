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

import com.cafeteria2.api.entities.Producto;
import com.cafeteria2.api.exceptions.ProductoException;
import com.cafeteria2.api.persistence.CafeteriaUtil;
import com.cafeteria2.api.service.ProductoServiceImpl;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/cafeteria")
public class ProductoRestController {
	
	public static final String RUTA_ARCHIVO_LOG="/Users/santiagogallego/Desktop/UNIVERSIDAD/PROGRAMACION/DT/cursos/ws_programacion/cursos/cursos/cursos-java2/README.md/cursos-java/ws-programacion2Spring/cafeteria2-api/src/main/java/com/cafeteria2/api/resources/Cafeteria2Log.txt";

	
	@Autowired
	private ProductoServiceImpl productoServiceImpl;
	
	@GetMapping("/productos")
	public List<Producto> getClients(){
		try {
			CafeteriaUtil.guardarRegistroLog("Lista de productos mostrada",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return productoServiceImpl.findAll();
	}
	
	@PostMapping("/productos")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProducto(@RequestBody Producto producto) {
		try {
			CafeteriaUtil.guardarRegistroLog("Producto creado",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		calcularMargen(producto);
		productoServiceImpl.save(producto);
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<?> getProductos(@PathVariable int id) {
		try {
			CafeteriaUtil.guardarRegistroLog("Procducto consultado",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> response= new HashMap<>();
		Producto producto=null;
		
		try {
			producto=productoServiceImpl.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error de conexion en la base de datos");
			response.put("mensaje", e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if (producto==null) {
			response.put("mensaje", "El producto con ID: ".concat(""+id).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Producto> (producto, HttpStatus.OK);
	}
	
	@PutMapping("productos/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateProducto(@RequestBody Producto producto, @PathVariable int id) {
		try {
			CafeteriaUtil.guardarRegistroLog("Producto modificado",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			productoServiceImpl.update(producto, id);
		} catch (ProductoException e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("productos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProducto(@PathVariable int id) {
		try {
			CafeteriaUtil.guardarRegistroLog("Producto eliminado",1,"GetMapping",RUTA_ARCHIVO_LOG);
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			productoServiceImpl.delete(id);
		} catch (ProductoException e) {
			e.printStackTrace();
		}
	}
	
	public void calcularMargen(Producto producto) {
		double margen;
		margen = producto.getPrecioVenta() - producto.getPrecioCompra();
		producto.setMargenGanancia(margen);
	}
}
