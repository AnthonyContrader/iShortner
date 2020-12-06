package com.ishortner.server.repository;

import com.ishortner.server.domain.Server;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Server entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {	
	
	Server findAllByFkurl(Long id);
	
}
