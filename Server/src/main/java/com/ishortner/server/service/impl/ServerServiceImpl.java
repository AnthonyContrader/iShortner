package com.ishortner.server.service.impl;

import com.ishortner.server.service.ServerService;
import com.ishortner.server.domain.Server;
import com.ishortner.server.repository.ServerRepository;
import com.ishortner.server.service.dto.ServerDTO;
import com.ishortner.server.service.mapper.ServerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Server}.
 */
@Service
@Transactional
public class ServerServiceImpl implements ServerService {

    private final Logger log = LoggerFactory.getLogger(ServerServiceImpl.class);

    private final ServerRepository serverRepository;

    private final ServerMapper serverMapper;

    public ServerServiceImpl(ServerRepository serverRepository, ServerMapper serverMapper) {
        this.serverRepository = serverRepository;
        this.serverMapper = serverMapper;
    }

    @Override
    public ServerDTO save(ServerDTO serverDTO) {
        log.debug("Request to save Server : {}", serverDTO);
        Server server = serverMapper.toEntity(serverDTO);
        server = serverRepository.save(server);
        return serverMapper.toDto(server);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServerDTO> findAll() {
        log.debug("Request to get all Servers");
        return serverRepository.findAll().stream()
            .map(serverMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ServerDTO> findOne(Long id) {
        log.debug("Request to get Server : {}", id);
        return serverRepository.findById(id)
            .map(serverMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Server : {}", id);
        serverRepository.deleteById(id);
    }
}
