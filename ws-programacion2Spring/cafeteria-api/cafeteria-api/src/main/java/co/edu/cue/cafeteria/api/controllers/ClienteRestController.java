package co.edu.cue.cafeteria.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import co.edu.cue.cafeteria.api.entities.Cliente;
import co.edu.cue.cafeteria.api.exceptions.ClienteException;
import co.edu.cue.cafeteria.api.services.ClienteServiceImpl;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/cafeteria")
public class ClienteRestController {
	
	
	//http://localhost:8080/api/cafeteria/clientes
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@GetMapping("/clientes")
	public List<Cliente> getClients(){
		return clienteServiceImpl.findAll();
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public void createPatient(@RequestBody Cliente cliente) {
		clienteServiceImpl.save(cliente);
	}
	
	@PutMapping("clienteu/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updatePatient(@RequestBody Cliente cliente, @PathVariable int id) {
		try {
			clienteServiceImpl.update(cliente, id);
		} catch (ClienteException e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("cliented/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePatient(@PathVariable int id) {
		try {
			clienteServiceImpl.delete(id);
		} catch (ClienteException e) {
			e.printStackTrace();
		}
	}

}
