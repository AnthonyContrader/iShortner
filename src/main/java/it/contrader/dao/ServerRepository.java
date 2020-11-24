package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Server;

@Repository
@Transactional
public interface ServerRepository extends CrudRepository<Server, Long>{

	@Query(value= "SELECT * FROM server WHERE fkidurl IN(SELECT id FROM short_url WHERE fkurl = :idx)", nativeQuery = true)
	public List<Server> searchList(Long idx);

}