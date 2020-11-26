package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Server;
import it.contrader.model.Url;

@Repository
@Transactional
public interface ServerRepository extends CrudRepository<Server, Long>{

	List<Server> findAllByFkidurl(Long id);
}