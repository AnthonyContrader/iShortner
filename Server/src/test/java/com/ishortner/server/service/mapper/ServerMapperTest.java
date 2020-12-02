package com.ishortner.server.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ServerMapperTest {

    private ServerMapper serverMapper;

    @BeforeEach
    public void setUp() {
        serverMapper = new ServerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(serverMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(serverMapper.fromId(null)).isNull();
    }
}
