package com.ishortner.server.service.mapper;


import com.ishortner.server.domain.*;
import com.ishortner.server.service.dto.ServerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Server} and its DTO {@link ServerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ServerMapper extends EntityMapper<ServerDTO, Server> {



    default Server fromId(Long id) {
        if (id == null) {
            return null;
        }
        Server server = new Server();
        server.setId(id);
        return server;
    }
}
