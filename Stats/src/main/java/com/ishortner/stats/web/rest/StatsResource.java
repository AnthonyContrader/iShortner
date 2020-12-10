package com.ishortner.stats.web.rest;

import com.ishortner.stats.service.StatsService;
import com.ishortner.stats.web.rest.errors.BadRequestAlertException;
import com.ishortner.stats.service.dto.StatsDTO;
import com.ishortner.stats.service.impl.StatsServiceImpl;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ishortner.stats.domain.Stats}.
 */
@RestController
@RequestMapping("/api")
public class StatsResource {

    private final Logger log = LoggerFactory.getLogger(StatsResource.class);

    private static final String ENTITY_NAME = "statsStats";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StatsService statsService;
    
    @Autowired
    private StatsServiceImpl statsServ;

    public StatsResource(StatsService statsService) {
        this.statsService = statsService;
    }
    
    @PostMapping("/stats/insert")
    public boolean insert(@RequestParam("url") String url) {	
    	return statsServ.insertOrUpdateCount(url);
    }
    
    @GetMapping("stats/getcount")
    public List<StatsDTO> getcount(){
    	return statsServ.getCount();
    }

    /**
     * {@code POST  /stats} : Create a new stats.
     *
     * @param statsDTO the statsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new statsDTO, or with status {@code 400 (Bad Request)} if the stats has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stats")
    public ResponseEntity<StatsDTO> createStats(@Valid @RequestBody StatsDTO statsDTO) throws URISyntaxException {
        log.debug("REST request to save Stats : {}", statsDTO);
        if (statsDTO.getId() != null) {
            throw new BadRequestAlertException("A new stats cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatsDTO result = statsService.save(statsDTO);
        return ResponseEntity.created(new URI("/api/stats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stats} : Updates an existing stats.
     *
     * @param statsDTO the statsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated statsDTO,
     * or with status {@code 400 (Bad Request)} if the statsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the statsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stats")
    public ResponseEntity<StatsDTO> updateStats(@Valid @RequestBody StatsDTO statsDTO) throws URISyntaxException {
        log.debug("REST request to update Stats : {}", statsDTO);
        if (statsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StatsDTO result = statsService.save(statsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, statsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stats} : get all the stats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stats in body.
     */
    @GetMapping("/stats")
    public List<StatsDTO> getAllStats() {
        log.debug("REST request to get all Stats");
        return statsService.findAll();
    }

    /**
     * {@code GET  /stats/:id} : get the "id" stats.
     *
     * @param id the id of the statsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stats/{id}")
    public ResponseEntity<StatsDTO> getStats(@PathVariable Long id) {
        log.debug("REST request to get Stats : {}", id);
        Optional<StatsDTO> statsDTO = statsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statsDTO);
    }

    /**
     * {@code DELETE  /stats/:id} : delete the "id" stats.
     *
     * @param id the id of the statsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stats/{id}")
    public ResponseEntity<Void> deleteStats(@PathVariable Long id) {
        log.debug("REST request to delete Stats : {}", id);
        statsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
