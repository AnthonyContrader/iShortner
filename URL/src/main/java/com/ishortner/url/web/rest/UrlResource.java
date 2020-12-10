package com.ishortner.url.web.rest;

import com.ishortner.url.service.UrlService;
import com.ishortner.url.web.rest.errors.BadRequestAlertException;
import com.ishortner.url.service.dto.UrlDTO;
import com.ishortner.url.service.impl.ShortServiceImpl;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ishortner.url.domain.Url}.
 */
@RestController
@RequestMapping("/api")
public class UrlResource {

    private final Logger log = LoggerFactory.getLogger(UrlResource.class);

    private static final String ENTITY_NAME = "urlUrl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UrlService urlService;
    
    @Autowired
    private ShortServiceImpl shortServ;

    public UrlResource(UrlService urlService) {
        this.urlService = urlService;
    }

    /**
     * {@code POST  /urls} : Create a new url.
     *
     * @param urlDTO the urlDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new urlDTO, or with status {@code 400 (Bad Request)} if the url has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws MalformedURLException 
     */
    @PostMapping("/urls")
    public UrlDTO createUrl(@RequestBody UrlDTO urlDto, @RequestHeader("Authorization") String jwt) throws MalformedURLException, URISyntaxException {	
    	urlDto = shortServ.createShortUrl(urlDto);
    	
    	if(urlDto != null) {
    		
    		RestTemplate restTemplate = new RestTemplate();
            
    		String baseUrl = "http://localhost:8080/services/server/api/servers";
    		URI uri = new URI(baseUrl);
    		
    		MultiValueMap<String, Long> map= new LinkedMultiValueMap<String, Long>();
    		map.add("id", urlDto.getId());
    		
    		HttpHeaders headers = new HttpHeaders();
    		headers.set("Authorization", jwt);  
    		
    		HttpEntity<MultiValueMap<String, Long>> request = new HttpEntity<>(map, headers);
            
            try {
                restTemplate.postForEntity(uri, request, String.class);
            }catch(HttpClientErrorException e) {
            	e.printStackTrace();
            }catch(HttpServerErrorException e) {
            	e.printStackTrace();
            }catch(UnknownHttpStatusCodeException e) {
            	e.printStackTrace();
            }
            
            baseUrl = "http://localhost:8080/services/stats/api/stats/insert";
            uri = new URI(baseUrl);
            
            MultiValueMap<String, String> mapStats= new LinkedMultiValueMap<String, String>();
            mapStats.add("url", urlDto.getLongurl());
            
            HttpEntity<MultiValueMap<String, String>> requestStats = new HttpEntity<>(mapStats, headers);
 
            try {
            	restTemplate.postForEntity(uri, requestStats, String.class);
            }catch(HttpClientErrorException e) {
            	e.printStackTrace();
            }catch(HttpServerErrorException e) {
            	e.printStackTrace();
            }catch(UnknownHttpStatusCodeException e) {
            	e.printStackTrace();
            }
    	}
    	
        return urlDto;
    }

    @PostMapping("/redirect")
    public UrlDTO redirectUrl(@RequestBody UrlDTO urlDto) {
    	return shortServ.findLongUrl(urlDto);
    } 
    
    @GetMapping("urls/user/{id}")
    public List<UrlDTO> getUserUrl(@PathVariable Long id){
    	return shortServ.readList(id);
    }
    
    
    
    /**
     * {@code PUT  /urls} : Updates an existing url.
     *
     * @param urlDTO the urlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated urlDTO,
     * or with status {@code 400 (Bad Request)} if the urlDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the urlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/urls")
    public ResponseEntity<UrlDTO> updateUrl(@RequestBody UrlDTO urlDTO) throws URISyntaxException {
        log.debug("REST request to update Url : {}", urlDTO);
        if (urlDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UrlDTO result = urlService.save(urlDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, urlDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /urls} : get all the urls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of urls in body.
     */
    @GetMapping("/urls")
    public List<UrlDTO> getAllUrls() {
        log.debug("REST request to get all Urls");
        return urlService.findAll();
    }

    /**
     * {@code GET  /urls/:id} : get the "id" url.
     *
     * @param id the id of the urlDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the urlDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/urls/{id}")
    public ResponseEntity<UrlDTO> getUrl(@PathVariable Long id) {
        log.debug("REST request to get Url : {}", id);
        Optional<UrlDTO> urlDTO = urlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(urlDTO);
    }

    /**
     * {@code DELETE  /urls/:id} : delete the "id" url.
     *
     * @param id the id of the urlDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/urls/{id}")
    public ResponseEntity<Void> deleteUrl(@PathVariable Long id) {
        log.debug("REST request to delete Url : {}", id);
        urlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * 
     * @param urlDto
     * @return il longUrl, ovvero l'Url inserito in origine dall'utente
     */
 
}
