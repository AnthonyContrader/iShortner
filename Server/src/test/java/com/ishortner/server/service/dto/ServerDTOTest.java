package com.ishortner.server.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ishortner.server.web.rest.TestUtil;

public class ServerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServerDTO.class);
        ServerDTO serverDTO1 = new ServerDTO();
        serverDTO1.setId(1L);
        ServerDTO serverDTO2 = new ServerDTO();
        assertThat(serverDTO1).isNotEqualTo(serverDTO2);
        serverDTO2.setId(serverDTO1.getId());
        assertThat(serverDTO1).isEqualTo(serverDTO2);
        serverDTO2.setId(2L);
        assertThat(serverDTO1).isNotEqualTo(serverDTO2);
        serverDTO1.setId(null);
        assertThat(serverDTO1).isNotEqualTo(serverDTO2);
    }
}
