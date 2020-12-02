package com.ishortner.stats.web.rest;

import com.ishortner.stats.StatsApp;
import com.ishortner.stats.domain.Stats;
import com.ishortner.stats.repository.StatsRepository;
import com.ishortner.stats.service.StatsService;
import com.ishortner.stats.service.dto.StatsDTO;
import com.ishortner.stats.service.mapper.StatsMapper;

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
 * Integration tests for the {@link StatsResource} REST controller.
 */
@SpringBootTest(classes = StatsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StatsResourceIT {

    private static final String DEFAULT_DOMAIN = "AAAAAAAAAA";
    private static final String UPDATED_DOMAIN = "BBBBBBBBBB";

    private static final Integer DEFAULT_COUNT = 1;
    private static final Integer UPDATED_COUNT = 2;

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private StatsMapper statsMapper;

    @Autowired
    private StatsService statsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStatsMockMvc;

    private Stats stats;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stats createEntity(EntityManager em) {
        Stats stats = new Stats()
            .domain(DEFAULT_DOMAIN)
            .count(DEFAULT_COUNT);
        return stats;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stats createUpdatedEntity(EntityManager em) {
        Stats stats = new Stats()
            .domain(UPDATED_DOMAIN)
            .count(UPDATED_COUNT);
        return stats;
    }

    @BeforeEach
    public void initTest() {
        stats = createEntity(em);
    }

    @Test
    @Transactional
    public void createStats() throws Exception {
        int databaseSizeBeforeCreate = statsRepository.findAll().size();
        // Create the Stats
        StatsDTO statsDTO = statsMapper.toDto(stats);
        restStatsMockMvc.perform(post("/api/stats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statsDTO)))
            .andExpect(status().isCreated());

        // Validate the Stats in the database
        List<Stats> statsList = statsRepository.findAll();
        assertThat(statsList).hasSize(databaseSizeBeforeCreate + 1);
        Stats testStats = statsList.get(statsList.size() - 1);
        assertThat(testStats.getDomain()).isEqualTo(DEFAULT_DOMAIN);
        assertThat(testStats.getCount()).isEqualTo(DEFAULT_COUNT);
    }

    @Test
    @Transactional
    public void createStatsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statsRepository.findAll().size();

        // Create the Stats with an existing ID
        stats.setId(1L);
        StatsDTO statsDTO = statsMapper.toDto(stats);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatsMockMvc.perform(post("/api/stats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Stats in the database
        List<Stats> statsList = statsRepository.findAll();
        assertThat(statsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStats() throws Exception {
        // Initialize the database
        statsRepository.saveAndFlush(stats);

        // Get all the statsList
        restStatsMockMvc.perform(get("/api/stats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stats.getId().intValue())))
            .andExpect(jsonPath("$.[*].domain").value(hasItem(DEFAULT_DOMAIN)))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT)));
    }
    
    @Test
    @Transactional
    public void getStats() throws Exception {
        // Initialize the database
        statsRepository.saveAndFlush(stats);

        // Get the stats
        restStatsMockMvc.perform(get("/api/stats/{id}", stats.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stats.getId().intValue()))
            .andExpect(jsonPath("$.domain").value(DEFAULT_DOMAIN))
            .andExpect(jsonPath("$.count").value(DEFAULT_COUNT));
    }
    @Test
    @Transactional
    public void getNonExistingStats() throws Exception {
        // Get the stats
        restStatsMockMvc.perform(get("/api/stats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStats() throws Exception {
        // Initialize the database
        statsRepository.saveAndFlush(stats);

        int databaseSizeBeforeUpdate = statsRepository.findAll().size();

        // Update the stats
        Stats updatedStats = statsRepository.findById(stats.getId()).get();
        // Disconnect from session so that the updates on updatedStats are not directly saved in db
        em.detach(updatedStats);
        updatedStats
            .domain(UPDATED_DOMAIN)
            .count(UPDATED_COUNT);
        StatsDTO statsDTO = statsMapper.toDto(updatedStats);

        restStatsMockMvc.perform(put("/api/stats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statsDTO)))
            .andExpect(status().isOk());

        // Validate the Stats in the database
        List<Stats> statsList = statsRepository.findAll();
        assertThat(statsList).hasSize(databaseSizeBeforeUpdate);
        Stats testStats = statsList.get(statsList.size() - 1);
        assertThat(testStats.getDomain()).isEqualTo(UPDATED_DOMAIN);
        assertThat(testStats.getCount()).isEqualTo(UPDATED_COUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingStats() throws Exception {
        int databaseSizeBeforeUpdate = statsRepository.findAll().size();

        // Create the Stats
        StatsDTO statsDTO = statsMapper.toDto(stats);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatsMockMvc.perform(put("/api/stats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Stats in the database
        List<Stats> statsList = statsRepository.findAll();
        assertThat(statsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStats() throws Exception {
        // Initialize the database
        statsRepository.saveAndFlush(stats);

        int databaseSizeBeforeDelete = statsRepository.findAll().size();

        // Delete the stats
        restStatsMockMvc.perform(delete("/api/stats/{id}", stats.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Stats> statsList = statsRepository.findAll();
        assertThat(statsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
