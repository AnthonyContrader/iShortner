package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.ShortUrl;

@Repository
@Transactional
public interface ShortUrlRepository  extends CrudRepository<ShortUrl, Long> {
	
	boolean existsByLongurlAndFkurl(String longurl, Long id);
	
	ShortUrl findByShorturl(String shorturl);
	
	List<ShortUrl> findAllByFkurl(Long id);
}
