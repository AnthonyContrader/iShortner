package com.ishortner.url.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UrlMapperTest {

    private UrlMapper urlMapper;

    @BeforeEach
    public void setUp() {
        urlMapper = new UrlMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(urlMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(urlMapper.fromId(null)).isNull();
    }
}
