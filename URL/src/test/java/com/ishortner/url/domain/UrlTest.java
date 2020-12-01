package com.ishortner.url.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ishortner.url.web.rest.TestUtil;

public class UrlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Url.class);
        Url url1 = new Url();
        url1.setId(1L);
        Url url2 = new Url();
        url2.setId(url1.getId());
        assertThat(url1).isEqualTo(url2);
        url2.setId(2L);
        assertThat(url1).isNotEqualTo(url2);
        url1.setId(null);
        assertThat(url1).isNotEqualTo(url2);
    }
}
