package com.cafeteria2.api.service;

import java.util.List;

import com.cafeteria2.api.entities.Cliente;
import com.cafeteria2.api.exceptions.ClienteException;

public interface IClienteService {

	public List<Cliente> findAll();
	public void save(Cliente cliente);
	public Cliente findById(int id);
	public void update(Cliente cliente, int id) throws ClienteException;
	public void delete (int id) throws ClienteException;
}
