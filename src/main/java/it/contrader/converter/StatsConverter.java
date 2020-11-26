package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.StatsUrlDTO;
import it.contrader.model.StatsUrl;

@Component
public class StatsConverter extends AbstractConverter<StatsUrl, StatsUrlDTO> {

	@Override
	public StatsUrl toEntity(StatsUrlDTO dto) {
		StatsUrl stats = null;
		if(dto != null) {
			stats = new StatsUrl(dto.getId(), dto.getUrl(), dto.getCount());
		}
		return stats;
	}

	@Override
	public StatsUrlDTO toDTO(StatsUrl entity) {
		StatsUrlDTO stats = null;
		if(entity != null) {
			stats = new StatsUrlDTO(entity.getId(), entity.getUrl(), entity.getCount());
		}
		return stats;
	}

}
