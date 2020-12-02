package com.ishortner.url.service.mapper;


import com.ishortner.url.domain.*;
import com.ishortner.url.service.dto.UrlDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Url} and its DTO {@link UrlDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UrlMapper extends EntityMapper<UrlDTO, Url> {



    default Url fromId(Long id) {
        if (id == null) {
            return null;
        }
        Url url = new Url();
        url.setId(id);
        return url;
    }
}
