package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.ShortUrl;

@Repository
@Transactional
public interface ShortUrlRepository  extends CrudRepository<ShortUrl, Long> {
	
	boolean existsByLongurl(String longurl);
	
	ShortUrl findByShorturl(String shorturl);
	
}
