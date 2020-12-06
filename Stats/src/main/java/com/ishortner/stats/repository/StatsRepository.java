package com.ishortner.stats.repository;

import com.ishortner.stats.domain.Stats;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Stats entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {
	
	boolean existsByDomain(String domain);
	
	Stats findByDomain(String domain);
}
