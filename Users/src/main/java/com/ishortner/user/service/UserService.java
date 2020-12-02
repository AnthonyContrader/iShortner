package com.ishortner.user.service;

import com.ishortner.user.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ishortner.user.domain.User}.
 */
public interface UserService {

    /**
     * Save a user.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    UserDTO save(UserDTO userDTO);
    
    UserDTO login(UserDTO userDTO);

    /**
     * Get all the users.
     *
     * @return the list of entities.
     */
    List<UserDTO> findAll();


    /**
     * Get the "id" user.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserDTO> findOne(Long id);

    /**
     * Delete the "id" user.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
