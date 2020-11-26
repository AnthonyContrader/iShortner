package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Url;

@Repository
@Transactional
public interface UrlRepository  extends CrudRepository<Url, Long> {
	
	boolean existsByLongurlAndFkurl(String longurl, Long id);
	
	boolean existsByLongurl(String longurl);
	
	boolean existsByShorturl(String shortUrl);
	
	Url findByShorturl(String shorturl);
	
	List<Url> findAllByFkurl(Long id);
}
