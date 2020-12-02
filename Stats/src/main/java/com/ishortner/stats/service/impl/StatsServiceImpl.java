package com.ishortner.stats.service.impl;

import com.ishortner.stats.service.StatsService;
import com.ishortner.stats.domain.Stats;
import com.ishortner.stats.repository.StatsRepository;
import com.ishortner.stats.service.dto.StatsDTO;
import com.ishortner.stats.service.mapper.StatsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Stats}.
 */
@Service
@Transactional
public class StatsServiceImpl implements StatsService {

    private final Logger log = LoggerFactory.getLogger(StatsServiceImpl.class);

    private final StatsRepository statsRepository;

    private final StatsMapper statsMapper;

    public StatsServiceImpl(StatsRepository statsRepository, StatsMapper statsMapper) {
        this.statsRepository = statsRepository;
        this.statsMapper = statsMapper;
    }

    @Override
    public StatsDTO save(StatsDTO statsDTO) {
        log.debug("Request to save Stats : {}", statsDTO);
        Stats stats = statsMapper.toEntity(statsDTO);
        stats = statsRepository.save(stats);
        return statsMapper.toDto(stats);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatsDTO> findAll() {
        log.debug("Request to get all Stats");
        return statsRepository.findAll().stream()
            .map(statsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<StatsDTO> findOne(Long id) {
        log.debug("Request to get Stats : {}", id);
        return statsRepository.findById(id)
            .map(statsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Stats : {}", id);
        statsRepository.deleteById(id);
    }
}
