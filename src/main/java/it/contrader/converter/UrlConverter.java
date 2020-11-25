package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.UrlDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Url;
import it.contrader.model.User;

@Component
public class UrlConverter extends AbstractConverter<Url, UrlDTO>{

	@Override
	public Url toEntity(UrlDTO dto) {
		Url url = null;
		if (dto != null) {
			url = new Url(dto.getId(), dto.getLongurl(), dto.getShorturl(), dto.getFkurl());
		}
		return url;
	}

	@Override
	public UrlDTO toDTO(Url entity) {
		UrlDTO urlDTO = null;
		if (entity != null) {
			urlDTO = new UrlDTO(entity.getId(), entity.getLongurl(), entity.getShorturl(), entity.getFkurl());
		}
		return urlDTO;
	}

}
