package com.ishortner.stats.service.mapper;


import com.ishortner.stats.domain.*;
import com.ishortner.stats.service.dto.StatsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Stats} and its DTO {@link StatsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StatsMapper extends EntityMapper<StatsDTO, Stats> {



    default Stats fromId(Long id) {
        if (id == null) {
            return null;
        }
        Stats stats = new Stats();
        stats.setId(id);
        return stats;
    }
}
