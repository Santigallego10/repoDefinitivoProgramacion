package co.edu.cue.cafeteria.api.services;

import java.util.List;

import co.edu.cue.cafeteria.api.entities.Cliente;
import co.edu.cue.cafeteria.api.exceptions.ClienteException;

public interface IClienteService {

	public List<Cliente> findAll();
	public void save(Cliente cliente);
	public Cliente findById(int id);
	public void update(Cliente cliente, int id) throws ClienteException;
	public void delete (int id) throws ClienteException;
}
