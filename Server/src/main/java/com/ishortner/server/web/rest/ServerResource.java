package com.ishortner.server.web.rest;

import com.ishortner.server.service.ServerService;
import com.ishortner.server.web.rest.errors.BadRequestAlertException;
import com.ishortner.server.service.dto.ServerDTO;
import com.ishortner.server.service.impl.ServerServiceImpl;
import com.ishortner.server.service.mapper.ServerMapper;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ishortner.server.domain.Server}.
 */
@RestController
@RequestMapping("/api")
public class ServerResource {

    private final Logger log = LoggerFactory.getLogger(ServerResource.class);

    private static final String ENTITY_NAME = "serverServer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    
    private final ServerService serverService;
    
    @Autowired
    private ServerServiceImpl serv;
    
    @Autowired
    private ServerMapper map;

    public ServerResource(ServerService serverService) {
        this.serverService = serverService;
    }

    /**
     * {@code POST  /servers} : Create a new server.
     *
     * @param serverDTO the serverDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serverDTO, or with status {@code 400 (Bad Request)} if the server has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/servers")
    public ServerDTO createServer(@RequestParam("id") Long id) throws URISyntaxException {
    	return serv.generator(id);
    }

    /**
     * {@code PUT  /servers} : Updates an existing server.
     *
     * @param serverDTO the serverDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serverDTO,
     * or with status {@code 400 (Bad Request)} if the serverDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serverDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/servers")
    public ResponseEntity<ServerDTO> updateServer(@RequestBody ServerDTO serverDTO) throws URISyntaxException {
        log.debug("REST request to update Server : {}", serverDTO);
        if (serverDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServerDTO result = serverService.save(serverDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, serverDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /servers} : get all the servers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of servers in body.
     */
    @GetMapping("/servers")
    public List<ServerDTO> getAllServers() {
        log.debug("REST request to get all Servers");
        return serverService.findAll();
    }

    /**
     * {@code GET  /servers/:id} : get the "id" server.
     *
     * @param id the id of the serverDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serverDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/servers/{id}")
    public ResponseEntity<ServerDTO> getServer(@PathVariable Long id) {
        log.debug("REST request to get Server : {}", id);
        Optional<ServerDTO> serverDTO = serverService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serverDTO);
    }

    /**
     * {@code DELETE  /servers/:id} : delete the "id" server.
     *
     * @param id the id of the serverDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/servers/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable Long id) {
        log.debug("REST request to delete Server : {}", id);
        serverService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
