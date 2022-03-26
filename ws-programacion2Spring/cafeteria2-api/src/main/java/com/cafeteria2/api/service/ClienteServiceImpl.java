package com.cafeteria2.api.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cafeteria2.api.entities.Cliente;
import com.cafeteria2.api.exceptions.ClienteException;
import com.cafeteria2.api.repository.IClienteRepository;


@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	IClienteRepository clienteRepository;

	@Override
	@Transactional
	public List<Cliente> findAll() {
		return (List<Cliente>)clienteRepository.findAll();
	}

	@Override
	public void save(Cliente cliente) {
		clienteRepository.save(cliente);
		
	}

	@Override
	public Cliente findById(int id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public void update(Cliente cliente, int id) throws ClienteException {
		if(findById(id) == null)
			throw new ClienteException("El cliente no existe");
		
		clienteRepository.save(cliente);
	}


	@Override
	public void delete(int id) throws ClienteException{
		Cliente cliente = null;
		cliente = findById(id);
		
		if(cliente == null)
			throw new ClienteException("El cliente no existe");
		
		clienteRepository.delete(cliente);
    }

}
