package com.ishortner.url.service;

import com.ishortner.url.service.dto.UrlDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ishortner.url.domain.Url}.
 */
public interface UrlService {

    /**
     * Save a url.
     *
     * @param urlDTO the entity to save.
     * @return the persisted entity.
     */
    UrlDTO save(UrlDTO urlDTO);

    /**
     * Get all the urls.
     *
     * @return the list of entities.
     */
    List<UrlDTO> findAll();


    /**
     * Get the "id" url.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UrlDTO> findOne(Long id);

    /**
     * Delete the "id" url.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
