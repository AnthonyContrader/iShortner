package com.ishortner.url.web.rest;

import com.ishortner.url.domain.Url;
import com.ishortner.url.repository.UrlRepository;
import com.ishortner.url.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ishortner.url.domain.Url}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UrlResource {

    private final Logger log = LoggerFactory.getLogger(UrlResource.class);

    private static final String ENTITY_NAME = "urlUrl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UrlRepository urlRepository;

    public UrlResource(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    /**
     * {@code POST  /urls} : Create a new url.
     *
     * @param url the url to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new url, or with status {@code 400 (Bad Request)} if the url has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/urls")
    public ResponseEntity<Url> createUrl(@RequestBody Url url) throws URISyntaxException {
        log.debug("REST request to save Url : {}", url);
        if (url.getId() != null) {
            throw new BadRequestAlertException("A new url cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Url result = urlRepository.save(url);
        return ResponseEntity.created(new URI("/api/urls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /urls} : Updates an existing url.
     *
     * @param url the url to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated url,
     * or with status {@code 400 (Bad Request)} if the url is not valid,
     * or with status {@code 500 (Internal Server Error)} if the url couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/urls")
    public ResponseEntity<Url> updateUrl(@RequestBody Url url) throws URISyntaxException {
        log.debug("REST request to update Url : {}", url);
        if (url.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Url result = urlRepository.save(url);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, url.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /urls} : get all the urls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of urls in body.
     */
    @GetMapping("/urls")
    public List<Url> getAllUrls() {
        log.debug("REST request to get all Urls");
        return urlRepository.findAll();
    }

    /**
     * {@code GET  /urls/:id} : get the "id" url.
     *
     * @param id the id of the url to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the url, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/urls/{id}")
    public ResponseEntity<Url> getUrl(@PathVariable Long id) {
        log.debug("REST request to get Url : {}", id);
        Optional<Url> url = urlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(url);
    }

    /**
     * {@code DELETE  /urls/:id} : delete the "id" url.
     *
     * @param id the id of the url to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/urls/{id}")
    public ResponseEntity<Void> deleteUrl(@PathVariable Long id) {
        log.debug("REST request to delete Url : {}", id);
        urlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
