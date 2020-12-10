package com.ishortner.url.service.impl;

import com.ishortner.url.service.UrlService;
import com.ishortner.url.domain.Url;
import com.ishortner.url.repository.UrlRepository;
import com.ishortner.url.service.dto.UrlDTO;
import com.ishortner.url.service.mapper.UrlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Url}.
 */
@Service
@Transactional
public class UrlServiceImpl implements UrlService {

    private final Logger log = LoggerFactory.getLogger(UrlServiceImpl.class);

    private final UrlRepository urlRepository;

    private final UrlMapper urlMapper;

    public UrlServiceImpl(UrlRepository urlRepository, UrlMapper urlMapper) {
        this.urlRepository = urlRepository;
        this.urlMapper = urlMapper;
    }

    @Override
    public UrlDTO save(UrlDTO urlDTO) {
        log.debug("Request to save Url : {}", urlDTO);
        Url url = urlMapper.toEntity(urlDTO);
        url = urlRepository.save(url);
        return urlMapper.toDto(url);
    }

   @Override
    @Transactional(readOnly = true)
    public List<UrlDTO> findAll() {
       log.debug("Request to get all Urls");
        return urlMapper.toDto(urlRepository.findAll());
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UrlDTO> findOne(Long id) {
        log.debug("Request to get Url : {}", id);
        return urlRepository.findById(id)
            .map(urlMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Url : {}", id);
        urlRepository.deleteById(id);
    }
    
}
