package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<ClienteVO> getAll() {
		return repository.findAll();
	}

	public Optional<ClienteVO> getClienteVOById(int id) {
		return repository.findById(id);
	}

	public ClienteVO createClienteVO(ClienteVO ClienteVO) {
		return repository.save(ClienteVO);
	}

	public ClienteVO updateClienteVO(ClienteVO ClienteVODetails) {

		return repository.save(ClienteVODetails);
	}

	public void deleteClienteVO(int id) {
		repository.deleteById(id);
	}

	public boolean existsById(int id) {

		return repository.existsById(id);
	}
}
