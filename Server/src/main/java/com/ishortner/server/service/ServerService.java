package com.ishortner.server.service;

import com.ishortner.server.service.dto.ServerDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ishortner.server.domain.Server}.
 */
public interface ServerService {

    /**
     * Save a server.
     *
     * @param serverDTO the entity to save.
     * @return the persisted entity.
     */
    ServerDTO save(ServerDTO serverDTO);

    /**
     * Get all the servers.
     *
     * @return the list of entities.
     */
    List<ServerDTO> findAll();


    /**
     * Get the "id" server.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServerDTO> findOne(Long id);

    /**
     * Delete the "id" server.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
