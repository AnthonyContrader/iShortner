package com.ishortner.server.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ishortner.server.web.rest.TestUtil;

public class ServerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Server.class);
        Server server1 = new Server();
        server1.setId(1L);
        Server server2 = new Server();
        server2.setId(server1.getId());
        assertThat(server1).isEqualTo(server2);
        server2.setId(2L);
        assertThat(server1).isNotEqualTo(server2);
        server1.setId(null);
        assertThat(server1).isNotEqualTo(server2);
    }
}
