package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.ServerConverter;
import it.contrader.dao.ServerRepository;
import it.contrader.dto.ServerDTO;
import it.contrader.model.Server;

@Service
public class ServerService extends AbstractService<Server, ServerDTO>{

	@Autowired
	private ServerConverter converter;
	
	@Autowired
	private ServerRepository repository;
}
