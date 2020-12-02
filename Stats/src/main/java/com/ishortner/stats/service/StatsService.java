package com.ishortner.stats.service;

import com.ishortner.stats.service.dto.StatsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ishortner.stats.domain.Stats}.
 */
public interface StatsService {

    /**
     * Save a stats.
     *
     * @param statsDTO the entity to save.
     * @return the persisted entity.
     */
    StatsDTO save(StatsDTO statsDTO);

    /**
     * Get all the stats.
     *
     * @return the list of entities.
     */
    List<StatsDTO> findAll();


    /**
     * Get the "id" stats.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StatsDTO> findOne(Long id);

    /**
     * Delete the "id" stats.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
