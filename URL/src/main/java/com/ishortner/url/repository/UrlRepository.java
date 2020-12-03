package com.ishortner.url.repository;

import com.ishortner.url.domain.Url;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Url entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UrlRepository extends CrudRepository<Url, Long> {
	
	boolean existsByShorturl(String s);
}
