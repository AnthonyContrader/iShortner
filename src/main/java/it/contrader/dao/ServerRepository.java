package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Server;

@Repository
@Transactional
public interface ServerRepository extends CrudRepository<Server, Long>{
	
}