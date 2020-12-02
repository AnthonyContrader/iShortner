package com.ishortner.stats.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ishortner.stats.web.rest.TestUtil;

public class StatsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatsDTO.class);
        StatsDTO statsDTO1 = new StatsDTO();
        statsDTO1.setId(1L);
        StatsDTO statsDTO2 = new StatsDTO();
        assertThat(statsDTO1).isNotEqualTo(statsDTO2);
        statsDTO2.setId(statsDTO1.getId());
        assertThat(statsDTO1).isEqualTo(statsDTO2);
        statsDTO2.setId(2L);
        assertThat(statsDTO1).isNotEqualTo(statsDTO2);
        statsDTO1.setId(null);
        assertThat(statsDTO1).isNotEqualTo(statsDTO2);
    }
}
