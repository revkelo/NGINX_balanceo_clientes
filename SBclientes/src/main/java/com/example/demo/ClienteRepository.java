package com.example.demo;

import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends CrudRepository<ClienteVO, Integer> {

	public List<ClienteVO> findAll();

}
