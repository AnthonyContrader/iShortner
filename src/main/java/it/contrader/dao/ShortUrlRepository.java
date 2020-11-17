package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.ShortUrl;
import it.contrader.model.User;

@Repository
@Transactional
public interface ShortUrlRepository  extends CrudRepository<ShortUrl, Long> {

	ShortUrl findByLongurl(String longurl);
}
