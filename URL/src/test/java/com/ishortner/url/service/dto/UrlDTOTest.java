package com.ishortner.url.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ishortner.url.web.rest.TestUtil;

public class UrlDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UrlDTO.class);
        UrlDTO urlDTO1 = new UrlDTO();
        urlDTO1.setId(1L);
        UrlDTO urlDTO2 = new UrlDTO();
        assertThat(urlDTO1).isNotEqualTo(urlDTO2);
        urlDTO2.setId(urlDTO1.getId());
        assertThat(urlDTO1).isEqualTo(urlDTO2);
        urlDTO2.setId(2L);
        assertThat(urlDTO1).isNotEqualTo(urlDTO2);
        urlDTO1.setId(null);
        assertThat(urlDTO1).isNotEqualTo(urlDTO2);
    }
}
