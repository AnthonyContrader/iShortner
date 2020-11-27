package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.dto.StatsUrlDTO;
import it.contrader.model.StatsUrl;

@Repository
@Transactional
public interface StatsRepository extends CrudRepository<StatsUrl, Long>  {

	boolean existsByUrl(String longurl);
	
	StatsUrl findByUrl(String longurl);
	
	List<StatsUrl> findAllByOrderByCountDesc();
	
}
