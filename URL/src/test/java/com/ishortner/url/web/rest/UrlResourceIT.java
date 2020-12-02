package com.ishortner.url.web.rest;

import com.ishortner.url.UrlApp;
import com.ishortner.url.domain.Url;
import com.ishortner.url.repository.UrlRepository;
import com.ishortner.url.service.UrlService;
import com.ishortner.url.service.dto.UrlDTO;
import com.ishortner.url.service.mapper.UrlMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UrlResource} REST controller.
 */
@SpringBootTest(classes = UrlApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UrlResourceIT {

    private static final String DEFAULT_LONGURL = "AAAAAAAAAA";
    private static final String UPDATED_LONGURL = "BBBBBBBBBB";

    private static final String DEFAULT_SHORTURL = "AAAAAAAAAA";
    private static final String UPDATED_SHORTURL = "BBBBBBBBBB";

    private static final Long DEFAULT_FKUSER = 1L;
    private static final Long UPDATED_FKUSER = 2L;

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UrlMapper urlMapper;

    @Autowired
    private UrlService urlService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUrlMockMvc;

    private Url url;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Url createEntity(EntityManager em) {
        Url url = new Url()
            .longurl(DEFAULT_LONGURL)
            .shorturl(DEFAULT_SHORTURL)
            .fkuser(DEFAULT_FKUSER);
        return url;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Url createUpdatedEntity(EntityManager em) {
        Url url = new Url()
            .longurl(UPDATED_LONGURL)
            .shorturl(UPDATED_SHORTURL)
            .fkuser(UPDATED_FKUSER);
        return url;
    }

    @BeforeEach
    public void initTest() {
        url = createEntity(em);
    }

    @Test
    @Transactional
    public void createUrl() throws Exception {
        int databaseSizeBeforeCreate = urlRepository.findAll().size();
        // Create the Url
        UrlDTO urlDTO = urlMapper.toDto(url);
        restUrlMockMvc.perform(post("/api/urls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(urlDTO)))
            .andExpect(status().isCreated());

        // Validate the Url in the database
        List<Url> urlList = urlRepository.findAll();
        assertThat(urlList).hasSize(databaseSizeBeforeCreate + 1);
        Url testUrl = urlList.get(urlList.size() - 1);
        assertThat(testUrl.getLongurl()).isEqualTo(DEFAULT_LONGURL);
        assertThat(testUrl.getShorturl()).isEqualTo(DEFAULT_SHORTURL);
        assertThat(testUrl.getFkuser()).isEqualTo(DEFAULT_FKUSER);
    }

    @Test
    @Transactional
    public void createUrlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = urlRepository.findAll().size();

        // Create the Url with an existing ID
        url.setId(1L);
        UrlDTO urlDTO = urlMapper.toDto(url);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUrlMockMvc.perform(post("/api/urls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(urlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Url in the database
        List<Url> urlList = urlRepository.findAll();
        assertThat(urlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUrls() throws Exception {
        // Initialize the database
        urlRepository.saveAndFlush(url);

        // Get all the urlList
        restUrlMockMvc.perform(get("/api/urls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(url.getId().intValue())))
            .andExpect(jsonPath("$.[*].longurl").value(hasItem(DEFAULT_LONGURL)))
            .andExpect(jsonPath("$.[*].shorturl").value(hasItem(DEFAULT_SHORTURL)))
            .andExpect(jsonPath("$.[*].fkuser").value(hasItem(DEFAULT_FKUSER.intValue())));
    }
    
    @Test
    @Transactional
    public void getUrl() throws Exception {
        // Initialize the database
        urlRepository.saveAndFlush(url);

        // Get the url
        restUrlMockMvc.perform(get("/api/urls/{id}", url.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(url.getId().intValue()))
            .andExpect(jsonPath("$.longurl").value(DEFAULT_LONGURL))
            .andExpect(jsonPath("$.shorturl").value(DEFAULT_SHORTURL))
            .andExpect(jsonPath("$.fkuser").value(DEFAULT_FKUSER.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingUrl() throws Exception {
        // Get the url
        restUrlMockMvc.perform(get("/api/urls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUrl() throws Exception {
        // Initialize the database
        urlRepository.saveAndFlush(url);

        int databaseSizeBeforeUpdate = urlRepository.findAll().size();

        // Update the url
        Url updatedUrl = urlRepository.findById(url.getId()).get();
        // Disconnect from session so that the updates on updatedUrl are not directly saved in db
        em.detach(updatedUrl);
        updatedUrl
            .longurl(UPDATED_LONGURL)
            .shorturl(UPDATED_SHORTURL)
            .fkuser(UPDATED_FKUSER);
        UrlDTO urlDTO = urlMapper.toDto(updatedUrl);

        restUrlMockMvc.perform(put("/api/urls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(urlDTO)))
            .andExpect(status().isOk());

        // Validate the Url in the database
        List<Url> urlList = urlRepository.findAll();
        assertThat(urlList).hasSize(databaseSizeBeforeUpdate);
        Url testUrl = urlList.get(urlList.size() - 1);
        assertThat(testUrl.getLongurl()).isEqualTo(UPDATED_LONGURL);
        assertThat(testUrl.getShorturl()).isEqualTo(UPDATED_SHORTURL);
        assertThat(testUrl.getFkuser()).isEqualTo(UPDATED_FKUSER);
    }

    @Test
    @Transactional
    public void updateNonExistingUrl() throws Exception {
        int databaseSizeBeforeUpdate = urlRepository.findAll().size();

        // Create the Url
        UrlDTO urlDTO = urlMapper.toDto(url);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUrlMockMvc.perform(put("/api/urls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(urlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Url in the database
        List<Url> urlList = urlRepository.findAll();
        assertThat(urlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUrl() throws Exception {
        // Initialize the database
        urlRepository.saveAndFlush(url);

        int databaseSizeBeforeDelete = urlRepository.findAll().size();

        // Delete the url
        restUrlMockMvc.perform(delete("/api/urls/{id}", url.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Url> urlList = urlRepository.findAll();
        assertThat(urlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
