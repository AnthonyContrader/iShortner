package com.ishortner.server.web.rest;

import com.ishortner.server.ServerApp;
import com.ishortner.server.domain.Server;
import com.ishortner.server.repository.ServerRepository;
import com.ishortner.server.service.ServerService;
import com.ishortner.server.service.dto.ServerDTO;
import com.ishortner.server.service.mapper.ServerMapper;

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
 * Integration tests for the {@link ServerResource} REST controller.
 */
@SpringBootTest(classes = ServerApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ServerResourceIT {

    private static final String DEFAULT_TIPOLOGIA = "AAAAAAAAAA";
    private static final String UPDATED_TIPOLOGIA = "BBBBBBBBBB";

    private static final String DEFAULT_POSIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_POSIZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_DATA = "AAAAAAAAAA";
    private static final String UPDATED_DATA = "BBBBBBBBBB";

    private static final Long DEFAULT_FKURL = 1L;
    private static final Long UPDATED_FKURL = 2L;

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private ServerService serverService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServerMockMvc;

    private Server server;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Server createEntity(EntityManager em) {
        Server server = new Server()
            .tipologia(DEFAULT_TIPOLOGIA)
            .posizione(DEFAULT_POSIZIONE)
            .data(DEFAULT_DATA)
            .fkurl(DEFAULT_FKURL);
        return server;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Server createUpdatedEntity(EntityManager em) {
        Server server = new Server()
            .tipologia(UPDATED_TIPOLOGIA)
            .posizione(UPDATED_POSIZIONE)
            .data(UPDATED_DATA)
            .fkurl(UPDATED_FKURL);
        return server;
    }

    @BeforeEach
    public void initTest() {
        server = createEntity(em);
    }

    @Test
    @Transactional
    public void createServer() throws Exception {
        int databaseSizeBeforeCreate = serverRepository.findAll().size();
        // Create the Server
        ServerDTO serverDTO = serverMapper.toDto(server);
        restServerMockMvc.perform(post("/api/servers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serverDTO)))
            .andExpect(status().isCreated());

        // Validate the Server in the database
        List<Server> serverList = serverRepository.findAll();
        assertThat(serverList).hasSize(databaseSizeBeforeCreate + 1);
        Server testServer = serverList.get(serverList.size() - 1);
        assertThat(testServer.getTipologia()).isEqualTo(DEFAULT_TIPOLOGIA);
        assertThat(testServer.getPosizione()).isEqualTo(DEFAULT_POSIZIONE);
        assertThat(testServer.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testServer.getFkurl()).isEqualTo(DEFAULT_FKURL);
    }

    @Test
    @Transactional
    public void createServerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serverRepository.findAll().size();

        // Create the Server with an existing ID
        server.setId(1L);
        ServerDTO serverDTO = serverMapper.toDto(server);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServerMockMvc.perform(post("/api/servers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serverDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Server in the database
        List<Server> serverList = serverRepository.findAll();
        assertThat(serverList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllServers() throws Exception {
        // Initialize the database
        serverRepository.saveAndFlush(server);

        // Get all the serverList
        restServerMockMvc.perform(get("/api/servers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(server.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipologia").value(hasItem(DEFAULT_TIPOLOGIA)))
            .andExpect(jsonPath("$.[*].posizione").value(hasItem(DEFAULT_POSIZIONE)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA)))
            .andExpect(jsonPath("$.[*].fkurl").value(hasItem(DEFAULT_FKURL.intValue())));
    }
    
    @Test
    @Transactional
    public void getServer() throws Exception {
        // Initialize the database
        serverRepository.saveAndFlush(server);

        // Get the server
        restServerMockMvc.perform(get("/api/servers/{id}", server.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(server.getId().intValue()))
            .andExpect(jsonPath("$.tipologia").value(DEFAULT_TIPOLOGIA))
            .andExpect(jsonPath("$.posizione").value(DEFAULT_POSIZIONE))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA))
            .andExpect(jsonPath("$.fkurl").value(DEFAULT_FKURL.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingServer() throws Exception {
        // Get the server
        restServerMockMvc.perform(get("/api/servers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServer() throws Exception {
        // Initialize the database
        serverRepository.saveAndFlush(server);

        int databaseSizeBeforeUpdate = serverRepository.findAll().size();

        // Update the server
        Server updatedServer = serverRepository.findById(server.getId()).get();
        // Disconnect from session so that the updates on updatedServer are not directly saved in db
        em.detach(updatedServer);
        updatedServer
            .tipologia(UPDATED_TIPOLOGIA)
            .posizione(UPDATED_POSIZIONE)
            .data(UPDATED_DATA)
            .fkurl(UPDATED_FKURL);
        ServerDTO serverDTO = serverMapper.toDto(updatedServer);

        restServerMockMvc.perform(put("/api/servers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serverDTO)))
            .andExpect(status().isOk());

        // Validate the Server in the database
        List<Server> serverList = serverRepository.findAll();
        assertThat(serverList).hasSize(databaseSizeBeforeUpdate);
        Server testServer = serverList.get(serverList.size() - 1);
        assertThat(testServer.getTipologia()).isEqualTo(UPDATED_TIPOLOGIA);
        assertThat(testServer.getPosizione()).isEqualTo(UPDATED_POSIZIONE);
        assertThat(testServer.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testServer.getFkurl()).isEqualTo(UPDATED_FKURL);
    }

    @Test
    @Transactional
    public void updateNonExistingServer() throws Exception {
        int databaseSizeBeforeUpdate = serverRepository.findAll().size();

        // Create the Server
        ServerDTO serverDTO = serverMapper.toDto(server);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServerMockMvc.perform(put("/api/servers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serverDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Server in the database
        List<Server> serverList = serverRepository.findAll();
        assertThat(serverList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServer() throws Exception {
        // Initialize the database
        serverRepository.saveAndFlush(server);

        int databaseSizeBeforeDelete = serverRepository.findAll().size();

        // Delete the server
        restServerMockMvc.perform(delete("/api/servers/{id}", server.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Server> serverList = serverRepository.findAll();
        assertThat(serverList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
