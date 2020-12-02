package com.ishortner.stats.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsMapperTest {

    private StatsMapper statsMapper;

    @BeforeEach
    public void setUp() {
        statsMapper = new StatsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(statsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(statsMapper.fromId(null)).isNull();
    }
}
