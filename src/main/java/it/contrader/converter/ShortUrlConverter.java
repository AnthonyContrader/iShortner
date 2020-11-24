package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.UrlDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.ShortUrl;
import it.contrader.model.User;

@Component
public class ShortUrlConverter extends AbstractConverter<ShortUrl, UrlDTO>{

	@Override
	public ShortUrl toEntity(UrlDTO dto) {
		ShortUrl url = null;
		if (dto != null) {
			url = new ShortUrl(dto.getId(), dto.getLongurl(), dto.getShorturl(), dto.getFk_url());
		}
		return url;
	}

	@Override
	public UrlDTO toDTO(ShortUrl entity) {
		UrlDTO urlDTO = null;
		if (entity != null) {
			urlDTO = new UrlDTO(entity.getId(), entity.getLongurl(), entity.getShorturl(), entity.getFkurl());
		}
		return urlDTO;
	}

}
